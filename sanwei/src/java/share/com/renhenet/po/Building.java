package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

public class Building extends IdPersistent {
	
	private static final long serialVersionUID = 1071501234131537857L;
	
	private int userId;
	private String subject;
	private String content;
	private int type;
	private Double placeX;
	private Double placeY;
	private Double placeZ;
	private Double pitch;
	private Double rotate;
	
	private String labelId;
	private String labelName;
	private String labelIcon;
	private String labelMemo;
	private String labelFont;
	private Double driveUp;
	private Double lod;
	private int color;
	private int bkColor;
	private String remark;
 		

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Double getPlaceX() {
		return placeX;
	}

	public void setPlaceX(Double placeX) {
		this.placeX = placeX;
	}

	public Double getPlaceY() {
		return placeY;
	}

	public void setPlaceY(Double placeY) {
		this.placeY = placeY;
	}

	public Double getPlaceZ() {
		return placeZ;
	}

	public void setPlaceZ(Double placeZ) {
		this.placeZ = placeZ;
	}

	public Double getPitch() {
		return pitch;
	}

	public void setPitch(Double pitch) {
		this.pitch = pitch;
	}

	public Double getRotate() {
		return rotate;
	}

	public void setRotate(Double rotate) {
		this.rotate = rotate;
	}

	public Double getDriveUp() {
		return driveUp;
	}

	public void setDriveUp(Double driveUp) {
		this.driveUp = driveUp;
	}

	public Double getLod() {
		return lod;
	}

	public void setLod(Double lod) {
		this.lod = lod;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getBkColor() {
		return bkColor;
	}

	public void setBkColor(int bkColor) {
		this.bkColor = bkColor;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelIcon() {
		return labelIcon;
	}

	public void setLabelIcon(String labelIcon) {
		this.labelIcon = labelIcon;
	}

	public String getLabelMemo() {
		return labelMemo;
	}

	public void setLabelMemo(String labelMemo) {
		this.labelMemo = labelMemo;
	}

	public String getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(String labelFont) {
		this.labelFont = labelFont;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
