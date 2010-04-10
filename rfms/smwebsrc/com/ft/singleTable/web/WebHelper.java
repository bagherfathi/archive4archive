package com.ft.singleTable.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.ListTool;

public class WebHelper {

	public static final int INT_HAVE_NO_VALUE = -999;

	public static void putCommonValues(WebContext context) {
		context.put("now", new Date());
		context.put("list", new ListTool());
		context.put("date", new DateTool());

	}

	public static String decode(String source) {
		if (StringUtils.isEmpty(source)) {
			return source;
		}
		try {
			return URLDecoder.decode(source, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encode(String source) {
		if (StringUtils.isEmpty(source)) {
			return source;
		}
		try {
			return URLEncoder.encode(source, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ����������������
	 * 
	 * @param context
	 * @param optionName
	 * @return
	 */
	public static String delSearchOption(WebContext context, String optionName,
			String moduleName) {
		String optionValue = "";
		optionValue = context.getParameter(optionName);

		context.put(optionName, optionValue);
		return optionValue;
	}

	public static int delIntSearchOption(WebContext context, String optionName,
			String moduleName) {
		String optionValue = delSearchOption(context, optionName, moduleName);
		if (!StringUtils.isEmpty(optionValue)) {
			return Integer.parseInt(optionValue);
		} else {
			return INT_HAVE_NO_VALUE;
		}
	}

	public static String writeUploadFile2Server(String fileName, FormFile file)
			throws FileNotFoundException, IOException {
		InputStream stream = file.getInputStream();// ���ļ�����

		OutputStream bos = new FileOutputStream(fileName);// ����һ���ϴ��ļ��������
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);// ���ļ�д�������
		}
		bos.close();
		stream.close();
		return fileName;
	}

}
