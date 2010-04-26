package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class FeedBack extends IdPersistent {
	private static final long serialVersionUID = 1071501234131537857L;
	
	private int userId;
	private String subject;
	private String content;
	private String reply;
	private int status;

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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
