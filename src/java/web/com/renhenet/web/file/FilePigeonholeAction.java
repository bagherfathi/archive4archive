package com.renhenet.web.file;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.FilePigeonholeService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.File;
import com.renhenet.po.FilePigeonhole;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.FilePigeonholeForm;

public class FilePigeonholeAction extends DispatchActions {
	private static FilePigeonholeService service = (FilePigeonholeService) ServiceLocator
			.getService("filePigeonholeService");
	private static StructureService structureService = (StructureService) ServiceLocator
			.getService("structureService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return FilePigeonhole.class;
	}

	@SuppressWarnings("unchecked")
	public String insertProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		int infoSortIdTo = context.getSIntParameter("infoSortIdsTo");
		context.put("infoSortIdTo", infoSortIdTo);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			FilePigeonholeForm form = (FilePigeonholeForm) context.getForm();
			super.insertProcess(context);

			return "/filepigeonhole/actions.html?method=insert&infoSortIdsTo="
					+ VMUtils.encrypt(form.getInfoSortIdsTo())
					+ "&infoSortIds=" + VMUtils.encrypt(form.getInfoSortId())
					+ "&statuses=" + VMUtils.encrypt(status) + "&cm="
					+ context.getParameter("cm");

		} else {

			List<Structure> structureList = structureService
					.getStructureByInfoSortId(infoSortId, status);
			context.put("structureList", structureList);

			List<Structure> structureToList = structureService
					.getStructureByInfoSortId(infoSortIdTo, status);
			context.put("structureToList", structureToList);

			List<FilePigeonhole> filePigeonholeLists = service
					.getFilePigeonholeByInfoSortId(infoSortId);

			List<FilePigeonhole> filePigeonholeList = new ArrayList();
			for (FilePigeonhole filePigeonhole : filePigeonholeLists) {
				FilePigeonhole filePigeonholeNew = new FilePigeonhole();
				Structure structure1 = structureService
						.getStructureByInfoSortIdAndSerialNumber(infoSortId,
								filePigeonhole.getInfoColumn());

				Structure structure2 = structureService
						.getStructureByInfoSortIdAndSerialNumber(infoSortIdTo,
								filePigeonhole.getInfoColumnTo());

				filePigeonholeNew.setInfoColumn(structure1.getZnName());
				filePigeonholeNew.setInfoColumnTo(structure2.getZnName());
				filePigeonholeNew.setInfoSortId(infoSortId);
				filePigeonholeNew.setId(filePigeonhole.getId());
				filePigeonholeList.add(filePigeonholeNew);
			}
			context.put("filePigeonholeList", filePigeonholeList);
		}

		return super.insertProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);
		int status = context.getSIntParameter("statuses");
		context.put("status", status);
		if (infoSortId > 0) {
			super.deleteProcess(context);
			return "/files/actions.html?method=list&infoSortId="
					+ VMUtils.encrypt(infoSortId) + "&statuses="
					+ VMUtils.encrypt(status) + "&cm="
					+ context.getParameter("cm");
		}
		return super.deleteProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		int infoSortIdTo = context.getSIntParameter("infoSortIdsTo");
		context.put("infoSortIdTo", infoSortIdTo);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		// 判断是否是是文件归档
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			FilePigeonholeForm form = (FilePigeonholeForm) context.getForm();
			for (int i = 0; i < form.getFileGds().length; i++) {
				// 更新当前文件为已归档
				File files = (File) service.getObjectById(File.class, form
						.getFileGds()[i]);
				files.setA6("");
				service.updateObject(files);
				
				// 添加一条归档记录
//				File
				
				
				
			}

			return "/files/actions.html?method=list&infoSortId="
					+ VMUtils.encrypt(infoSortId) + "&statuses="
					+ VMUtils.encrypt(status) + "&cm="
					+ context.getParameter("cm");
		}

		return super.updateProcess(context);
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();
		int infoSortId = context.getSIntParameter("infoSortId");
		if (infoSortId >= 0) {
			searchContext.addOption(new SearchOption("infoSortId", infoSortId,
					SearchOption.Option.eqaul));
		}
		context.put("infoSortId", infoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

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
