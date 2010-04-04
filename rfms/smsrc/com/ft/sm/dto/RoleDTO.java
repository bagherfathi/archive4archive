package com.ft.sm.dto;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Role;

/**
 * Class comments.
 * 
 */
public class RoleDTO implements DTO {
    private static final long serialVersionUID = -7503322845227644364L;

    // 角色类型
    public static final long ROLE_TYPE_FUNCTION = 1; // 功能角色

    public static final long ROLE_TYPE_DATA = 2; // 业务角色
    
    //虚拟的功能角色，用于实现对操作员直接赋功能权限
    public static final long ROLE_TYPE_VIRTUAL_FUNCTION = 3;
    
    //虚拟的业务角色，用于实现对操作员直接赋业务权限
    public static final long ROLE_TYPE_VIRTUAL_DATA = 4;

    private Role role;
    
    private Operator op;
    

    public RoleDTO() {
        this.role = new Role();
    }

    public RoleDTO(Role role) {
        this.role = role;
    }
    
    public RoleDTO(Role role,Operator op) {
        this.role = role;
        this.op = op;
    }
    
    /**
     * 所属组织ID
     * @return
     */
    public Long getOrgId(){
        return new Long(this.role.getOrgId());	
    }
    
    public void setOrgId(Long orgId){
    	this.role.setOrgId(orgId.longValue());
    }
    
    /**
     * 创建者ID
     * @return
     */
    public Long getOperatorId(){
    	return new Long(this.role.getOperatorId());
    }
    
    public void setOperatorId(Long opId){
        this.role.setOperatorId(opId.longValue());
    }

    /**
     * 角色描述信息。
     * 
     * @return
     */
    public String getDescription() {
        return this.role.getRoleDesc();
    }

    public void setDescription(String description) {
        this.role.setRoleDesc(description);
    }

    /**
     * 角色ID。
     * 
     * @return Returns the id.
     */
    public Long getRoleId() {
        return new Long(this.role.getRoleId());
    }

    /**
     * 角色名称。
     * 
     * @return
     */
    public String getRoleName() {
        return this.role.getRoleName();
    }

    public void setRoleName(String roleName) {
        this.role.setRoleName(roleName);
    }

    /**
     * 角色类型 类型：1表示功能角色,2表示业务角色。
     * 
     * 
     */
    public long getType() {
        return this.role.getRoleType();
    }

    public void setType(long type) {
        this.role.setRoleType(type);
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     *                the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.role;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.role = (Role) target;
    }

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

}
