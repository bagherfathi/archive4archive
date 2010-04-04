package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

/**
 * 枚举类型数据查找接口
 * 
 */
public interface EnumLookuper {
	/**
	 * Lookuper名称，作为Context中Attribute name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 根据枚举类型数据代码，获取枚举数据类型所有条目
	 * 
	 * @param code
	 * @return
	 */
	public LabelValueBean[] getElement(String code);

	/**
	 * 根据枚举类型数据代码和指定值，查找该枚举数据中的条目
	 * 
	 * @param code
	 * @param value
	 * @return
	 */
	public LabelValueBean lookup(String code, Object value);
}
