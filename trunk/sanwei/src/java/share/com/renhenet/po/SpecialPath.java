package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class SpecialPath extends IdPersistent {
	private static final long serialVersionUID = 1071501234131537857L;
	
	private int userId;
	private String fileName;
	private String filePath;
	private String content;
	private String picture;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
