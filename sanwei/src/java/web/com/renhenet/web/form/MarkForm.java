package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class MarkForm extends BasePOForm {

	private int userId;
	private String name;
	private String icon;
	private String font;
	private Double placeX;
	private Double placeY;
	private Double placeZ;
	private Double lod;
	private Long color;
	private Long bkColor;
	private Double driveUp;
	private String remark;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public Double getLod() {
		return lod;
	}

	public void setLod(Double lod) {
		this.lod = lod;
	}

	public Long getColor() {
		return color;
	}

	public void setColor(Long color) {
		this.color = color;
	}

	public Long getBkColor() {
		return bkColor;
	}

	public void setBkColor(Long bkColor) {
		this.bkColor = bkColor;
	}

	public Double getDriveUp() {
		return driveUp;
	}

	public void setDriveUp(Double driveUp) {
		this.driveUp = driveUp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
