package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsCommisionStep.java Create on 2008-12-22 9:39:43
  * <p> 
  * 实体类:RfmsCommisionStep
  * <p>
  * 对应表名:RFMS_COMMISION_STEP
  */

public class RfmsCommisionStep implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsCommisionStep () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsCommisionStep (Long commisionStepId) {
		this.setCommisionStepId(commisionStepId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsCommisionStep";
	public static String PROP_MIN_CHARGE = "minCharge";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_COMMISION_CHARGE = "commisionCharge";
	public static String PROP_MAX_CHARGE = "maxCharge";
	public static String PROP_COMMISION_STEP_ID = "commisionStepId";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long commisionStepId;

	// fields
	private java.lang.Long merchantId;
	private java.lang.Long minCharge;
	private java.lang.Long maxCharge;
	private java.lang.Float commisionCharge;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="COMMISION_STEP_ID"
     */
	public java.lang.Long getCommisionStepId () {
		return commisionStepId;
	}
    public java.lang.Long getId () {
		return commisionStepId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param commisionStepId the new ID
	 */
	public void setCommisionStepId (java.lang.Long commisionStepId) {
		this.commisionStepId = commisionStepId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long commisionStepId) {
		this.commisionStepId = commisionStepId;
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
	 * Return the value associated with the column: MIN_CHARGE
	 */
	public java.lang.Long getMinCharge () {
		return minCharge;
	}

	/**
	 * Set the value related to the column: MIN_CHARGE
	 * @param minCharge the MIN_CHARGE value
	 */
	public void setMinCharge (java.lang.Long minCharge) {
		this.minCharge = minCharge;
	}



	/**
	 * Return the value associated with the column: MAX_CHARGE
	 */
	public java.lang.Long getMaxCharge () {
		return maxCharge;
	}

	/**
	 * Set the value related to the column: MAX_CHARGE
	 * @param maxCharge the MAX_CHARGE value
	 */
	public void setMaxCharge (java.lang.Long maxCharge) {
		this.maxCharge = maxCharge;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsCommisionStep)) return false;
		else {
			RfmsCommisionStep rfmsCommisionStep = (RfmsCommisionStep) obj;
			if (null == this.getCommisionStepId() || null == rfmsCommisionStep.getCommisionStepId()) return false;
			else return (this.getCommisionStepId().equals(rfmsCommisionStep.getCommisionStepId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getCommisionStepId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getCommisionStepId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}