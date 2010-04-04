package com.ft.web.sm.priv.op;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgsTreeBuilder;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.commons.tree.TreeNode;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.ActionDefinition;

/**
 * 操作员维护页面控制类
 * 
 * @spring.bean id="operatorAction"
 * 
 * @struts.action path="/op" name="operatorForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.list"
 * 
 * @struts.action-forward name="view" path="sm.op.view"
 * @struts.action-forward name="create" path="sm.op.create"
 * @struts.action-forward name="edit" path="sm.op.edit"
 * @struts.action-forward name="password" path="sm.op.password"
 * @struts.action-forward name="copy" path="sm.op.copy"
 * 
 * @struts.tiles name="sm.op.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/listOp.jsp"
 * 
 * @version 1.0
 */
public class OperatorAction extends OperatorBaseAction {
    private static Log logger = LogFactory.getLog(OperatorAction.class);

    private OrgsTreeBuilder orgsTreeBuilder;
    
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        Long orgId = opForm.getOrgId_s();
        String loginName = opForm.getLoginName();
        String name = opForm.getName();

        boolean includeAll = false;
        if (orgId == null || orgId.longValue() == 0) {
            orgId = OperatorSessionHelper.getLoginOrg(request).getOrgId();
            includeAll = true;
        }
        
        if(OperatorSessionHelper.getLoginOrg(request).isRoot()){
            request.setAttribute("rootOrg", new Boolean(true));
        }

