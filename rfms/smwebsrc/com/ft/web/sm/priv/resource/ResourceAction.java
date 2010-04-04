package com.ft.web.sm.priv.resource;

import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.ResourceRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.commons.template.VelocityTemplateEngine;
import com.ft.struts.ActionMessagesHelper;

/**
 * Ȩ������ά����
 * 
 * @spring.bean id="resourceAction"
 * 
 * @struts.action path="/resource" name="resourceForm" scope="request"
 *                input="sm.resource.main" parameter="act" validate="false"
 * 
 * @struts.action-forward name="resourceView" path="sm.resource.view"
 * 
 * @struts.action-forward name="menuAdd" path="sm.resource.menu.add"
 * 
 * @struts.action-forward name="resourceEdit" path="sm.resource.edit"
 * 
 * @struts.action-forward name="buttonAdd" path="sm.resource.button.add"
 * 
 * @struts.action-forward name="import" path="sm.resource.import"
 * 
 * @struts.tiles name="sm.resource.main" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/resource/listMenu.jsp"
 * 
 * @version 1.0
 */
public class ResourceAction extends BaseAction {

	private static Log logger = LogFactory.getLog(ResourceAction.class);

	// Ȩ����Ϣά����
	private ResourceManager resourceManager;

	// Ȩ�޻���
	private ResourceRepository resourceRepository;

	private VelocityTemplateEngine templateEngine;

