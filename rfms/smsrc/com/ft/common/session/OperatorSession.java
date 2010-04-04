package com.ft.common.session;

import java.util.ArrayList;
import java.util.List;

import com.ft.sm.dto.OperatorDTO;

/**
 * �û��Ự��Ϣ
 * 
 * @version 1.0
 */
public class OperatorSession {
    private String sessionId;

    private OperatorDTO loginOp; // ��ǰ��¼����Ա

    private Long loginOpCompanyId; // ��ǰ��¼����Ա�����ֹ�˾ID

    private Long loginOrgId; // ��ǰ��¼��֯ID

    private Long loginOrgCompanyId; // ��ǰ��¼��֯�����ֹ�˾ID

    private List accessRegionIdsOfLoginOrg; // ��ǰ��¼��֯�ɷ�����������ID�б�

    private List accessCompanyIdsOfLoginOrg; // ��ǰ��¼��֯�ɷ��ʹ�˾ID�б�

    private List accessBusiHallIdsOfLoginOrg; //��ǰ��¼��֯�ɷ���Ӫҵ��ID�б�
    
    private List accessBusiHallIdsOfAgent;    //�����̿ɷ���Ӫҵ��ID�б�
    
    private List accessOrgIdsOfOp; // ��ǰ��¼����Ա�ɷ�����֯ID�б�
    
    private PermissionChecker checker;

    /**
     * Ĭ�Ϲ��캯����
     */
    public OperatorSession() {
    }

    /**
     * ���캯����
     * 
     * @param sessionId
     */
    public OperatorSession(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * �����̿ɷ���Ӫҵ��ID�б�,ֻ�е�ǰ��¼��֯Ϊ����������Ч��
     * @return the accessBusiHallIdsOfAgent
     */
    public List getAccessBusiHallIdsOfAgent() {
        if(accessBusiHallIdsOfAgent == null)
            accessBusiHallIdsOfAgent = new ArrayList();
        
        return accessBusiHallIdsOfAgent;
    }

    /**
     * ��ǰ��¼��֯�ɷ���Ӫҵ��ID�б�
     * @return the accessBusiHallIdsOfLoginOrg
     */
    public List getAccessBusiHallIdsOfLoginOrg() {
        if(accessBusiHallIdsOfLoginOrg == null)
            accessBusiHallIdsOfLoginOrg = new ArrayList();
        
        return accessBusiHallIdsOfLoginOrg;
    }

    /**
     * ��ǰ��¼��֯�ɷ��ʷֹ�˾ID�б�
     * 
     * @return the accessCompanyIdsOfLoginOrg
     */
    public List getAccessCompanyIdsOfLoginOrg() {
        if (accessCompanyIdsOfLoginOrg == null)
            accessCompanyIdsOfLoginOrg = new ArrayList();

        return accessCompanyIdsOfLoginOrg;
    }

    /**
     * ��ǰ��¼����Ա�ɷ�����֯ID�б�
     * 
     * @return the accessOrgIdsOfOp
     */
    public List getAccessOrgIdsOfOp() {
        if (accessOrgIdsOfOp == null)
            accessOrgIdsOfOp = new ArrayList();

        return accessOrgIdsOfOp;
    }

    /**
     * ��ǰ��¼��֯�ɷ�����������ID�б�
     * 
     * @return the accessRegionIdsOfLoginOrg
     */
    public List getAccessRegionIdsOfLoginOrg() {
        if (accessRegionIdsOfLoginOrg == null)
            accessRegionIdsOfLoginOrg = new ArrayList();

        return accessRegionIdsOfLoginOrg;
    }

    /**
     * Ȩ�޼����
     * 
     * @return
     */
    public PermissionChecker getChecker() {
        return checker;
    }

    /**
     * ��ȡ��ǰ��¼����Ա��
     * 
     * @return the loginOp
     */
    public OperatorDTO getLoginOp() {
        return loginOp;
    }

    /**
     * ��ǰ��¼����Ա�����ֹ�˾ID��
     * 
     * @return the loginOpCompanyId
     */
    public Long getLoginOpCompanyId() {
        return loginOpCompanyId;
    }

    /**
     * ��ǰ��¼��֯�����ֹ�˾ID��
     * 
     * @return the loginOrgCompanyId
     */
    public Long getLoginOrgCompanyId() {
        return loginOrgCompanyId;
    }

    /**
     * ��ǰ��¼��֯ID��
     * 
     * @return the loginOrgId
     */
    public Long getLoginOrgId() {
        if (loginOrgId == null) {
            loginOrgId = this.accessOrgIdsOfOp.size() > 0 ? (Long) this.accessOrgIdsOfOp
                    .get(0)
                    : null;
        }

        return loginOrgId;
    }

    /**
     * �ỰΩһ��ʶ
     * 
     * @return
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param accessBusiHallIdsOfAgent the accessBusiHallIdsOfAgent to set
     */
    public void setAccessBusiHallIdsOfAgent(List accessBusiHallIdsOfAgent) {
        this.accessBusiHallIdsOfAgent = accessBusiHallIdsOfAgent;
    }

    /**
     * @param accessBusiHallIdsOfLoginOrg the accessBusiHallIdsOfLoginOrg to set
     */
    public void setAccessBusiHallIdsOfLoginOrg(List accessBusiHallIdsOfLoginOrg) {
        this.accessBusiHallIdsOfLoginOrg = accessBusiHallIdsOfLoginOrg;
    }

    /**
     * @param accessCompanyIdsOfLoginOrg
     *                the accessCompanyIdsOfLoginOrg to set
     */
    public void setAccessCompanyIdsOfLoginOrg(List accessCompanyIdsOfLoginOrg) {
        this.accessCompanyIdsOfLoginOrg = accessCompanyIdsOfLoginOrg;
    }

    /**
     * @param accessOrgIdsOfOp
     *                the accessOrgIdsOfOp to set
     */
    public void setAccessOrgIdsOfOp(List accessOrgIdsOfOp) {
        this.accessOrgIdsOfOp = accessOrgIdsOfOp;
    }

    /**
     * @param accessRegionIdsOfLoginOrg
     *                the accessRegionIdsOfLoginOrg to set
     */
    public void setAccessRegionIdsOfLoginOrg(List accessRegionIdsOfLoginOrg) {
        this.accessRegionIdsOfLoginOrg = accessRegionIdsOfLoginOrg;
    }

    public void setChecker(PermissionChecker checker) {
        this.checker = checker;
    }

    public void setLoginOp(OperatorDTO loginOp) {
        this.loginOp = loginOp;
    }

    /**
     * @param loginOpCompanyId
     *                the loginOpCompanyId to set
     */
    public void setLoginOpCompanyId(Long loginOpCompanyId) {
        this.loginOpCompanyId = loginOpCompanyId;
    }

    /**
     * @param loginOrgCompanyId
     *                the loginOrgCompanyId to set
     */
    public void setLoginOrgCompanyId(Long loginOrgCompanyId) {
        this.loginOrgCompanyId = loginOrgCompanyId;
    }

    /**
     * @param loginOrgId
     *                the loginOrgId to set
     */
    public void setLoginOrgId(Long loginOrgId) {
        this.loginOrgId = loginOrgId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
