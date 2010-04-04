package com.ft.webui.table;
/**
 * 创建Table 对象的Callback方法
 */
public class TableCallbackFactory {
	/**
	 * 创建工手实现翻页查询的Callback
	 * @return
	 */
	public static  TableCallback createPageBeanCallback(){
		return new PageBeanTableCallback();
	}
	/**
	 * 创建Hibernate自动查询的Callback
	 * @return
	 */
	public static TableCallback createHibernateCallback(){
		return new HbiernateTableCallback();
	}
}
