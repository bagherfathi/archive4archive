package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class ResourcesForm extends BasePOForm {
	private static final long serialVersionUID = -7596087538445117323L;

	private String type;

	private String key;

	private String value;

	private Integer createdDate;

	private Integer lastUpdatedDate;

	public Integer getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Integer lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
