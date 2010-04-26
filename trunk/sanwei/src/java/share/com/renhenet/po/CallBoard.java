package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class CallBoard extends IdPersistent {
	private static final long serialVersionUID = 1071501234131537857L;
	private String subject;
	private int userId;
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
