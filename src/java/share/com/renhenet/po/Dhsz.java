package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class Dhsz extends IdPersistent implements java.io.Serializable {
	private static final long serialVersionUID = 1133707058388137297L;

	private String title;
	private int len;
	private int infoSortId;
	private int structureId;
	private int taxis;
	private Structure structure;

	public int getTaxis() {
		return taxis;
	}

	public void setTaxis(int taxis) {
		this.taxis = taxis;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

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

}
