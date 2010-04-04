package com.ft.sm.dto;

import com.ft.sm.entity.DataResource;
import com.ft.sm.entity.DataResourceEntry;

/**
 * ҵ��Ȩ��ʵ���װ�ࡣ
 * 
 */
public class DataResourceDTO implements DTO {
    private static final long serialVersionUID = 5667068078131610538L;

    // ��Ŀ�������ͣ�1����ֵ��2������ֵ
    public static final int DATA_TYPE_SINGLE = 1;

    public static final int DATA_TYPE_INTERZONE = 2;

    // Ȩ�޷��䷽ʽ��1����ѡ��2����ѡ
    public static final int ASSIGN_TYPE_SINGLE = 1;

    public static final int ASSIGN_YTPE_MULTI = 2;

    private DataResource dataResource;

    private DataResourceEntry[] entries;

    public DataResourceDTO() {
        this.dataResource = new DataResource();
        entries = new DataResourceEntry[0];
    }

    public DataResourceDTO(DataResource dataResource) {
        this.dataResource = dataResource;
    }

    /**
     * ����Ȩ��Ωһ���롣
     * 
     * @return
     */
    public String getCode() {
        return this.dataResource.getPrivCode();
    }

    public void setCode(String code) {
        this.dataResource.setPrivCode(code);
    }

    /**
     * ����Ȩ��������Ϣ��
     * 
     * @return
     */
    public String getDescription() {
        return this.dataResource.getResourceDesc();
    }

    public void setDescription(String description) {
        this.dataResource.setResourceDesc(description);
    }

    /**
     * ����Ȩ��ID��
     * 
     * @return Returns the id.
     */
    public Long getResourceId() {
        return new Long(this.dataResource.getResourceId());
    }

    public void setResourceId(Long resourceId) {
        this.dataResource.setResourceId(resourceId.longValue());
    }

    /**
     * ����Ȩ����ʾ���⡣
     * 
     * @return
     */
    public String getTitle() {
        return this.dataResource.getPrivName();
    }

    public void setTitle(String title) {
        this.dataResource.setPrivName(title);
    }

    /**
     * Ȩ�޷��䷽ʽ����ѡ����ѡ��
     * 
     * @return Returns the assignType.
     */
    public long getAssignType() {
        return this.dataResource.getAssignType();
    }

    /**
     * @param assignType
     *                The assignType to set.
     */
    public void setAssignType(long assignType) {
        this.dataResource.setAssignType(assignType);
    }

    /**
     * Ȩ����Ŀ�������ͣ���ֵ������ֵ��
     * 
     * @return Returns the dataType.
     */
    public long getDataType() {
        return this.dataResource.getDataType();
    }

    /**
     * @param dataType
     *                The dataType to set.
     */
    public void setDataType(long dataType) {
        this.dataResource.setDataType(dataType);
    }

    /**
     * @return the dataResource
     */
    public DataResource getDataResource() {
        return dataResource;
    }

    /**
     * @return the entries
     */
    public DataResourceEntry[] getEntries() {
        return entries;
    }

    /**
     * @param entries
     *                the entries to set
     */
    public void setEntries(DataResourceEntry[] entries) {
        this.entries = entries;
    }

    /**
     * @param dataResource
     *                the dataResource to set
     */
    public void setDataResource(DataResource dataResource) {
        this.dataResource = dataResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.dataResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.dataResource = (DataResource) target;
    }

}
