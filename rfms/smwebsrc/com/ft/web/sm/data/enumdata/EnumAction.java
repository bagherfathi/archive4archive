package com.ft.web.sm.data.enumdata;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.EnumManager;
import com.ft.common.enumdata.EnumRepository;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.EnumEntry;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * Enum数据维护页面控制类
 * 
 * @struts.action path="/enum" name="enumForm" scope="request" validate="false"
 *                parameter="act" input="sm.enum.edit"
 * 
 * @struts.action-forward name="edit" path="sm.enum.edit"
 * 
 * @struts.tiles name="sm.enum.edit" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/enum/editEnum.jsp"
 * 
 * @spring.bean id="enumAction"
 * 
 * @version 1.0
 */
public class EnumAction extends BaseAction {
	private static Log logger = LogFactory.getLog(EnumAction.class);

	private EnumManager enumManager;

	private EnumRepository enumRepository;

	/**
	 * @spring.property ref="enumRepository"
	 * @param enumRepository
	 */
	public void setEnumRepository(EnumRepository enumRepository) {
		this.enumRepository = enumRepository;
	}

	/**
	 * @spring.property ref="enumManager"
	 * 
	 */
	public void setEnumManager(EnumManager enumManager) {
		this.enumManager = enumManager;
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
	 * 跳转到创建Enum数据页面
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
		return mapping.findForward("edit");
	}

