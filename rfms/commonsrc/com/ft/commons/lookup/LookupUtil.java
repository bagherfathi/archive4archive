package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * �����ַ����ͷָ������bean
 * 
 */
public class LookupUtil {
	/**
	 * ���ݷָ�������ַ����������ڵ�bean��������
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
	 * �����ַ��������ӷָ��
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static List list(String str) {
		return list(str, ",");
	}
}
