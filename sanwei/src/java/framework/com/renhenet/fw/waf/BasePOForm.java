package com.renhenet.fw.waf;

import java.io.Serializable;

public abstract class BasePOForm extends BaseForm {

	private int id;

	public Serializable getPKValue() {
		return getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