	/**
	 * 保存枚举类型数据，跳转到察看页面
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
		EnumForm enumForm = (EnumForm) form;
		Enumeration paramenum = request.getParameterNames();
		EnumDTO enumData = enumForm.getEnumData();
		EnumCategoryDTO category = enumForm.getEnumCategory();

		// 校验系统数据代码和名称是否在类别中重复
		if (!validateEnum(enumData, category, request)) {
			return mapping.findForward("edit");
		}

		Long enumDataId = null;

		String actionCode = ActionDefinition.SYS_UPDATE_ENUM;
		if (enumData.getEnumId() == null
				|| enumData.getEnumId().longValue() <= 0) {
			actionCode = ActionDefinition.SYS_ADD_ENUM;
		}

		// //保存受理记录
		AppRequest appRequest = enumForm.getAppRequest(request, actionCode);

		try {
			// 创建系统数据
			if (enumData.getEnumId() == null
					|| enumData.getEnumId().longValue() <= 0) {
				enumData.setCategoryId(category.getCategoryId());
				this.createEntries(paramenum, enumData, request);
				// 保存系统数据
				enumData.setCreateDate(new Date());
				enumData.setOperatorId(enumForm.getCurrentUser()
						.getOperatorId().longValue());
				enumData
						.setOrgId(enumForm.getCurrentUser().getOrg().getOrgId());
				enumData.setLoginOrgId(OperatorSessionHelper.getLoginOrg(
						request).getOrgId().longValue());
				enumData.setAppId(appRequest.getAppId());
				enumDataId = this.enumManager.saveEnumData(enumData);
				// 更新系统数据
			} else {
				this.createEntries(paramenum, enumData, request);
				this.enumManager.updateEnumData(enumData, appRequest);
				enumDataId = enumData.getEnumId();
			}

			enumData.setEnumId(enumDataId);
			// 更新系统数据仓库
			// 重新获取，对条目进行排序
			enumData = this.enumManager.findEnumDataById(enumData.getEnumId());
			enumRepository.addEnumData(category, enumData);
		} catch (Exception ex) {
			logger.log(request, actionCode, "" + appRequest.getAppId(),
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + appRequest.getAppId(), ex);
			throw ex;
		}

		logger.log(request, actionCode, "" + appRequest.getAppId(),
				ActionDefinition.ACTION_SUCCESS);

		return this
				.getRedirectForwardAction("enumCategory.do?act=edit&categoryId="
						+ category.getCategoryId());
	}

	/**
	 * 跳转到Enum数据编辑页面
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
		return mapping.findForward("edit");
	}

	/**
	 * 禁用系统数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward disable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EnumForm enumForm = (EnumForm) form;
		EnumDTO enumData = enumForm.getEnumData();
		EnumCategoryDTO category = enumForm.getEnumCategory();
		String flag = XmlTreeNode.UPDATE_NODE_FLAG;

		String actionCode = ActionDefinition.SYS_DISABLE_ENUM;
		// //保存受理记录
		AppRequest appRequest = enumForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
		try {
			this.enumManager.disableEnumData(enumData.getEnumId(), appRequest);

			// 更新缓存中Enum数据
			this.enumRepository.disable(category.getCategoryCode(), enumData
					.getEnumCode());
		} catch (Exception ex) {
			logger.log(request, actionCode, "" + appRequest.getAppId(),
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + appRequest.getAppId(), ex);
			throw ex;
		}

		logger.log(request, actionCode, "" + appRequest.getAppId(),
				ActionDefinition.ACTION_SUCCESS);

		return this.getRedirectForwardAction("enum.do?act=edit&enumId="
				+ enumData.getEnumId() + "&flag=" + flag);
	}

	/**
	 * 启用系统数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EnumForm enumForm = (EnumForm) form;
		EnumDTO enumData = enumForm.getEnumData();
		EnumCategoryDTO category = enumForm.getEnumCategory();
		String flag = XmlTreeNode.UPDATE_NODE_FLAG;

		String actionCode = ActionDefinition.SYS_DISABLE_ENUM;
		// //保存受理记录
		AppRequest appRequest = enumForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
		try {
			this.enumManager.enableEnumData(enumData.getEnumId(), appRequest);

			// 更新缓存中Enum数据
			this.enumRepository.enable(category.getCategoryCode(), enumData
					.getEnumCode());
		} catch (Exception ex) {
			logger.log(request, actionCode, "" + appRequest.getAppId(),
					ActionDefinition.ACTION_FAIL);
			logger.error("Excute action " + actionCode
					+ " failed,action sequence =" + appRequest.getAppId(), ex);
			throw ex;
		}

		logger.log(request, actionCode, "" + appRequest.getAppId(),
				ActionDefinition.ACTION_SUCCESS);

		return this.getRedirectForwardAction("enum.do?act=edit&enumId="
				+ enumData.getEnumId() + "&flag=" + flag);
	}

	/**
	 * 分析页面请求中的参数，创建系统数据条目
	 * 
	 * @param paramenum
	 * @param enumData
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void createEntries(Enumeration paramenum, EnumDTO enumData,
			HttpServletRequest request) {
		while (paramenum.hasMoreElements()) {
			String paramName = (String) paramenum.nextElement();
			if (paramName.startsWith("entryNode")) {
				String params = paramName.substring("entryNode[".length());

				int splitLen = "entryNode[".length();
				int len = params.indexOf("]");

				String temp = paramName.substring(splitLen, splitLen + len);
				String order = request.getParameter(paramName);
				EnumEntry enumEntry = new EnumEntry();

				String[] values = temp.split("#");
				enumEntry.setEnumEntryLabel(values[0]);
				enumEntry.setEnumEntryValue(values[1]);
				enumEntry.setEntryOrder(new Long(order).longValue());
				if (values.length > 2) {
					enumEntry.setEntryLinkValue(values[2]);
				}
				enumData.getEntries().add(enumEntry);
			}
		}
	}

	/**
	 * 校验系统数据名称和代码是否在系统类别中重复
	 * 
	 * @param enumData
	 * @param category
	 * @param request
	 * @return
	 */
	private boolean validateEnum(EnumDTO enumData, EnumCategoryDTO category,
			HttpServletRequest request) throws Exception {
		List enumList = this.enumManager.findEnumDatasByCategory(category
				.getCategoryId());

		for (Iterator iter = enumList.iterator(); iter.hasNext();) {
			EnumDTO element = (EnumDTO) iter.next();
			if (element.getEnumCode().equals(enumData.getEnumCode())) {
				if (enumData.getEnumId() == null
						|| enumData.getEnumId().longValue() != element
								.getEnumId().longValue()) {
					ActionMessagesHelper.saveRequestMessage(request,
							"errors.enumcode.exist", new Object[] {
									enumData.getEnumCode(),
									category.getCategoryName() });
					return false;
				}
			}

			if (element.getEnumName().equals(enumData.getEnumName())) {
				if (enumData.getEnumId() == null
						|| enumData.getEnumId().longValue() != element
								.getEnumId().longValue()) {
					ActionMessagesHelper.saveRequestMessage(request,
							"errors.enumname.exist", new Object[] {
									enumData.getEnumName(),
									category.getCategoryName() });
					return false;
				}
			}
		}

		return true;
	}
}
