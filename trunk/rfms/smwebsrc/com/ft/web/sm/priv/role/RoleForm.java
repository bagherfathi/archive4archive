package com.ft.web.sm.priv.role;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 角色数据表单类
 * 
 * @struts.form name = "roleForm"
 * 
 * @version 1.0
 * 
 */
public class RoleForm extends BaseValidatorForm {

    private static final long serialVersionUID = 1651201715199864178L;

    /**
     * 存放单个角色信息
     */
    private RoleDTO role;

    /**
     * 存放多个角色信息
     */
    private RoleDTO[] roles;

    /**
     * 存放多个操作员信息
     */
    private OperatorDTO[] ops;

    /**
     * 存放多个功能权限信息
     */
    private ResourceDTO[] resources;

    /**
     * 存放角色数据
     */
    private Map<String,Object> roleInfo = new HashMap<String,Object>();

    private String[] paths;

    private String act;
    
    private Long[] orgIds;
    
    private String roleName;
    
    private Long orgId;

    
    
    public Long[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(Long[] orgIds) {
		this.orgIds = orgIds;
	}

	/**
     * @struts.entity-field initial="opIds"
     * @return Returns the attach.
     */
    public OperatorDTO[] getOps() {
        return ops;
    }

    public void setOps(OperatorDTO[] ops) {
        this.ops = ops;
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
    public RoleDTO[] getRoles() {
        return roles;
    }

    public void setRoles(RoleDTO[] roles) {
        this.roles = roles;
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (this.role == null) {
            this.role = new RoleDTO();
        }
        super.reset(arg0, arg1);
    }

    /**
     * @struts.entity-field initial="ids"
     * @return Returns the attach.
     */
    public ResourceDTO[] getResources() {
        return resources;
    }

    public void setResources(ResourceDTO[] resources) {
        this.resources = resources;
    }

    public Map<String,Object> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(Map<String,Object> roleInfo) {
        this.roleInfo = roleInfo;
    }

    /**
     * @return Returns the attribute.
     */
    public Object getAttribute(String key) {
        return this.getRoleInfo().get(key);
    }

    /**
     * @param attribute
     *                The attribute to set.
     */
    public void setAttribute(String key, Object value) {
        this.getRoleInfo().put(key, value);
    }

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
    
    

}
