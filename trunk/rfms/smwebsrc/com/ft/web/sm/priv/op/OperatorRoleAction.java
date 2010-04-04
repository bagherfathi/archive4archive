package com.ft.web.sm.priv.op;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
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
 * @version 1.0
 * @spring.bean id="operatorRoleAction"
 * 
 * 
 * @struts.action path="/opRole" name="operatorRoleForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.role.index"
 * 
 * @struts.action-forward name="roleConfig" path="sm.op.role.config"
 * @struts.action-forward name="roleBatchConfig" path="sm.op.role.batch.config"
 * 
 * @struts.tiles name="sm.op.role.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/listOpRole.jsp"
 */
public class OperatorRoleAction extends OperatorBaseAction {

    private static Log logger = LogFactory.getLog(OperatorRoleAction.class);

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opRoleForm = (OperatorRoleForm) form;
        Long operatorId = opRoleForm.getOp().getOperatorId();
        List opRoles = this.opMgmt.findOperatorRoleForOrgsOfOperator(
                operatorId, new Long(RoleDTO.ROLE_TYPE_FUNCTION), false);
        List roleInGroups = this.groupManager.findGroupRoleForOrgsOfOperator(
                operatorId, new Long(RoleDTO.ROLE_TYPE_FUNCTION));
        request.setAttribute("opRoles", opRoles);
        request.setAttribute("roleInGroups", roleInGroups);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.op.role.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opRoleConfig.jsp"
     */
    public ActionForward configRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorRoleForm opRoleForm = (OperatorRoleForm) form;
        OperatorDTO op = opRoleForm.getOp();
        OrganizationDTO org = opRoleForm.getOrg();
        if (org.getOrgId().longValue() == 0) {
            org = null;
        }

        // 查询当前操作员的登陆可访问组织与指定操作员的可访问组织的交集
        OrganizationDTO[] loginOrgs = OperatorSessionHelper
                .getAccessOrgsOfLoginOrg(request, true);
        long[] ids = ArrayUtils.EMPTY_LONG_ARRAY;
        for (int i = 0; i < loginOrgs.length; i++) {
            ids = ArrayUtils.add(ids, loginOrgs[i].getOrgId().longValue());
        }
        List allOrgs = this.orgManager.findAccessOrgForOperatorInLoginOrg(op
                .getOperatorId(),ids);

        if (org == null && !allOrgs.isEmpty()) {
            org = (OrganizationDTO) allOrgs.get(0);
        }
        if (org != null) {
            Long orgId = org.getOrgId();
            List opRoles = this.roleManager.findRolesOfOperator(op
                    .getOperatorId(), orgId, RoleDTO.ROLE_TYPE_FUNCTION);
            // 如果组织不是分公司，则查找出该组织所属的分公司
            if (org.getType() != OrganizationDTO.ORG_TYPE_COMPANY) {
                orgId = this.orgManager.findCompanyIdOfOrg(orgId);
            }
            List allRole = this.roleManager.findRoleByAdaptOrgId(orgId,
                    RoleDTO.ROLE_TYPE_FUNCTION);

            BaseTree tree = new BaseTree();
            BaseTreeNode root = new BaseTreeNode("root", "功能角色列表");
            tree.setRoot(root);
            for (Iterator iter = allRole.iterator(); iter.hasNext();) {
                RoleDTO element = (RoleDTO) iter.next();
                BaseTreeNode role = new BaseTreeNode(element.getRoleId()
                        .toString(), element.getRoleName());
                root.addChildLast(role);
            }
            request.setAttribute("root", root);
            request.setAttribute("opRoles", opRoles);
        }

        request.setAttribute("allOrgs", allOrgs);

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
        try {
            this.opMgmt.saveOperatorRoleForOrg(opForm.getCurrentUser(), op,
                    opForm.getRoles(), opForm.getOrg(),
                    RoleDTO.ROLE_TYPE_FUNCTION);
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
                .getRedirectForwardAction("op.do?act=view&selectedPane=role&opId="
                        + op.getOperatorId().longValue()
                        + "&"
                        + plusParams(opForm));

    }

    /**
     * 批量设置角色
     * 
     * @struts.tiles name="sm.op.role.batch.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/op/opRoleBatchConfig.jsp"
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
        List allOrgs = OperatorSessionHelper.getAccessOrgsOfLoginOp(request);
        OrganizationDTO org = opRoleForm.getOrg();
        if (org == null || org.getOrgId().longValue() == 0) {
            org = OperatorSessionHelper.getLoginOrg(request);
        }
        Long companyId = org.getOrgId();
        if (org.getType() != OrganizationDTO.ORG_TYPE_COMPANY) {
            companyId = this.orgManager.findCompanyIdOfOrg(org.getOrgId());
        }
        List allRole = this.roleManager.findRoleByAdaptOrgId(companyId,
                RoleDTO.ROLE_TYPE_FUNCTION);

        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "功能角色列表");
        tree.setRoot(root);

        for (Iterator iter = allRole.iterator(); iter.hasNext();) {
            RoleDTO element = (RoleDTO) iter.next();
            BaseTreeNode role = new BaseTreeNode(
                    element.getRoleId().toString(), element.getRoleName());
            root.addChildLast(role);
        }

        request.setAttribute("orgId", org.getOrgId());
        request.setAttribute("opList", Arrays.asList(opRoleForm.getOps()));
        request.setAttribute("allOrgs", allOrgs);
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
                    RoleDTO.ROLE_TYPE_FUNCTION);
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
                    RoleDTO.ROLE_TYPE_FUNCTION);
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
