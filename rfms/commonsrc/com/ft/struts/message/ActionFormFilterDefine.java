package com.ft.struts.message;

import java.util.ArrayList;
import java.util.List;


public class ActionFormFilterDefine {
	private String name;
	private List  attributes ;
	
	public ActionFormFilterDefine(){
		attributes = new ArrayList(5);
	}
	public List getAttributes() {
		return attributes;
	}
	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
