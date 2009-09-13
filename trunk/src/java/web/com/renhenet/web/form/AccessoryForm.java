package com.renhenet.web.form;

import org.apache.struts.upload.FormFile;

import com.renhenet.fw.waf.BasePOForm;

public class AccessoryForm extends BasePOForm {
	private static final long serialVersionUID = 4691757313112076967L;
	private FormFile accessoryFiles;
	private String oldName;
	private String newName;
	private int fileId;

	public FormFile getAccessoryFiles() {
		return accessoryFiles;
	}

	public void setAccessoryFiles(FormFile accessoryFiles) {
		this.accessoryFiles = accessoryFiles;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
}
