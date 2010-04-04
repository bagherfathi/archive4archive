package com.ft.busi.dto;

/**
 * 受理记录接口请求参数类。
 * 
 * @version 1.0
 */
public class AppRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 受理记录标识
	 */
	private long appId;

	/** 受理编号 */
	private String appCode;
    /**相关受理标识*/
	private long relatAppId;
	/**
	 * 操作员标识。
	 */
	private long operatorId;

	/**
	 * 营业厅组织标识。
	 */
	private long orgId;

	/**
	 * 登陆组织标识。
	 */
	private long loginOrgId;

	/**
	 * 分公司组织标识。
	 */
	private long corpOrgId;

	/**
	 * 记录归属组织标识。
	 */
	private long recOrgId;

	/**
	 * 开户营业厅标识
	 */
	private long officeOrgId;

	/**
	 * 受理类型标识。
	 */
	private String appAction;

	/**
	 * 受理备注。
	 */
	private String notes;

	/**
	 * 直接通过busi做数据库操作: ---主要是为保存受理对象操作明细使用
	 * <li>true:直接保存或更新对象</li>
	 * <li>false:不作保存或更新对象</li>
	 */
	private boolean directSaveUpdate;

	/** 受理记录操作明细.增1;删:0;改2 */
	private Long operatorType;

	/**
	 * 
	 * 构造函数。
	 */
	public AppRequest() {

	}

	/**
	 * 
	 * @return 返回受理类型。
	 */
	public String getAppAction() {
		return appAction;
	}

	/**
	 * 设置受理类型。
	 * 
	 * @param appAction
	 *            受理类型。
	 */
	public void setAppAction(String appAction) {
		this.appAction = appAction;
	}

	/**
	 * @return 返回备注。
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 设置备注信息。
	 * 
	 * @param notes
	 *            备注信息。
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 返回操作员标识。
	 * 
	 * @return 返回操作员标识。
	 */
	public long getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置操作员标识。
	 * 
	 * @param operatorId
	 *            操作员标识。
	 */
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 返回登陆组织标识。
	 * 
	 * @return 返回登陆组织标识。
	 */
	public long getLoginOrgId() {
		return loginOrgId;
	}

	/**
	 * 设置登陆组织标识。
	 * 
	 * @param loginOrgId
	 *            登陆组织标识。
	 */
	public void setLoginOrgId(long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}

	/**
	 * 返回组织标识。
	 * 
	 * @return 组织标识。
	 */
	public long getOrgId() {
		return orgId;
	}

	/**
	 * 设置组织标识。
	 * 
	 * @param orgId
	 *            组织标识。
	 */
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return 返回受理记录标识。
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * 设置受理记录标识
	 * 
	 * @param appId
	 *            受理记录标识。
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

	/**
	 * @return Returns the corpOrgId.
	 */
	public long getCorpOrgId() {
		return corpOrgId;
	}

	/**
	 * @param corpOrgId
	 *            The corpOrgId to set.
	 */
	public void setCorpOrgId(long corpOrgId) {
		this.corpOrgId = corpOrgId;
	}

	/**
	 * @return Returns the recOrgId.
	 */
	public long getRecOrgId() {
		return recOrgId;
	}

	/**
	 * @param recOrgId
	 *            The recOrgId to set.
	 */
	public void setRecOrgId(long recOrgId) {
		this.recOrgId = recOrgId;
	}

	/**
	 * 返回directSaveUpdate。 直接通过busi做数据库操作: ---主要是为保存受理对象操作明细使用
	 * <li>true:直接保存或更新对象</li>
	 * <li>false:不作保存或更新对象</li>
	 * 
	 * @return Returns the directSaveUpdate.
	 */
	public boolean isDirectSaveUpdate() {
		return directSaveUpdate;
	}

	/**
	 * 设置directSaveUpdate 到 directSaveUpdate 直接通过busi做数据库操作: --主要是为保存受理对象操作明细使用
	 * <li>true:直接保存或更新对象</li>
	 * <li>false:不作保存或更新对象</li>
	 * 
	 * @param directSaveUpdate
	 *            The directSaveUpdate to set.
	 */
	public void setDirectSaveUpdate(boolean directSaveUpdate) {
		this.directSaveUpdate = directSaveUpdate;
	}

	/**
	 * 返回operatorType。 受理记录操作明细.增1;删:0;改2
	 * 
	 * @return Returns the operatorType.
	 */

	public Long getOperatorType() {
		return operatorType;
	}

	/**
	 * 设置operatorType 到 operatorType 受理记录操作明细.增1;删:0;改2
	 * 
	 * @param operatorType
	 *            The operatorType to set.
	 */

	public void setOperatorType(Long operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * @return Returns the officeOrgId.
	 */
	public long getOfficeOrgId() {
		return officeOrgId;
	}

	/**
	 * @param officeOrgId The officeOrgId to set.
	 */
	public void setOfficeOrgId(long officeOrgId) {
		this.officeOrgId = officeOrgId;
	}

	/**
	 * 返回appCode。
	 * @return Returns the appCode.
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * 设置appCode 到 appCode
	 * @param appCode The appCode to set.
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * 返回relatAppId。
	 * @return Returns the relatAppId.
	 */
	public long getRelatAppId() {
		return relatAppId;
	}

	/**
	 * 设置relatAppId 到 relatAppId
	 * @param relatAppId The relatAppId to set.
	 */
	public void setRelatAppId(long relatAppId) {
		this.relatAppId = relatAppId;
	}

}
