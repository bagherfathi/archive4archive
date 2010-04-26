package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class CallBoardForm extends BasePOForm {
	private int userId;
	private String subject;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
