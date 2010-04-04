package com.ft.busi.dto;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 */
public class AppQueryDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1903964248511950750L;
	
	private String appCode;

	private Date startDate;

	private Date endDate;

	private String appAction;

	private long recOrgId;

	private long operatorId;
	
	private long loginOrgId;
	
	private Long[] loginOrgIds;
	
	private long category;
	
	private String[] appActions;

	// 受理营业厅
	// 操作员归属组织
	private long opOrgId;
	
	private List appIds;

	/**
	 * @return Returns the appAction.
	 */
	public String getAppAction() {
		return appAction;
	}

	/**
	 * @param appAction The appAction to set.
	 */
	public void setAppAction(String appAction) {
		this.appAction = appAction;
	}

	/**
	 * @return Returns the appIds.
	 */
	public List getAppIds() {
		return appIds;
	}

	/**
	 * @param appIds The appIds to set.
	 */
	public void setAppIds(List appIds) {
		this.appIds = appIds;
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	/**
	 * @return Returns the operatorId.
	 */
	public long getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId The operatorId to set.
	 */
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * @return Returns the opOrgId.
	 */
	public long getOpOrgId() {
		return opOrgId;
	}

	/**
	 * @param opOrgId The opOrgId to set.
	 */
	public void setOpOrgId(long opOrgId) {
		this.opOrgId = opOrgId;
	}

	/**
	 * @return Returns the recOrgId.
	 */
	public long getRecOrgId() {
		return recOrgId;
	}

	/**
	 * @param recOrgId The recOrgId to set.
	 */
	public void setRecOrgId(long recOrgId) {
		this.recOrgId = recOrgId;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return Returns the appCode.
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode The appCode to set.
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return Returns the loginOrgId.
	 */
	public long getLoginOrgId() {
		return loginOrgId;
	}

	/**
	 * @param loginOrgId The loginOrgId to set.
	 */
	public void setLoginOrgId(long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}

	/**
	 * @return Returns the loginOrgIds.
	 */
	public Long[] getLoginOrgIds() {
		return loginOrgIds;
	}

	/**
	 * @param loginOrgIds The loginOrgIds to set.
	 */
	public void setLoginOrgIds(Long[] loginOrgIds) {
		this.loginOrgIds = loginOrgIds;
	}

	/**
	 * @return Returns the category.
	 */
	public long getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(long category) {
		this.category = category;
	}

	/**
	 * @return Returns the appActions.
	 */
	public String[] getAppActions() {
		return appActions;
	}

	/**
	 * @param appActions The appActions to set.
	 */
	public void setAppActions(String[] appActions) {
		this.appActions = appActions;
	}

}
