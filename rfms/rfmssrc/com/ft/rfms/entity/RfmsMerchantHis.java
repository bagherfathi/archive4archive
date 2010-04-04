package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMerchantHis.java Create on 2008-12-16 15:38:49
  * <p> 
  * 实体类:RfmsMerchantHis
  * <p>
  * 对应表名:RFMS_MERCHANT_HIS
  */

public class RfmsMerchantHis implements Serializable {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMerchantHis () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMerchantHis (Long historyId) {
		this.setHistoryId(historyId);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public RfmsMerchantHis (
		Long historyId,
		Long merchantId) {

		this.setHistoryId(historyId);
		this.setMerchantId(merchantId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMerchantHis";
	public static String PROP_AUDIT_STATUS = "auditStatus";
	public static String PROP_PHONE = "phone";
	public static String PROP_HISTORY_ID = "historyId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_SETTLE_UPPERLIMIT = "settleUpperlimit";
	public static String PROP_SPECIAL_CONTACT_PHONE = "specialContactPhone";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_NEW_APP_ID = "newAppId";
	public static String PROP_BANK_NAME = "bankName";
	public static String PROP_SETTLE_TYPE = "settleType";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_HANDLING_CHARGE = "handlingCharge";
	public static String PROP_MERCHANT_CODE = "merchantCode";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_DISCOUNT_REMARK = "discountRemark";
	public static String PROP_BANK_ACCOUNT_CODE = "bankAccountCode";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_COMMISION_CHARGE = "commisionCharge";
	public static String PROP_CHECK_DATE = "checkDate";
	public static String PROP_SYS_MERCHANT_CODE = "sysMerchantCode";
	public static String PROP_FINANCE_CONTACT_EMAIL = "financeContactEmail";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_SETTLE_STARTDATE = "settleStartdate";
	public static String PROP_DISCOUNT_RATE = "discountRate";
	public static String PROP_CONTACT_NAME = "contactName";
	public static String PROP_FAX = "fax";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_CONTACT_PHONE = "contactPhone";
	public static String PROP_BRANCH_NUM = "branchNum";
	public static String PROP_USER_ID = "userId";
	public static String PROP_MERCHANT_NAME = "merchantName";
	public static String PROP_POS_FEE = "posFee";
	public static String PROP_FINANCE_CONTACT_PHONE = "financeContactPhone";
	public static String PROP_STATUS = "status";
	public static String PROP_REBATES = "rebates";
	public static String PROP_FINANCE_CONTACT_FAX = "financeContactFax";
	public static String PROP_ADDRESS = "address";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_SETTLE_PERIOD = "settlePeriod";
	public static String PROP_BANK_ACCOUNT_NAME = "bankAccountName";
	public static String PROP_SETTLE_DATE = "settleDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_SPECIAL_CONTACT_NAME = "specialContactName";
	public static String PROP_FINANCE_CONTACT = "financeContact";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long historyId;

	// fields
	private java.lang.Long merchantId;
	private java.lang.String merchantName;
	private java.lang.String merchantCode;
	private java.lang.String sysMerchantCode;
	private java.lang.String address;
	private java.lang.String phone;
	private java.lang.Long branchNum;
	private java.lang.String fax;
	private java.lang.String contactName;
	private java.lang.String contactPhone;
	private java.lang.String specialContactName;
	private java.lang.String specialContactPhone;
	private java.lang.String status;
	private java.lang.Long auditStatus;
	private java.util.Date createTime;
	private java.lang.Long userId;
	private java.lang.String financeContact;
	private java.lang.String financeContactPhone;
	private java.lang.String financeContactFax;
	private java.lang.String financeContactEmail;
	private java.lang.String bankName;
	private java.lang.String bankAccountName;
	private java.lang.String bankAccountCode;
	private java.lang.Long settlePeriod;
	private java.lang.Long settleType;
	private java.lang.Float settleUpperlimit;
	private java.util.Date settleStartdate;
	private java.lang.Long settleDate;
	private java.lang.Long checkDate;
	private java.lang.Float handlingCharge;
	private java.lang.Float commisionCharge;
	private java.lang.Float discountRate;
	private java.lang.String discountRemark;
	private java.lang.Float rebates;
	private java.lang.Float posFee;
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
	 * Return the value associated with the column: MERCHANT_NAME
	 */
	public java.lang.String getMerchantName () {
		return merchantName;
	}

	/**
	 * Set the value related to the column: MERCHANT_NAME
	 * @param merchantName the MERCHANT_NAME value
	 */
	public void setMerchantName (java.lang.String merchantName) {
		this.merchantName = merchantName;
	}



	/**
	 * Return the value associated with the column: MERCHANT_CODE
	 */
	public java.lang.String getMerchantCode () {
		return merchantCode;
	}

	/**
	 * Set the value related to the column: MERCHANT_CODE
	 * @param merchantCode the MERCHANT_CODE value
	 */
	public void setMerchantCode (java.lang.String merchantCode) {
		this.merchantCode = merchantCode;
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
	 * Return the value associated with the column: ADDRESS
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: ADDRESS
	 * @param address the ADDRESS value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}



	/**
	 * Return the value associated with the column: PHONE
	 */
	public java.lang.String getPhone () {
		return phone;
	}

	/**
	 * Set the value related to the column: PHONE
	 * @param phone the PHONE value
	 */
	public void setPhone (java.lang.String phone) {
		this.phone = phone;
	}



	/**
	 * Return the value associated with the column: BRANCH_NUM
	 */
	public java.lang.Long getBranchNum () {
		return branchNum;
	}

	/**
	 * Set the value related to the column: BRANCH_NUM
	 * @param branchNum the BRANCH_NUM value
	 */
	public void setBranchNum (java.lang.Long branchNum) {
		this.branchNum = branchNum;
	}



	/**
	 * Return the value associated with the column: FAX
	 */
	public java.lang.String getFax () {
		return fax;
	}

	/**
	 * Set the value related to the column: FAX
	 * @param fax the FAX value
	 */
	public void setFax (java.lang.String fax) {
		this.fax = fax;
	}



	/**
	 * Return the value associated with the column: CONTACT_NAME
	 */
	public java.lang.String getContactName () {
		return contactName;
	}

	/**
	 * Set the value related to the column: CONTACT_NAME
	 * @param contactName the CONTACT_NAME value
	 */
	public void setContactName (java.lang.String contactName) {
		this.contactName = contactName;
	}



	/**
	 * Return the value associated with the column: CONTACT_PHONE
	 */
	public java.lang.String getContactPhone () {
		return contactPhone;
	}

	/**
	 * Set the value related to the column: CONTACT_PHONE
	 * @param contactPhone the CONTACT_PHONE value
	 */
	public void setContactPhone (java.lang.String contactPhone) {
		this.contactPhone = contactPhone;
	}



	/**
	 * Return the value associated with the column: SPECIAL_CONTACT_NAME
	 */
	public java.lang.String getSpecialContactName () {
		return specialContactName;
	}

	/**
	 * Set the value related to the column: SPECIAL_CONTACT_NAME
	 * @param specialContactName the SPECIAL_CONTACT_NAME value
	 */
	public void setSpecialContactName (java.lang.String specialContactName) {
		this.specialContactName = specialContactName;
	}



	/**
	 * Return the value associated with the column: SPECIAL_CONTACT_PHONE
	 */
	public java.lang.String getSpecialContactPhone () {
		return specialContactPhone;
	}

	/**
	 * Set the value related to the column: SPECIAL_CONTACT_PHONE
	 * @param specialContactPhone the SPECIAL_CONTACT_PHONE value
	 */
	public void setSpecialContactPhone (java.lang.String specialContactPhone) {
		this.specialContactPhone = specialContactPhone;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: AUDIT_STATUS
	 */
	public java.lang.Long getAuditStatus () {
		return auditStatus;
	}

	/**
	 * Set the value related to the column: AUDIT_STATUS
	 * @param auditStatus the AUDIT_STATUS value
	 */
	public void setAuditStatus (java.lang.Long auditStatus) {
		this.auditStatus = auditStatus;
	}



	/**
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}



	/**
	 * Return the value associated with the column: USER_ID
	 */
	public java.lang.Long getUserId () {
		return userId;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param userId the USER_ID value
	 */
	public void setUserId (java.lang.Long userId) {
		this.userId = userId;
	}



	/**
	 * Return the value associated with the column: FINANCE_CONTACT
	 */
	public java.lang.String getFinanceContact () {
		return financeContact;
	}

	/**
	 * Set the value related to the column: FINANCE_CONTACT
	 * @param financeContact the FINANCE_CONTACT value
	 */
	public void setFinanceContact (java.lang.String financeContact) {
		this.financeContact = financeContact;
	}



	/**
	 * Return the value associated with the column: FINANCE_CONTACT_PHONE
	 */
	public java.lang.String getFinanceContactPhone () {
		return financeContactPhone;
	}

	/**
	 * Set the value related to the column: FINANCE_CONTACT_PHONE
	 * @param financeContactPhone the FINANCE_CONTACT_PHONE value
	 */
	public void setFinanceContactPhone (java.lang.String financeContactPhone) {
		this.financeContactPhone = financeContactPhone;
	}



	/**
	 * Return the value associated with the column: FINANCE_CONTACT_FAX
	 */
	public java.lang.String getFinanceContactFax () {
		return financeContactFax;
	}

	/**
	 * Set the value related to the column: FINANCE_CONTACT_FAX
	 * @param financeContactFax the FINANCE_CONTACT_FAX value
	 */
	public void setFinanceContactFax (java.lang.String financeContactFax) {
		this.financeContactFax = financeContactFax;
	}



	/**
	 * Return the value associated with the column: FINANCE_CONTACT_EMAIL
	 */
	public java.lang.String getFinanceContactEmail () {
		return financeContactEmail;
	}

	/**
	 * Set the value related to the column: FINANCE_CONTACT_EMAIL
	 * @param financeContactEmail the FINANCE_CONTACT_EMAIL value
	 */
	public void setFinanceContactEmail (java.lang.String financeContactEmail) {
		this.financeContactEmail = financeContactEmail;
	}



	/**
	 * Return the value associated with the column: BANK_NAME
	 */
	public java.lang.String getBankName () {
		return bankName;
	}

	/**
	 * Set the value related to the column: BANK_NAME
	 * @param bankName the BANK_NAME value
	 */
	public void setBankName (java.lang.String bankName) {
		this.bankName = bankName;
	}



	/**
	 * Return the value associated with the column: BANK_ACCOUNT_NAME
	 */
	public java.lang.String getBankAccountName () {
		return bankAccountName;
	}

	/**
	 * Set the value related to the column: BANK_ACCOUNT_NAME
	 * @param bankAccountName the BANK_ACCOUNT_NAME value
	 */
	public void setBankAccountName (java.lang.String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}



	/**
	 * Return the value associated with the column: BANK_ACCOUNT_CODE
	 */
	public java.lang.String getBankAccountCode () {
		return bankAccountCode;
	}

	/**
	 * Set the value related to the column: BANK_ACCOUNT_CODE
	 * @param bankAccountCode the BANK_ACCOUNT_CODE value
	 */
	public void setBankAccountCode (java.lang.String bankAccountCode) {
		this.bankAccountCode = bankAccountCode;
	}



	/**
	 * Return the value associated with the column: SETTLE_PERIOD
	 */
	public java.lang.Long getSettlePeriod () {
		return settlePeriod;
	}

	/**
	 * Set the value related to the column: SETTLE_PERIOD
	 * @param settlePeriod the SETTLE_PERIOD value
	 */
	public void setSettlePeriod (java.lang.Long settlePeriod) {
		this.settlePeriod = settlePeriod;
	}



	/**
	 * Return the value associated with the column: SETTLE_TYPE
	 */
	public java.lang.Long getSettleType () {
		return settleType;
	}

	/**
	 * Set the value related to the column: SETTLE_TYPE
	 * @param settleType the SETTLE_TYPE value
	 */
	public void setSettleType (java.lang.Long settleType) {
		this.settleType = settleType;
	}



	/**
	 * Return the value associated with the column: SETTLE_UPPERLIMIT
	 */
	public java.lang.Float getSettleUpperlimit () {
		return settleUpperlimit;
	}

	/**
	 * Set the value related to the column: SETTLE_UPPERLIMIT
	 * @param settleUpperlimit the SETTLE_UPPERLIMIT value
	 */
	public void setSettleUpperlimit (java.lang.Float settleUpperlimit) {
		this.settleUpperlimit = settleUpperlimit;
	}



	/**
	 * Return the value associated with the column: SETTLE_STARTDATE
	 */
	public java.util.Date getSettleStartdate () {
		return settleStartdate;
	}

	/**
	 * Set the value related to the column: SETTLE_STARTDATE
	 * @param settleStartdate the SETTLE_STARTDATE value
	 */
	public void setSettleStartdate (java.util.Date settleStartdate) {
		this.settleStartdate = settleStartdate;
	}



	/**
	 * Return the value associated with the column: SETTLE_DATE
	 */
	public java.lang.Long getSettleDate () {
		return settleDate;
	}

	/**
	 * Set the value related to the column: SETTLE_DATE
	 * @param settleDate the SETTLE_DATE value
	 */
	public void setSettleDate (java.lang.Long settleDate) {
		this.settleDate = settleDate;
	}



	/**
	 * Return the value associated with the column: CHECK_DATE
	 */
	public java.lang.Long getCheckDate () {
		return checkDate;
	}

	/**
	 * Set the value related to the column: CHECK_DATE
	 * @param checkDate the CHECK_DATE value
	 */
	public void setCheckDate (java.lang.Long checkDate) {
		this.checkDate = checkDate;
	}



	/**
	 * Return the value associated with the column: HANDLING_CHARGE
	 */
	public java.lang.Float getHandlingCharge () {
		return handlingCharge;
	}

	/**
	 * Set the value related to the column: HANDLING_CHARGE
	 * @param handlingCharge the HANDLING_CHARGE value
	 */
	public void setHandlingCharge (java.lang.Float handlingCharge) {
		this.handlingCharge = handlingCharge;
	}



	/**
	 * Return the value associated with the column: COMMISION_CHARGE
	 */
	public java.lang.Float getCommisionCharge () {
		return commisionCharge;
	}

	/**
	 * Set the value related to the column: COMMISION_CHARGE
	 * @param commisionCharge the COMMISION_CHARGE value
	 */
	public void setCommisionCharge (java.lang.Float commisionCharge) {
		this.commisionCharge = commisionCharge;
	}



	/**
	 * Return the value associated with the column: DISCOUNT_RATE
	 */
	public java.lang.Float getDiscountRate () {
		return discountRate;
	}

	/**
	 * Set the value related to the column: DISCOUNT_RATE
	 * @param discountRate the DISCOUNT_RATE value
	 */
	public void setDiscountRate (java.lang.Float discountRate) {
		this.discountRate = discountRate;
	}



	/**
	 * Return the value associated with the column: DISCOUNT_REMARK
	 */
	public java.lang.String getDiscountRemark () {
		return discountRemark;
	}

	/**
	 * Set the value related to the column: DISCOUNT_REMARK
	 * @param discountRemark the DISCOUNT_REMARK value
	 */
	public void setDiscountRemark (java.lang.String discountRemark) {
		this.discountRemark = discountRemark;
	}



	/**
	 * Return the value associated with the column: REBATES
	 */
	public java.lang.Float getRebates () {
		return rebates;
	}

	/**
	 * Set the value related to the column: REBATES
	 * @param rebates the REBATES value
	 */
	public void setRebates (java.lang.Float rebates) {
		this.rebates = rebates;
	}



	/**
	 * Return the value associated with the column: POS_FEE
	 */
	public java.lang.Float getPosFee () {
		return posFee;
	}

	/**
	 * Set the value related to the column: POS_FEE
	 * @param posFee the POS_FEE value
	 */
	public void setPosFee (java.lang.Float posFee) {
		this.posFee = posFee;
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
		if (!(obj instanceof RfmsMerchantHis)) return false;
		else {
			RfmsMerchantHis rfmsMerchantHis = (RfmsMerchantHis) obj;
			if (null == this.getHistoryId() || null == rfmsMerchantHis.getHistoryId()) return false;
			else return (this.getHistoryId().equals(rfmsMerchantHis.getHistoryId()));
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