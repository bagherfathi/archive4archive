package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class InfoSortForm extends BasePOForm {
	private static final long serialVersionUID = 6700588647849845079L;

    private int[] listseq;

    public int[] getListseq() {
        return listseq;
    }

    public void setListseq(int[] listseq) {
        this.listseq = listseq;
    }

    private String name;

	private String seq;

	private int type;

	private int taxis;

	private int parentId;

	private String oneFloor;

	private String twoFloor;

	private String threeFloor;

	private int status;

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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
