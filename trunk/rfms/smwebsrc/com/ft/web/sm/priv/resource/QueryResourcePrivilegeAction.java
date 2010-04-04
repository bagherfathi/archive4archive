package com.ft.web.sm.priv.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.GroupManager;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.web.sm.BaseAction;

/**
 * 权限查询类 说明：操作员权限查询，功能权限查询，业务权限查询
 * 
 * @spring.bean id="queryResourcePrivilegeAction"
 * 
 * @struts.action path="/queryRPrivilege" name="queryResourcePrivilegeForm"
 *                scope="request" input="sm.query.resource.privilege"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.query.resource.privilege" extends="main.layout"
 * @struts.tiles-put name="body"
 *                   value="/sm/privilege/queryResourcePrivilege.jsp"
 * 
 * @struts.action-forward name="resourcePrivilegeList"
 *                        path="sm.query.resource.privilege.list"
 * @version 1.0
 */
public class QueryResourcePrivilegeAction extends BaseAction {

	/**
	 * 操作员管理类
	 */
	@SuppressWarnings("unused")
	private OperatorManager operatorManager;

	/**
	 * 操作员组管理类
	 */
	@SuppressWarnings("unused")
	private GroupManager groupManager;

	private ResourceManager resourceManager;

	/**
	 * @spring.property ref="operatorManager"
	 */
	public void setOperatorManager(OperatorManager operatorManager) {
		this.operatorManager = operatorManager;
	}

	/**
	 * @spring.property ref="groupManager"
	 */
	public void setGroupManager(GroupManager groupManager) {
		this.groupManager = groupManager;
	}

	/**
	 * @spring.property ref="resourceManager"
	 * @param resourceManager
	 *            the resourceManager to set
	 */
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	/**
	 * 
	 * 默认方法
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List resources = resourceManager.findAllResources();
		request.setAttribute("resources", resources);
		return mapping.getInputForward();
	}

	/**
	 * 查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}

	/**
	 * 查询功能权限分配情况
	 * 
	 * @struts.tiles name="sm.query.resource.privilege.list"
	 *               extends="main.layout"
	 * @struts.tiles-put name="body"
	 *                   value="/sm/privilege/listResourcePrivilege.jsp"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryResourcePrivilege(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * QueryResourcePrivilegeForm queryForm = (QueryResourcePrivilegeForm)
		 * form; Long id = queryForm.getResource().getResourceId(); //
		 * 获得功能权限分配给的操作员组信息 List groupList =
		 * this.groupManager.findGroupByResource(id); // 获得功能权限分配给的操作员信息 List
		 * opList = this.operatorManager.findResourceDistributeOperator(id);
		 * request.setAttribute("groups", groupList);
		 * request.setAttribute("operators", opList); return
		 * mapping.findForward("resourcePrivilegeList");
		 */
		return null;
	}
}
