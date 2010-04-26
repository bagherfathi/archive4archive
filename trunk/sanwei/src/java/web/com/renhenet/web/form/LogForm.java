package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class LogForm extends BasePOForm {
	private static final long serialVersionUID = 1071501234131537857L;
	
	private int userId;
	private String subject;
	  

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
