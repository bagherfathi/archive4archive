package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsTicketBind.java Create on 2010-4-4 19:16:28
  * <p> 
  * 实体类:RfmsTicketBind
  * <p>
  * 对应表名:RFMS_TICKET_BIND
  */

public class RfmsTicketBind implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsTicketBind () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsTicketBind (Long bindId) {
		this.setBindId(bindId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsTicketBind";
	public static String PROP_BIND_ID = "bindId";
	public static String PROP_POS_ID = "posId";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_TICKET_ID = "ticketId";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long bindId;

	// fields
	private java.lang.Long ticketId;
	private java.lang.String posCode;
	private java.lang.Long createDate;
	private java.lang.Long updateDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="BIND_ID"
     */
	public java.lang.Long getBindId () {
		return bindId;
	}
    public java.lang.Long getId () {
		return bindId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param bindId the new ID
	 */
	public void setBindId (java.lang.Long bindId) {
		this.bindId = bindId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long bindId) {
		this.bindId = bindId;
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
	 * @return the posCode
	 */
	public java.lang.String getPosCode() {
		return posCode;
	}

	/**
	 * @param posCode the posCode to set
	 */
	public void setPosCode(java.lang.String posCode) {
		this.posCode = posCode;
	}

	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.lang.Long getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.lang.Long createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: UPDATE_DATE
	 */
	public java.lang.Long getUpdateDate () {
		return updateDate;
	}

	/**
	 * Set the value related to the column: UPDATE_DATE
	 * @param updateDate the UPDATE_DATE value
	 */
	public void setUpdateDate (java.lang.Long updateDate) {
		this.updateDate = updateDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsTicketBind)) return false;
		else {
			RfmsTicketBind rfmsTicketBind = (RfmsTicketBind) obj;
			if (null == this.getBindId() || null == rfmsTicketBind.getBindId()) return false;
			else return (this.getBindId().equals(rfmsTicketBind.getBindId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getBindId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getBindId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}