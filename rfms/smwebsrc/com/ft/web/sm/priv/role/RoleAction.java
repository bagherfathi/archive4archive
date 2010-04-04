package com.ft.web.sm.priv.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgTreeBuilder;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.web.sm.priv.resource.ResourceFilter;
import com.ft.commons.tree.TreeNode;
import com.ft.struts.ActionMessagesHelper;

/**
 * 角色信息维护类
 * 
 * @spring.bean name="roleAction"
 * 
 * @struts.action name="roleForm" path="/role" scope="request"
 *                input="sm.role.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.role.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/role/listRole.jsp"
 * 
 * @struts.action-forward name="roleCreate" path="sm.role.create"
 * 
 * @struts.action-forward name="roleUpdate" path="sm.role.update"
 * 
 * @struts.action-forward name="rolePreview" path="sm.role.preview"
 * 
 * @struts.action-forward name="roleView" path="sm.role.view"
 * 
 * @version 1.0
 * 
 */
public class RoleAction extends BaseAction {

    private static Log logger = LogFactory.getLog(RoleAction.class);

    private RoleManager roleManager;

    private ResourceManager resourceManager;

    private OrgManager orgManager;

    private ResourceTreeBuilder resourceTreeBuilder;

    private OrgTreeBuilder orgTreeBuilder;

    /**
     * @spring.property ref="roleManager"
     */
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref="resourceTreeBuilder"
     */
    public void setResourceTreeBuilder(ResourceTreeBuilder resourceTreeBuilder) {
        this.resourceTreeBuilder = resourceTreeBuilder;
    }

    /**
     * @spring.property ref="orgTreeBuilder"
     */
    public void setOrgTreeBuilder(OrgTreeBuilder orgTreeBuilder) {
        this.orgTreeBuilder = orgTreeBuilder;
    }

