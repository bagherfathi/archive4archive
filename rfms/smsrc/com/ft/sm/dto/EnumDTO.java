
package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.sm.entity.EnumEntry;

/**
 * 枚举数据实体封装类。
 * 
 * @version 1.0
 */
public class EnumDTO extends XmlTreeNode implements HisDTO {
    private static final long serialVersionUID = -836783084055506581L;

    public static final long STATUS_DISABLE = 1;

    public static final long STATUS_NORMAL = 0;

    // 系统数据组中的条目列表
    private List<EnumEntry> entries;
    private com.ft.sm.entity.Enum enumEntity;
    
    public EnumDTO(){
        this.enumEntity = new com.ft.sm.entity.Enum();
    }
    
    public EnumDTO(com.ft.sm.entity.Enum enumEntity){
        this.enumEntity = enumEntity;
    }

    /**
     * 枚举数据编码。
     * 
     * @return
     */
    public String getEnumCode() {
        return this.enumEntity.getEnumCode();
    }

    public void setEnumCode(String enumCode) {
        this.enumEntity.setEnumCode(enumCode);
    }

    /**
     * 枚举数据描述信息。
     *
     * @return
     */
    public String getEnumDesc() {
        return this.enumEntity.getEnumDesc();
    }

    public void setEnumDesc(String enumDesc) {
        this.enumEntity.setEnumDesc(enumDesc);
    }

    /**
     * 枚举数据记录ID。
     * 
     * @return Returns the id.
     */
    public Long getEnumId() {
        return new Long(this.enumEntity.getEnumId());
    }

    public void setEnumId(Long enumId) {
        this.enumEntity.setEnumId(enumId.longValue());
    }

    /**
     * 枚举数据名称。
     * 
     * @return
     */
    public String getEnumName() {
        return this.enumEntity.getEnumName();
    }

    public void setEnumName(String enumName) {
        this.enumEntity.setEnumName(enumName);
    }

    /**
     * 枚举数据类别ID。
     * @return the categoryId
     */
    public Long getCategoryId() {
        return new Long(this.enumEntity.getCategoryId());
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.enumEntity.setCategoryId(categoryId.longValue());
    }

    /**
     * 根据条目标签获取条目。
     * 
     * @param label
     * @return
     */
    public EnumEntry getEntryByLabel(String label) {
        for (Iterator iter = this.getEntries().iterator(); iter.hasNext();) {
            EnumEntry entry = (EnumEntry) iter.next();
            if (label.equals(entry.getEnumEntryLabel()))
                return entry;
        }

        return null;
    }

    /**
     * 系统数据状态。
     * 
     */
    public long getStatus() {
        return this.enumEntity.getStatus();
    }

    public void setStatus(long status) {
        this.enumEntity.setStatus(status);
    }

    public List getEntries() {
        if (this.entries == null) {
            entries = new ArrayList<EnumEntry>();
        }
        return entries;
    }

    /**
     * 添加数据条目。
     * 
     * @param enumDataEntry
     */
    @SuppressWarnings("unchecked")
	public void addEnumEntry(EnumEntry enumDataEntry) {
        this.getEntries().add(enumDataEntry);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.enumEntity;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.enumEntity = (com.ft.sm.entity.Enum)target;
    }

    /*
     * (non-Javadoc)
     * @see com.huashu.boss.sm.dto.XmlTreeNode#getNodeId()
     */
    public Long getNodeId() {
	return this.getEnumId();
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.sm.dto.XmlTreeNode#getNodeName()
     */
    public String getNodeName() {
	return this.getEnumName();
    }

    /*
     * (non-Javadoc)
     * @see com.huashu.boss.sm.dto.XmlTreeNode#getNodeStatus()
     */
    public Long getNodeStatus() {
	return new Long(this.getStatus());
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.XmlTreeNode#getNodeType()
     */
    public String getNodeType() {
	return "enum";
    }
    
    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.enumEntity.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.enumEntity.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.enumEntity.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.enumEntity.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.enumEntity.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.enumEntity.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.enumEntity.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.enumEntity.setValidDate(validDate);
    }
}
