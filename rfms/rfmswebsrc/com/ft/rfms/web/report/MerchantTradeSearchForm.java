/**
 * 
 */
package com.ft.rfms.web.report;

import com.ft.web.sm.BaseForm;

/**
 * @author soler
 *
 */
public class MerchantTradeSearchForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845764145882312191L;
	
	private String beginDate;
	private String endDate;
	private String posCode;
	private String tradeCode;
	private String cardCode;
	private String tradeStatus;
	private String minMoney;
	private String maxMoney;

	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the cardCode
	 */
	public String getCardCode() {
		return cardCode;
	}
	/**
	 * @param cardCode the cardCode to set
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	/**
	 * @return the maxMoney
	 */
	public String getMaxMoney() {
		return maxMoney;
	}
	/**
	 * @param maxMoney the maxMoney to set
	 */
	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
	}
	/**
	 * @return the minMoney
	 */
	public String getMinMoney() {
		return minMoney;
	}
	/**
	 * @param minMoney the minMoney to set
	 */
	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
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
	 * @return the tradeCode
	 */
	public String getTradeCode() {
		return tradeCode;
	}
	/**
	 * @param tradeCode the tradeCode to set
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	/**
	 * @return the tradeStatus
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}
	/**
	 * @param tradeStatus the tradeStatus to set
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	

}
