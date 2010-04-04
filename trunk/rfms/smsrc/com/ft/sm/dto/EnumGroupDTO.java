package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumGroup;

/**
 * ö��������ʵ���װ�ࡣ
 * 
 */
public class EnumGroupDTO extends XmlTreeNode implements HisDTO{
    private static final long serialVersionUID = 6450022629786866488L;

    // ϵͳ�������е�ϵͳ�����б�
    private List<EnumCategory> enumCategories;
    private EnumGroup enumGroup;
    
    public EnumGroupDTO(){
        this.enumGroup = new EnumGroup();
    }
    
    public EnumGroupDTO(EnumGroup enumGroup){
        this.enumGroup = enumGroup;
    }

    /**
     * ö��������������Ϣ��
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
     * ö��������ID��
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
     * ö�����������ơ�
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
     * ö����������б�
     * @return the enumCategories
     */
    public List<EnumCategory> getEnumCategories() {
        if (this.enumCategories == null) {
            this.enumCategories = new ArrayList<EnumCategory>();
        }
        return enumCategories;
    }

    /**
     * ���ö���������
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
