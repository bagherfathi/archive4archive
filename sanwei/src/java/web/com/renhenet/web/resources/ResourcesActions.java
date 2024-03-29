package com.renhenet.web.resources;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.ResourcesService;
import com.renhenet.po.Resources;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.form.ResourcesForm;
@SuppressWarnings("unchecked")
public class ResourcesActions extends DispatchActions {
	private static ResourcesService service = (ResourcesService) ServiceLocator
			.getService("resourcesService");

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	protected Class getActionClass() {
		return Resources.class;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		String type = context.getParameter("type");
		if (type == null) {
			type = context.getTempClientValue("type");
		}
		context.put("type", type);

		if (!StringUtils.isEmpty(type)) {
			searchContext.addOption(new SearchOption("type", type,
					SearchOption.Option.eqaul));
		}

		ResourcesForm form = new ResourcesForm();
		form.setType(type);
		context.put("bizObj", form);

		return searchContext;
	}

	public String insertProcess(WebContext context) throws ServletException {
		String type = context.getParameter("type");
		super.insertProcess(context);
		return "/resources/actions.html?method=list&type=" + type;
	}

	public String updateProcess(WebContext context) throws ServletException {
		String type = context.getParameter("type");
		context.put("type", type);
		return super.updateProcess(context);

	}

	public String deleteProcess(WebContext context) throws ServletException {
		String type = context.getParameter("type");
		context.put("type", type);
		super.deleteProcess(context);

		return "/resources/actions.html?method=list&type=" + type;

	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "";
	}

}
