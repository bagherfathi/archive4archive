package com.ft.common.login;

import com.ft.sm.dto.OperatorDTO;

/**
 * 认证结果
 * @version 1.0
 */
public class Authentication {
    // 错误登陆名称
    public static final String ERROR_LOGINNAME = "error.authenticate.identification.loginname";

    // 密码错误
    public static final String ERROR_PASSWORD = "error.authenticate.identification.password";

    // 锁定状态
    public static final String ERROR_LOCKED = "error.authenticate.identification.blocked";

    // 系统错误
    public static final String ERROR_SYSTEM = "error.authenticate.identification.system";

    // 禁止状态
    public static final String ERROR_DISABLE = "error.authenticate.identification.disabled";

    // SSO禁止
    public static final String ERROR_SSO_DISABLE = "error.authenticate.identification.sso.disabled";

    // SSO无权访问该应用
    public static final String ERROR_NOT_ACCESS = "error.authenticate.identification.not.access";

    private OperatorDTO principal;

    private String code;

    private boolean success;

    public Authentication(String code, boolean success) {
        this.code = code;
        this.success = success;
    }

    public Authentication(String code, boolean success, OperatorDTO principal) {
        this.code = code;
        this.success = success;
        this.principal = principal;
    }

    /**
     * 错误代码
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 认证成功标志
     * 
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 认证通过后的操作员信息
     * 
     * @return
     */
    public OperatorDTO getPrincipal() {
        return principal;
    }

    public void setPrincipal(OperatorDTO principal) {
        this.principal = principal;
    }
}
