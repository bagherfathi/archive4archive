package com.renhenet.web.structure;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.InfoSort;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.form.InfoSortForm;

public class CPStructureAction extends DispatchActions {
	private static StructureService service = (StructureService) ServiceLocator
			.getService("structureService");

	private static InfoSortService infoSortService = (InfoSortService) ServiceLocator
			.getService("infoSortService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return Structure.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		boolean updated = false;
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			InfoSortForm form = (InfoSortForm) context.getForm();

			// ���������Ϣ
			InfoSort infoSort = (InfoSort) service.getObjectById(
					InfoSort.class, form.getInfoSortId());
			infoSort.setId(null);
			infoSort.setName(form.getName());
			infoSort.setTimeCreate(new Date());
			infoSort.setParentId(form.getParentId());
			infoSort.setTimeModified(new Date());

			service.insertObject(infoSort);

			// ����������Ϣ(1�㡢2�㡢3��)
			List<Structure> structureList = service
					.getStructureByInfoSortIdAndInStatus(form.getInfoSortId(),
							3);

			for (Structure structure : structureList) {
				structure.setId(null);
				structure.setTimeCreate(new Date());
				structure.setTimeModified(new Date());
				structure.setInfoSortId(infoSort.getId());

				service.insertObject(structure);
			}

			updated = true;
		}

		// ��ʾ���п��Կ����ĵײ�����
		List<InfoSort> infoSortList2 = infoSortService
				.getInfoSortByTypeAndCopy(2);
		context.put("infoSortList2", infoSortList2);

		// �õ����зǵײ�����
		List<InfoSort> infoSortList = infoSortService
				.getInfoSortByNotTypeAndCopy(2);
		context.put("infoSortList", infoSortList);
		context.put("updated", updated);
		if (updated) {
			return "insert";
		}

		return super.insertProcess(context);
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "file";
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		return null;
	}

}
