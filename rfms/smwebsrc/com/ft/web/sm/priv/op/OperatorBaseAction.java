package com.ft.web.sm.priv.op;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.ft.busi.sm.model.GroupManager;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.web.sm.BaseAction;

/**
 * 操作员基础维护类
 * 
 * @version 1.0
 */
public class OperatorBaseAction extends BaseAction {
    protected OperatorManager opMgmt;

    protected RoleManager roleManager;

    protected GroupManager groupManager;

    protected OrgManager orgManager;

    //protected SyncProxy syncProxy;

    /**
     * @spring.property ref="operatorManager"
     * @param opMgmt
     */
    public void setOpMgmt(OperatorManager opMgmt) {
        this.opMgmt = opMgmt;
    }

    /**
     * @spring.property ref="roleManager"
     */
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * @spring.property ref="groupManager"
     * @param groupManager
     */
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref="syncProxy"
     * @param syncProxy
     *                The syncProxy to set.
     */
    //public void setSyncProxy(SyncProxy syncProxy) {
        //this.syncProxy = syncProxy;
    //}
    
    protected String plusParams(OperatorBaseForm opForm) throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();
        url.append("orgId_s=").append(opForm.getOrgId_s().toString());
        url.append("&loginName=").append(URLEncoder.encode(opForm.getLoginName(),"GBK"));
        url.append("&name=").append(URLEncoder.encode(opForm.getName(),"GBK"));
        url.append("&listOp_p=").append(opForm.getListOp_p());
        return url.toString();
    }
}
