package com.ft.web.sm.priv.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgsTreeBuilder;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.commons.tree.TreeNode;

/**
 * 操作员组维护页面控制类
 * 
 * 
 * @spring.bean id="groupAction"
 * 
 * @struts.action path="/group" name="groupForm" scope="request"
 *                validate="false" parameter="act" input="sm.group.index"
 * 
 * @struts.action-forward name="create" path="sm.group.create"
 * @struts.action-forward name="edit" path="sm.group.edit"
 * @struts.action-forward name="view" path="sm.group.view"
 * @struts.action-forward name="orgView" path="sm.group.org.view"
 * @struts.action-forward name="roleList" path="sm.group.role.list"
 * @struts.action-forward name="roleConfig" path="sm.group.role.config"
 * 
 * @struts.tiles name="sm.group.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/group/listGroup.jsp"
 * 
 * @version 1.0
 */
public class GroupAction extends GroupBaseAction {

	private static Log logger = LogFactory.getLog(GroupAction.class);
    
    private OrgsTreeBuilder orgsTreeBuilder;
	

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GroupForm groupForm = (GroupForm) form;
		String groupName = groupForm.getGroupName();
		Long orgId = groupForm.getOrgId();
        boolean includeAll = false;
        if(orgId == null || orgId.longValue() ==0){
            orgId = OperatorSessionHelper.getLoginOrg(request).getOrgId();
            includeAll = true;
        }
		List groups = groupManager.searchGroup( orgId, groupName, includeAll);
		request.setAttribute("groups", groups);
		return mapping.getInputForward();
	}

	/**
	 * 
	 * 跳转到操作员组新建页面
	 * 
	 * @struts.tiles name="sm.group.create" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/group/createGroup.jsp"
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
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
	 * 
	 * @struts.tiles name="sm.group.view" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/group/viewGroup.jsp"
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GroupForm groupForm = (GroupForm) form;
//		List accessOrg = this.orgManager.findAcessOrgByGroupId(groupForm
//				.getGroup().getGroupId());
        List accessOrg = this.orgManager.findAcessOrgByGroupIdWithChild(groupForm.getGroup().getGroupId());
        TreeNode orgTree = this.orgsTreeBuilder.buildOrgNode(accessOrg);
        
		List groupRoles = this.groupManager
				.findGroupRoleForOrgsByGroupId(groupForm.getGroup()
						.getGroupId());
		//request.setAttribute("accessOrg", accessOrg);
        request.setAttribute("orgTree", orgTree);
		request.setAttribute("groupRoles", groupRoles);
		return mapping.findForward("view");
	}

	/**
	 * @struts.tiles name="sm.group.edit" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/group/editGroup.jsp"
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}

	/**
	 * 创建组信息
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
		GroupForm groupForm = (GroupForm) form;
		GroupDTO group = groupForm.getGroup();
		group.setOwnerOrgId(groupForm.getOrgId());
		
		AppRequest appRequest = groupForm.getAppRequest(request, ActionDefinition.SYS_ADD_GROUP);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());
		try {
			Long groupId = this.groupManager.saveGroup(group,appRequest );
			group.setGroupId(groupId);
		} catch (Exception ex) {
			logger.log(request, ActionDefinition.SYS_ADD_GROUP, appRequest.getAppId()
					+ "", ActionDefinition.ACTION_FAIL);
			logger.error("Execute action " + ActionDefinition.SYS_ADD_GROUP
					+ " failed,action sequence ="
					+ group.getGroupId().longValue(), ex);
			throw ex;
		}
		logger.log(request, ActionDefinition.SYS_ADD_GROUP, appRequest.getAppId()
				+ "", ActionDefinition.ACTION_SUCCESS);
		return this.getRedirectForwardAction("group.do?act=view&id="
				+ group.getGroupId().longValue());
	}

	/**
	 * 更新组信息
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
		GroupForm groupForm = (GroupForm) form;
		GroupDTO group = groupForm.getGroup();
		group.setOwnerOrgId(groupForm.getOrgId());
		
		AppRequest appRequest = groupForm.getAppRequest(request, ActionDefinition.SYS_ADD_GROUP);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
		
		try {
			Long groupId = this.groupManager.saveGroup(group, appRequest);
			group.setGroupId(groupId);
		} catch (Exception ex) {
			logger.log(request, ActionDefinition.SYS_UPDATE_GROUP, appRequest.getAppId()+""
					, ActionDefinition.ACTION_FAIL);
			logger.error("Execute action " + ActionDefinition.SYS_UPDATE_GROUP
					+ " failed,action sequence ="
					+ appRequest.getAppId()+"", ex);
			throw ex;
		}
		logger.log(request, ActionDefinition.SYS_UPDATE_GROUP, appRequest.getAppId()+""
				, ActionDefinition.ACTION_SUCCESS);
		return this.getRedirectForwardAction("group.do?act=view&id="
				+ group.getGroupId().longValue());
	}

	/**
	 * 删除组
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
		GroupForm groupForm = (GroupForm) form;
		GroupDTO[] groups = groupForm.getGroups();
		String allId = "";
		for (int i = 0; i < groups.length; i++) {
			allId = groups[i].getGroupId().longValue() + ",";
			// 清除组的相关信息
			this.groupManager.saveGroupRoleForOrg(groupForm.getCurrentUser(),
					groups[i], new RoleDTO[0], OperatorSessionHelper.getLoginOrg(request));
			this.groupManager.saveGroupAccessOrg(groupForm.getCurrentUser(),
					new OrganizationDTO[0], groups[i]);
		}
		try {
			this.groupManager.deleteGroup(groupForm.getGroups(), groupForm
					.getAppRequest(request, ActionDefinition.SYS_DELETE_GROUP));
		} catch (Exception ex) {
			logger.log(request, ActionDefinition.SYS_DELETE_GROUP, allId,
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + ActionDefinition.SYS_DELETE_GROUP
					+ " failed,action sequence =" + allId, ex);
			throw ex;
		}
		logger.log(request, ActionDefinition.SYS_DELETE_GROUP, allId,
				ActionDefinition.ACTION_SUCCESS);
		return getRedirectForwardAction("group.do");
	}

    /**
     * @spring.property ref="orgsTreeBuilder"
     * @param orgsTreeBuilder the orgsTreeBuilder to set
     */
    public void setOrgsTreeBuilder(OrgsTreeBuilder orgsTreeBuilder) {
        this.orgsTreeBuilder = orgsTreeBuilder;
    }
    

}
