package com.ft.web.sm.priv.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.web.sm.BaseAction;

/**
 * 操作员权限查询
 * 
 * @spring.bean id="queryOperatorPrivilegeAction"
 * 
 * @struts.action path="/queryOPPrivilege" name="queryOperatorPrivilegeForm"
 *                scope="request" input="sm.query.operator.privilege"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.query.operator.privilege" extends="main.layout"
 * @struts.tiles-put name="body"
 *                   value="/sm/privilege/queryOperatorPrivilege.jsp"
 * 
 * @struts.action-forward name="list" path="sm.query.operator.privilege.list"
 * 
 * @version 1.0
 */
public class QueryOperatorPrivilegeAction extends BaseAction {

	/**
	 * 功能权限管理类
	 */
	private ResourceManager resourceManager;

	private ResourceTreeBuilder resourceTreeBuilder;

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
	 * 默认方法
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}

	/**
	 * 查询操作员所拥有的权限
	 * 
	 * @struts.tiles name="sm.query.operator.privilege.list"
	 *               extends="main.layout"
	 * @struts.tiles-put name="body"
	 *                   value="/sm/privilege/listOperatorPrivilege.jsp"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryOperatorPrivilege(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QueryOperatorPrivilegeForm opForm = (QueryOperatorPrivilegeForm) form;
		Long opId = opForm.getOperator().getOperatorId();
		// 查询操作员可访问的功能权限
		List resourceList = resourceManager
				.findAllResourcesExcludeConsignedOfOperator(opId);
		// 查询操作员可访问的业务权限关联
		List dataResourceACLList = resourceManager
				.findDataResourceEntriesOfOperator(opId);

		// 创建功能权限树
		PermissionChecker checker = new PermissionChecker();
		checker.addPermission(resourceList);
		request.setAttribute("resourceRoot", this.resourceTreeBuilder
				.buildTreeNode(checker));

		// 获得业务权限条目
		List dataResourceEntryList = getEntryByACL(dataResourceACLList);
		request.setAttribute("entrys", dataResourceEntryList);
		return mapping.findForward("list");
	}

	/**
	 * 从业务权限关联中获得业务权限条目信息
	 * 
	 * @param dataResourceACLList
	 * @return
	 */
	private List getEntryByACL(List dataResourceACLList) {
		List<DataResourceEntryDTO> dataEntryList = new ArrayList<DataResourceEntryDTO>();
		Map<String,DataResourceEntryDTO> entryMap = new HashMap<String,DataResourceEntryDTO>();
		if (null != dataResourceACLList && dataResourceACLList.size() > 0) {
			for (Iterator iter = dataResourceACLList.iterator(); iter.hasNext();) {
				DataResourceEntryDTO entry = (DataResourceEntryDTO) iter.next();
				if (!entryMap.containsKey(entry.getEntryId() + "")) {
					entryMap.put(entry.getEntryId() + "", entry);
				}
			}
			for (Iterator iter = entryMap.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				dataEntryList.add(entryMap.get(key));
			}
		}
		return dataEntryList;
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
}
