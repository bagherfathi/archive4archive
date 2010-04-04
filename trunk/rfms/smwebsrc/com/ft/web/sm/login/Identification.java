package com.ft.web.sm.login;

/**
 * 身份信息
 * 
 * @version 1.0
 */
public class Identification {
    private String loginName;

    private String password;

    private String validateCode;

    public Identification(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public Identification(String loginName, String password, String validateCode) {
        this.loginName = loginName;
        this.password = password;
        this.validateCode = validateCode;
    }

    /**
     * 登陆名
     * 
     * @return
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登陆密码
     * 
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 校验码
     * 
     * @return
     */
    public String getValidateCode() {
        return validateCode;
    }
}
