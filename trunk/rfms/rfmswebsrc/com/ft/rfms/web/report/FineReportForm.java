package com.ft.rfms.web.report;

import java.util.Date;

import com.ft.commons.datetime.TimeSegment;
import com.ft.web.sm.BaseForm;

public class FineReportForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4184880037660101574L;

	private Long[] merchantIds;
	private Long[] tempMerchantIds;
	
	private TimeSegment timeSegment=new TimeSegment();

	private Date beginDate;
	private Date endDate;
	
	private int beginDay;
	private int endDay;
	
	private Long settleType;
	
	private Long settlePeriod;
	
	private String merchantName;
	private String posCode;
	private Long payStatus;
	private String cardNo;
	private Long beginMoney;
	private Long endMoney;
	private String tradeNo;
	
	/**
	 * @return the beginMoney
	 */
	public Long getBeginMoney() {
		return beginMoney;
	}

	/**
	 * @param beginMoney the beginMoney to set
	 */
	public void setBeginMoney(Long beginMoney) {
		this.beginMoney = beginMoney;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the endMoney
	 */
	public Long getEndMoney() {
		return endMoney;
	}

	/**
	 * @param endMoney the endMoney to set
	 */
	public void setEndMoney(Long endMoney) {
		this.endMoney = endMoney;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the payStatus
	 */
	public Long getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(Long payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * @return the posCode
	 */
	public String getPosCode() {
		return posCode;
	}

	/**
	 * @param posCode the posCode to set
	 */
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * @return the beginDay
	 */
	public int getBeginDay() {
		return beginDay;
	}

	/**
	 * @param beginDay the beginDay to set
	 */
	public void setBeginDay(int beginDay) {
		this.beginDay = beginDay;
	}

	/**
	 * @return the endDay
	 */
	public int getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay the endDay to set
	 */
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return the settlePeriod
	 */
	public Long getSettlePeriod() {
		return settlePeriod;
	}

	/**
	 * @param settlePeriod the settlePeriod to set
	 */
	public void setSettlePeriod(Long settlePeriod) {
		this.settlePeriod = settlePeriod;
	}

	/**
	 * @return the settleType
	 */
	public Long getSettleType() {
		return settleType;
	}

	/**
	 * @param settleType the settleType to set
	 */
	public void setSettleType(Long settleType) {
		this.settleType = settleType;
	}

	/**
	 * @return the merchantIds
	 */
	public Long[] getMerchantIds() {
		return merchantIds;
	}

	/**
	 * @param merchantIds the merchantIds to set
	 */
	public void setMerchantIds(Long[] merchantIds) {
		this.merchantIds = merchantIds;
	}

	/**
	 * @return the timeSegment
	 */
	public TimeSegment getTimeSegment() {
		return timeSegment;
	}

	/**
	 * @param timeSegment the timeSegment to set
	 */
	public void setTimeSegment(TimeSegment timeSegment) {
		this.timeSegment = timeSegment;
	}

	/**
	 * @return the tempMerchantIds
	 */
	public Long[] getTempMerchantIds() {
		return tempMerchantIds;
	}

	/**
	 * @param tempMerchantIds the tempMerchantIds to set
	 */
	public void setTempMerchantIds(Long[] tempMerchantIds) {
		this.tempMerchantIds = tempMerchantIds;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
