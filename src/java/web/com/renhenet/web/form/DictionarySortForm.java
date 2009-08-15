package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

@SuppressWarnings("serial")
public class DictionarySortForm extends BasePOForm {
	private String name;

	private String seq;

	private int defaultValue;

	private int ifDelete;

	private int taxis;

	private int parentId;

	private String memo;

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

	public int getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(int ifDelete) {
		this.ifDelete = ifDelete;
	}

	public int getTaxis() {
		return taxis;
	}

	public void setTaxis(int taxis) {
		this.taxis = taxis;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
