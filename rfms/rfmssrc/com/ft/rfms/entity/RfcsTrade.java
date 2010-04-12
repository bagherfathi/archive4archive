package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfcsTrade.java Create on Apr 12, 2010 8:23:19 PM
  * <p> 
  * 实体类:RfcsTrade
  * <p>
  * 对应表名:RFCS_TRADE
  */

public class RfcsTrade implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

        public static String STATUS_SUCCESS = "00"; //成功交易
    	public static String STATUS_FAIL = "99"; //失败交易
    	public static String STATUS_CANCEL = "01"; //撤销交易
    	public static String STATUS_BACK_GOODS = "02"; //消费退货
/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfcsTrade () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfcsTrade (Long transNo) {
		this.setTransNo(transNo);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfcsTrade";
	public static String PROP_TRANS_NO = "transNo";
	public static String PROP_SYSTEM_ID = "systemId";
	public static String PROP_AMOUNT = "amount";
	public static String PROP_POS_ERR_CODE = "posErrCode";
	public static String PROP_CARD_TRANSNO = "cardTransno";
	public static String PROP_RND = "rnd";
	public static String PROP_POS_CODE = "posCode";
	public static String PROP_SYSTEM_CODE = "systemCode";
	public static String PROP_POS_TRANS_NO = "posTransNo";
	public static String PROP_MERCHANT_CODE = "merchantCode";
	public static String PROP_STATUS = "status";
	public static String PROP_POS_ERR_DISC = "posErrDisc";
	public static String PROP_TRADE_TYPE = "tradeType";
	public static String PROP_RELATE_TRANSNO = "relateTransno";
	public static String PROP_MERCHANT_ID = "merchantId";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_TICKET_NO = "ticketNo";
	public static String PROP_ERR_CODE = "errCode";
	public static String PROP_POS_ID = "posId";
	public static String PROP_MERCHANT_TRANS_NO = "merchantTransNo";
	public static String PROP_REMARK = "remark";
	public static String PROP_POS_TRADE_TYPE = "posTradeType";
	public static String PROP_ERR_DISC = "errDisc";
	public static String PROP_TRANS_DATE = "transDate";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long transNo;

	// fields
	private java.lang.Long systemId;
	private java.lang.String systemCode;
	private java.lang.Long merchantId;
	private java.lang.String merchantCode;
	private java.lang.Long posId;
	private java.lang.String posCode;
	private java.lang.Long posTransNo;
	private java.lang.String merchantTransNo;
	private java.lang.Long cardTransno;
	private java.lang.Long tradeType;
	private java.lang.String ticketNo;
	private java.lang.String rnd;
	private java.lang.Long amount;
	private java.lang.String status;
	private java.util.Date transDate;
	private java.lang.Long relateTransno;
	private java.lang.String errCode;
	private java.lang.String errDisc;
	private java.lang.String posErrCode;
	private java.lang.String posErrDisc;
	private java.lang.String posTradeType;
	private java.util.Date createTime;
	private java.lang.String remark;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="TRANS_NO"
     */
	public java.lang.Long getTransNo () {
		return transNo;
	}
    public java.lang.Long getId () {
		return transNo;
	}
	/**
	 * Set the unique identifier of this class
	 * @param transNo the new ID
	 */
	public void setTransNo (java.lang.Long transNo) {
		this.transNo = transNo;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long transNo) {
		this.transNo = transNo;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: SYSTEM_ID
	 */
	public java.lang.Long getSystemId () {
		return systemId;
	}

	/**
	 * Set the value related to the column: SYSTEM_ID
	 * @param systemId the SYSTEM_ID value
	 */
	public void setSystemId (java.lang.Long systemId) {
		this.systemId = systemId;
	}



	/**
	 * Return the value associated with the column: SYSTEM_CODE
	 */
	public java.lang.String getSystemCode () {
		return systemCode;
	}

	/**
	 * Set the value related to the column: SYSTEM_CODE
	 * @param systemCode the SYSTEM_CODE value
	 */
	public void setSystemCode (java.lang.String systemCode) {
		this.systemCode = systemCode;
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
	 * Return the value associated with the column: POS_ID
	 */
	public java.lang.Long getPosId () {
		return posId;
	}

	/**
	 * Set the value related to the column: POS_ID
	 * @param posId the POS_ID value
	 */
	public void setPosId (java.lang.Long posId) {
		this.posId = posId;
	}



	/**
	 * Return the value associated with the column: POS_CODE
	 */
	public java.lang.String getPosCode () {
		return posCode;
	}

	/**
	 * Set the value related to the column: POS_CODE
	 * @param posCode the POS_CODE value
	 */
	public void setPosCode (java.lang.String posCode) {
		this.posCode = posCode;
	}



	/**
	 * Return the value associated with the column: POS_TRANS_NO
	 */
	public java.lang.Long getPosTransNo () {
		return posTransNo;
	}

	/**
	 * Set the value related to the column: POS_TRANS_NO
	 * @param posTransNo the POS_TRANS_NO value
	 */
	public void setPosTransNo (java.lang.Long posTransNo) {
		this.posTransNo = posTransNo;
	}



	/**
	 * Return the value associated with the column: MERCHANT_TRANS_NO
	 */
	public java.lang.String getMerchantTransNo () {
		return merchantTransNo;
	}

	/**
	 * Set the value related to the column: MERCHANT_TRANS_NO
	 * @param merchantTransNo the MERCHANT_TRANS_NO value
	 */
	public void setMerchantTransNo (java.lang.String merchantTransNo) {
		this.merchantTransNo = merchantTransNo;
	}



	/**
	 * Return the value associated with the column: CARD_TRANSNO
	 */
	public java.lang.Long getCardTransno () {
		return cardTransno;
	}

	/**
	 * Set the value related to the column: CARD_TRANSNO
	 * @param cardTransno the CARD_TRANSNO value
	 */
	public void setCardTransno (java.lang.Long cardTransno) {
		this.cardTransno = cardTransno;
	}



	/**
	 * Return the value associated with the column: TRADE_TYPE
	 */
	public java.lang.Long getTradeType () {
		return tradeType;
	}

	/**
	 * Set the value related to the column: TRADE_TYPE
	 * @param tradeType the TRADE_TYPE value
	 */
	public void setTradeType (java.lang.Long tradeType) {
		this.tradeType = tradeType;
	}



	/**
	 * Return the value associated with the column: TICKET_NO
	 */
	public java.lang.String getTicketNo () {
		return ticketNo;
	}

	/**
	 * Set the value related to the column: TICKET_NO
	 * @param ticketNo the TICKET_NO value
	 */
	public void setTicketNo (java.lang.String ticketNo) {
		this.ticketNo = ticketNo;
	}



	/**
	 * Return the value associated with the column: RND
	 */
	public java.lang.String getRnd () {
		return rnd;
	}

	/**
	 * Set the value related to the column: RND
	 * @param rnd the RND value
	 */
	public void setRnd (java.lang.String rnd) {
		this.rnd = rnd;
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
	 * Return the value associated with the column: TRANS_DATE
	 */
	public java.util.Date getTransDate () {
		return transDate;
	}

	/**
	 * Set the value related to the column: TRANS_DATE
	 * @param transDate the TRANS_DATE value
	 */
	public void setTransDate (java.util.Date transDate) {
		this.transDate = transDate;
	}



	/**
	 * Return the value associated with the column: RELATE_TRANSNO
	 */
	public java.lang.Long getRelateTransno () {
		return relateTransno;
	}

	/**
	 * Set the value related to the column: RELATE_TRANSNO
	 * @param relateTransno the RELATE_TRANSNO value
	 */
	public void setRelateTransno (java.lang.Long relateTransno) {
		this.relateTransno = relateTransno;
	}



	/**
	 * Return the value associated with the column: ERR_CODE
	 */
	public java.lang.String getErrCode () {
		return errCode;
	}

	/**
	 * Set the value related to the column: ERR_CODE
	 * @param errCode the ERR_CODE value
	 */
	public void setErrCode (java.lang.String errCode) {
		this.errCode = errCode;
	}



	/**
	 * Return the value associated with the column: ERR_DISC
	 */
	public java.lang.String getErrDisc () {
		return errDisc;
	}

	/**
	 * Set the value related to the column: ERR_DISC
	 * @param errDisc the ERR_DISC value
	 */
	public void setErrDisc (java.lang.String errDisc) {
		this.errDisc = errDisc;
	}



	/**
	 * Return the value associated with the column: POS_ERR_CODE
	 */
	public java.lang.String getPosErrCode () {
		return posErrCode;
	}

	/**
	 * Set the value related to the column: POS_ERR_CODE
	 * @param posErrCode the POS_ERR_CODE value
	 */
	public void setPosErrCode (java.lang.String posErrCode) {
		this.posErrCode = posErrCode;
	}



	/**
	 * Return the value associated with the column: POS_ERR_DISC
	 */
	public java.lang.String getPosErrDisc () {
		return posErrDisc;
	}

	/**
	 * Set the value related to the column: POS_ERR_DISC
	 * @param posErrDisc the POS_ERR_DISC value
	 */
	public void setPosErrDisc (java.lang.String posErrDisc) {
		this.posErrDisc = posErrDisc;
	}



	/**
	 * Return the value associated with the column: POS_TRADE_TYPE
	 */
	public java.lang.String getPosTradeType () {
		return posTradeType;
	}

	/**
	 * Set the value related to the column: POS_TRADE_TYPE
	 * @param posTradeType the POS_TRADE_TYPE value
	 */
	public void setPosTradeType (java.lang.String posTradeType) {
		this.posTradeType = posTradeType;
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
	 * Return the value associated with the column: REMARK
	 */
	public java.lang.String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (java.lang.String remark) {
		this.remark = remark;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfcsTrade)) return false;
		else {
			RfcsTrade rfcsTrade = (RfcsTrade) obj;
			if (null == this.getTransNo() || null == rfcsTrade.getTransNo()) return false;
			else return (this.getTransNo().equals(rfcsTrade.getTransNo()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getTransNo()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getTransNo().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}