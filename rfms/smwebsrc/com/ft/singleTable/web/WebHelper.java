package com.ft.singleTable.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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
	 * 对搜索条件做处理
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

  
}
