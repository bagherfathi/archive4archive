package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
 * RfcsPosSignin.java Create on Apr 12, 2010 9:04:40 PM
 * <p>
 * 实体类:RfcsPosSignin
 * <p>
 * 对应表名:RFCS_POS_SIGNIN
 */

public class RfcsPosSignin implements Serializable, IBaseEntity {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	// constructors
	public RfcsPosSignin () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfcsPosSignin (Long signinId) {
		this.setSigninId(signinId);
		initialize();
	}

	protected void initialize () {}

	/* [CONSTRUCTOR MARKER END] */

	public static String REF = "RfcsPosSignin";

	public static String PROP_OPERATOR_ID = "operatorId";

	public static String PROP_SIGNIN_TIME = "signinTime";

	public static String PROP_MAC_KEY = "macKey";

	public static String PROP_PIN_KEY = "pinKey";

	public static String PROP_POS_ID = "posId";

	public static String PROP_SIGNIN_ID = "signinId";

	// fields
	private java.lang.Long signinId;

	private java.lang.Long operatorId;

	private java.lang.Long posId;

	private java.lang.String pinKey;

	private java.lang.String macKey;

	private java.util.Date signinTime;

	/**
	 * Return the value associated with the column: SIGNIN_ID
	 */
	public java.lang.Long getSigninId() {
		return signinId;
	}

	/**
	 * Set the value related to the column: SIGNIN_ID
	 * 
	 * @param signinId
	 *            the SIGNIN_ID value
	 */
	public void setSigninId(java.lang.Long signinId) {
		this.signinId = signinId;
	}

	/**
	 * Return the value associated with the column: OPERATOR_ID
	 */
	public java.lang.Long getOperatorId() {
		return operatorId;
	}

	/**
	 * Set the value related to the column: OPERATOR_ID
	 * 
	 * @param operatorId
	 *            the OPERATOR_ID value
	 */
	public void setOperatorId(java.lang.Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * Return the value associated with the column: POS_ID
	 */
	public java.lang.Long getPosId() {
		return posId;
	}

	/**
	 * Set the value related to the column: POS_ID
	 * 
	 * @param posId
	 *            the POS_ID value
	 */
	public void setPosId(java.lang.Long posId) {
		this.posId = posId;
	}

	/**
	 * Return the value associated with the column: PIN_KEY
	 */
	public java.lang.String getPinKey() {
		return pinKey;
	}

	/**
	 * Set the value related to the column: PIN_KEY
	 * 
	 * @param pinKey
	 *            the PIN_KEY value
	 */
	public void setPinKey(java.lang.String pinKey) {
		this.pinKey = pinKey;
	}

	/**
	 * Return the value associated with the column: MAC_KEY
	 */
	public java.lang.String getMacKey() {
		return macKey;
	}

	/**
	 * Set the value related to the column: MAC_KEY
	 * 
	 * @param macKey
	 *            the MAC_KEY value
	 */
	public void setMacKey(java.lang.String macKey) {
		this.macKey = macKey;
	}

	/**
	 * Return the value associated with the column: SIGNIN_TIME
	 */
	public java.util.Date getSigninTime() {
		return signinTime;
	}

	/**
	 * Set the value related to the column: SIGNIN_TIME
	 * 
	 * @param signinTime
	 *            the SIGNIN_TIME value
	 */
	public void setSigninTime(java.util.Date signinTime) {
		this.signinTime = signinTime;
	}

	public String toString() {
		return super.toString();
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.signinId;
	}

	public void setId(Long id) {
		this.signinId=id;
		
	}

}