package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class BusForm extends BasePOForm {
	
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
