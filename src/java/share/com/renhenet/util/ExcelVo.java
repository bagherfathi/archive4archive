package com.renhenet.util;

import java.util.List;

import com.renhenet.fw.vo.BaseVo;

public class ExcelVo extends BaseVo {
	private static final long serialVersionUID = 6029122321480612871L;

	private String[] fields;

	private String sheetName;

	private String fileTitle;

	private List<Object[]> rows;

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public List<Object[]> getRows() {
		return rows;
	}

	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
