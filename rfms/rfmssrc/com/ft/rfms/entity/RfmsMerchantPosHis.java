package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMerchantPosHis.java Create on 2008-12-16 15:38:49
  * <p> 
  * 实体类:RfmsMerchantPosHis
  * <p>
  * 对应表名:RFMS_MERCHANT_POS_HIS
  */

public class RfmsMerchantPosHis implements Serializable {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMerchantPosHis () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMerchantPosHis (Long historyId) {
		this.setHistoryId(historyId);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public RfmsMerchantPosHis (
		Long historyId,
		Long merchantPosId) {

		this.setHistoryId(historyId);
		this.setMerchantPosId(merchantPosId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMerchantPosHis";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_OWNER = "owner";
	public static String PROP_HISTORY_ID = "historyId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_NEW_APP_ID = "newAppId";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_MERCHANT_BRANCH_ID = "merchantBranchId";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_MERCHANT_POS_ID = "merchantPosId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_POS_TYPE = "posType";
	public static String PROP_SYS_POS_CODE = "sysPosCode";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long historyId;

	// fields
	private java.lang.Long merchantPosId;
	private java.lang.Long merchantBranchId;
	private java.lang.String sysPosCode;
	private java.lang.String owner;
	private java.lang.String posType;
	private java.lang.Long newAppId;
	private java.lang.Long appId;
	private java.util.Date createDate;
	private java.util.Date validDate;
	private java.util.Date expireDate;
	private java.util.Date updateDate;
	private java.lang.Long operatorId;
	private java.lang.Long orgId;
	private java.lang.Long loginOrgId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="HISTORY_ID"
     */
	public java.lang.Long getHistoryId () {
		return historyId;
	}
 
	/**
	 * Set the unique identifier of this class
	 * @param historyId the new ID
	 */
	public void setHistoryId (java.lang.Long historyId) {
		this.historyId = historyId;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: MERCHANT_POS_ID
	 */
	public java.lang.Long getMerchantPosId () {
		return merchantPosId;
	}

	/**
	 * Set the value related to the column: MERCHANT_POS_ID
	 * @param merchantPosId the MERCHANT_POS_ID value
	 */
	public void setMerchantPosId (java.lang.Long merchantPosId) {
		this.merchantPosId = merchantPosId;
	}



	/**
	 * Return the value associated with the column: MERCHANT_BRANCH_ID
	 */
	public java.lang.Long getMerchantBranchId () {
		return merchantBranchId;
	}

	/**
	 * Set the value related to the column: MERCHANT_BRANCH_ID
	 * @param merchantBranchId the MERCHANT_BRANCH_ID value
	 */
	public void setMerchantBranchId (java.lang.Long merchantBranchId) {
		this.merchantBranchId = merchantBranchId;
	}



	/**
	 * Return the value associated with the column: SYS_POS_CODE
	 */
	public java.lang.String getSysPosCode () {
		return sysPosCode;
	}

	/**
	 * Set the value related to the column: SYS_POS_CODE
	 * @param sysPosCode the SYS_POS_CODE value
	 */
	public void setSysPosCode (java.lang.String sysPosCode) {
		this.sysPosCode = sysPosCode;
	}



	/**
	 * Return the value associated with the column: OWNER
	 */
	public java.lang.String getOwner () {
		return owner;
	}

	/**
	 * Set the value related to the column: OWNER
	 * @param owner the OWNER value
	 */
	public void setOwner (java.lang.String owner) {
		this.owner = owner;
	}



	/**
	 * Return the value associated with the column: POS_TYPE
	 */
	public java.lang.String getPosType () {
		return posType;
	}

	/**
	 * Set the value related to the column: POS_TYPE
	 * @param posType the POS_TYPE value
	 */
	public void setPosType (java.lang.String posType) {
		this.posType = posType;
	}



	/**
	 * Return the value associated with the column: NEW_APP_ID
	 */
	public java.lang.Long getNewAppId () {
		return newAppId;
	}

	/**
	 * Set the value related to the column: NEW_APP_ID
	 * @param newAppId the NEW_APP_ID value
	 */
	public void setNewAppId (java.lang.Long newAppId) {
		this.newAppId = newAppId;
	}



	/**
	 * Return the value associated with the column: APP_ID
	 */
	public java.lang.Long getAppId () {
		return appId;
	}

	/**
	 * Set the value related to the column: APP_ID
	 * @param appId the APP_ID value
	 */
	public void setAppId (java.lang.Long appId) {
		this.appId = appId;
	}



	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: VALID_DATE
	 */
	public java.util.Date getValidDate () {
		return validDate;
	}

	/**
	 * Set the value related to the column: VALID_DATE
	 * @param validDate the VALID_DATE value
	 */
	public void setValidDate (java.util.Date validDate) {
		this.validDate = validDate;
	}



	/**
	 * Return the value associated with the column: EXPIRE_DATE
	 */
	public java.util.Date getExpireDate () {
		return expireDate;
	}

	/**
	 * Set the value related to the column: EXPIRE_DATE
	 * @param expireDate the EXPIRE_DATE value
	 */
	public void setExpireDate (java.util.Date expireDate) {
		this.expireDate = expireDate;
	}



	/**
	 * Return the value associated with the column: UPDATE_DATE
	 */
	public java.util.Date getUpdateDate () {
		return updateDate;
	}

	/**
	 * Set the value related to the column: UPDATE_DATE
	 * @param updateDate the UPDATE_DATE value
	 */
	public void setUpdateDate (java.util.Date updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * Return the value associated with the column: OPERATOR_ID
	 */
	public java.lang.Long getOperatorId () {
		return operatorId;
	}

	/**
	 * Set the value related to the column: OPERATOR_ID
	 * @param operatorId the OPERATOR_ID value
	 */
	public void setOperatorId (java.lang.Long operatorId) {
		this.operatorId = operatorId;
	}



	/**
	 * Return the value associated with the column: ORG_ID
	 */
	public java.lang.Long getOrgId () {
		return orgId;
	}

	/**
	 * Set the value related to the column: ORG_ID
	 * @param orgId the ORG_ID value
	 */
	public void setOrgId (java.lang.Long orgId) {
		this.orgId = orgId;
	}



	/**
	 * Return the value associated with the column: LOGIN_ORG_ID
	 */
	public java.lang.Long getLoginOrgId () {
		return loginOrgId;
	}

	/**
	 * Set the value related to the column: LOGIN_ORG_ID
	 * @param loginOrgId the LOGIN_ORG_ID value
	 */
	public void setLoginOrgId (java.lang.Long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsMerchantPosHis)) return false;
		else {
			RfmsMerchantPosHis rfmsMerchantPosHis = (RfmsMerchantPosHis) obj;
			if (null == this.getHistoryId() || null == rfmsMerchantPosHis.getHistoryId()) return false;
			else return (this.getHistoryId().equals(rfmsMerchantPosHis.getHistoryId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getHistoryId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getHistoryId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}