        List ops = this.opMgmt.searchOperator(orgId, loginName, name,
                includeAll);
        request.setAttribute("ops", ops);
        request.setAttribute("flag", "searched");
        return mapping.getInputForward();
    }

    /**
     * 跳转到创建操作员页面
     * 
     * @struts.tiles name="sm.op.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/createOp.jsp"
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Long loginOrgId = OperatorSessionHelper.getLoginOrg(request).getOrgId();
        request.setAttribute("loginOrgId", loginOrgId);
        
        return mapping.findForward("create");
    }

    /**
     * 跳转到编辑操作员页面
     * 
     * @struts.tiles name="sm.op.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/editOp.jsp"
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 预览操作员
     * 
     * @struts.tiles name="sm.op.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/viewOp.jsp"
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        List accessOrgs = this.orgManager
                .findAccessOrgsForOperatorWithChild(opForm.getOp()
                        .getOperatorId());
        List orgsInGroup = this.orgManager
                .findAccessOrgForOperatorInGroupsWithChild(opForm.getOp()
                        .getOperatorId());

        TreeNode orgTree = this.orgsTreeBuilder.buildOrgNode(accessOrgs);
        TreeNode orgTreeInGroup = this.orgsTreeBuilder
                .buildOrgNode(orgsInGroup);

        request.setAttribute("orgTree", orgTree);
        request.setAttribute("orgTreeInGroup", orgTreeInGroup);

        return mapping.findForward("view");
    }

    /**
     * @struts.tiles name="sm.op.copy" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/copyOp.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copy(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        Long opId = opForm.getOp().getOperatorId();
        String targetLoginName = opForm.getTargetLoginName();
        String targetName = opForm.getTargetName();
        List ops = this.opMgmt.findOperatorsInAccessOrgs(targetLoginName,targetName,opId,
                OperatorSessionHelper.getLoginOrg(request).getOrgId());
        request.setAttribute("ops", ops);
        return mapping.findForward("copy");
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveCopy(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        long[] types = opForm.getCopyType();
        Long sourceOpId = opForm.getOp().getOperatorId();
        try {
            this.opMgmt.copyOperator(opForm.getCurrentUser(), sourceOpId,
                    opForm.getOps(), types);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_COPY_OPERATOR, sourceOpId
                    .toString(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_COPY_OPERATOR
                    + " failed,action sequence = ", ex);
            throw ex;
        }

        logger.log(request, ActionDefinition.SYS_COPY_OPERATOR, sourceOpId
                .toString(), ActionDefinition.ACTION_SUCCESS);

        return this.getRedirectForwardAction("op.do" + "?" + plusParams(opForm));
    }

    /**
     * 保存操作员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        OperatorDTO op = opForm.getOp();
        OrganizationDTO org = opForm.getOrganization();
        try {
            // 和SSO同步
            //this.syncProxy.addOperator(op, org);

            Long opId = this.opMgmt.saveOrUpdateOperator(op, org);
            op.setOperatorId(opId);
            request.setAttribute("op", op);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_OP, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_ADD_OP
                    + " failed,action sequence = ", ex);
            throw ex;
        }

        logger.log(request, ActionDefinition.SYS_ADD_OP, op.getOperatorId()
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?act=view&opId="
                + op.getOperatorId().longValue() + "&" + plusParams(opForm));
    }

    /**
     * 更新操作员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        OperatorDTO op = opForm.getOp();
        OrganizationDTO org = opForm.getOrganization();
        try {
            // 和SSO同步
            //this.syncProxy.updateOperator(op, org);

            this.opMgmt.saveOrUpdateOperator(op, org);
            request.setAttribute("op", opForm.getOp());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_OP, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_UPDATE_OP
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_UPDATE_OP, op.getOperatorId()
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?act=view&opId="
                + op.getOperatorId().longValue() + "&" + plusParams(opForm));
    }

    /**
     * 禁止操作员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm operatorForm = (OperatorForm) form;
        String[] ids = request.getParameterValues("opIds");
        String allId = "";
        for (int i = 0; i < ids.length; i++) {
            allId = ids[i] + ",";
        }

        //OperatorDTO[] ops = operatorForm.getOps();
        try {
            // 和SSO同步
           // for (int i = 0; i < ops.length; i++) {
                //this.syncProxy.disableOperator(ops[i]);
            //}

            this.opMgmt.disableOperator(operatorForm.getOps());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DISABLE_OP, allId,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DISABLE_OP
                    + " failed,action sequence =" + allId, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DISABLE_OP, allId,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do" + "?"
                + plusParams(operatorForm));
    }

    /**
     * 禁止单个用户
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disableSingle(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        OperatorDTO op = opForm.getOp();
        try {
            // 和SSO同步
            //this.syncProxy.disableOperator(op);

            this.opMgmt.disableOperator(new OperatorDTO[] { op });
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DISABLE_OP, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DISABLE_OP
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DISABLE_OP, op.getOperatorId()
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?act=view&opId="
                + op.getOperatorId().longValue() + "&" + plusParams(opForm));
    }

    /**
     * 解禁操作员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recover(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm opForm = (OperatorForm) form;
        OperatorDTO op = opForm.getOp();
        try {
            // 和SSO同步
            //this.syncProxy.enableOperator(op);
            this.opMgmt.enableOperator(op);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_RECOVER_OP, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_RECOVER_OP
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_RECOVER_OP, op.getOperatorId()
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?act=view&opId="
                + op.getOperatorId().longValue() + "&" + plusParams(opForm));
    }

    /**
     * @struts.tiles name="sm.op.password" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opPassword.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward changePassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorForm operatorForm = (OperatorForm) form;
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return mapping.findForward("password");
        }
        OperatorDTO op = operatorForm.getOp();
        try {
            // 和SSO同步
            //this.syncProxy.updatePassword(op, operatorForm.getPassword());
            this.opMgmt.changePassword(op, operatorForm.getPassword());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_CHANGE_OP_PWD, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_CHANGE_OP_PWD
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_CHANGE_OP_PWD, op
                .getOperatorId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?act=view&opId="
                + op.getOperatorId().longValue() + "&"
                + plusParams(operatorForm));
    }

    private String plusParams(OperatorForm opForm) throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();
        url.append("orgId_s=").append(opForm.getOrgId_s().toString());
        url.append("&loginName=").append(URLEncoder.encode(opForm.getLoginName(),"GBK"));
        url.append("&name=").append(URLEncoder.encode(opForm.getName(),"GBK"));
        url.append("&listOp_p=").append(opForm.getListOp_p());
        return  url.toString();
    }

    /**
     * @spring.property ref="orgsTreeBuilder"
     * @param orgsTreeBuilder
     *            the orgsTreeBuilder to set
     */
    public void setOrgsTreeBuilder(OrgsTreeBuilder orgsTreeBuilder) {
        this.orgsTreeBuilder = orgsTreeBuilder;
    }  

}
