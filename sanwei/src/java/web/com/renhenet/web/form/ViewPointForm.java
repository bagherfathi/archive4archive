package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class ViewPointForm extends BasePOForm {
	
	private int userId;
	private String name;
	private String content;
	private Double placeZ;
	private Double placeY;
	private Double placeX;
	private Double pitch;
	private Double rotate;
	  

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Double getPlaceZ() {
		return placeZ;
	}

	public void setPlaceZ(Double placeZ) {
		this.placeZ = placeZ;
	}

	public Double getPlaceY() {
		return placeY;
	}

	public void setPlaceY(Double placeY) {
		this.placeY = placeY;
	}

	public Double getPlaceX() {
		return placeX;
	}

	public void setPlaceX(Double placeX) {
		this.placeX = placeX;
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
