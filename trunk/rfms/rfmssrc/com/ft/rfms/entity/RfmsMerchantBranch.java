package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMerchantBranch.java Create on 2008-12-16 15:38:49
  * <p> 
  * 实体类:RfmsMerchantBranch
  * <p>
  * 对应表名:RFMS_MERCHANT_BRANCH
  */

public class RfmsMerchantBranch implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMerchantBranch () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMerchantBranch (Long merchantBranchId) {
		this.setMerchantBranchId(merchantBranchId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMerchantBranch";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_BRANCH_CONTACT_NAME = "branchContactName";
	public static String PROP_SYS_JION_STATE = "sysJionState";
	public static String PROP_POS_NUM = "posNum";
	public static String PROP_APP_ID = "appId";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_BRANCH_ADDRESS = "branchAddress";
	public static String PROP_MERCHANT_BRANCH_ID = "merchantBranchId";
	public static String PROP_BRANCH_PHONE = "branchPhone";
	public static String PROP_BRANCH_NAME = "branchName";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_POS_TYPE = "posType";
	public static String PROP_SYS_MERCHANT_CODE = "sysMerchantCode";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long merchantBranchId;

	// fields
	private java.lang.Long merchantId;
	private java.lang.String branchName;
	private java.lang.String branchAddress;
	private java.lang.String branchContactName;
	private java.lang.String branchPhone;
	private java.lang.Long posNum;
	private java.lang.String posType;
	private java.lang.String sysMerchantCode;
	private java.lang.String sysJionState;
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
     *  column="MERCHANT_BRANCH_ID"
     */
	public java.lang.Long getMerchantBranchId () {
		return merchantBranchId;
	}
    public java.lang.Long getId () {
		return merchantBranchId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param merchantBranchId the new ID
	 */
	public void setMerchantBranchId (java.lang.Long merchantBranchId) {
		this.merchantBranchId = merchantBranchId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long merchantBranchId) {
		this.merchantBranchId = merchantBranchId;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: MERCHANT_ID
	 */
	public java.lang.Long getMerchantId () {
		return merchantId;
	}

	/**
	 * Set the value related to the column: MERCHANT_ID
	 * @param merchantId the MERCHANT_ID value
	 */
	public void setMerchantId (java.lang.Long merchantId) {
		this.merchantId = merchantId;
	}



	/**
	 * Return the value associated with the column: BRANCH_NAME
	 */
	public java.lang.String getBranchName () {
		return branchName;
	}

	/**
	 * Set the value related to the column: BRANCH_NAME
	 * @param branchName the BRANCH_NAME value
	 */
	public void setBranchName (java.lang.String branchName) {
		this.branchName = branchName;
	}



	/**
	 * Return the value associated with the column: BRANCH_ADDRESS
	 */
	public java.lang.String getBranchAddress () {
		return branchAddress;
	}

	/**
	 * Set the value related to the column: BRANCH_ADDRESS
	 * @param branchAddress the BRANCH_ADDRESS value
	 */
	public void setBranchAddress (java.lang.String branchAddress) {
		this.branchAddress = branchAddress;
	}



	/**
	 * Return the value associated with the column: BRANCH_CONTACT_NAME
	 */
	public java.lang.String getBranchContactName () {
		return branchContactName;
	}

	/**
	 * Set the value related to the column: BRANCH_CONTACT_NAME
	 * @param branchContactName the BRANCH_CONTACT_NAME value
	 */
	public void setBranchContactName (java.lang.String branchContactName) {
		this.branchContactName = branchContactName;
	}



	/**
	 * Return the value associated with the column: BRANCH_PHONE
	 */
	public java.lang.String getBranchPhone () {
		return branchPhone;
	}

	/**
	 * Set the value related to the column: BRANCH_PHONE
	 * @param branchPhone the BRANCH_PHONE value
	 */
	public void setBranchPhone (java.lang.String branchPhone) {
		this.branchPhone = branchPhone;
	}



	/**
	 * Return the value associated with the column: POS_NUM
	 */
	public java.lang.Long getPosNum () {
		return posNum;
	}

	/**
	 * Set the value related to the column: POS_NUM
	 * @param posNum the POS_NUM value
	 */
	public void setPosNum (java.lang.Long posNum) {
		this.posNum = posNum;
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
	 * Return the value associated with the column: SYS_MERCHANT_CODE
	 */
	public java.lang.String getSysMerchantCode () {
		return sysMerchantCode;
	}

	/**
	 * Set the value related to the column: SYS_MERCHANT_CODE
	 * @param sysMerchantCode the SYS_MERCHANT_CODE value
	 */
	public void setSysMerchantCode (java.lang.String sysMerchantCode) {
		this.sysMerchantCode = sysMerchantCode;
	}



	/**
	 * Return the value associated with the column: SYS_JION_STATE
	 */
	public java.lang.String getSysJionState () {
		return sysJionState;
	}

	/**
	 * Set the value related to the column: SYS_JION_STATE
	 * @param sysJionState the SYS_JION_STATE value
	 */
	public void setSysJionState (java.lang.String sysJionState) {
		this.sysJionState = sysJionState;
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
		if (!(obj instanceof RfmsMerchantBranch)) return false;
		else {
			RfmsMerchantBranch rfmsMerchantBranch = (RfmsMerchantBranch) obj;
			if (null == this.getMerchantBranchId() || null == rfmsMerchantBranch.getMerchantBranchId()) return false;
			else return (this.getMerchantBranchId().equals(rfmsMerchantBranch.getMerchantBranchId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getMerchantBranchId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getMerchantBranchId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}