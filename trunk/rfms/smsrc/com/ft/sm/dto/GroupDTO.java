package com.ft.sm.dto;

import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;

/**
 * 操作员组实体封装类。
 * 
 */
public class GroupDTO implements DTO {
    private static final long serialVersionUID = 2537565826472065520L;

    private Group group;
    
    private Organization org; 

    public GroupDTO() {
        this.group = new Group();
    }

    public GroupDTO(Group group) {
        this.group = group;
    }
    
    public GroupDTO(Group group,Organization org) {
        this.group = group;
        this.org = org;
    }

    /**
     * 操作员组描述信息。
     * 
     * @return
     */
    public String getDescription() {
        return this.group.getGroupDesc();
    }

    public void setDescription(String description) {
        this.group.setGroupDesc(description);
    }

    /**
     * 操作员组ID。
     * 
     * @return Returns the id.
     */
    public Long getGroupId() {
        return new Long(this.group.getGroupId());
    }

    public void setGroupId(Long groupId) {
        this.group.setGroupId(groupId.longValue());
    }

    /**
     * 操作员组名称。
     * 
     * @return
     */
    public String getName() {
        return this.group.getGroupName();
    }

    public void setName(String name) {
        this.group.setGroupName(name);
    }

    /**
     * 操作员组实体。
     * 
     * @return the group
     */
    public Group getGroup() {
        return group;
    }
    
    /**
     * 组所属组织ID
     * @return
     */
    public Long getOwnerOrgId(){
    	if(this.group.getOwnerOrgId() == 0){
    		return null;
    	}
    	return new Long(this.group.getOwnerOrgId());
    }
    
    public void setOwnerOrgId(Long orgId){
    	this.group.setOwnerOrgId(orgId.longValue());
    }
    
    /**
     * 组所属组织
     * @return
     */
    public Organization getOwnerOrg(){
    	if(this.org != null){
    		return this.org;
    	}
    	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.group;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.group = (Group) target;
    }
}
