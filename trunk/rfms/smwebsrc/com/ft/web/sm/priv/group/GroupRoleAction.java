package com.ft.web.sm.priv.group;

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
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.ActionDefinition;

/**
 * 组的功能角色
 * 
 * 
 * @spring.bean id="groupRoleAction"
 * 
 * @struts.action path="/groupRole" name="groupRoleForm" scope="request"
 *                validate="false" parameter="act" input="sm.group.role.list"
 * @struts.tiles name="sm.group.role.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/group/listGroupRole.jsp"
 * 
 * @struts.action-forward path="sm.group.role.config" name="roleConfig"
 * 
 */
public class GroupRoleAction extends GroupBaseAction {

    private static Log logger = LogFactory.getLog(GroupRoleAction.class);

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GroupRoleForm groupForm = (GroupRoleForm) form;
        List groupRoles = this.groupManager
                .findGroupRoleForOrgsByGroupId(groupForm.getGroup()
                        .getGroupId());
        request.setAttribute("groupRoles", groupRoles);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.group.role.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/group/groupRoleConfig.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward configRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GroupRoleForm groupForm = (GroupRoleForm) form;
        Long groupId = groupForm.getGroup().getGroupId();
        OrganizationDTO org = groupForm.getOrg();
        if (org != null && org.getOrgId().longValue() == 0) {
            org = null;
        }

        OrganizationDTO[] loginOrgs = OperatorSessionHelper
                .getAccessOrgsOfLoginOrg(request, true);
        long[] ids = ArrayUtils.EMPTY_LONG_ARRAY;
        for (int i = 0; i < loginOrgs.length; i++) {
            ids = ArrayUtils.add(ids, loginOrgs[i].getOrgId().longValue());
        }
        List allOrgs = orgManager.findAccessOrgForGroupInLoginOrg(groupId,
                ids);

        if (org == null && !allOrgs.isEmpty()) {
            org = (OrganizationDTO) allOrgs.get(0);
        }
        BaseTree tree = new BaseTree();
        BaseTreeNode root = new BaseTreeNode("root", "功能角色列表");
        tree.setRoot(root);
        if(org != null){
            Long orgId = org.getOrgId();
            List groupRoles = this.roleManager.findRolesOfGroup(groupId, orgId);

            // 如果组织不是分公司，则查找出该组织所属的分公司
            if (org.getType() != OrganizationDTO.ORG_TYPE_COMPANY) {
                orgId = orgManager.findCompanyIdOfOrg(orgId);
            }
            List allRole = this.roleManager.findRoleByAdaptOrgId(orgId,
                    RoleDTO.ROLE_TYPE_FUNCTION);
            
           

            for (Iterator iter = allRole.iterator(); iter.hasNext();) {
                RoleDTO element = (RoleDTO) iter.next();
                BaseTreeNode role = new BaseTreeNode(
                        element.getRoleId().toString(), element.getRoleName());
                root.addChildLast(role);
            }
            request.setAttribute("groupRoles", groupRoles);
        }
        request.setAttribute("root", root);
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
        GroupRoleForm groupForm = (GroupRoleForm) form;
        GroupDTO group = groupForm.getGroup();
        try {
            this.groupManager.saveGroupRoleForOrg(groupForm.getCurrentUser(),
                    group, groupForm.getRoles(), groupForm.getOrg());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_GROUP_ROLE, group
                    .getGroupId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_SAVE_GROUP_ROLE
                    + " failed,action sequence ="
                    + group.getGroupId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_GROUP_ROLE, group
                .getGroupId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);

        return this
                .getRedirectForwardAction("group.do?act=view&selectedPane=role&id="
                        + group.getGroupId().longValue());
        // return this.getRedirectForwardAction("groupRole.do?id="
        // + group.getGroupId().longValue());

    }

}
