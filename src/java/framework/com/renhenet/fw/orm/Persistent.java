package com.renhenet.fw.orm;

import java.io.Serializable;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class Persistent implements Serializable {

	private Date timeModified;

	private Date timeCreate;

	private Integer lastUpdatedDate;

	private Integer createdDate;

	public Integer getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Integer createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Integer lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeModified() {
		return timeModified;
	}

	public void setTimeModified(Date timeModified) {
		this.timeModified = timeModified;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE).toString();
	}

}
