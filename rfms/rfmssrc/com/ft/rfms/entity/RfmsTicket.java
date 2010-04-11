package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
 * RfmsTicket.java Create on 2010-4-4 19:16:28
 * <p>
 * 实体类:RfmsTicket
 * <p>
 * 对应表名:RFMS_TICKET
 */

public class RfmsTicket implements Serializable, IBaseEntity {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	// constructors
	public RfmsTicket() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsTicket(Long ticketId) {
		this.setTicketId(ticketId);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public RfmsTicket(Long ticketId, Long sendCount, Long useCount) {

		this.setTicketId(ticketId);
		this.setSendCount(sendCount);
		this.setUseCount(useCount);
		initialize();
	}

	protected void initialize() {
	}

	/* [CONSTRUCTOR MARKER END] */

	public static String REF = "RfmsTicket";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_SEND_COUNT = "sendCount";
	public static String PROP_USE_RULE = "useRule";
	public static String PROP_TICKET_SERIAL = "ticketSerial";
	public static String PROP_BEGIN_DATE = "beginDate";
	public static String PROP_TYPE = "type";
	public static String PROP_OHTER_INFO = "ohterInfo";
	public static String PROP_TICKET_ID = "ticketId";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_TICKET_NAME = "ticketName";
	public static String PROP_PAR_VALUE = "parValue";
	public static String PROP_STATUS = "status";
	public static String PROP_END_DATE = "endDate";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_TARGET_MEMBER_TYPE = "targetMemberType";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_PAR_ZHEKOU = "parZhekou";
	public static String PROP_USE_COUNT = "useCount";
	public static String PROP_TICKET_COUNT = "ticketCount";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long ticketId;

	// fields
	private java.lang.String ticketSerial;
	private java.lang.String ticketName;
	private java.lang.String type;
	private java.lang.Long parValue;
	private java.lang.Long sendCount;
	private java.lang.Long useCount;
	private java.lang.String status;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private java.lang.Long operatorId;
	private java.lang.Long parZhekou;
	private java.util.Date beginDate;
	private java.util.Date endDate;
	private java.lang.Long ticketCount;
	private java.lang.String targetMemberType;
	private java.lang.String useRule;
	private java.lang.String ohterInfo;
	private java.lang.Long merchantId;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="sequence" column="TICKET_ID"
	 */
	public java.lang.Long getTicketId() {
		return ticketId;
	}

	public java.lang.Long getId() {
		return ticketId;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param ticketId
	 *            the new ID
	 */
	public void setTicketId(java.lang.Long ticketId) {
		this.ticketId = ticketId;
		this.hashCode = Integer.MIN_VALUE;
	}

	public void setId(java.lang.Long ticketId) {
		this.ticketId = ticketId;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: TICKET_SERIAL
	 */
	public java.lang.String getTicketSerial() {
		return ticketSerial;
	}

	/**
	 * Set the value related to the column: TICKET_SERIAL
	 * 
	 * @param ticketSerial
	 *            the TICKET_SERIAL value
	 */
	public void setTicketSerial(java.lang.String ticketSerial) {
		this.ticketSerial = ticketSerial;
	}

	/**
	 * Return the value associated with the column: TICKET_NAME
	 */
	public java.lang.String getTicketName() {
		return ticketName;
	}

	/**
	 * Set the value related to the column: TICKET_NAME
	 * 
	 * @param ticketName
	 *            the TICKET_NAME value
	 */
	public void setTicketName(java.lang.String ticketName) {
		this.ticketName = ticketName;
	}

	/**
	 * Return the value associated with the column: TYPE
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * Set the value related to the column: TYPE
	 * 
	 * @param type
	 *            the TYPE value
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * Return the value associated with the column: PAR_VALUE
	 */
	public java.lang.Long getParValue() {
		return parValue;
	}

	/**
	 * Set the value related to the column: PAR_VALUE
	 * 
	 * @param parValue
	 *            the PAR_VALUE value
	 */
	public void setParValue(java.lang.Long parValue) {
		this.parValue = parValue;
	}

	/**
	 * Return the value associated with the column: SEND_COUNT
	 */
	public java.lang.Long getSendCount() {
		return sendCount;
	}

	/**
	 * Set the value related to the column: SEND_COUNT
	 * 
	 * @param sendCount
	 *            the SEND_COUNT value
	 */
	public void setSendCount(java.lang.Long sendCount) {
		this.sendCount = sendCount;
	}

	/**
	 * Return the value associated with the column: USE_COUNT
	 */
	public java.lang.Long getUseCount() {
		return useCount;
	}

	/**
	 * Set the value related to the column: USE_COUNT
	 * 
	 * @param useCount
	 *            the USE_COUNT value
	 */
	public void setUseCount(java.lang.Long useCount) {
		this.useCount = useCount;
	}

	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * 
	 * @param status
	 *            the STATUS value
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate() {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * 
	 * @param createDate
	 *            the CREATE_DATE value
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Return the value associated with the column: UPDATE_DATE
	 */
	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Set the value related to the column: UPDATE_DATE
	 * 
	 * @param updateDate
	 *            the UPDATE_DATE value
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
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
	 * Return the value associated with the column: PAR_ZHEKOU
	 */
	public java.lang.Long getParZhekou() {
		return parZhekou;
	}

	/**
	 * Set the value related to the column: PAR_ZHEKOU
	 * 
	 * @param parZhekou
	 *            the PAR_ZHEKOU value
	 */
	public void setParZhekou(java.lang.Long parZhekou) {
		this.parZhekou = parZhekou;
	}

	/**
	 * Return the value associated with the column: BEGIN_DATE
	 */
	public java.util.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Set the value related to the column: BEGIN_DATE
	 * 
	 * @param beginDate
	 *            the BEGIN_DATE value
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Return the value associated with the column: END_DATE
	 */
	public java.util.Date getEndDate() {
		return endDate;
	}

	/**
	 * Set the value related to the column: END_DATE
	 * 
	 * @param endDate
	 *            the END_DATE value
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Return the value associated with the column: TICKET_COUNT
	 */
	public java.lang.Long getTicketCount() {
		return ticketCount;
	}

	/**
	 * Set the value related to the column: TICKET_COUNT
	 * 
	 * @param ticketCount
	 *            the TICKET_COUNT value
	 */
	public void setTicketCount(java.lang.Long ticketCount) {
		this.ticketCount = ticketCount;
	}

	/**
	 * Return the value associated with the column: TARGET_MEMBER_TYPE
	 */
	public java.lang.String getTargetMemberType() {
		return targetMemberType;
	}

	/**
	 * Set the value related to the column: TARGET_MEMBER_TYPE
	 * 
	 * @param targetMemberType
	 *            the TARGET_MEMBER_TYPE value
	 */
	public void setTargetMemberType(java.lang.String targetMemberType) {
		this.targetMemberType = targetMemberType;
	}

	/**
	 * Return the value associated with the column: USE_RULE
	 */
	public java.lang.String getUseRule() {
		return useRule;
	}

	/**
	 * Set the value related to the column: USE_RULE
	 * 
	 * @param useRule
	 *            the USE_RULE value
	 */
	public void setUseRule(java.lang.String useRule) {
		this.useRule = useRule;
	}

	/**
	 * Return the value associated with the column: OHTER_INFO
	 */
	public java.lang.String getOhterInfo() {
		return ohterInfo;
	}

	/**
	 * Set the value related to the column: OHTER_INFO
	 * 
	 * @param ohterInfo
	 *            the OHTER_INFO value
	 */
	public void setOhterInfo(java.lang.String ohterInfo) {
		this.ohterInfo = ohterInfo;
	}

	/**
	 * Return the value associated with the column: MERCHANT_ID
	 */
	public java.lang.Long getMerchantId() {
		return merchantId;
	}

	/**
	 * Set the value related to the column: MERCHANT_ID
	 * 
	 * @param merchantId
	 *            the MERCHANT_ID value
	 */
	public void setMerchantId(java.lang.Long merchantId) {
		this.merchantId = merchantId;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof RfmsTicket))
			return false;
		else {
			RfmsTicket rfmsTicket = (RfmsTicket) obj;
			if (null == this.getTicketId() || null == rfmsTicket.getTicketId())
				return false;
			else
				return (this.getTicketId().equals(rfmsTicket.getTicketId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getTicketId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getTicketId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}