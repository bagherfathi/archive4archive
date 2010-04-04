/**
 * 
 */
package com.ft.rfms.web.report;

import java.util.Date;

import com.ft.web.sm.BaseForm;

/**
 * @author soler
 *
 */
public class CardReportForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845764145882312191L;
	
	private Date beginDate=new Date();
	private Date endDate=new Date();
	
	
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
