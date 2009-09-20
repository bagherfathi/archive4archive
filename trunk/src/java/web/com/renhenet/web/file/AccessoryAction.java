package com.renhenet.web.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import com.renhenet.fw.Config;
import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.util.FileManager;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.AccessoryService;
import com.renhenet.po.Accessory;
import com.renhenet.util.DateUtil;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.WebHelper;
import com.renhenet.web.form.AccessoryForm;

public class AccessoryAction extends DispatchActions {
	private static final String FILE_PATH = Config.getString("file.path",
			"D:/dangan/dangan/web/upload/");
	

	private static AccessoryService service = (AccessoryService) ServiceLocator
			.getService("accessoryService");

	@SuppressWarnings("unchecked")
	protected Class getActionClass() {
		return Accessory.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int fileId = context.getSIntParameter("fileIds");
		context.put("fileId", fileId);

		// 得到文件的原文
		// List<Accessory> accessoryList = service.getAccessoryByFileId(fileId);
		// context.put("accessoryList", accessoryList);

		List<FileDto> fileDtoList = new ArrayList();
		FileManager fm = new FileManager();
		List<String> fileList = fm.serachFiles(FILE_PATH);
		for (int i = 0; i < fileList.size(); i++) {
			FileDto fileDto = new FileDto();
			fileDto.setFilePath("/upload/"+fileList.get(i));
			fileDtoList.add(fileDto);
		}
		context.put("fileDtoList", fileDtoList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			AccessoryForm accessoryForm = (AccessoryForm) context.getForm();
			FormFile file = accessoryForm.getAccessoryFiles();
			if (file != null) {
				if (!StringUtils.isEmpty(file.getFileName())) {
					String fName = file.getFileName().toLowerCase();
					if (!StringUtils.isEmpty(fName)) {
						try {
							Date now = new Date();
							String seq = DateUtil.date2MysqlDate(now) + "";
							String fileName = FILE_PATH + seq + fName;
							WebHelper.writeUploadFile2Server(fileName, file);
							accessoryForm.setNewName(seq + fName);

							accessoryForm.setOldName(fName);

							super.insertProcess(context);

							return "/accessory/actions.html?method=insert&fileIds="
									+ VMUtils
											.encrypt(accessoryForm.getFileId())
									+ "&cm=" + context.getParameter("cm");

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
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
