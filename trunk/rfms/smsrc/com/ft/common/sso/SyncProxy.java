/*
package com.ft.common.sso;

import com.ft.common.exception.CommonRuntimeException;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.common.EntityConverter;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.utils.encode.Md5PasswordEncoder;
import com.ft.sso.agent.SSOClientFactory;
import com.ft.sso.agent.client.DataAccessClient;
import com.ft.sso.agent.client.DataAccessException;
import com.ft.sso.api.dto.OperatorVO;
import com.ft.sso.api.dto.OrganizationVO;

*//**
 * ����ͬ������.
 * 
 * ����SSO Agent������ͬ����SSOϵͳ��.
 * 
 * 
 * @spring.bean id="syncProxy"
 *//*
public class SyncProxy {
    private static Log logger = LogFactory.getLog(SyncProxy.class);

    public static final String ERROR_CODE_SYNC_ORG = "sm.error.synchronize.org";

    public static final String ERROR_CODE_SYNC_OP = "sm.error.synchronize.op";

    private SSOClientFactory ssoClientFactory;

    private DataAccessClient dataAccessClient;

    // ͬ������
    private boolean synOn;

    *//**
     * @spring.property ref="ssoClientFactory"
     * @param ssoClientFactory
     *                The ssoClientFactory to set.
     *//*
    public void setSsoClientFactory(SSOClientFactory ssoClientFactory) {
        this.ssoClientFactory = ssoClientFactory;
    }

    *//**
     * @spring.property value="${data.syn.enable}"
     * @param synOn
     *                The synOn to set.
     *//*
    public void setSynOn(boolean synOn) {
        this.synOn = synOn;
    }

    private DataAccessClient getDataAccessClient() {
        if (this.dataAccessClient == null)
            this.dataAccessClient = this.ssoClientFactory
                    .createDataAccessClient();

        return this.dataAccessClient;
    }

    *//**
     * ��ӵ�SSOϵͳ��
     * 
     * @param operator
     * @return
     *//*
    public void addOperator(OperatorDTO operator, OrganizationDTO org) {
        if (!this.synOn)
            return;

        operator.setOrgSSOCode(org.getSsoCode());
        OperatorVO vo = EntityConverter.convertOperator2VO(operator);

        String ssoCode;
        try {
            vo.setPassword(Md5PasswordEncoder.encode(vo.getPassword())); 
            ssoCode = this.getDataAccessClient().addOperator(vo);
            operator.setSsoCode(ssoCode);
        } catch (DataAccessException e) {
            logger.error("Synchronization error:add operator failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_OP, e);
        }
    }

    *//**
     * �����֯��Ϣ��SSOϵͳ��
     * 
     * @param org
     * @return
     *//*
    public void addOrg(OrganizationDTO org, OrganizationDTO parent) {
        if (!this.synOn)
            return;

        if (parent != null) {
            org.setParentSSOCode(parent.getSsoCode());
        }

        OrganizationVO orgVo = EntityConverter.convertOrganization2VO(org);
        String ssoCode;
        try {
            ssoCode = this.getDataAccessClient().addOrganization(orgVo);
            org.setSsoCode(ssoCode);
        } catch (DataAccessException e) {
            logger.error("Synchronization error:add organization failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_ORG, e);
        }
    }

    *//**
     * ����SSOϵͳ�в���Ա��Ϣ
     * 
     * @param operator
     * @return
     *//*
    public void updateOperator(OperatorDTO operator, OrganizationDTO org) {

        if (!this.synOn)
            return;

        operator.setOrgSSOCode(org.getSsoCode());
        OperatorVO vo = EntityConverter.convertOperator2VO(operator);

        try {
            this.getDataAccessClient().updateOperator(vo);
        } catch (DataAccessException e) {
            logger.error("Synchronization error:update operator failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_OP, e);
        }
    }

    *//**
     * ����SSOϵͳ����֯��Ϣ
     * 
     * @param org
     * @return
     *//*
    public void updateOrg(OrganizationDTO org, OrganizationDTO parent) {
        if (!this.synOn)
            return;

        if (parent != null) {
            org.setParentSSOCode(parent.getSsoCode());
        }

        OrganizationVO orgVo = EntityConverter.convertOrganization2VO(org);

        try {
            this.getDataAccessClient().updateOrganization(orgVo);
        } catch (DataAccessException e) {
            logger
                    .error("Synchronization error:update organization failed.",
                            e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_ORG, e);
        }
    }

    *//**
     * ��ֹ����Ա
     * 
     * @param operator
     * @return
     *//*
    public void disableOperator(OperatorDTO operator) {
        // ����/����״̬��ͬ����SSO
        
         * if (!this.synOn) return;
         * 
         * try {
         * this.getDataAccessClient().disableOperator(operator.getSsoCode()); }
         * catch (DataAccessException e) { logger.error("Synchronization
         * error:disable operator failed.", e); throw new SyncException(e); }
         
    }

    *//**
     * ��ֹ��֯
     * 
     * @param org
     * @return
     *//*
    public void disableOrganization(OrganizationDTO org) {
        // ����/����״̬��ͬ����SSO
        
         * if (!this.synOn) return;
         * 
         * try {
         * this.getDataAccessClient().disableOrganization(org.getSsoCode()); }
         * catch (DataAccessException e) { logger.error("Synchronization
         * error:disable organization failed.", e); throw new SyncException(e); }
         
    }

    *//**
     * ���²���Ա����
     * 
     * @param operator
     * @param newPassword
     * @return
     *//*
    public void updatePassword(OperatorDTO operator, String newPassword) {
        if (!this.synOn)
            return;

        try {
            this.getDataAccessClient().updatePassword(operator.getSsoCode(),
                    newPassword);
        } catch (DataAccessException e) {
            logger.error("Synchronization error:change password failed.", e);
            throw new CommonRuntimeException(ERROR_CODE_SYNC_OP, e);
        }
    }

    *//**
     * �������Ա
     * 
     * @param operator
     * @return
     *//*
    public void enableOperator(OperatorDTO operator) {
        // ����/����״̬��ͬ����SSO
        
         * if (!this.synOn) return;
         * 
         * try {
         * this.getDataAccessClient().enableOperator(operator.getSsoCode()); }
         * catch (DataAccessException e) { logger.error("Synchronization
         * error:enable operator failed", e); throw new SyncException(e); }
         
    }

    *//**
     * �����֯
     * 
     * @param org
     * @return
     *//*
    public void enableOrganization(OrganizationDTO org) {
        // ����/����״̬��ͬ����SSO
        
         * if (!this.synOn) return;
         * 
         * try {
         * this.getDataAccessClient().enableOrganization(org.getSsoCode()); }
         * catch (DataAccessException e) { logger.error("Synchronization
         * error:enbale organization failed", e); throw new SyncException(e); }
         
    }
}
*/