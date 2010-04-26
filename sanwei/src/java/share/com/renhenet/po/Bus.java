package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class Bus extends IdPersistent {
	private static final long serialVersionUID = -5259956219885086759L;
	private String name;
	private int userId;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
