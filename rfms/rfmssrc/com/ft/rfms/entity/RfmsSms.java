package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsSms.java Create on 2010-4-4 19:16:28
  * <p> 
  * 实体类:RfmsSms
  * <p>
  * 对应表名:RFMS_SMS
  */

public class RfmsSms implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsSms () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsSms (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsSms";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_STATUS = "status";
	public static String PROP_SEND_DATE = "sendDate";
	public static String PROP_MESSAGE = "message";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_CREATE_DATE = "createDate";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.lang.String mobile;
	private java.lang.String message;
	private java.lang.String status;
	private java.util.Date sendDate;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private java.lang.Long operatorId;



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
	 * Return the value associated with the column: MOBILE
	 */
	public java.lang.String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: MOBILE
	 * @param mobile the MOBILE value
	 */
	public void setMobile (java.lang.String mobile) {
		this.mobile = mobile;
	}



	/**
	 * Return the value associated with the column: MESSAGE
	 */
	public java.lang.String getMessage () {
		return message;
	}

	/**
	 * Set the value related to the column: MESSAGE
	 * @param message the MESSAGE value
	 */
	public void setMessage (java.lang.String message) {
		this.message = message;
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
	 * Return the value associated with the column: SEND_DATE
	 */
	public java.util.Date getSendDate () {
		return sendDate;
	}

	/**
	 * Set the value related to the column: SEND_DATE
	 * @param sendDate the SEND_DATE value
	 */
	public void setSendDate (java.util.Date sendDate) {
		this.sendDate = sendDate;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsSms)) return false;
		else {
			RfmsSms rfmsSms = (RfmsSms) obj;
			if (null == this.getId() || null == rfmsSms.getId()) return false;
			else return (this.getId().equals(rfmsSms.getId()));
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