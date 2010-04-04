package com.ft.rfms.web.merchant;

// Generated 2010-4-3 21:22:47 by Hibernate Tools 3.3.0.GA

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsCardSend;
import com.ft.singleTable.web.BaseSimpleForm;

/**
 * RfmsCardSend generated by hbm2java
 */
public class CardSendForm extends BaseSimpleForm {

	private Long id;
	private Long rfmsCardId;
	private String mobile;
	private Character status;
	private Long createOperatorId;
	private Date createSendDate;
	private Long updateOperatorId;
	private Date updateSendDate;
	private Date createDate;
	private Date updateDate;
	private Long operatorId;

	public CardSendForm() {
	}

	public CardSendForm(Long id) {
		this.id = id;
	}

	public CardSendForm(Long id, Long rfmsCardId, String mobile,
			Character status, Long createOperatorId, Date createSendDate,
			Long updateOperatorId, Date updateSendDate, Date createDate,
			Date updateDate, Long operatorId) {
		this.id = id;
		this.rfmsCardId = rfmsCardId;
		this.mobile = mobile;
		this.status = status;
		this.createOperatorId = createOperatorId;
		this.createSendDate = createSendDate;
		this.updateOperatorId = updateOperatorId;
		this.updateSendDate = updateSendDate;
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

	public Long getRfmsCardId() {
		return this.rfmsCardId;
	}

	public void setRfmsCardId(Long rfmsCardId) {
		this.rfmsCardId = rfmsCardId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Long getCreateOperatorId() {
		return this.createOperatorId;
	}

	public void setCreateOperatorId(Long createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public Date getCreateSendDate() {
		return this.createSendDate;
	}

	public void setCreateSendDate(Date createSendDate) {
		this.createSendDate = createSendDate;
	}

	public Long getUpdateOperatorId() {
		return this.updateOperatorId;
	}

	public void setUpdateOperatorId(Long updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public Date getUpdateSendDate() {
		return this.updateSendDate;
	}

	public void setUpdateSendDate(Date updateSendDate) {
		this.updateSendDate = updateSendDate;
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
		this.setBaseEntity(new RfmsCardSend());
		this.setSearchObj(new RfmsCardSend());
	}

}