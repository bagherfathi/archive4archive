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
 * ����ԱȨ�޲�ѯ
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
	 * ����Ȩ�޹�����
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
	 * Ĭ�Ϸ���
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}

	/**
	 * ��ѯ����Ա��ӵ�е�Ȩ��
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
		// ��ѯ����Ա�ɷ��ʵĹ���Ȩ��
		List resourceList = resourceManager
				.findAllResourcesExcludeConsignedOfOperator(opId);
		// ��ѯ����Ա�ɷ��ʵ�ҵ��Ȩ�޹���
		List dataResourceACLList = resourceManager
				.findDataResourceEntriesOfOperator(opId);

		// ��������Ȩ����
		PermissionChecker checker = new PermissionChecker();
		checker.addPermission(resourceList);
		request.setAttribute("resourceRoot", this.resourceTreeBuilder
				.buildTreeNode(checker));

		// ���ҵ��Ȩ����Ŀ
		List dataResourceEntryList = getEntryByACL(dataResourceACLList);
		request.setAttribute("entrys", dataResourceEntryList);
		return mapping.findForward("list");
	}

	/**
	 * ��ҵ��Ȩ�޹����л��ҵ��Ȩ����Ŀ��Ϣ
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
	 * ��ѯ
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
