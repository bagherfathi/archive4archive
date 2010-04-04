/**
 * @{#} Constants.java Created on 2006-10-4 ����04:15:47
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.busi.common;

/**
 * ����ҵ���߼����õ��ĳ�����
 * 
 * @author <a href="mailto:chengj@onewaveinc.com">wilson</a>
 * @version 1.0
 */
public class Constants {

	/**
	 * ���캯��
	 */
	private Constants() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ҵ������ʶ��
	 */
	public static String PROP_APP_ID = "appId";

	/**
	 * ��ҵ������ʶ��
	 */
	public static String PROP_NEW_APP_ID = "newAppId";

	/**
	 * ����Ա��ʶ��
	 */
	public static final String PROP_OPERATOR_ID = "operatorId";

	/**
	 * ����Ա����Ӫҵ����ʶ��
	 */
	public static String PROP_ORG_ID = "orgId";

	/**
	 * ��¼��֯��ʶ��
	 */
	public static final String PROP_LOGIN_ORG_ID = "loginOrgId";

	/**
	 * ��¼������֯��ʶ��
	 */
	public static String PROP_REC_ORG_ID = "recOrgId";

	/**
	 * �ֹ�˾��֯��ʶ��
	 */
	public static String PROP_CORP_ORG_ID = "corpOrgId";
	
	
	public static String PROP_OFFICE_ORG_ID ="officeOrgId";

	/**
	 * ����ʱ��
	 */
	public static String PROP_CREATE_DATE = "createDate";

	/**
	 * ��¼��Чʱ�䡣
	 */
	public static String PROP_VALID_DATE = "validDate";

	/**
	 * 3 ��¼����ʱ�䡣
	 */
	public static String PROP_EXPIRE_DATE = "expireDate";

	/**
	 * ��¼����޸�ʱ�䡣
	 */
	public static String PROP_UPDATE_DATE = "updateDate";

	/**
	 * ��ʷ�����׺��ʶ -His
	 */
	public static String HIS_OBJECT_SUFFIX = "His";
    /**�����¼������ϸ.����*/  
	public static long OPERATOR_TYPE_ADD=1;
	/**�����¼������ϸ.�޸�*/  
	public static long OPERATOR_TYPE_MODIFY=2;
	/**�����¼������ϸ.ɾ��*/  
	public static long OPERATOR_TYPE_DELETE=0;
}
