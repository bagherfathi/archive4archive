package com.ft.common.login;

import com.ft.sm.dto.OperatorDTO;

/**
 * ��֤���
 * @version 1.0
 */
public class Authentication {
    // �����½����
    public static final String ERROR_LOGINNAME = "error.authenticate.identification.loginname";

    // �������
    public static final String ERROR_PASSWORD = "error.authenticate.identification.password";

    // ����״̬
    public static final String ERROR_LOCKED = "error.authenticate.identification.blocked";

    // ϵͳ����
    public static final String ERROR_SYSTEM = "error.authenticate.identification.system";

    // ��ֹ״̬
    public static final String ERROR_DISABLE = "error.authenticate.identification.disabled";

    // SSO��ֹ
    public static final String ERROR_SSO_DISABLE = "error.authenticate.identification.sso.disabled";

    // SSO��Ȩ���ʸ�Ӧ��
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
     * �������
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
     * ��֤�ɹ���־
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
     * ��֤ͨ����Ĳ���Ա��Ϣ
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
