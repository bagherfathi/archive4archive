package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class InfoSort extends IdPersistent implements java.io.Serializable {
	private static final long serialVersionUID = -1977247821642969808L;

	private String name;

	private String seq;

	// 顶层分类，中间分类，底层分类
	private int type;

	// 几层分类
	private int status;

	private int taxis;

	private int parentId;

	private String oneFloor;

	private String twoFloor;

	private String threeFloor;

	private int copy;

	public int getCopy() {
		return copy;
	}

	public void setCopy(int copy) {
		this.copy = copy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOneFloor() {
		return oneFloor;
	}

	public void setOneFloor(String oneFloor) {
		this.oneFloor = oneFloor;
	}

	public String getTwoFloor() {
		return twoFloor;
	}

	public void setTwoFloor(String twoFloor) {
		this.twoFloor = twoFloor;
	}

	public String getThreeFloor() {
		return threeFloor;
	}

	public void setThreeFloor(String threeFloor) {
		this.threeFloor = threeFloor;
	}

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