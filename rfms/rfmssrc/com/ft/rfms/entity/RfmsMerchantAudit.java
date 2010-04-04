package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMerchantAudit.java Create on 2008-12-17 12:52:39
  * <p> 
  * 实体类:RfmsMerchantAudit
  * <p>
  * 对应表名:RFMS_MERCHANT_AUDIT
  */

public class RfmsMerchantAudit implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMerchantAudit () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMerchantAudit (Long auditId) {
		this.setAuditId(auditId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMerchantAudit";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_AUDIT_TIME = "auditTime";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_NEXT_OPERATOR_ID = "nextOperatorId";
	public static String PROP_FLOW_CTRL_ID = "flowCtrlId";
	public static String PROP_RETURNTO = "returnto";
	public static String PROP_AUDIT_RESULT = "auditResult";
	public static String PROP_AUDIT_ID = "auditId";
	public static String PROP_AUDIT_REMARK = "auditRemark";
	public static String PROP_FINISHED = "finished";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long auditId;

	// fields
	private java.lang.Long merchantId;
	private java.lang.Long operatorId;
	private java.util.Date auditTime;
	private java.lang.String nextOperatorId;
	private java.lang.Long returnto;
	private java.lang.Long auditResult;
	private java.lang.Long flowCtrlId;
	private java.lang.Long finished;
	private java.lang.String auditRemark;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="AUDIT_ID"
     */
	public java.lang.Long getAuditId () {
		return auditId;
	}
    public java.lang.Long getId () {
		return auditId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param auditId the new ID
	 */
	public void setAuditId (java.lang.Long auditId) {
		this.auditId = auditId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long auditId) {
		this.auditId = auditId;
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
	 * Return the value associated with the column: AUDIT_TIME
	 */
	public java.util.Date getAuditTime () {
		return auditTime;
	}

	/**
	 * Set the value related to the column: AUDIT_TIME
	 * @param auditTime the AUDIT_TIME value
	 */
	public void setAuditTime (java.util.Date auditTime) {
		this.auditTime = auditTime;
	}



	/**
	 * Return the value associated with the column: NEXT_OPERATOR_ID
	 */
	public java.lang.String getNextOperatorId () {
		return nextOperatorId;
	}

	/**
	 * Set the value related to the column: NEXT_OPERATOR_ID
	 * @param nextOperatorId the NEXT_OPERATOR_ID value
	 */
	public void setNextOperatorId (java.lang.String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}



	/**
	 * Return the value associated with the column: RETURNTO
	 */
	public java.lang.Long getReturnto () {
		return returnto;
	}

	/**
	 * Set the value related to the column: RETURNTO
	 * @param returnto the RETURNTO value
	 */
	public void setReturnto (java.lang.Long returnto) {
		this.returnto = returnto;
	}



	/**
	 * Return the value associated with the column: AUDIT_RESULT
	 */
	public java.lang.Long getAuditResult () {
		return auditResult;
	}

	/**
	 * Set the value related to the column: AUDIT_RESULT
	 * @param auditResult the AUDIT_RESULT value
	 */
	public void setAuditResult (java.lang.Long auditResult) {
		this.auditResult = auditResult;
	}



	/**
	 * Return the value associated with the column: FLOW_CTRL_ID
	 */
	public java.lang.Long getFlowCtrlId () {
		return flowCtrlId;
	}

	/**
	 * Set the value related to the column: FLOW_CTRL_ID
	 * @param flowCtrlId the FLOW_CTRL_ID value
	 */
	public void setFlowCtrlId (java.lang.Long flowCtrlId) {
		this.flowCtrlId = flowCtrlId;
	}



	/**
	 * Return the value associated with the column: FINISHED
	 */
	public java.lang.Long getFinished () {
		return finished;
	}

	/**
	 * Set the value related to the column: FINISHED
	 * @param finished the FINISHED value
	 */
	public void setFinished (java.lang.Long finished) {
		this.finished = finished;
	}



	/**
	 * Return the value associated with the column: AUDIT_REMARK
	 */
	public java.lang.String getAuditRemark () {
		return auditRemark;
	}

	/**
	 * Set the value related to the column: AUDIT_REMARK
	 * @param auditRemark the AUDIT_REMARK value
	 */
	public void setAuditRemark (java.lang.String auditRemark) {
		this.auditRemark = auditRemark;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsMerchantAudit)) return false;
		else {
			RfmsMerchantAudit rfmsMerchantAudit = (RfmsMerchantAudit) obj;
			if (null == this.getAuditId() || null == rfmsMerchantAudit.getAuditId()) return false;
			else return (this.getAuditId().equals(rfmsMerchantAudit.getAuditId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getAuditId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getAuditId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}