    /**
     * @spring.property ref="orgManager"
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RoleForm roleForm = (RoleForm) form;
        List roles = roleManager.findRoleByRoleName(roleForm.getRoleName(),
                RoleDTO.ROLE_TYPE_FUNCTION);
        request.setAttribute("roles", roles);
        return mapping.getInputForward();
    }

    /**
     * 创建角色
     * 
     * @struts.tiles name="sm.role.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/addRole.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("roleCreate");
    }

    /**
     * 跳转到编辑页面
     * 
     * @struts.tiles name="sm.role.update" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/editRole.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RoleForm roleForm = (RoleForm) form;
        RoleDTO role = roleForm.getRole();
        if (null == role.getRoleId()) {
            RoleDTO[] roles = roleForm.getRoles();
            role = roles[0];
            roleForm.setRole(role);
        }
        List orgs = this.orgManager.findOrgsOfRole(role.getRoleId());
     //   List allOrgs = orgManager.findOrgsByType(OrganizationDTO.ORG_TYPE_COMPANY);
     //   TreeNode orgRoot = orgTreeBuilder.buildOrgNode(allOrgs, "适应组织");
      //  roleForm.setOrgRoot(orgRoot);
     //   request.setAttribute("orgRoot", orgRoot);
        request.setAttribute("orgs", orgs);
        request.setAttribute("role", role);
        return mapping.findForward("roleUpdate");
    }

    /**
     * 编辑角色
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_UPDATE_ROLE;

        RoleForm roleForm = (RoleForm) form;
        RoleDTO role = roleForm.getRole();
//      检验
        RoleDTO existRole = this.roleManager.findRoleByName(role.getRoleName(),
                RoleDTO.ROLE_TYPE_FUNCTION);
        if (existRole != null
                && !existRole.getRoleId().equals(role.getRoleId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.role.name.exist",
                    new Object[] { role.getRoleName() });
            return mapping.findForward("roleCreate");
        }
        String roleName = roleForm.getRole().getRoleName();
        ResourceDTO[] Resources = roleForm.getResources();
        List<ResourceDTO> resourceList = new ArrayList<ResourceDTO>();
        if (null != Resources) {
            for (int i = 0; i < Resources.length; i++) {
                resourceList.add(Resources[i]);
            }
        }

        List aclList = ResourceFilter.filter(resourceList);

        AppRequest appRequest = roleForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            roleManager.updateRole(roleForm.getCurrentUser(), roleForm
                    .getRole(), aclList, roleForm.getOrgIds(), appRequest);
        } catch (Exception e) {
            // 记录更新失败日志
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + roleName, e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, roleName,
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("role.do");
    }

    /**
     * 删除角色
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_DELETE_ROLE;

        RoleForm roleForm = (RoleForm) form;
        String roleName = roleForm.getRole().getRoleName();

        try {
            // 删除选择的角色信息
            roleManager.deleteRole(roleForm.getCurrentUser(), roleForm
                    .getRole(), roleForm.getAppRequest(request, actionCode));
        } catch (Exception e) {
            // 记录删除失败日志
            logger.log(request, actionCode, roleName,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + roleName, e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, roleName,
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("role.do");
    }

    /**
     * 创建角色前先将角色信息及选择的功能显示到预览页面
     * 
     * @struts.tiles name="sm.role.preview" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/previewRole.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward preview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RoleForm roleForm = (RoleForm) form;
        RoleDTO role = roleForm.getRole();
        ResourceDTO[] resources = roleForm.getResources();
        List<ResourceDTO> resList = new ArrayList<ResourceDTO>();
        if (null != resources) {
            for (int i = 0; i < resources.length; i++) {
                resList.add((ResourceDTO) resources[i]);
            }
        }
        // 对所选的功能权限做过滤
        List resourceList = ResourceFilter.filter(resList);

        // 根据所选功能生成预览数
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(resourceList);
        request.setAttribute("root", this.resourceTreeBuilder
                .buildTreeNode(checker));

        HttpSession hs = request.getSession();
        // 将选择的功能权限存放到session中为保存做准备
        hs.setAttribute("menuList", resList);
        request.setAttribute("role", role);
        return mapping.findForward("rolePreview");
    }

    /**
     * 保存Role
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

        String actionCode = ActionDefinition.SYS_ADD_ROLE;

        RoleForm roleForm = (RoleForm) form;
        RoleDTO role = roleForm.getRole();

        // 检验
        RoleDTO existRole = this.roleManager.findRoleByName(role.getRoleName(),
                RoleDTO.ROLE_TYPE_FUNCTION);
        if (existRole != null
                && !existRole.getRoleId().equals(role.getRoleId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.role.name.exist",
                    new Object[] { role.getRoleName() });
            return mapping.findForward("roleCreate");
        }

        String roleName = role.getRoleName();
        // hs.removeAttribute("menuList");

        ResourceDTO[] resources = roleForm.getResources();
        List resList = new ArrayList();
        resList = Arrays.asList(resources);
        // 对所选的功能权限做过滤

        List aclList = ResourceFilter.filter(resList);

        AppRequest appRequest = roleForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());

        // 创建角色信息
        try {
            roleManager.addRole(roleForm.getCurrentUser(), role, aclList,
                    roleForm.getOrgIds(), appRequest);
        } catch (Exception e) {
            // 记录删除失败日志
            logger.log(request, actionCode, roleName,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + roleName, e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, roleName,
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction("role.do");
    }

    /**
     * 浏览页面
     * 
     * @struts.tiles name="sm.role.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/viewRoleIndex.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RoleForm roleForm = (RoleForm) form;
        RoleDTO role = roleForm.getRole();
        // 获得当前角色所拥有的功能
        List ret = this.resourceManager.findResourceOfRole(role.getRoleId());
        List orgs = this.orgManager.findOrgsOfRole(role.getRoleId());
        TreeNode orgTree = this.orgTreeBuilder.buildOrgNode(orgs, "适用组织");
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(ret);
        request.setAttribute("root", this.resourceTreeBuilder
                .buildTreeNode(checker));
        request.setAttribute("orgTree", orgTree);
        request.setAttribute("role", role);
        return mapping.findForward("roleView");
    }

}
