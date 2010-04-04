package com.ft.web.sm.priv.op;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;

/**
 * 操作员业务角色页面控制类
 * 
 * @version 1.0
 * @spring.bean id="operatorDataRoleAction"
 * 
 * 
 * @struts.action path="/opDataRole" name="operatorRoleForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.data.role.index"
 * 
 * @struts.action-forward name="roleConfig" path="sm.op.data.role.config"
 * @struts.action-forward name="roleBatchConfig"
 *                        path="sm.op.data.role.batch.config"
 * 
 * @struts.tiles name="sm.op.data.role.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/listOpDataRole.jsp"
 */
public class OperatorDataRoleAction extends OperatorBaseAction {

    private static Log logger = LogFactory.getLog(OperatorDataRoleAction.class);

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opRoleForm = (OperatorRoleForm) form;
        Long operatorId = opRoleForm.getOp().getOperatorId();
        List opRoles = this.opMgmt.findOperatorRoleForOrgsOfOperator(
                operatorId, new Long(RoleDTO.ROLE_TYPE_DATA), false);
        List roleInGroups = this.groupManager.findGroupRoleForOrgsOfOperator(
                operatorId, new Long(RoleDTO.ROLE_TYPE_DATA));
        request.setAttribute("opRoles", opRoles);
        request.setAttribute("roleInGroups", roleInGroups);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.op.data.role.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opDataRoleConfig.jsp"
     */
    public ActionForward configRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opRoleForm = (OperatorRoleForm) form;
        Long opId = opRoleForm.getOp().getOperatorId();

        OrganizationDTO org = opRoleForm.getOrg();
        OrganizationDTO[] loginOrgs = OperatorSessionHelper
                .getAccessOrgsOfLoginOrg(request, true);
        long[] ids = ArrayUtils.EMPTY_LONG_ARRAY;
        for (int i = 0; i < loginOrgs.length; i++) {
            ids = ArrayUtils.add(ids, loginOrgs[i].getOrgId().longValue());
        }
        List allOrgs = this.orgManager.findAccessOrgForOperatorInLoginOrg(opId ,ids);
        
        if ((org == null || org.getOrgId().longValue() == 0)
                && !allOrgs.isEmpty()) {
            org = (OrganizationDTO) allOrgs.get(0);
        }
        Long orgId = null;
        if (org != null) {
            orgId = org.getOrgId();
        }

        List opRoles = this.roleManager.findRolesOfOperator(opId, orgId,
                RoleDTO.ROLE_TYPE_DATA);
        List allDataRole = this.roleManager.findRoleByType(new Long(
                RoleDTO.ROLE_TYPE_DATA));

        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "业务角色列表");
        tree.setRoot(root);

        for (Iterator iter = allDataRole.iterator(); iter.hasNext();) {
            RoleDTO element = (RoleDTO) iter.next();
            BaseTreeNode role = new BaseTreeNode(
                    element.getRoleId().toString(), element.getRoleName());
            root.addChildLast(role);

        }
        request.setAttribute("opRoles", opRoles);
        request.setAttribute("root", root);
        request.setAttribute("allOrgs", allOrgs);
        // request.setAttribute("availableRoles", availableRoles);
        return mapping.findForward("roleConfig");
    }

    /**
     * 保存角色关系
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opForm = (OperatorRoleForm) form;
        OperatorDTO op = opForm.getOp();
        OrganizationDTO org = opForm.getOrg();
        try {
            this.opMgmt.saveOperatorRoleForOrg(opForm.getCurrentUser(), op,
                    opForm.getRoles(), org, RoleDTO.ROLE_TYPE_DATA);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_OP_ROLE, op
                    .getOperatorId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_SAVE_OP_ROLE
                    + " failed,action sequence ="
                    + op.getOperatorId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_OP_ROLE, op
                .getOperatorId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("op.do?act=view&selectedPane=dataRole&opId="
                        + op.getOperatorId().longValue()
                        + "&"
                        + plusParams(opForm));

    }

    /**
     * 批量设置业务角色
     * 
     * @struts.tiles name="sm.op.data.role.batch.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opDataRoleBatchConfig.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchConfigRole(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        OperatorRoleForm opRoleForm = (OperatorRoleForm) form;

        String orgIdstr = request.getParameter("orgId");
        Long orgId;
        if (StringUtils.isEmpty(orgIdstr)) {
            orgId = new Long(OperatorSessionHelper.getLoginOrg(request)
                    .getOrgId().longValue());
        } else {
            orgId = Long.valueOf(orgIdstr);
        }
        List allRole = this.roleManager.findRoleByType(new Long(
                RoleDTO.ROLE_TYPE_DATA));

        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "业务角色列表");
        tree.setRoot(root);

        for (Iterator iter = allRole.iterator(); iter.hasNext();) {
            RoleDTO element = (RoleDTO) iter.next();
            BaseTreeNode role = new BaseTreeNode(
                    element.getRoleId().toString(), element.getRoleName());
            root.addChildLast(role);
        }

        request.setAttribute("orgId", orgId);
        request.setAttribute("opList", Arrays.asList(opRoleForm.getOps()));
        request.setAttribute("root", root);

        return mapping.findForward("roleBatchConfig");
    }

    /**
     * 批量保存操作员的角色的关系
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchSaveRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opForm = (OperatorRoleForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchSaveOperatorRoleForOrg(opForm.getCurrentUser(),
                    ops, opForm.getRoles(), opForm.getOrg(),
                    RoleDTO.ROLE_TYPE_DATA);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_ROLE, opIds,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_SAVE_OP_ROLE
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_SAVE_OP_ROLE, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

    /**
     * 批量删除操作员的角色的关系
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward batchDeleteRole(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        OperatorRoleForm opForm = (OperatorRoleForm) form;
        OperatorDTO[] ops = opForm.getOps();
        String opIds = "";
        for (int i = 0; i < ops.length; i++) {
            opIds = opIds + ops[i].getOperatorId().toString() + " ";
        }
        try {
            this.opMgmt.batchDeleteOperatorRoleForOrg(opForm.getCurrentUser(),
                    ops, opForm.getRoles(), opForm.getOrg(),
                    RoleDTO.ROLE_TYPE_DATA);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_ROLE,
                    opIds, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_BATCH_DELETE_OP_ROLE
                    + " failed,action sequence =" + opIds, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_BATCH_DELETE_OP_ROLE, opIds,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("op.do?" + plusParams(opForm));
    }

}
