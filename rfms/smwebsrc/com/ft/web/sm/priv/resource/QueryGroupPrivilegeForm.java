package com.ft.web.sm.priv.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.GroupDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 操作员组权限查询表单类
 * 
 * @struts.form name="queryGroupPrivilegeForm"
 * 
 * @version 1.0
 */
public class QueryGroupPrivilegeForm extends BaseValidatorForm {

    private static final long serialVersionUID = 1935460526070705091L;

    /**
     * 存放操作员组信息
     */
    private GroupDTO group;

    private String act;

    private String groupName;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == group) {
            group = new GroupDTO();
        }
    }

    /**
     * @struts.entity-field initial="gId"
     * @return
     */
    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     *                the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
