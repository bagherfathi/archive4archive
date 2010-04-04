package com.ft.web.sm.priv.op;

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
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;

/**
 * @version 1.0
 * @spring.bean id="operatorGroupAction"
 * 
 * @struts.action path="/opGroup" name="operatorGroupForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.group.index"
 * 
 * @struts.action-forward name="groupConfig" path="sm.op.group.config"
 * @struts.action-forward name="batchConfigGroup"
 *                        path="sm.op.group.batch.config"
 * 
 * 
 * @struts.tiles name="sm.op.group.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/listOpGroup.jsp"
 */

public class OperatorGroupAction extends OperatorBaseAction {
    private static Log logger = LogFactory.getLog(OperatorGroupAction.class);

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorGroupForm opForm = (OperatorGroupForm) form;
        List opGroups = this.groupManager.findGroupsByOperator(opForm.getOp()
                .getOperatorId());
        request.setAttribute("opGroups", opGroups);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.op.group.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opGroupConfig.jsp"
     */
    public ActionForward configGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorGroupForm opForm = (OperatorGroupForm) form;
//        List opGroups = this.groupManager.findGroupsByOperator(opForm.getOp()
//                .getOperatorId());
        
        OperatorDTO opDto = opForm.getOp();
        OrganizationDTO org = OperatorSessionHelper.getLoginOrg(request);
        
        List opGroups = this.groupManager.findGroupsByOperator(opForm.getOp()
             .getOperatorId(),org.getOrgId());

        // 查询操作员所属分公司下可分配的操作员组
        Long comanyId = org.getOrgId();
        if(org.getType() != OrganizationDTO.ORG_TYPE_COMPANY){
            comanyId = orgManager.findCompanyIdOfOrg(org.getOrgId());
        }
        List availableGroups = this.groupManager.findAvailableGroupsByOperator(
                opDto.getOperatorId(), comanyId);
        request.setAttribute("opGroups", opGroups);
        request.setAttribute("availableGroups", availableGroups);
        return mapping.findForward("groupConfig");
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
    public ActionForward saveGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorGroupForm opForm = (OperatorGroupForm) form;
        OperatorDTO op = opForm.getOp();
        try {
            this.opMgmt.saveOperatorGroup(opForm.getCurrentUser(), op, opForm
                    .getGroups());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_OP_GROUP, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_SAVE_OP_GROUP
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_OP_GROUP, op
                .getOperatorId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("op.do?act=view&selectedPane=group&opId="
                        + op.getOperatorId().longValue() + "&" + plusParams(opForm));
    }

    /**
     * 
     * @struts.tiles name="sm.op.group.batch.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opGroupBatchConfig.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchConfigGroup(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        OperatorGroupForm opGroupForm = (OperatorGroupForm) form;
        OperatorDTO[] ops = opGroupForm.getOps();

        List allGroup = groupManager.findAllGroup();

        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "组列表");
        tree.setRoot(root);
        
        
         Long[] orgIds = OperatorSessionHelper.getAccessCompanyIdsOfLoginOrg(request);

        for (Iterator iter = allGroup.iterator(); iter.hasNext();) {
            GroupDTO element = (GroupDTO) iter.next();
            for (int i = 0; i < orgIds.length; i++) {
                if (element.getOwnerOrgId().equals(orgIds[i])) {
                    BaseTreeNode group = new BaseTreeNode(element.getGroupId()
                            .toString(), element.getName());
                    root.addChildLast(group);
                    break;
                } 
            }
        }

        request.setAttribute("opList", Arrays.asList(ops));
        request.setAttribute("root", root);

        return mapping.findForward("batchConfigGroup");
    }

    /**
     * 批量保存操作员和组的关系
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchSaveGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorGroupForm opForm = (OperatorGroupForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchSaveOperatorGroup(opForm.getCurrentUser(), ops,
                    opForm.getGroups());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_GROUP,
                    opIds, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_SAVE_OP_GROUP
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_GROUP, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

    /**
     * 批量删除操作员和组的关系
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchDeleteGroup(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        OperatorGroupForm opForm = (OperatorGroupForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchDeleteOperatorGroup(opForm.getCurrentUser(), ops,
                    opForm.getGroups());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_GROUP,
                    opIds, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_DELETE_OP_GROUP
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_GROUP, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

}
