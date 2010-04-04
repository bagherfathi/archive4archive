package com.ft.web.sm.priv.role;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 业务权限类角色数据类
 * 
 * @struts.form name="dataRoleForm"
 * 
 * @version 1.0
 */
public class DataRoleForm extends BaseValidatorForm {

    private static final long serialVersionUID = -5018910602812743648L;

    /**
     * 存放单个角色信息
     */
    private RoleDTO role;

    /**
     * 存放多个角色信息
     */
    private RoleDTO[] roles;

    /**
     * 存放多条业务权限条目数据
     */
    private DataResourceEntryDTO[] entrys;

    private String act;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (null == role) {
            role = new RoleDTO();
        }
        super.reset(arg0, arg1);
    }

    /**
     * @struts.entity-field initial="id"
     * @return Returns the attach.
     */
    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    /**
     * @struts.entity-field initial="ids"
     * @return Returns the attach.
     */
    public DataResourceEntryDTO[] getEntrys() {
        return entrys;
    }

    public void setEntrys(DataResourceEntryDTO[] entrys) {
        this.entrys = entrys;
    }

    /**
     * @struts.entity-field initial="roleIds"
     * @return Returns the attach.
     */
    public RoleDTO[] getRoles() {
        return roles;
    }

    public void setRoles(RoleDTO[] roles) {
        this.roles = roles;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

}
