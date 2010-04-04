package com.ft.web.sm.login;

/**
 * �����Ϣ
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
     * ��½��
     * 
     * @return
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * ��½����
     * 
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * У����
     * 
     * @return
     */
    public String getValidateCode() {
        return validateCode;
    }
}
