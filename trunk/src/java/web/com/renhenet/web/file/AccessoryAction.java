package com.renhenet.web.file;

import java.io.FileInputStream;
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
import com.renhenet.modules.member.DhszService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.po.Accessory;
import com.renhenet.po.Dhsz;
import com.renhenet.po.File;
import com.renhenet.po.InfoSortDTO;
import com.renhenet.util.DateUtil;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;
import com.renhenet.web.WebHelper;
import com.renhenet.web.form.AccessoryForm;

public class AccessoryAction extends DispatchActions {
	private static final String FILE_PATH = Config.getString("file.path");

	private static final String SUCCESS = null;

	private static AccessoryService service = (AccessoryService) ServiceLocator
			.getService("accessoryService");
	private static InfoSortService infoSortService = (InfoSortService) ServiceLocator
			.getService("infoSortService");

	private static DhszService dhszService = (DhszService) ServiceLocator
			.getService("dhszService");

	@SuppressWarnings("unchecked")
	protected Class getActionClass() {
		return Accessory.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int fileId = context.getSIntParameter("fileIds");
		context.put("fileId", fileId);

		File files = (File) service.getObjectById(File.class, fileId);
		List<Dhsz> dhszList = dhszService.getDhszByinfoSortId(files
				.getInfoSortId());

		boolean dh = false;
		if (dhszList != null && dhszList.size() > 0) {
			dh = true;
		}
		context.put("dh", dh);

		String path = "";

		List<InfoSortDTO> infoSortDtoList = infoSortService
				.getInfoSortNameByInfoSortId(files.getInfoSortId());
		for (InfoSortDTO infoSortDTO : infoSortDtoList) {
			path += infoSortDTO.getName() + "/";
			mkDir(FILE_PATH + path);
		}

		if (files.getStatus() == 0) {
			if (!StringUtils.isEmpty(files.getA9())) {
				String str[] = files.getA9().split("-");
				for (int i = 0; i < str.length; i++) {
					path += str[i] + "/";
					;
					mkDir(FILE_PATH + path);
				}
				// path += files.getA9() + "/";

			}
		} else if (files.getStatus() == 1) {
			if (!StringUtils.isEmpty(files.getA10())) {
				if (files.getA10() != null) {
					String str[] = files.getA10().split("-");
					for (int i = 0; i < str.length; i++) {
						path += str[i] + "/";
						mkDir(FILE_PATH + path);
					}
				}
			}
		} else if (files.getStatus() == 2) {
			if (!StringUtils.isEmpty(files.getA11())) {
				if (files.getA11() != null) {
					String str[] = files.getA11().split("-");
					for (int i = 0; i < str.length; i++) {
						path += str[i] + "/";
						;
						mkDir(FILE_PATH + path);
					}
				}
			}
		}

		List<FileDto> fileDtoList = new ArrayList<FileDto>();
		FileManager fm = new FileManager();
		mkDir(FILE_PATH + path);

		if (!StringUtils.isEmpty(path)) {
			List<String> fileList = fm.serachFiles(FILE_PATH + path);
			for (int i = 0; i < fileList.size(); i++) {
				FileDto fileDto = new FileDto();
				fileDto.setFilePath(path + fileList.get(i));
				fileDtoList.add(fileDto);
			}
		}
		context.put("fileDtoList", fileDtoList);

		String filename = context.getParameter("filename");
		if (!StringUtils.isEmpty(filename)) {
			// context.getResponse().setContentType(("application/octet-stream"));
			// context.getResponse().setHeader("Content-Disposition",
			// "attachment; filename=\"" + filename+"\"");
			// 设置响应头和下载保存的文件名
			
			String strName[] = filename.split("/");
			String fileName="test";
			if(strName!=null){
				fileName = strName[strName.length-1];
			}
			context.getResponse().reset();
			context.getResponse().setContentType("application/octet-stream");
			context.getResponse().addHeader("Content-Disposition",
					"filename=\"" + fileName + "\"");

			String filePath = FILE_PATH + filename;
			try {
				java.io.File file = new java.io.File(filePath);

				byte[] b = new byte[10240];
				FileInputStream inStream = null;
				int len;
				if (file.exists() && file.isFile()) {
					inStream = new FileInputStream(file);
					if (inStream != null) {
						while ((len = inStream.read(b)) > 0) {
							context.getResponse().getOutputStream().write(b, 0,
									len);
						}
						context.getResponse().getOutputStream().flush();
						inStream.close();
					}
				}
			} catch (Exception e) {

			} finally {

			}
		}

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
							mkDir(FILE_PATH + path);
							String fileName = FILE_PATH + path + seq + fName;
							WebHelper.writeUploadFile2Server(fileName, file);
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

	public void mkDir(String strPath) {
		java.io.File dirFile;
		boolean bFile;
		try {
			dirFile = new java.io.File(strPath);
			bFile = dirFile.exists();

			if (bFile == true) {
				System.out.println("The folder exists.");
			} else {
				bFile = dirFile.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// AccessoryAction a = new AccessoryAction();
	// a.mkDir("D:/dangan/dangan/web/upload/222");
	//
	// }
}
