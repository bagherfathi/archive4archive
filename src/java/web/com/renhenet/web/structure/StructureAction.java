package com.renhenet.web.structure;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.DictionarySortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.DictionarySort;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.StructureForm;

public class StructureAction extends DispatchActions {
	private static StructureService service = (StructureService) ServiceLocator
			.getService("structureService");

	private static DictionarySortService dictionarySortService = (DictionarySortService) ServiceLocator
			.getService("dictionarySortService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return Structure.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		// 根据infoSortId得到所有表结构
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);
		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = service.getStructureByInfoSortId(
				infoSortId, status);
		context.put("structureList", structureList);

		List<DictionarySort> dictionarySortList = dictionarySortService
				.getDictionarySortByParentId(0);
		context.put("dictionarySortList", dictionarySortList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			StructureForm form = (StructureForm) context.getForm();

			int serialNumber = service.getStructureByinfoSortId(form
					.getInfoSortId());

			String strSerialNumber = "a" + serialNumber;
			form.setSerialNumber(strSerialNumber);

			super.insertProcess(context);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId());
		}

		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		// 根据infoSortId得到所有表结构
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);
		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = service.getStructureByInfoSortId(
				infoSortId, status);
		context.put("structureList", structureList);

		List<DictionarySort> dictionarySortList = dictionarySortService
				.getDictionarySortByParentId(0);
		context.put("dictionarySortList", dictionarySortList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			StructureForm form = (StructureForm) context.getForm();
			super.updateProcess(context);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId());
		}

		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		int sid = context.getSIntParameter("sid");

		if (sid > 0) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, sid);
			structure.setIsDelete(1);
			service.updateObject(structure);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(infoSortId);
		}
		return super.deleteProcess(context);
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "file";
	}

}
