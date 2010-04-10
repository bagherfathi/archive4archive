package com.ft.rfms.web.merchant;

// Generated 2010-4-3 21:22:47 by Hibernate Tools 3.3.0.GA

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.rfms.entity.RfmsMember;
import com.ft.singleTable.web.BaseSimpleForm;

public class MemberForm extends BaseSimpleForm {
	private static final long serialVersionUID = 8681188861522749631L;

	private FormFile strFile;

	public FormFile getStrFile() {
		return strFile;
	}

	public void setStrFile(FormFile strFile) {
		this.strFile = strFile;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		this.setBaseEntity(new RfmsMember());
		this.setSearchObj(new RfmsMember());
	}

}
