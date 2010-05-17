package com.renhenet.web.structure;

import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.DictionarySortService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.DictionarySort;
import com.renhenet.po.InfoSortDTO;
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

	private static InfoSortService infoSortService = (InfoSortService) ServiceLocator
			.getService("infoSortService");

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

		List<InfoSortDTO> infoSortDtoList = infoSortService
				.getInfoSortNameByInfoSortId(infoSortId);
		context.put("infoSortDtoList", infoSortDtoList);

		List<Structure> structureList = service
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);

		context.put("structureList", structureList);

		List<DictionarySort> dictionarySortList = dictionarySortService
				.getDictionarySortByParentId(0);
		context.put("dictionarySortList", dictionarySortList);

		if ("list".equals(context.getParameter("insert"))) {
			StructureForm form = (StructureForm) context.getForm();
			List<Structure> structureInList = service
					.getStructureByInfoSortIdAndInStatus(infoSortId, status);
			for (int i = 0; i < structureInList.size(); i++) {
				Structure structure = structureInList.get(i);
				if (status == 0) {
					for (int j = 0; j < form.getListseq().length; j++) {
						if (form.getListseq()[j] == i + 1) {
							structure.setTaxis(j + 1);
							service.updateObject(structure);
						}
					}
				} else if (status == 1) {
					for (int j = 0; j < form.getListseq().length; j++) {
						if (form.getListseq()[j] == i + 1) {
							structure.setTaxis2(j + 1);
							service.updateObject(structure);
						}
					}
				} else if (status == 2) {
					for (int j = 0; j < form.getListseq().length; j++) {
						if (form.getListseq()[j] == i + 1) {
							structure.setTaxis3(j + 1);
							service.updateObject(structure);
						}
					}
				}
			}
			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(infoSortId) + "&statuses="
					+ VMUtils.encrypt(status);
		}

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			StructureForm form = (StructureForm) context.getForm();
			if(!StringUtils.isEmpty(form.getSelectSerialNumber())){
				form.setSerialNumber(form.getSelectSerialNumber());
			}else if(!StringUtils.isEmpty(form.getSerialNumber())){
				form.setSerialNumber(form.getSerialNumber());
			}else{
				int serialNumber = service.getStructureByinfoSortId(form
						.getInfoSortId());
				if(serialNumber>=5 && serialNumber<=11){
					serialNumber=12;
				}
				String strSerialNumber = "a" + serialNumber;
				form.setSerialNumber(strSerialNumber);
			}

			super.insertProcess(context);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId()) + "&statuses="
					+ VMUtils.encrypt(form.getStatus());
		}

		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		// 根据infoSortId得到所有表结构
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);
		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = service
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);
		context.put("structureList", structureList);

		List<DictionarySort> dictionarySortList = dictionarySortService
				.getDictionarySortByParentId(0);
		context.put("dictionarySortList", dictionarySortList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			StructureForm form = (StructureForm) context.getForm();
			super.updateProcess(context);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId()) + "&statuses="
					+ VMUtils.encrypt(form.getStatus());
		}

		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		int sid = context.getSIntParameter("sid");
		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		if (sid > 0) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, sid);
			structure.setIsDelete(1);
			service.updateObject(structure);

			return "/structure/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(infoSortId) + "&statuses="
					+ VMUtils.encrypt(status);
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
