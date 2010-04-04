package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMerchantPayment.java Create on 2010-4-4 21:05:45
  * <p> 
  * 实体类:RfmsMerchantPayment
  * <p>
  * 对应表名:RFMS_MERCHANT_PAYMENT
  */

public class RfmsMerchantPayment implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMerchantPayment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMerchantPayment (Long paymentId) {
		this.setPaymentId(paymentId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMerchantPayment";
	public static String PROP_NOTES = "notes";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_PAYMENT_TYPE = "paymentType";
	public static String PROP_AMOUNT = "amount";
	public static String PROP_METCHANT_ID = "metchantId";
	public static String PROP_PAYMENT_DATE = "paymentDate";
	public static String PROP_PAYMENT_ID = "paymentId";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long paymentId;

	// fields
	private java.lang.Long metchantId;
	private java.lang.Long amount;
	private java.util.Date paymentDate;
	private java.lang.Long operatorId;
	private java.lang.Long paymentType;
	private java.lang.String notes;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="PAYMENT_ID"
     */
	public java.lang.Long getPaymentId () {
		return paymentId;
	}
    public java.lang.Long getId () {
		return paymentId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param paymentId the new ID
	 */
	public void setPaymentId (java.lang.Long paymentId) {
		this.paymentId = paymentId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long paymentId) {
		this.paymentId = paymentId;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: METCHANT_ID
	 */
	public java.lang.Long getMetchantId () {
		return metchantId;
	}

	/**
	 * Set the value related to the column: METCHANT_ID
	 * @param metchantId the METCHANT_ID value
	 */
	public void setMetchantId (java.lang.Long metchantId) {
		this.metchantId = metchantId;
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
	 * Return the value associated with the column: PAYMENT_DATE
	 */
	public java.util.Date getPaymentDate () {
		return paymentDate;
	}

	/**
	 * Set the value related to the column: PAYMENT_DATE
	 * @param paymentDate the PAYMENT_DATE value
	 */
	public void setPaymentDate (java.util.Date paymentDate) {
		this.paymentDate = paymentDate;
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
	 * Return the value associated with the column: PAYMENT_TYPE
	 */
	public java.lang.Long getPaymentType () {
		return paymentType;
	}

	/**
	 * Set the value related to the column: PAYMENT_TYPE
	 * @param paymentType the PAYMENT_TYPE value
	 */
	public void setPaymentType (java.lang.Long paymentType) {
		this.paymentType = paymentType;
	}



	/**
	 * Return the value associated with the column: NOTES
	 */
	public java.lang.String getNotes () {
		return notes;
	}

	/**
	 * Set the value related to the column: NOTES
	 * @param notes the NOTES value
	 */
	public void setNotes (java.lang.String notes) {
		this.notes = notes;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsMerchantPayment)) return false;
		else {
			RfmsMerchantPayment rfmsMerchantPayment = (RfmsMerchantPayment) obj;
			if (null == this.getPaymentId() || null == rfmsMerchantPayment.getPaymentId()) return false;
			else return (this.getPaymentId().equals(rfmsMerchantPayment.getPaymentId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getPaymentId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getPaymentId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}