	/**
	 * @spring.property ref="templateEngine"
	 * 
	 */
	public void setTemplateEngine(VelocityTemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	/**
	 * @spring.property ref = "resourceRepository"
	 * 
	 * @param resourceRepository
	 */
	public void setResourceRepository(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	/**
	 * @spring.property ref="resourceManager"
	 */
	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}

	/**
	 * Ĭ�Ϸ���
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return list(mapping, form, request, response);
	}

	/**
	 * ��ʾ�˵���Ϣ
	 * 
	 * @struts.tiles name="sm.resource.view" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/resource/viewMenu.jsp"
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
		return mapping.findForward("resourceView");
	}

	/**
	 * �˵��б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResourceForm resForm = (ResourceForm) form;
		ResourceDTO res = resForm.getResource();
		long resId = res.getResourceId().longValue();
		if (resId == 0) {
			res = null;
		}
		List allResources = resourceRepository.iterator();
		List<ResourceDTO> resources = new ArrayList<ResourceDTO>();
		for (Iterator iter = allResources.iterator(); iter.hasNext();) {
			ResourceDTO resource = (ResourceDTO) iter.next();
			if (res == null && resource.getLevel() == 1) {
				resources.add(resource);
			} else if (resource.getParentId().longValue() == resId
					&& resource.getResourceId().longValue() != resource
							.getParentId().longValue()) {
				resources.add(resource);
			}
		}
		request.setAttribute("resources", resources);
		request.setAttribute("parent", res);
		return mapping.getInputForward();
	}

	/**
	 * �����˵���
	 * 
	 * @struts.tiles name="sm.resource.menu.add" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/resource/addMenu.jsp"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ResourceForm menuForm = (ResourceForm) form;
		if (request.getMethod().equals("GET")) {
			return mapping.findForward("menuAdd");
		}
		String flag = XmlTreeNode.ADD_NODE_FLAG;
		String actionCode = ActionDefinition.SYS_ADD_RESOURCE;
		ResourceDTO menu = menuForm.getResource();
		String url = menu.getUrl();
		if (null == url) {
			url = "";
		}
		menu.setUrl(url.trim());
		if (!resourceManager
				.isExistResource(menu, menuForm.getParentResource())) {
			if (!resourceManager.isExistMenuUrl(url.trim(), null)) {

				AppRequest appRequest = menuForm.getAppRequest(request,
						actionCode);
				// AppDTO app = appService.saveApp(appRequest);
				// appRequest.setAppId(app.getApp().getAppId());
				//				
				try {
					// �����˵�
					menu = resourceManager.addResource(menu, menuForm
							.getParentResource(), appRequest);
				} catch (Exception e) {
					// ��¼��������Ȩ�޲˵�ʧ����־
					logger.log(request, actionCode, appRequest.getAppId() + "",
							ActionDefinition.ACTION_FAIL);
					logger.error("Excute action " + actionCode
							+ " failed,action sequence =" + menu.getTitle(), e);
					throw e;
				}
				// ������־
				logger.log(request, actionCode, appRequest.getAppId() + "",
						ActionDefinition.ACTION_SUCCESS);
				resourceRepository.addResource(menu);
			} else {
				ActionMessagesHelper.saveRequestMessage(request,
						"errors.resource.menu.url.exist");
				return mapping.findForward("menuAdd");
			}
		} else {
			ActionMessagesHelper.saveRequestMessage(request,
					"errors.resource.menu.title.exist");
			return mapping.findForward("menuAdd");
		}
		String returnUrl = "";
		// if(menu.getParentId().equals(menu.getResourceId())){
		// returnUrl = "/sm/resource.do?flag=" + flag ;
		// if(flag == XmlTreeNode.ADD_NODE_FLAG){
		// returnUrl = returnUrl + "&addedId=" + menu.getResourceId() ;
		// }
		// }else{
		returnUrl = "resource.do?act=view&resource.resourceId="
				+ menu.getResourceId() + "&flag=" + flag;
		if (flag == XmlTreeNode.ADD_NODE_FLAG) {
			returnUrl = returnUrl + "&addedId=" + menu.getResourceId();
			// }
		}
		return getRedirectForwardAction(returnUrl);
	}

	/**
	 * ɾ���˵���
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

		String actionCode = ActionDefinition.SYS_DELETE_RESOURCE;
		ResourceForm resourceForm = (ResourceForm) form;
		ResourceDTO resource = resourceForm.getResource();
		String code = resource.getTitle();
		String flag = XmlTreeNode.DELETE_NODE_FLAG;
		try {
			// ��Ȩ����Ϣ��ϵͳ��ɾ��
			resourceManager.deleteResource(resource, resourceForm
					.getAppRequest(request, actionCode));
		} catch (Exception e) {
			// ��¼����Ȩ��ɾ��ʧ����־
			logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + code, e);
			throw e;
		}
		// ������־
		logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
		// ��Ȩ����Ϣ���ڴ���ɾ��
		resourceRepository.removeResource(resource.getResourceId());

		// �ж���תĿ�꣬����и��ڵ�����ת�����ڵ㴦�����û������ת��listҳ��
		if (null != resource.getParentId()
				&& resource.getResourceId().longValue() != resource
						.getParentId().longValue()) {
			return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
					+ resource.getParentId().toString()
					+ "&flag="
					+ flag
					+ "&deletedId=" + resource.getResourceId().toString());
		}

		return getRedirectForwardAction("resource.do?flag=" + flag
				+ "&deletedId=" + resource.getResourceId().toString());
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
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("resourceEdit");
	}

	/**
	 * �༭�˵���
	 * 
	 * @struts.tiles name="sm.resource.edit" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/resource/editMenu.jsp"
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

		ResourceForm resourceForm = (ResourceForm) form;
		if (request.getMethod() == "GET") {
			return mapping.findForward("resourceEdit");
		}
		String flag = XmlTreeNode.UPDATE_NODE_FLAG;
		String actionCode = ActionDefinition.SYS_UPDATE_RESOURCE;
		// boolean visible = resourceForm.isVisible();
		ResourceDTO resource = resourceForm.getResource();
		// resource.setVisible(visible);
		String url = resource.getUrl();
		if (null == url) {
			url = "";
		}
		resource.setUrl(url.trim());
		// ����Ȩ����Ϣ
		if (!resourceManager.isExistResource(resource, new ResourceDTO(resource
				.getParent()))) {
			if (!resourceManager.isExistMenuUrl(url.trim(), resource
					.getResourceId())) {
				AppRequest appRequest = resourceForm.getAppRequest(request,
						actionCode);
				// AppDTO app = appService.saveApp(appRequest);
				// appRequest.setAppId(app.getApp().getAppId());

				try {
					resourceManager.updateResource(resource, appRequest);
				} catch (Exception e) {
					// ��¼���¹���Ȩ��ʧ����־
					logger.log(request, actionCode, appRequest.getAppId() + "",
							ActionDefinition.ACTION_FAIL);
					logger.error("Excute action " + actionCode
							+ " failed,action sequence ="
							+ appRequest.getAppId() + "", e);
					throw e;
				}
				// ������־
				logger.log(request, actionCode, appRequest.getAppId() + "",
						ActionDefinition.ACTION_SUCCESS);
				// �����ڴ��е�Ȩ����Ϣ
				resourceRepository.updateResource(resource);
			} else {
				resourceForm.setResource(resource);
				ActionMessagesHelper.saveRequestMessage(request,
						"errors.resource.menu.url.exist");
				return mapping.findForward("resourceEdit");
			}
		} else {
			resourceForm.setResource(resource);
			if (resource.getType() == ResourceDTO.BUTTON_TYPE) {
				ActionMessagesHelper.saveRequestMessage(request,
						"errors.resource.button.title.exist");
			} else {
				ActionMessagesHelper.saveRequestMessage(request,
						"errors.resource.menu.title.exist");
			}
			return mapping.findForward("resourceEdit");
		}

		// return mapping.findForward("resourceView");

		return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
				+ resource.getResourceId() + "&flag=" + flag);
	}

	/**
	 * ������ťȨ����
	 * 
	 * @struts.tiles name="sm.resource.button.add" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/resource/addButton.jsp"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addButton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResourceForm menuForm = (ResourceForm) form;
		if (request.getMethod().equals("GET")) {
			return mapping.findForward("buttonAdd");
		}

		String actionCode = ActionDefinition.SYS_ADD_RESOURCE;
		ResourceDTO button = menuForm.getResource();

		if (!resourceManager.isExistResource(button, menuForm
				.getParentResource())) {

			AppRequest appRequest = menuForm.getAppRequest(request, actionCode);
			// AppDTO app = appService.saveApp(appRequest);
			// appRequest.setAppId(app.getApp().getAppId());
			try {
				// ������ť��Ϣ
				button = resourceManager.addResource(button, menuForm
						.getParentResource(), appRequest);
			} catch (Exception e) {
				// ��¼��������Ȩ�ް�ťʧ����־
				logger.log(request, actionCode, appRequest.getAppId() + "",
						ActionDefinition.ACTION_FAIL);
				logger.error("Excute action " + actionCode
						+ " failed,action sequence =" + appRequest.getAppId()
						+ "", e);
				throw e;
			}
			// ������־
			logger.log(request, actionCode, appRequest.getAppId() + "",
					ActionDefinition.ACTION_SUCCESS);
			// ���ڴ������������ť��Ϣ
			resourceRepository.addResource(button);
		} else {
			ActionMessagesHelper.saveRequestMessage(request,
					"errors.resource.button.title.exist");
			return mapping.findForward("buttonAdd");
		}

		return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
				+ menuForm.getParentResource().getResourceId());
	}

	/**
	 * �Ե�ǰȨ�޵��Ӳ˵���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ResourceForm resourceForm = (ResourceForm) form;
		ResourceDTO resource = resourceForm.getResource();
		List children = new ArrayList();
		if (null != resource && null != resource.getResourceId()
				&& resource.getResourceId().longValue() != 0) {
			children = this.resourceManager.findChildren(resource
					.getResourceId());
		} else {
			children = this.resourceManager.findAllResources();
		}

		// �����б�
		List<ResourceDTO> updateRet = new ArrayList<ResourceDTO>();

		Enumeration paramenum = request.getParameterNames();

		while (paramenum.hasMoreElements()) {
			String paramName = (String) paramenum.nextElement();
			if (paramName.startsWith("orderMenu")) {
				String params = paramName.substring("orderMenu[".length());

				int splitLen = "orderMenu[".length();
				int len = params.indexOf("]");

				// Ȩ�ޱ�ʶ
				String label = paramName.substring(splitLen, splitLen + len);

				for (Iterator iter = children.iterator(); iter.hasNext();) {
					ResourceDTO element = (ResourceDTO) iter.next();
					if (label.equals(String.valueOf(element.getResourceId()))) {
						String temp = request.getParameter(paramName);
						int order = 0;
						if (null == temp) {
							temp = "";
						}
						if (!temp.equals("")) {
							order = Integer.parseInt(temp);
						}
						// �������ֻ�ǽ�ҳ�������������뵽�����С�
						// ����Щֵ���ܶ��Ǻ�����д�ģ����Ǳ��뱣֤����˳��������Ҫ����һ����������
						element.setOrder(order);
						updateRet.add(element);
						break;
					}
				}
			}
		}

		// ����һ������order��С��resourceIdǰ������
		Comparator rc = new ComperToResource();
		Collections.sort(updateRet, rc);
		// ��������orderֵ,����ҳ���������ʲô��������������ó�������
		setOrder(updateRet);
		// ��������-����Ȩ�޲˵�����
		String actionCode = ActionDefinition.SYS_ORDER_RESOURCE;

		try {
			// �������ݿ�
			if (updateRet.size() > 0) {
				this.resourceManager.updateResources(updateRet);
				this.resourceRepository.updateResource(updateRet);
			}
		} catch (Exception e) {
			// ��¼����ʧ����־
			logger.log(request, actionCode, "order resource",
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode + " failed", e);
			throw e;
		}
		// ������־
		logger.log(request, actionCode, "order resource",
				ActionDefinition.ACTION_SUCCESS);

		if (null != resource.getResourceId()
				&& resource.getResourceId().longValue() != 0) {
			return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
					+ resource.getResourceId());
		}
		return getRedirectForwardAction("resource.do?act=list");
	}

	/**
	 * �������
	 * 
	 * @param resourceList
	 */
	private void setOrder(List resourceList) {
		if (null != resourceList && resourceList.size() > 0) {
			int i = 1;
			for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
				ResourceDTO resource = (ResourceDTO) iter.next();
				resource.setOrder(i);
				i++;
			}
		}
	}

