package com.ft.sm.dto;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Role;

/**
 * Class comments.
 * 
 */
public class RoleDTO implements DTO {
    private static final long serialVersionUID = -7503322845227644364L;

    // ��ɫ����
    public static final long ROLE_TYPE_FUNCTION = 1; // ���ܽ�ɫ

    public static final long ROLE_TYPE_DATA = 2; // ҵ���ɫ
    
    //����Ĺ��ܽ�ɫ������ʵ�ֶԲ���Աֱ�Ӹ�����Ȩ��
    public static final long ROLE_TYPE_VIRTUAL_FUNCTION = 3;
    
    //�����ҵ���ɫ������ʵ�ֶԲ���Աֱ�Ӹ�ҵ��Ȩ��
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
     * ������֯ID
     * @return
     */
    public Long getOrgId(){
        return new Long(this.role.getOrgId());	
    }
    
    public void setOrgId(Long orgId){
    	this.role.setOrgId(orgId.longValue());
    }
    
    /**
     * ������ID
     * @return
     */
    public Long getOperatorId(){
    	return new Long(this.role.getOperatorId());
    }
    
    public void setOperatorId(Long opId){
        this.role.setOperatorId(opId.longValue());
    }

    /**
     * ��ɫ������Ϣ��
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
     * ��ɫID��
     * 
     * @return Returns the id.
     */
    public Long getRoleId() {
        return new Long(this.role.getRoleId());
    }

    /**
     * ��ɫ���ơ�
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
     * ��ɫ���� ���ͣ�1��ʾ���ܽ�ɫ,2��ʾҵ���ɫ��
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
