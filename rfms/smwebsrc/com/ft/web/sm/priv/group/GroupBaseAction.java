package com.ft.web.sm.priv.group;

import com.ft.busi.sm.model.GroupManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.web.sm.BaseAction;

/**
 * 操作员组基础类
 * 
 * @version 1.0
 */
public class GroupBaseAction extends BaseAction {

    protected GroupManager groupManager;

    protected OrgManager orgManager;

    protected RoleManager roleManager;
    

    /**
     * @spring.property ref="groupManager"
     * @param groupManager
     */
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    /**
     * @spring.property ref="roleManager"
     */
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    
}
