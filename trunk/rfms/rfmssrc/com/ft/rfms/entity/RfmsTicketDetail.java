package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsTicketDetail.java Create on 2010-4-4 19:16:28
  * <p> 
  * 实体类:RfmsTicketDetail
  * <p>
  * 对应表名:RFMS_TICKET_DETAIL
  */

public class RfmsTicketDetail implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsTicketDetail () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsTicketDetail (Long detailId) {
		this.setDetailId(detailId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsTicketDetail";
	public static String PROP_STATUS = "status";
	public static String PROP_SEND_DATE = "sendDate";
	public static String PROP_SEQ_NUMBER = "seqNumber";
	public static String PROP_SEND_OPERATOR_ID = "sendOperatorId";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_DETAIL_ID = "detailId";
	public static String PROP_USER_POS = "userPos";
	public static String PROP_TICKET_ID = "ticketId";
	public static String PROP_USE_DATE = "useDate";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long detailId;

	// fields
	private java.lang.Long ticketId;
	private java.lang.String seqNumber;
	private java.lang.Long status;
	private java.lang.String mobile;
	private java.util.Date sendDate;
	private java.lang.Long sendOperatorId;
	private java.util.Date useDate;
	private java.lang.String userPos;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="DETAIL_ID"
     */
	public java.lang.Long getDetailId () {
		return detailId;
	}
    public java.lang.Long getId () {
		return detailId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param detailId the new ID
	 */
	public void setDetailId (java.lang.Long detailId) {
		this.detailId = detailId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long detailId) {
		this.detailId = detailId;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: TICKET_ID
	 */
	public java.lang.Long getTicketId () {
		return ticketId;
	}

	/**
	 * Set the value related to the column: TICKET_ID
	 * @param ticketId the TICKET_ID value
	 */
	public void setTicketId (java.lang.Long ticketId) {
		this.ticketId = ticketId;
	}



	/**
	 * Return the value associated with the column: SEQ_NUMBER
	 */
	public java.lang.String getSeqNumber () {
		return seqNumber;
	}

	/**
	 * Set the value related to the column: SEQ_NUMBER
	 * @param seqNumber the SEQ_NUMBER value
	 */
	public void setSeqNumber (java.lang.String seqNumber) {
		this.seqNumber = seqNumber;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.Long getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.Long status) {
		this.status = status;
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
	 * Return the value associated with the column: SEND_OPERATOR_ID
	 */
	public java.lang.Long getSendOperatorId () {
		return sendOperatorId;
	}

	/**
	 * Set the value related to the column: SEND_OPERATOR_ID
	 * @param sendOperatorId the SEND_OPERATOR_ID value
	 */
	public void setSendOperatorId (java.lang.Long sendOperatorId) {
		this.sendOperatorId = sendOperatorId;
	}



	/**
	 * Return the value associated with the column: USE_DATE
	 */
	public java.util.Date getUseDate () {
		return useDate;
	}

	/**
	 * Set the value related to the column: USE_DATE
	 * @param useDate the USE_DATE value
	 */
	public void setUseDate (java.util.Date useDate) {
		this.useDate = useDate;
	}



	/**
	 * Return the value associated with the column: USER_POS
	 */
	public java.lang.String getUserPos () {
		return userPos;
	}

	/**
	 * Set the value related to the column: USER_POS
	 * @param userPos the USER_POS value
	 */
	public void setUserPos (java.lang.String userPos) {
		this.userPos = userPos;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsTicketDetail)) return false;
		else {
			RfmsTicketDetail rfmsTicketDetail = (RfmsTicketDetail) obj;
			if (null == this.getDetailId() || null == rfmsTicketDetail.getDetailId()) return false;
			else return (this.getDetailId().equals(rfmsTicketDetail.getDetailId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getDetailId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getDetailId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}