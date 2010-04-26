package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class MemberForm extends BasePOForm {
	private static final long serialVersionUID = -5426753310947380570L;

	private String loginName;

	private String password;

	private String isAdmin;

	private Integer state;

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

}
