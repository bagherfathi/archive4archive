package com.ft.rfms.web.merchant;

// Generated 2010-3-31 17:04:50 by Hibernate Tools 3.3.0.GA

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.singleTable.web.BaseSimpleForm;

/**
 * RfmsTicket generated by hbm2java
 */
public class TicketDetailForm extends BaseSimpleForm {
	private static final long serialVersionUID = -4253519821578386058L;
	private String mobiles;
	private String mobiles1;
	private RfmsTicket rfmsTicket;
	private int type;
	private FormFile strFile;
	private String ticketSerial;
	private String mobile;

	public String getTicketSerial() {
		return ticketSerial;
	}

	public void setTicketSerial(String ticketSerial) {
		this.ticketSerial = ticketSerial;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public FormFile getStrFile() {
		return strFile;
	}

	public void setStrFile(FormFile strFile) {
		this.strFile = strFile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public RfmsTicket getRfmsTicket() {
		return rfmsTicket;
	}

	public void setRfmsTicket(RfmsTicket rfmsTicket) {
		this.rfmsTicket = rfmsTicket;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		this.setBaseEntity(new RfmsTicketDetail());
		this.setSearchObj(new RfmsTicketDetail());
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getMobiles1() {
		return mobiles1;
	}

	public void setMobiles1(String mobiles1) {
		this.mobiles1 = mobiles1;
	}

}
