package com.renhenet.web.form;

import com.renhenet.fw.waf.BasePOForm;

public class LoginForm extends BasePOForm {
	private static final long serialVersionUID = 1952538525440265631L;

	private String loginName;

	private String password;

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
}
