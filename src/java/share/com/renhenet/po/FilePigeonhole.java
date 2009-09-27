package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class FilePigeonhole extends IdPersistent implements
		java.io.Serializable {
	private static final long serialVersionUID = 561747347747316457L;
	private String infoColumn;
	private String infoColumnTo;
	private int infoSortIdsTo;
	private int infoSortId;
	private Structure structure;

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
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

	public int getInfoSortIdsTo() {
		return infoSortIdsTo;
	}

	public void setInfoSortIdsTo(int infoSortIdsTo) {
		this.infoSortIdsTo = infoSortIdsTo;
	}

}
