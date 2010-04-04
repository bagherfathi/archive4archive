/**
 * @{#} Constants.java Created on 2006-10-4 下午04:15:47
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.busi.common;

/**
 * 定义业务逻辑中用到的常量。
 * 
 * @author <a href="mailto:chengj@onewaveinc.com">wilson</a>
 * @version 1.0
 */
public class Constants {

	/**
	 * 构造函数
	 */
	private Constants() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 业务代码标识。
	 */
	public static String PROP_APP_ID = "appId";

	/**
	 * 新业务代码标识。
	 */
	public static String PROP_NEW_APP_ID = "newAppId";

	/**
	 * 操作员标识。
	 */
	public static final String PROP_OPERATOR_ID = "operatorId";

	/**
	 * 操作员所在营业厅标识。
	 */
	public static String PROP_ORG_ID = "orgId";

	/**
	 * 登录组织标识。
	 */
	public static final String PROP_LOGIN_ORG_ID = "loginOrgId";

	/**
	 * 记录归属组织标识。
	 */
	public static String PROP_REC_ORG_ID = "recOrgId";

	/**
	 * 分公司组织标识。
	 */
	public static String PROP_CORP_ORG_ID = "corpOrgId";
	
	
	public static String PROP_OFFICE_ORG_ID ="officeOrgId";

	/**
	 * 创建时间
	 */
	public static String PROP_CREATE_DATE = "createDate";

	/**
	 * 记录生效时间。
	 */
	public static String PROP_VALID_DATE = "validDate";

	/**
	 * 3 记录过期时间。
	 */
	public static String PROP_EXPIRE_DATE = "expireDate";

	/**
	 * 记录最后修改时间。
	 */
	public static String PROP_UPDATE_DATE = "updateDate";

	/**
	 * 历史对象后缀标识 -His
	 */
	public static String HIS_OBJECT_SUFFIX = "His";
    /**受理记录操作明细.增加*/  
	public static long OPERATOR_TYPE_ADD=1;
	/**受理记录操作明细.修改*/  
	public static long OPERATOR_TYPE_MODIFY=2;
	/**受理记录操作明细.删除*/  
	public static long OPERATOR_TYPE_DELETE=0;
}
