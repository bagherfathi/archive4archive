package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class InfoSort extends IdPersistent implements
		java.io.Serializable {
	private static final long serialVersionUID = -1977247821642969808L;

	private String name;

	private String seq;

	private int type;

	private int taxis;
	
	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

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

	public int getTaxis() {
		return taxis;
	}

	public void setTaxis(int taxis) {
		this.taxis = taxis;
	}

}