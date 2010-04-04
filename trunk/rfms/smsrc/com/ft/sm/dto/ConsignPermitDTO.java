package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.ConsignPermit;

/**
 * ί��Ȩ��ʵ���װ�ࡣ
 * 
 */
public class ConsignPermitDTO implements DTO {
    private static final long serialVersionUID = -2756345284217640927L;

    // ����Ȩ������
    public static final int RESOURCE_TYPE = 1;

    // ҵ��Ȩ������
    public static final int DATA_RESOURCE_TYPE = 2;

    // ί�ж������ƣ�ҳ����ʾ��
    private String consigneeName;

    // ί��Ȩ�����ƣ�ҳ����ʾ��
    private String resourceName;

    // Ȩ�޵�path,ҳ����ʾ��
    private String resourcePath;
    
    private String orgName;

    private ConsignPermit consignPermit;

    public ConsignPermitDTO() {
        consignPermit = new ConsignPermit();
        consignPermit.setConsignTime(new Date());
    }

    public ConsignPermitDTO(ConsignPermit consignPermit) {
        this.consignPermit = consignPermit;
    }

    public ConsignPermitDTO(Long consignId, String consigneeName,
            String resourceName, Date consignTime, Date validTime,
            Date expireTime) {
        consignPermit = new ConsignPermit();
        consignPermit.setConsignId(consignId.longValue());
        consignPermit.setExpireTime(expireTime);
        consignPermit.setValidTime(validTime);
        consignPermit.setConsignTime(consignTime);

        this.consigneeName = consigneeName;
        this.resourceName = resourceName;
    }

    public ConsignPermitDTO(Long consignId, String consigneeName,
            String resourceName, String resourcePath, Date consignTime,
            Date validTime, Date expireTime) {
        this.consigneeName = consigneeName;
        this.resourceName = resourceName;
        this.resourcePath = resourcePath;

        consignPermit = new ConsignPermit();
        consignPermit.setConsignId(consignId.longValue());
        consignPermit.setExpireTime(expireTime);
        consignPermit.setValidTime(validTime);
        consignPermit.setConsignTime(consignTime);
    }
    
    public ConsignPermitDTO(Long consignId, String consigneeName,
            String resourceName, String resourcePath, Date consignTime,
            Date validTime, Date expireTime,String orgName) {
        this.consigneeName = consigneeName;
        this.resourceName = resourceName;
        this.resourcePath = resourcePath;
        this.orgName = orgName;

        consignPermit = new ConsignPermit();
        consignPermit.setConsignId(consignId.longValue());
        consignPermit.setExpireTime(expireTime);
        consignPermit.setValidTime(validTime);
        consignPermit.setConsignTime(consignTime);
    }

    /**
     * ί�ж���ID��
     * 
     * @return
     */
    public Long getConsigneeId() {
        return new Long(this.consignPermit.getConsigneeId());
    }

    public void setConsigneeId(Long consigneeId) {
        this.consignPermit.setConsigneeId(consigneeId.longValue());
    }

    /**
     * ί�ж���������֯����ID��
     * 
     * @return
     */
    public long getConsigneeOrgId() {
        return this.consignPermit.getConsigneeOrgId();
    }

    public void setConsigneeOrgId(long consigneeOrgId) {
        this.consignPermit.setConsigneeOrgId(consigneeOrgId);
    }

    /**
     * ί����ID��
     * 
     * @return
     */
    public long getConsignerId() {
        return this.consignPermit.getConsignerId();
    }

    public void setConsignerId(long consignerId) {
        this.consignPermit.setConsignerId(consignerId);
    }

    /**
     * ί����������֯����ID��
     * 
     * @return
     */
    public long getConsignerOrgId() {
        return this.consignPermit.getConsignerOrgId();
    }

    public void setConsignerOrgId(long consignerOrgId) {
        this.consignPermit.setConsignerOrgId(consignerOrgId);
    }

    /**
     * ��Ȩ��ʷ��¼ID��
     * 
     * @return Returns the id.
     */
    public long getConsignId() {
        return this.consignPermit.getConsignId();
    }

    public void setConsignId(long consignId) {
        this.consignPermit.setConsignId(consignId);
    }

    /**
     * �ɷ�����֯����ID��
     * 
     * @return
     */
    public long getOrgId() {
        return this.consignPermit.getOrgId();
    }

    public void setOrgId(long orgId) {
        this.consignPermit.setOrgId(orgId);
    }

    /**
     * ί�еĿɷ���Ȩ��ID��
     * 
     * @return
     */
    public long getResourceId() {
        return this.consignPermit.getResourceId();
    }

    public void setResourceId(long resourceId) {
        this.consignPermit.setResourceId(resourceId);
    }

    /**
     * ί�еĿɷ���Ȩ������ Ȩ�����ͷ�Ϊ��1������Ȩ��ʵ�壻2��ҵ��Ȩ��ʵ�塣
     * 
     * @return
     */
    public long getResourceType() {
        return this.consignPermit.getResourceType();
    }

    public void setResourceType(long resourceType) {
        this.consignPermit.setResourceType(resourceType);
    }

    /**
     * ί��ʱ�䡣
     * 
     * @return
     */
    public Date getConsignTime() {
        return this.consignPermit.getConsignTime();
    }

    public void setConsignTime(Date consignTime) {
        this.consignPermit.setConsignTime(consignTime);
    }

    /**
     * ί��ʧЧʱ�䡣
     * 
     * @return Returns the expireTime.
     */
    public Date getExpireTime() {
        return this.consignPermit.getExpireTime();
    }

    /**
     * @param expireTime
     *                The expireTime to set.
     */
    public void setExpireTime(Date expireTime) {
        this.consignPermit.setExpireTime(expireTime);
    }

    /**
     * ί����Чʱ�䡣
     * 
     * @hibernate.property column="valid_time" length="7"
     * @return Returns the validTime.
     */
    public Date getValidTime() {
        return this.consignPermit.getValidTime();
    }

    /**
     * @param validTime
     *                The validTime to set.
     */
    public void setValidTime(Date validTime) {
        this.consignPermit.setValidTime(validTime);
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setRsourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @return Returns the consignerName.
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * @param consignerName
     *                The consignerName to set.
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    /**
     * @return Returns the resourceName.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName
     *                The resourceName to set.
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.consignPermit;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.consignPermit = (ConsignPermit) target;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
