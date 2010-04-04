package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

/**
 * ö���������ݲ��ҽӿ�
 * 
 */
public interface EnumLookuper {
	/**
	 * Lookuper���ƣ���ΪContext��Attribute name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * ����ö���������ݴ��룬��ȡö����������������Ŀ
	 * 
	 * @param code
	 * @return
	 */
	public LabelValueBean[] getElement(String code);

	/**
	 * ����ö���������ݴ����ָ��ֵ�����Ҹ�ö�������е���Ŀ
	 * 
	 * @param code
	 * @param value
	 * @return
	 */
	public LabelValueBean lookup(String code, Object value);
}
