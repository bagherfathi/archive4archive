package com.renhenet.web.structure;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.DhszService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.Dhsz;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.DhszForm;

public class DhszAction extends DispatchActions {
	private static DhszService service = (DhszService) ServiceLocator
			.getService("dhszService");

	private static StructureService structureService = (StructureService) ServiceLocator
			.getService("structureService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return Dhsz.class;
	}

	@SuppressWarnings("unchecked")
	public String insertProcess(WebContext context) throws ServletException {
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			DhszForm form = (DhszForm) context.getForm();

			super.insertProcess(context);
			return "/dhsz/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId());
		}
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		// 根据infosortId得到structure里面要用来作为档号的字段
		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		// 得到当前分类的档号信息
		List<Dhsz> dhszList = new ArrayList();
		List<Dhsz> dhszList1 = service.getDhszByinfoSortId(infoSortId);
		for (Dhsz dhsz : dhszList1) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, dhsz.getStructureId());
			dhsz.setStructure(structure);
			dhszList.add(dhsz);
		}
		context.put("dhszList", dhszList);

		return super.insertProcess(context);
	}

	
	public String updateProcess(WebContext context) throws ServletException {
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			DhszForm form = (DhszForm) context.getForm();

			super.updateProcess(context);
			return "/dhsz/actions.html?method=update&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId());
		}
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		// 根据infosortId得到structure里面要用来作为档号的字段
		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		// 得到当前分类的档号信息
		List<Dhsz> dhszList = new ArrayList();
		List<Dhsz> dhszList1 = service.getDhszByinfoSortId(infoSortId);
		for (Dhsz dhsz : dhszList1) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, dhsz.getStructureId());
			dhsz.setStructure(structure);
			dhszList.add(dhsz);
		}
		context.put("dhszList", dhszList);

		return super.updateProcess(context);
	}
	
	
	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortId");
		context.put("infoSortId", infoSortId);
		if (infoSortId > 0) {
			super.deleteProcess(context);
			return "/dhsz/actions.html?method=insert&infoSortIds="
					+ VMUtils.encrypt(infoSortId);
		}
		return super.deleteProcess(context);
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
