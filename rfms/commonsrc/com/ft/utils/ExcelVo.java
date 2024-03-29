package com.ft.utils;

import java.util.List;

public class ExcelVo extends BaseVo {
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
