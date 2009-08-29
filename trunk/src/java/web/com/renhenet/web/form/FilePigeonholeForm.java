package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class FilePigeonholeForm extends BasePOForm {
	private static final long serialVersionUID = -4367797624222605057L;
	private String infoColumn;
	private String infoColumnTo;
	private int infoSortId;
	private int infoSortIdsTo;

	private int[] fileGds;
	private String year;
	private int qzh;

	public int[] getFileGds() {
		return fileGds;
	}

	public void setFileGds(int[] fileGds) {
		this.fileGds = fileGds;
	}

	public int getQzh() {
		return qzh;
	}

	public void setQzh(int qzh) {
		this.qzh = qzh;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getInfoSortIdsTo() {
		return infoSortIdsTo;
	}

	public void setInfoSortIdsTo(int infoSortIdsTo) {
		this.infoSortIdsTo = infoSortIdsTo;
	}

	public String getInfoColumn() {
		return infoColumn;
	}

	public void setInfoColumn(String infoColumn) {
		this.infoColumn = infoColumn;
	}

	public String getInfoColumnTo() {
		return infoColumnTo;
	}

	public void setInfoColumnTo(String infoColumnTo) {
		this.infoColumnTo = infoColumnTo;
	}

	public int getInfoSortId() {
		return infoSortId;
	}

	public void setInfoSortId(int infoSortId) {
		this.infoSortId = infoSortId;
	}

}
