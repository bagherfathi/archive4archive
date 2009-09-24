package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class DhszForm extends BasePOForm {
	private static final long serialVersionUID = 4691757313112076967L;
	private String title;
	private int len;
	private int infoSortId;
	private int structureId;
	private int taxis;

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getInfoSortId() {
		return infoSortId;
	}

	public void setInfoSortId(int infoSortId) {
		this.infoSortId = infoSortId;
	}

	public int getTaxis() {
		return taxis;
	}

	public void setTaxis(int taxis) {
		this.taxis = taxis;
	}

}
