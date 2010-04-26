package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class BusStationForm extends BasePOForm {

	private int userId;
	private int busId;
	private String name;
	private String content;
	private double placeX;
	private double placeY;
	private double placeZ;
	private double pitch;
	private double rotate;
	private double driveUp;
	private double lod;
	private int color;
	private int bkColor;
	private String labelId;
	private String labelName;
	private String labelIcon;
	private String labelMemo;
	private String labelFont;
	private String remark;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public double getPlaceX() {
		return placeX;
	}

	public void setPlaceX(double placeX) {
		this.placeX = placeX;
	}

	public double getPlaceY() {
		return placeY;
	}

	public void setPlaceY(double placeY) {
		this.placeY = placeY;
	}

	public double getPlaceZ() {
		return placeZ;
	}

	public void setPlaceZ(double placeZ) {
		this.placeZ = placeZ;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getRotate() {
		return rotate;
	}

	public void setRotate(double rotate) {
		this.rotate = rotate;
	}

	public double getDriveUp() {
		return driveUp;
	}

	public void setDriveUp(double driveUp) {
		this.driveUp = driveUp;
	}

	public double getLod() {
		return lod;
	}

	public void setLod(double lod) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
