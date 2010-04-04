package com.ft.common.session;

import java.util.ArrayList;
import java.util.List;

import com.ft.sm.dto.OperatorDTO;

/**
 * 用户会话信息
 * 
 * @version 1.0
 */
public class OperatorSession {
    private String sessionId;

    private OperatorDTO loginOp; // 当前登录操作员

    private Long loginOpCompanyId; // 当前登录操作员所属分公司ID

    private Long loginOrgId; // 当前登录组织ID

    private Long loginOrgCompanyId; // 当前登录组织所属分公司ID

    private List accessRegionIdsOfLoginOrg; // 当前登录组织可访问数据区域ID列表

    private List accessCompanyIdsOfLoginOrg; // 当前登录组织可访问公司ID列表

    private List accessBusiHallIdsOfLoginOrg; //当前登录组织可访问营业厅ID列表
    
    private List accessBusiHallIdsOfAgent;    //代理商可访问营业厅ID列表
    
    private List accessOrgIdsOfOp; // 当前登录操作员可访问组织ID列表
    
    private PermissionChecker checker;

    /**
     * 默认构造函数。
     */
    public OperatorSession() {
    }

    /**
     * 构造函数。
     * 
     * @param sessionId
     */
    public OperatorSession(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 代理商可访问营业厅ID列表,只有当前登录组织为代理商是有效。
     * @return the accessBusiHallIdsOfAgent
     */
    public List getAccessBusiHallIdsOfAgent() {
        if(accessBusiHallIdsOfAgent == null)
            accessBusiHallIdsOfAgent = new ArrayList();
        
        return accessBusiHallIdsOfAgent;
    }

    /**
     * 当前登录组织可访问营业厅ID列表。
     * @return the accessBusiHallIdsOfLoginOrg
     */
    public List getAccessBusiHallIdsOfLoginOrg() {
        if(accessBusiHallIdsOfLoginOrg == null)
            accessBusiHallIdsOfLoginOrg = new ArrayList();
        
        return accessBusiHallIdsOfLoginOrg;
    }

    /**
     * 当前登录组织可访问分公司ID列表。
     * 
     * @return the accessCompanyIdsOfLoginOrg
     */
    public List getAccessCompanyIdsOfLoginOrg() {
        if (accessCompanyIdsOfLoginOrg == null)
            accessCompanyIdsOfLoginOrg = new ArrayList();

        return accessCompanyIdsOfLoginOrg;
    }

    /**
     * 当前登录操作员可访问组织ID列表。
     * 
     * @return the accessOrgIdsOfOp
     */
    public List getAccessOrgIdsOfOp() {
        if (accessOrgIdsOfOp == null)
            accessOrgIdsOfOp = new ArrayList();

        return accessOrgIdsOfOp;
    }

    /**
     * 当前登录组织可访问数据区域ID列表。
     * 
     * @return the accessRegionIdsOfLoginOrg
     */
    public List getAccessRegionIdsOfLoginOrg() {
        if (accessRegionIdsOfLoginOrg == null)
            accessRegionIdsOfLoginOrg = new ArrayList();

        return accessRegionIdsOfLoginOrg;
    }

    /**
     * 权限检查器
     * 
     * @return
     */
    public PermissionChecker getChecker() {
        return checker;
    }

    /**
     * 获取当前登录操作员。
     * 
     * @return the loginOp
     */
    public OperatorDTO getLoginOp() {
        return loginOp;
    }

    /**
     * 当前登录操作员所属分公司ID。
     * 
     * @return the loginOpCompanyId
     */
    public Long getLoginOpCompanyId() {
        return loginOpCompanyId;
    }

    /**
     * 当前登录组织所属分公司ID。
     * 
     * @return the loginOrgCompanyId
     */
    public Long getLoginOrgCompanyId() {
        return loginOrgCompanyId;
    }

    /**
     * 当前登录组织ID。
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
     * 会话惟一标识
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
