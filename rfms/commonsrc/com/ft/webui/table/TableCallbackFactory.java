package com.ft.webui.table;
/**
 * ����Table �����Callback����
 */
public class TableCallbackFactory {
	/**
	 * ��������ʵ�ַ�ҳ��ѯ��Callback
	 * @return
	 */
	public static  TableCallback createPageBeanCallback(){
		return new PageBeanTableCallback();
	}
	/**
	 * ����Hibernate�Զ���ѯ��Callback
	 * @return
	 */
	public static TableCallback createHibernateCallback(){
		return new HbiernateTableCallback();
	}
}
