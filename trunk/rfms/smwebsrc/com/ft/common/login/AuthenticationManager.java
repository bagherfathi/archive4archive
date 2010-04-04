package com.ft.common.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.OperatorManager;
import com.ft.sm.dto.OperatorDTO;
import com.ft.utils.encode.Md5PasswordEncoder;
import com.ft.web.sm.login.Identification;

/**
 * ʵ�ֲ���Ա�����֤.
 * 
 * @spring.bean id = "authenticationManager"
 * 
 * @version 1.0
 */
public class AuthenticationManager {
    private final Log log = LogFactory.getLog(AuthenticationManager.class);

    private OperatorManager operatorMgmt;

    public Authentication authenticate(Identification identification) {
        try {
            OperatorDTO op = operatorMgmt
                    .findOperatorByLoginName(identification.getLoginName());

            // ����Ա������
            if (op == null) {
                return new Authentication(Authentication.ERROR_LOGINNAME, false);
            }

            // ��ֹ״̬
            if (OperatorDTO.STATUS_DISABLE == op.getStatus()) {
                return new Authentication(Authentication.ERROR_DISABLE, false);
            }

            // SSO��ֹ״̬
            if (OperatorDTO.STATUS_DISABLE == op.getSsoStatus()) {
                return new Authentication(Authentication.ERROR_SSO_DISABLE,
                        false);
            }

            // SSOϵͳ����Ȩ���ʸ�Ӧ��
            if (OperatorDTO.STATUS_DISABLE == op.getSsoAccessed()) {
                return new Authentication(Authentication.ERROR_NOT_ACCESS,
                        false);
            }

            // ����״̬
            if (1 == op.getLockStatus()) {
                return new Authentication(Authentication.ERROR_LOCKED, false);
            }

            String encodedPass = Md5PasswordEncoder.encode(identification
                    .getPassword());

            // �������
            if (!encodedPass.equals(op.getPassword())) {
                return new Authentication(Authentication.ERROR_PASSWORD, false);
            }

            return new Authentication("", true, op);
        } catch (Exception ex) {
            log.error("Authenticate error", ex);
            return new Authentication(Authentication.ERROR_SYSTEM, false);
        }
    }

    /**
     * @spring.property ref="operatorManager"
     * @param operatorMgmt
     */
    public void setOperatorMgmt(OperatorManager operatorMgmt) {
        this.operatorMgmt = operatorMgmt;
    }

    public static String getBeanName() {
        return "authenticationManager";
    }
}
