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
 * 权限数据维护类
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

	// 权限信息维护类
	private ResourceManager resourceManager;

	// 权限缓存
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
	 * 默认方法
	 */
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return list(mapping, form, request, response);
	}

	/**
	 * 显示菜单信息
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
	 * 菜单列表
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
	 * 新增菜单项
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
					// 新增菜单
					menu = resourceManager.addResource(menu, menuForm
							.getParentResource(), appRequest);
				} catch (Exception e) {
					// 记录新增功能权限菜单失败日志
					logger.log(request, actionCode, appRequest.getAppId() + "",
							ActionDefinition.ACTION_FAIL);
					logger.error("Excute action " + actionCode
							+ " failed,action sequence =" + menu.getTitle(), e);
					throw e;
				}
				// 操作日志
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
	 * 删除菜单项
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
			// 将权限信息从系统中删除
			resourceManager.deleteResource(resource, resourceForm
					.getAppRequest(request, actionCode));
		} catch (Exception e) {
			// 记录功能权限删除失败日志
			logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + code, e);
			throw e;
		}
		// 操作日志
		logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
		// 将权限信息从内存中删除
		resourceRepository.removeResource(resource.getResourceId());

		// 判断跳转目标，如果有父节点则跳转到父节点处，如果没有则跳转到list页面
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
	 * 编辑菜单项
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
		// 更新权限信息
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
					// 记录更新功能权限失败日志
					logger.log(request, actionCode, appRequest.getAppId() + "",
							ActionDefinition.ACTION_FAIL);
					logger.error("Excute action " + actionCode
							+ " failed,action sequence ="
							+ appRequest.getAppId() + "", e);
					throw e;
				}
				// 操作日志
				logger.log(request, actionCode, appRequest.getAppId() + "",
						ActionDefinition.ACTION_SUCCESS);
				// 更新内存中的权限信息
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
	 * 新增按钮权限项
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
				// 新增按钮信息
				button = resourceManager.addResource(button, menuForm
						.getParentResource(), appRequest);
			} catch (Exception e) {
				// 记录新增功能权限按钮失败日志
				logger.log(request, actionCode, appRequest.getAppId() + "",
						ActionDefinition.ACTION_FAIL);
				logger.error("Excute action " + actionCode
						+ " failed,action sequence =" + appRequest.getAppId()
						+ "", e);
				throw e;
			}
			// 操作日志
			logger.log(request, actionCode, appRequest.getAppId() + "",
					ActionDefinition.ACTION_SUCCESS);
			// 在内存中添加新增按钮信息
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
	 * 对当前权限的子菜单进行排序
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

		// 更新列表
		List<ResourceDTO> updateRet = new ArrayList<ResourceDTO>();

		Enumeration paramenum = request.getParameterNames();

		while (paramenum.hasMoreElements()) {
			String paramName = (String) paramenum.nextElement();
			if (paramName.startsWith("orderMenu")) {
				String params = paramName.substring("orderMenu[".length());

				int splitLen = "orderMenu[".length();
				int len = params.indexOf("]");

				// 权限标识
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
						// 这里仅仅只是将页面输入的序号填入到对象中。
						// 但这些值可能都是胡乱填写的，我们必须保证他的顺序，所以需要进行一次重新排序。
						element.setOrder(order);
						updateRet.add(element);
						break;
					}
				}
			}
		}

		// 进行一次排序按order大小和resourceId前后排序
		Comparator rc = new ComperToResource();
		Collections.sort(updateRet, rc);
		// 重新设置order值,无论页面输入的是什么都将序号重新设置成正常的
		setOrder(updateRet);
		// 操作编码-功能权限菜单排序
		String actionCode = ActionDefinition.SYS_ORDER_RESOURCE;

		try {
			// 更新数据库
			if (updateRet.size() > 0) {
				this.resourceManager.updateResources(updateRet);
				this.resourceRepository.updateResource(updateRet);
			}
		} catch (Exception e) {
			// 记录排序失败日志
			logger.log(request, actionCode, "order resource",
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode + " failed", e);
			throw e;
		}
		// 操作日志
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
	 * 设置序号
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
	 * 对Resource排序，按序号和id号排序
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
	 * 导出,以一级节点为根导出菜单树
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
			// 获得所有的Resource
			List allResources = this.resourceManager
					.findChildResourceByPatch(res.getResourcePath());
			HashMap<String,List> params = new HashMap<String,List>();
			params.put("resources", allResources);
			// 将Resource以XML方式输出
			templateEngine.execute(params, "resource.vm", writer);
			writer.flush();
		} catch (Exception e) {
			// 记录导出功能权限文件失败日志
			logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + code, e);
			throw e;
		}
		// 操作日志
		logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
		return null;
	}

	/**
	 * 导入
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
	// // 解析功能权限文件
	// XmlParser xmpParser = new XmlParser();
	// xmpParser.parse(input, handler);
	// } catch (SAXException e) {
	// // 文件内数据格式错误，解析出现问题
	// // 记录导入数据失败日志
	// logger.log(request, actionCode, e.getMessage(),
	// ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // 导入的功能权限数据格式不正确
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.data.error");
	// return list(mapping, form, request, response);
	// } finally {
	// input.close();
	// }
	// List resourceList = new ArrayList();
	// List resList = handler.getResourceList();
	// // 将去除列表中resource各值的空格
	// resListTrim(resList);
	// ResourceDTO resRoot = null;
	// try {
	// resRoot = this.findRoot(resList);
	// } catch (Exception e) {
	// // 根节点重复
	// logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // 导入的文件根节点数据重复
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.root.error");
	// return list(mapping, form, request, response);
	// }
	// if (null != resRoot) {
	// if (resourceManager.isExistRoot(resRoot.getTitle())) {
	// // 存在抛出已存在的错误信息
	// // 根节点已存在
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.root.exist");
	// return list(mapping, form, request, response);
	// } else {
	// // 不存在则需要判断权限信息是否有重复的，比如url，resourceKey
	// if (isExistInfo(resList)) {
	// // 抛出错误信息 url 和 key 已存在
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.url.key.exist");
	// return list(mapping, form, request, response);
	// } else {
	// // 如果url和key都不重复则执行输入操作
	// try {
	// resourceList = resourceManager
	// .addImportResource(resList);
	// } catch (Exception e) {
	// // 同级数据中有title内容重复的数据
	// // 记录导入数据失败日志
	// logger.log(request, actionCode, name,
	// ActionDefinition.ACTION_FAIL);
	// logger.error("Excute action " + actionCode
	// + " failed,action sequence =" + name, e);
	// // 同级title数据重复
	// ActionMessagesHelper.saveRequestMessage(request,
	// "errors.resource.import.title.error");
	// return list(mapping, form, request, response);
	// }
	// // 向内存中加载新数据
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
	// // 操作日志
	// logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
	// resourceForm.setRefresh(true);
	// return list(mapping, form, request, response);
	// }
	/**
	 * 判断url和key是否有重复信息 key是从内存中以有数据中判断的，key不判断"/"的情况
	 * url是从数据库中判断的，url不判断null，""，"/"的情况
	 * 
	 * @param resList
	 * @return
	 */
	// private boolean isExistInfo(List resList) {
	// boolean isExist = false;
	//
	// // 判断文件中的url是否有重复
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
	// // 判断url和key在系统中是否有重复
	// for (Iterator iter = resList.iterator(); iter.hasNext();) {
	// Resource resource = (Resource) iter.next();
	// String url = resource.getUrl().trim();
	// String key = resource.getResourceKey().trim();
	// // 判断key是否存在
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
	 * 获得根节点 如果根节点唯一则说明导入的数据正确 如果根节点没有或多个则说明导入的数据错误
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
	 * 去除resList中各值的空格
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
	 * 跳转到文件上传页面
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
	 * 返回
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
		// 设置跳转的目标
		if (null != resource && null != resource.getResourceId()) {
			// resource存在说明是编辑
			resourceForm.setResource(resource);
			return view(mapping, resourceForm, request, response);
		} else if (null != parent && null != parent.getResourceId()) {
			// resource不存在parent存在说明是新增
			return getRedirectForwardAction("resource.do?act=view&resource.resourceId="
					+ parent.getResourceId());
		}
		// resource不存在且parent也不存在说明是新增根的
		return list(mapping, form, request, response);
	}
}
