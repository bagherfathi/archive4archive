package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsSettlePay.java Create on 2008-12-23 12:11:30
  * <p> 
  * 实体类:RfmsSettlePay
  * <p>
  * 对应表名:RFMS_SETTLE_PAY
  */

public class RfmsSettlePay implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsSettlePay () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsSettlePay (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsSettlePay";
	public static String PROP_AMOUNT = "amount";
	public static String PROP_REJECTED_AMOUNT = "rejectedAmount";
	public static String PROP_DISCOUNT_RATE = "discountRate";
	public static String PROP_PAY_USER = "payUser";
	public static String PROP_COMMISION_CHARGE_AMOUNT = "commisionChargeAmount";
	public static String PROP_PAY_FLAG = "payFlag";
	public static String PROP_REALPAY_DATE = "realpayDate";
	public static String PROP_HANDLING_CHARGE_AMOUNT = "handlingChargeAmount";
	public static String PROP_DISCOUNT_AMOUNT = "discountAmount";
	public static String PROP_REJECTED_NUM = "rejectedNum";
	public static String PROP_HANDLING_CHARGE = "handlingCharge";
	public static String PROP_CONSUME_AMOUNT = "consumeAmount";
	public static String PROP_END_DATE = "endDate";
	public static String PROP_START_DATE = "startDate";
	public static String PROP_CONSUME_NUM = "consumeNum";
	public static String PROP_COMMISION_CHARGE = "commisionCharge";
	public static String PROP_ID = "id";
	public static String PROP_SETTLE_RECORD_ID = "settleRecordId";
	public static String PROP_PAY_DATE = "payDate";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.lang.Long settleRecordId;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private java.util.Date payDate;
	private java.lang.Long payUser;
	private java.lang.String payFlag;
	private java.lang.Float handlingCharge;
	private java.lang.Float commisionCharge;
	private java.lang.Float discountRate;
	private java.lang.Long amount;
	private java.lang.Long handlingChargeAmount;
	private java.lang.Long commisionChargeAmount;
	private java.lang.Long discountAmount;
	private java.lang.Long consumeNum;
	private java.lang.Long rejectedNum;
	private java.lang.Long consumeAmount;
	private java.lang.Long rejectedAmount;
	private java.util.Date realpayDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="ID"
     */
 
    public java.lang.Long getId () {
		return id;
	}
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
 
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: SETTLE_RECORD_ID
	 */
	public java.lang.Long getSettleRecordId () {
		return settleRecordId;
	}

	/**
	 * Set the value related to the column: SETTLE_RECORD_ID
	 * @param settleRecordId the SETTLE_RECORD_ID value
	 */
	public void setSettleRecordId (java.lang.Long settleRecordId) {
		this.settleRecordId = settleRecordId;
	}



	/**
	 * Return the value associated with the column: START_DATE
	 */
	public java.util.Date getStartDate () {
		return startDate;
	}

	/**
	 * Set the value related to the column: START_DATE
	 * @param startDate the START_DATE value
	 */
	public void setStartDate (java.util.Date startDate) {
		this.startDate = startDate;
	}



	/**
	 * Return the value associated with the column: END_DATE
	 */
	public java.util.Date getEndDate () {
		return endDate;
	}

	/**
	 * Set the value related to the column: END_DATE
	 * @param endDate the END_DATE value
	 */
	public void setEndDate (java.util.Date endDate) {
		this.endDate = endDate;
	}



	/**
	 * Return the value associated with the column: PAY_DATE
	 */
	public java.util.Date getPayDate () {
		return payDate;
	}

	/**
	 * Set the value related to the column: PAY_DATE
	 * @param payDate the PAY_DATE value
	 */
	public void setPayDate (java.util.Date payDate) {
		this.payDate = payDate;
	}



	/**
	 * Return the value associated with the column: PAY_USER
	 */
	public java.lang.Long getPayUser () {
		return payUser;
	}

	/**
	 * Set the value related to the column: PAY_USER
	 * @param payUser the PAY_USER value
	 */
	public void setPayUser (java.lang.Long payUser) {
		this.payUser = payUser;
	}



	/**
	 * Return the value associated with the column: PAY_FLAG
	 */
	public java.lang.String getPayFlag () {
		return payFlag;
	}

	/**
	 * Set the value related to the column: PAY_FLAG
	 * @param payFlag the PAY_FLAG value
	 */
	public void setPayFlag (java.lang.String payFlag) {
		this.payFlag = payFlag;
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
	 * Return the value associated with the column: AMOUNT
	 */
	public java.lang.Long getAmount () {
		return amount;
	}

	/**
	 * Set the value related to the column: AMOUNT
	 * @param amount the AMOUNT value
	 */
	public void setAmount (java.lang.Long amount) {
		this.amount = amount;
	}



	/**
	 * Return the value associated with the column: HANDLING_CHARGE_AMOUNT
	 */
	public java.lang.Long getHandlingChargeAmount () {
		return handlingChargeAmount;
	}

	/**
	 * Set the value related to the column: HANDLING_CHARGE_AMOUNT
	 * @param handlingChargeAmount the HANDLING_CHARGE_AMOUNT value
	 */
	public void setHandlingChargeAmount (java.lang.Long handlingChargeAmount) {
		this.handlingChargeAmount = handlingChargeAmount;
	}



	/**
	 * Return the value associated with the column: COMMISION_CHARGE_AMOUNT
	 */
	public java.lang.Long getCommisionChargeAmount () {
		return commisionChargeAmount;
	}

	/**
	 * Set the value related to the column: COMMISION_CHARGE_AMOUNT
	 * @param commisionChargeAmount the COMMISION_CHARGE_AMOUNT value
	 */
	public void setCommisionChargeAmount (java.lang.Long commisionChargeAmount) {
		this.commisionChargeAmount = commisionChargeAmount;
	}



	/**
	 * Return the value associated with the column: DISCOUNT_AMOUNT
	 */
	public java.lang.Long getDiscountAmount () {
		return discountAmount;
	}

	/**
	 * Set the value related to the column: DISCOUNT_AMOUNT
	 * @param discountAmount the DISCOUNT_AMOUNT value
	 */
	public void setDiscountAmount (java.lang.Long discountAmount) {
		this.discountAmount = discountAmount;
	}



	/**
	 * Return the value associated with the column: CONSUME_NUM
	 */
	public java.lang.Long getConsumeNum () {
		return consumeNum;
	}

	/**
	 * Set the value related to the column: CONSUME_NUM
	 * @param consumeNum the CONSUME_NUM value
	 */
	public void setConsumeNum (java.lang.Long consumeNum) {
		this.consumeNum = consumeNum;
	}



	/**
	 * Return the value associated with the column: REJECTED_NUM
	 */
	public java.lang.Long getRejectedNum () {
		return rejectedNum;
	}

	/**
	 * Set the value related to the column: REJECTED_NUM
	 * @param rejectedNum the REJECTED_NUM value
	 */
	public void setRejectedNum (java.lang.Long rejectedNum) {
		this.rejectedNum = rejectedNum;
	}



	/**
	 * Return the value associated with the column: CONSUME_AMOUNT
	 */
	public java.lang.Long getConsumeAmount () {
		return consumeAmount;
	}

	/**
	 * Set the value related to the column: CONSUME_AMOUNT
	 * @param consumeAmount the CONSUME_AMOUNT value
	 */
	public void setConsumeAmount (java.lang.Long consumeAmount) {
		this.consumeAmount = consumeAmount;
	}



	/**
	 * Return the value associated with the column: REJECTED_AMOUNT
	 */
	public java.lang.Long getRejectedAmount () {
		return rejectedAmount;
	}

	/**
	 * Set the value related to the column: REJECTED_AMOUNT
	 * @param rejectedAmount the REJECTED_AMOUNT value
	 */
	public void setRejectedAmount (java.lang.Long rejectedAmount) {
		this.rejectedAmount = rejectedAmount;
	}



	/**
	 * Return the value associated with the column: REALPAY_DATE
	 */
	public java.util.Date getRealpayDate () {
		return realpayDate;
	}

	/**
	 * Set the value related to the column: REALPAY_DATE
	 * @param realpayDate the REALPAY_DATE value
	 */
	public void setRealpayDate (java.util.Date realpayDate) {
		this.realpayDate = realpayDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsSettlePay)) return false;
		else {
			RfmsSettlePay rfmsSettlePay = (RfmsSettlePay) obj;
			if (null == this.getId() || null == rfmsSettlePay.getId()) return false;
			else return (this.getId().equals(rfmsSettlePay.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}