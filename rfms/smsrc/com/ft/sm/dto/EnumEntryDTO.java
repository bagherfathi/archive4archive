package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.EnumEntry;

/**
 * Class comments.
 * 
 * @version 1.0
 */
public class EnumEntryDTO implements HisDTO {
    private static final long serialVersionUID = -2162684712746035054L;

    public static final long STATUS_DISABLE = 1;

    public static final long STATUS_NORMAL = 0;

    private EnumEntry enumEntry;

    public EnumEntryDTO() {
        this.enumEntry = new EnumEntry();
    }

    public EnumEntryDTO(EnumEntry enumEntry) {
        this.enumEntry = enumEntry;
    }

    /**
     * 枚举数据条目ID。
     * 
     * @return Returns the id.
     */
    public Long getEntryId() {
        return new Long(this.enumEntry.getEnumEntryId());
    }

    public void setEntryId(Long entryId) {
        this.enumEntry.setEnumEntryId(entryId.longValue());
    }

    /**
     * 所属枚举数据实体ID。
     * 
     * @return the enumDataId
     */
    public Long getEnumDataId() {
        return new Long(this.enumEntry.getEnumId());
    }

    /**
     * @param enumDataId
     *                the enumDataId to set
     */
    public void setEnumDataId(Long enumDataId) {
        this.enumEntry.setEnumId(enumDataId.longValue());
    }

    /**
     * 枚举数据条目显示标签。
     * 
     * @return
     */
    public String getLabel() {
        return this.enumEntry.getEnumEntryLabel();
    }

    public void setLabel(String label) {
        this.enumEntry.setEnumEntryLabel(label);
    }

    /**
     * 枚举数据条目状态。
     * 
     * @return
     */
    public long getStatus() {
        return this.enumEntry.getEnumEntryStatus();
    }

    public void setStatus(int status) {
        this.setStatus(status);
    }

    /**
     * 枚举数据条目值。
     * 
     * @return
     */
    public String getValue() {
        return this.enumEntry.getEnumEntryValue();
    }

    public void setValue(String value) {
        this.enumEntry.setEnumEntryValue(value);
    }

    /**
     * 关联值。
     * 
     * @return the linkValue
     */
    public String getLinkValue() {
        return this.enumEntry.getEntryLinkValue();
    }

    /**
     * @param linkValue
     *                the linkValue to set
     */
    public void setLinkValue(String linkValue) {
        this.enumEntry.setEntryLinkValue(linkValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.enumEntry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.enumEntry = (EnumEntry) target;
    }
    
    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.enumEntry.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.enumEntry.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.enumEntry.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.enumEntry.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.enumEntry.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.enumEntry.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.enumEntry.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.enumEntry.setValidDate(validDate);
    }
    
    public long getOrder(){
        return this.enumEntry.getEntryOrder();
    }
    
    public void setOrder(long order){
        this.enumEntry.setEntryOrder(order);
    }
}
