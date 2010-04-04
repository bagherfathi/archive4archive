package com.ft.rfms.web.merchant;
// Generated 2010-4-3 21:22:47 by Hibernate Tools 3.3.0.GA

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsSms;
import com.ft.singleTable.web.BaseSimpleForm;

/**
 * RfmsEmail generated by hbm2java
 */
public class SmsForm extends BaseSimpleForm {

	private Long id;
	private String mobile;
	private String message;
	private Character status;
	private Date sendDate;
	private Date createDate;
	private Date updateDate;
	private Long operatorId;

	public SmsForm() {
	}

	public SmsForm(Long id) {
		this.id = id;
	}

	public SmsForm(Long id, String mobile, String message, Character status,
			Date sendDate, Date createDate, Date updateDate, Long operatorId) {
		this.id = id;
		this.mobile = mobile;
		this.message = message;
		this.status = status;
		this.sendDate = sendDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operatorId = operatorId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		this.setBaseEntity(new RfmsSms());
		this.setSearchObj(new RfmsSms());
	}

}
