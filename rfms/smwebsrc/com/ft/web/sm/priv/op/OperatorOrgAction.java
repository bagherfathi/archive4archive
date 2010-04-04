package com.ft.web.sm.priv.op;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgTreeBuilder;
import com.ft.common.security.OrgsTreeBuilder;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.ActionDefinition;


/**
 * @version 1.0
 * @spring.bean id="operatorOrgAction"
 * 
 * @struts.action path="/opOrg" name="operatorOrgForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.org.index"
 * 
 * @struts.action-forward name="configOrg" path="sm.op.org.config"
 * @struts.action-forward name="batchConfigOrg" path="sm.op.org.batch.config"
 * 
 * 
 * @struts.tiles name="sm.op.org.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/listOpOrg.jsp"
 */

public class OperatorOrgAction extends OperatorBaseAction {

    private static Log logger = LogFactory.getLog(OperatorOrgAction.class);

    @SuppressWarnings("unused")
	private OrgTreeBuilder orgTreeBuilder;

    @SuppressWarnings("unused")
	private OrgsTreeBuilder orgsTreeBuilder;

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        List accessOrgs = this.orgManager.findAccessOrgsForOperator(opForm
                .getOp().getOperatorId());
        List orgInGroups = this.groupManager
                .findGroupAccessOrgsOfOperator(opForm.getOp().getOperatorId());
        request.setAttribute("accessOrgs", accessOrgs);
        request.setAttribute("orgInGroups", orgInGroups);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.op.org.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opOrgConfig.jsp"
     */

    public ActionForward configOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        OperatorDTO operator = opForm.getOp();
        List accessOrgs = this.orgManager
                .findAccessOrgsForOperatorWithChild(operator.getOperatorId());

        // TreeNode treenode = this.orgTreeBuilder.buildAccessOrgTreeNode(true,
        // false, OperatorSessionHelper.getAccessOrgsOfLoginOp(request));

        // TreeNode treenode = this.orgTreeBuilder.buildOrgNode(
        // OperatorSessionHelper.getAccessOrgsOfLoginOp(request), "可访问组织");

        // TreeNode treenode = this.orgsTreeBuilder
        // .buildOrgNode(OperatorSessionHelper
        // .getAccessOrgsOfLoginOp(request));

        // List availableOrgs = this.orgManager
        // .findAvailableAccessOrgsForOperator(operator.getOperatorId());
        request.setAttribute("accessOrgs", accessOrgs);
        // request.setAttribute("root", treenode);
        // request.setAttribute("availableOrgs", availableOrgs);
        return mapping.findForward("configOrg");
    }

    /**
     * 保存操作员的可访问组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        OperatorDTO op = opForm.getOp();
        try {
            this.opMgmt.saveOperatorAccessOrg(opForm.getCurrentUser(), op,
                    filterOrgs(opForm.getOrgs()));
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_OP_ORG, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_SAVE_OP_ORG
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_OP_ORG, op
                .getOperatorId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("op.do?act=view&selectedPane=org&opId="
                        + op.getOperatorId().longValue()
                        + "&"
                        + plusParams(opForm));
    }

    /**
     * 批量设置操作员的可访问组织
     * 
     * @struts.tiles name="sm.op.org.batch.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opOrgBatchConfig.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchConfigOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        // List accessOrgs =
        // OperatorSessionHelper.getAccessOrgsOfLoginOrg(request, true);
        // TreeNode treenode = this.orgTreeBuilder
        // .buildOrgNode(accessOrgs, "可选组织");

        request.setAttribute("opList", Arrays.asList(opForm.getOps()));
        // request.setAttribute("root", treenode);

        return mapping.findForward("batchConfigOrg");
    }

    /**
     * 批量保存操作员的可访问组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchSaveOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchSaveOperatorAccessOrg(opForm.getCurrentUser(),
                    ops, opForm.getOrgs());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_ORG, opIds,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_SAVE_OP_ORG
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_ORG, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

    /**
     * 批量删除操作员的可访问组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchDeleteOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorOrgForm opForm = (OperatorOrgForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchDeleteOperatorAccessOrg(opForm.getCurrentUser(),
                    ops, opForm.getOrgs());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_ORG,
                    opIds, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_DELETE_OP_ORG
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_ORG, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

    /**
     * 过滤组织信息
     * 
     * @param orgDTOs
     * @return
     */
    private OrganizationDTO[] filterOrgs(OrganizationDTO[] orgDTOs) {
        List<OrganizationDTO> result = new ArrayList<OrganizationDTO>();

        for (int i = 0; i < orgDTOs.length; i++) {
            boolean save = true;
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                OrganizationDTO element = (OrganizationDTO) iter.next();
                if (orgDTOs[i].getPath().startsWith(element.getPath())) {
                    save = false;
                    break;
                }
            }
            if (save) {
                result.add(orgDTOs[i]);
            }
        }
        return (OrganizationDTO[]) result.toArray(new OrganizationDTO[result
                .size()]);

    }

    /**
     * @spring.property ref="orgTreeBuilder"
     * @param orgTreeBuilder
     */
    public void setOrgTreeBuilder(OrgTreeBuilder orgTreeBuilder) {
        this.orgTreeBuilder = orgTreeBuilder;
    }

    /**
     * @spring.property ref="orgsTreeBuilder"
     * @param orgsTreeBuilder
     */
    public void setOrgsTreeBuilder(OrgsTreeBuilder orgsTreeBuilder) {
        this.orgsTreeBuilder = orgsTreeBuilder;
    }

}
