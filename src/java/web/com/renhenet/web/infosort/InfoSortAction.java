package com.renhenet.web.infosort;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.po.InfoSort;
import com.renhenet.po.InfoSortDTO;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.InfoSortForm;

public class InfoSortAction extends DispatchActions {
	private static InfoSortService service = (InfoSortService) ServiceLocator
			.getService("infoSortService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return InfoSort.class;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		searchContext.setOrderByField("seq");
		searchContext.setOrderBy("asc");

		searchContext.addOption(new SearchOption("parentId", 0,
				SearchOption.Option.eqaul));

		return searchContext;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		context.put("parentId", parentId);

		if ("list".equals(context.getParameter("insert"))) {
			InfoSortForm form = (InfoSortForm) context.getForm();

			List<InfoSort> infoSortList = service
					.getInfoSortByParentId(parentId);
			for (int i = 0; i < infoSortList.size(); i++) {
				InfoSort infoSort = infoSortList.get(i);
				for (int j = 0; j < form.getListseq().length; j++) {
					if (form.getListseq()[j] == i + 1) {
						infoSort.setSeq(j + 1 + "");
						service.updateObject(infoSort);
					}
				}
			}
			return "/infosort/actions.html?method=list&parentId="
					+ VMUtils.encrypt(form.getParentId());
		}
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			InfoSortForm form = (InfoSortForm) context.getForm();
			if (form.getParentId() > 0) {
				super.insertProcess(context);
				return "/infosort/actions.html?method=insert&parentId="
						+ VMUtils.encrypt(form.getParentId());
			}
		}
		List<InfoSort> infoSortList = service.getInfoSortByParentId(parentId);
		context.put("infoSortList", infoSortList);

		List<InfoSortDTO> infoSortDtoList = service
				.getInfoSortNameByInfoSortId(parentId);
		context.put("infoSortDtoList", infoSortDtoList);

		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		context.put("parentId", parentId);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			InfoSortForm form = (InfoSortForm) context.getForm();
			if (form.getParentId() > 0) {
				super.updateProcess(context);
				return "/infosort/actions.html?method=update&parentId="
						+ VMUtils.encrypt(form.getParentId());
			}
		}

		List<InfoSort> infoSortList = service.getInfoSortByParentId(parentId);
		context.put("infoSortList", infoSortList);
		
		int sid = context.getSIntParameter("sid");
		List<InfoSortDTO> infoSortDtoList = service
				.getInfoSortNameByInfoSortId(sid);
		context.put("infoSortDtoList", infoSortDtoList);

		return super.updateProcess(context);
	}

	public String saveList(WebContext context) throws ServletException {

		// List<InfoSort> infoSortList =
		// service.getInfoSortByParentId(parentId);
		// context.put("infoSortList", infoSortList);
		return "";
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		if (parentId > 0) {
			super.deleteProcess(context);

			return "/infosort/actions.html?method=update&parentId="
					+ VMUtils.encrypt(parentId);
		}
		return super.deleteProcess(context);
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "infosort";
	}
}
