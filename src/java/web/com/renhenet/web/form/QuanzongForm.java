package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class QuanzongForm extends BasePOForm {
	private static final long serialVersionUID = 6700588647849845079L;

	private String name;

	private String seq;

	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
