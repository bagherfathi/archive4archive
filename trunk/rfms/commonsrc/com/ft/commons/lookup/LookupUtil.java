package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据字符串和分割符分析bean
 * 
 */
public class LookupUtil {
	/**
	 * 根据分割符分析字符串中所存在的bean，并返回
	 * 
	 * @param str
	 * @param split
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List list(String str, String split) {
		List result = new ArrayList();

		if ((str != null) && (str.length() > 0)) {
			String[] values = str.split(split);

			for (int i = 0; i < values.length; i++) {
				String string = values[i];
				String[] nameValue = string.split("=");

				if (nameValue.length > 1) {
					LabelValueBean bean = new LabelValueBean(nameValue[0],
							nameValue[1]);
					result.add(bean);
				}
			}
		}

		return result;
	}

	/**
	 * 根据字符串，增加分割符
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static List list(String str) {
		return list(str, ",");
	}
}
