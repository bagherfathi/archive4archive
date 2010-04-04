package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumGroup;

/**
 * 枚举数据组实体封装类。
 * 
 */
public class EnumGroupDTO extends XmlTreeNode implements HisDTO{
    private static final long serialVersionUID = 6450022629786866488L;

    // 系统数据组中的系统数据列表
    private List<EnumCategory> enumCategories;
    private EnumGroup enumGroup;
    
    public EnumGroupDTO(){
        this.enumGroup = new EnumGroup();
    }
    
    public EnumGroupDTO(EnumGroup enumGroup){
        this.enumGroup = enumGroup;
    }

    /**
     * 枚举数据组描述信息。
     *
     * @return
     */
    public String getDescription() {
        return this.enumGroup.getGroupDesc();
    }

    public void setDescription(String description) {
        this.enumGroup.setGroupDesc(description);
    }

    /**
     * 枚举数据组ID。
     * 
     * @return Returns the id.
     */
    public Long getGroupId() {
        return new Long(this.enumGroup.getGroupId());
    }

    public void setGroupId(Long groupId) {
        this.enumGroup.setGroupId(groupId.longValue());
    }

    /**
     * 枚举数据组名称。
     * 
     * @return
     */
    public String getGroupName() {
        return this.enumGroup.getGroupName();
    }

    public void setGroupName(String groupName) {
        this.enumGroup.setGroupName(groupName);
    }

    /**
     * 枚举数据类别列表。
     * @return the enumCategories
     */
    public List<EnumCategory> getEnumCategories() {
        if (this.enumCategories == null) {
            this.enumCategories = new ArrayList<EnumCategory>();
        }
        return enumCategories;
    }

    /**
     * 添加枚举数据类别。
     * 
     * @param enumData
     */
    public void addEnumData(EnumCategory enumCategory) {
        this.getEnumCategories().add(enumCategory);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.enumGroup;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.enumGroup = (EnumGroup)target;
    }

    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeId()
     */
    public Long getNodeId() {
	return this.getGroupId();
    }

    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeName()
     */
    public String getNodeName() {
	return this.getGroupName();
    }
    
    /* (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeType()
     */
    public String getNodeType() {
	return "group";
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.enumGroup.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.enumGroup.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.enumGroup.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.enumGroup.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.enumGroup.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.enumGroup.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.enumGroup.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.enumGroup.setValidDate(validDate);
    }
}