	/**
	 * ��Resource���򣬰���ź�id������
	 * 
	 * @author <a href="mailto:zlkn2006@163.com">zhouliang</a>
	 * @version 1.0
	 */
	private class ComperToResource implements Comparator {

		public int compare(Object o1, Object o2) {
			ResourceDTO r1 = (ResourceDTO) o1;
			ResourceDTO r2 = (ResourceDTO) o2;
			if (r1.getOrder() > r2.getOrder()) {
				return 1;
			} else if (r1.getOrder() == r2.getOrder()) {
				if (r1.getResourceId().longValue() > r2.getResourceId()
						.longValue()) {
					return 1;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		}
	}

	/**
	 * ����,��һ���ڵ�Ϊ�������˵���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String actionCode = ActionDefinition.SYS_EXPORT_RESOURCE;

		ResourceForm resourceForm = (ResourceForm) form;
		ResourceDTO res = resourceForm.getResource();
		String code = res.getResourceKey();
		response.addHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode("menus.xml", "GBK"));
		// Tomcat 4.0 servlet 2.3
		response.setContentType("text/html;charset=GBK");
		// Tomcat 5.5 servlet 2.4
		// response.setCharacterEncoding("GBK");
		Writer writer = response.getWriter();

		try {
			// ������е�Resource
			List allResources = this.resourceManager
					.findChildResourceByPatch(res.getResourcePath());
			HashMap<String,List> params = new HashMap<String,List>();
			params.put("resources", allResources);
			// ��Resource��XML��ʽ���
			templateEngine.execute(params, "resource.vm", writer);
			writer.flush();
		} catch (Exception e) {
			// ��¼��������Ȩ���ļ�ʧ����־
			logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + code, e);
			throw e;
		}
		// ������־
		logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
		return null;
	}

	/**
	 * ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward importMenu(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	//
	// ResourceForm resourceForm = (ResourceForm) form;
	// FormFile resFile = resourceForm.getResFile();
	// String name = resFile.getFileName();
	// InputStream input = resFile.getInputStream();
	// String actionCode = ActionDefinition.SYS_IMPORT_RESOURCE;
	// ResourceParserHandler handler = new ResourceParserHandler();
	// try {
	// // ��������Ȩ���ļ�
	// XmlParser xmpParser = new XmlParser();
	// xmpParser.parse(input, handler);
	// } catch (SAXException e) {
	// // �ļ������ݸ�ʽ���󣬽�����������
	// // ��¼��������ʧ����־
	// logger.log(request, actionCode, e.getMessage(),
	// ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // ����Ĺ���Ȩ�����ݸ�ʽ����ȷ
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.data.error");
	// return list(mapping, form, request, response);
	// } finally {
	// input.close();
	// }
	// List resourceList = new ArrayList();
	// List resList = handler.getResourceList();
	// // ��ȥ���б���resource��ֵ�Ŀո�
	// resListTrim(resList);
	// ResourceDTO resRoot = null;
	// try {
	// resRoot = this.findRoot(resList);
	// } catch (Exception e) {
	// // ���ڵ��ظ�
	// logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // ������ļ����ڵ������ظ�
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.root.error");
	// return list(mapping, form, request, response);
	// }
	// if (null != resRoot) {
	// if (resourceManager.isExistRoot(resRoot.getTitle())) {
	// // �����׳��Ѵ��ڵĴ�����Ϣ
	// // ���ڵ��Ѵ���
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.root.exist");
	// return list(mapping, form, request, response);
	// } else {
	// // ����������Ҫ�ж�Ȩ����Ϣ�Ƿ����ظ��ģ�����url��resourceKey
	// if (isExistInfo(resList)) {
	// // �׳�������Ϣ url �� key �Ѵ���
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.url.key.exist");
	// return list(mapping, form, request, response);
	// } else {
	// // ���url��key�����ظ���ִ���������
	// try {
	// resourceList = resourceManager
	// .addImportResource(resList);
	// } catch (Exception e) {
	// // ͬ����������title�����ظ�������
	// // ��¼��������ʧ����־
	// logger.log(request, actionCode, name,
	// ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // ͬ��title�����ظ�
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.title.error");
	// return list(mapping, form, request, response);
	// }
	// // ���ڴ��м���������
	// for (Iterator iter = resourceList.iterator(); iter
	// .hasNext();) {
	// Resource res = (Resource) iter.next();
	// if (null != res.getResourceId()) {
	// resourceRepository.addResource(res);
	// }
	// }
	// }
	// }
	// }
	// // ������־
	// logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
	// resourceForm.setRefresh(true);
	// return list(mapping, form, request, response);
	// }
	/**
	 * �ж�url��key�Ƿ����ظ���Ϣ key�Ǵ��ڴ��������������жϵģ�key���ж�"/"�����
	 * url�Ǵ����ݿ����жϵģ�url���ж�null��""��"/"�����
	 * 
	 * @param resList
	 * @return
	 */
	// private boolean isExistInfo(List resList) {
	// boolean isExist = false;
	//
	// // �ж��ļ��е�url�Ƿ����ظ�
	// for (int i = 0; i < resList.size(); i++) {
	// Resource res = (Resource) resList.get(i);
	// String resUrl = res.getUrl();
	// if (null != resUrl && !resUrl.equals("") && !resUrl.equals("/")) {
	// for (int j = 0; j < resList.size(); j++) {
	// if (i != j) {
	// Resource temp = (Resource) resList.get(j);
	// if (resUrl.equals(temp.getUrl())) {
	// isExist = true;
	// return isExist;
	// }
	// }
	// }
	// }
	// }
	// // �ж�url��key��ϵͳ���Ƿ����ظ�
	// for (Iterator iter = resList.iterator(); iter.hasNext();) {
	// Resource resource = (Resource) iter.next();
	// String url = resource.getUrl().trim();
	// String key = resource.getResourceKey().trim();
	// // �ж�key�Ƿ����
	// if (!key.equals("/")) {
	// if (null != resourceRepository.getResource(key)) {
	// isExist = true;
	// break;
	// }
	// }
	// if (null != url && !url.equals("") && !url.equals("/")) {
	// if (resourceManager.isExistMenuUrl(url, null)) {
	// isExist = true;
	// break;
	// }
	// }
	// }
	// return isExist;
	// }
	/**
	 * ��ø��ڵ� ������ڵ�Ψһ��˵�������������ȷ ������ڵ�û�л�����˵����������ݴ���
	 * 
	 * @param resList
	 * @return
	 * @throws Exception
	 */
	// private Resource findRoot(List resList) throws Exception {
	//
	// List rootList = new ArrayList();
	// if (null != resList && resList.size() > 0) {
	// for (Iterator iter = resList.iterator(); iter.hasNext();) {
	// Resource resource = (Resource) iter.next();
	// String path = resource.getResourcePath();
	// String[] ids = path.split(Resource.PATH_SEPARATOR);
	// if (ids.length == 1) {
	// rootList.add(resource);
	// }
	// }
	// }
	// if (rootList.size() == 1) {
	// return (Resource) rootList.iterator().next();
	// } else {
	// throw new Exception();
	// }
	// }
	/**
	 * ȥ��resList�и�ֵ�Ŀո�
	 * 
	 * @param resList
	 */
	// private void resListTrim(List resList) {
	// if (null != resList && resList.size() > 0) {
	// for (Iterator iter = resList.iterator(); iter.hasNext();) {
	// Resource resource = (Resource) iter.next();
	// resource.setResourceKey(resource.getResourceKey().trim());
	// resource.setResourcePath(resource.getResourcePath().trim());
	// resource.setUrl(resource.getUrl().trim());
	// resource.setTitle(resource.getTitle().trim());
	// }
	// }
	// }
	/**
	 * ��ת���ļ��ϴ�ҳ��
	 * 
	 * @struts.tiles name="sm.resource.import" extends="main.layout"
	 * @struts.tiles-put name="body" value="/sm/resource/importResource.jsp"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("import");
	}

	/**
	 * ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResourceForm resourceForm = (ResourceForm) form;
		ResourceDTO resource = resourceForm.getResource();
		ResourceDTO parent = resourceForm.getParentResource();
		// ������ת��Ŀ��
		if (null != resource && null != resource.getResourceId()) {
			// resource����˵���Ǳ༭
			resourceForm.setResource(resource);
			return view(mapping, resourceForm, request, response);
		} else if (null != parent && null != parent.getResourceId()) {
			// resource������parent����˵��������
			return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
					+ parent.getResourceId());
		}
		// resource��������parentҲ������˵������������
		return list(mapping, form, request, response);
	}
}
