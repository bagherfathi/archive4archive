package com.renhenet.po;

import java.util.Date;

import com.renhenet.fw.orm.IdPersistent;

public class Member extends IdPersistent implements java.io.Serializable {

	private static final long serialVersionUID = 4557104755589524548L;

	private Integer id;

	private String loginName;

	private String password;

	private String isAdmin;

	private Integer state;

	private Date timeCreate;

	private Date timeModified;

	private int type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeModified() {
		return timeModified;
	}

	public void setTimeModified(Date timeModified) {
		this.timeModified = timeModified;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}