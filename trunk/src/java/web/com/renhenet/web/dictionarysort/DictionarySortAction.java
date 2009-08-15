package com.renhenet.web.dictionarysort;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.DictionarySortService;
import com.renhenet.po.DictionarySort;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.DictionarySortForm;

public class DictionarySortAction extends DispatchActions {
	private static DictionarySortService service = (DictionarySortService) ServiceLocator
			.getService("dictionarySortService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return DictionarySort.class;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		searchContext.addOption(new SearchOption("parentId", 0,
				SearchOption.Option.eqaul));

		return searchContext;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		context.put("parentId", parentId);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			DictionarySortForm form = (DictionarySortForm) context.getForm();
			if (form.getParentId() > 0) {
				super.insertProcess(context);
				return "/dictionarySort/actions.html?method=insert&parentId="
						+ VMUtils.encrypt(form.getParentId());
			}
		}
		List<DictionarySort> infoSortList = service
				.getDictionarySortByParentId(parentId);
		context.put("infoSortList", infoSortList);
		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		context.put("parentId", parentId);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			DictionarySortForm form = (DictionarySortForm) context.getForm();
			if (form.getParentId() > 0) {
				super.updateProcess(context);
				return "/dictionarySort/actions.html?method=update&parentId="
						+ VMUtils.encrypt(form.getParentId());
			}
		}

		List<DictionarySort> infoSortList = service
				.getDictionarySortByParentId(parentId);
		context.put("infoSortList", infoSortList);

		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int parentId = context.getSIntParameter("parentId");
		if (parentId > 0) {
			super.deleteProcess(context);
			return "/dictionarySort/actions.html?method=update&parentId="
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
		return "dictionarysort";
	}
}
