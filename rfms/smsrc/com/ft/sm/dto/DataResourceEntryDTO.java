package com.ft.sm.dto;

import com.ft.sm.entity.DataResource;
import com.ft.sm.entity.DataResourceEntry;

/**
 * ҵ��Ȩ����Ŀʵ���װ�ࡣ
 * 
 * @version 1.0
 */
public class DataResourceEntryDTO implements DTO {
    private static final long serialVersionUID = 2116220205844218384L;

    private DataResource dataResource;

    private DataResourceEntry dataResourceEntry;

    public DataResourceEntryDTO() {
        this.dataResourceEntry = new DataResourceEntry();
    }

    public DataResourceEntryDTO(DataResourceEntry dataResourceEntry) {
        this.dataResourceEntry = dataResourceEntry;
    }

    public DataResourceEntryDTO(DataResource dataResource,
            DataResourceEntry dataResourceEntry) {
        this.dataResource = dataResource;
        this.dataResourceEntry = dataResourceEntry;
    }

    /**
     * ����Ȩ����Ŀ��ע��Ϣ��
     * 
     * @return
     */
    public String getDescription() {
        return this.dataResourceEntry.getEntryNotes();
    }

    public void setDescription(String description) {
        this.dataResourceEntry.setEntryNotes(description);
    }

    /**
     * ����Ȩ����ĿID��
     * 
     * @return Returns the id.
     */
    public Long getEntryId() {
        return new Long(this.dataResourceEntry.getEntryId());
    }

    public void setEntryId(Long entryId) {
        this.dataResourceEntry.setEntryId(entryId.longValue());
    }

    /**
     * ����Ȩ����Ŀ��ʾ���⡣
     * 
     * @return
     */
    public String getTitle() {
        return this.dataResourceEntry.getEntryName();
    }

    public void setTitle(String title) {
        this.dataResourceEntry.setEntryName(title);
    }

    /**
     * ��Ŀ���ֵ����ҵ��Ȩ���ǵ�ֵ���ͣ�������Ϊ��Ŀֵ��
     * 
     * @return Returns the maxValue.
     */
    public String getMaxValue() {
        return this.dataResourceEntry.getMaxValue();
    }

    /**
     * @param maxValue
     *                The maxValue to set.
     */
    public void setMaxValue(String maxValue) {
        this.dataResourceEntry.setMaxValue(maxValue);
    }

    /**
     * ��Ŀ��Сֵ����ҵ��Ȩ������Ϊ����ֵʱ��Ч��
     * 
     */
    public String getMinValue() {
        return this.dataResourceEntry.getMinValue();
    }

    /**
     * @param minValue
     *                The minValue to set.
     */
    public void setMinValue(String minValue) {
        this.dataResourceEntry.setMinValue(minValue);
    }

    /**
     * ҵ��Ȩ����Ŀ����ҵ��Ȩ��ʵ�塣
     * 
     * @return
     */
    public DataResource getDataResource() {
        return dataResource;
    }

    /**
     * ҵ��Ȩ����Ŀʵ�塣
     * 
     * @return the dataResourceEntry
     */
    public DataResourceEntry getDataResourceEntry() {
        return dataResourceEntry;
    }

    /**
     * @param dataResource
     *                the dataResource to set
     */
    public void setDataResource(DataResource dataResource) {
        this.dataResource = dataResource;
        this.dataResourceEntry.setResourceId(dataResource.getResourceId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.dataResourceEntry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.dataResourceEntry = (DataResourceEntry) target;
    }

    public Long getResourceId() {
        return new Long(this.dataResourceEntry.getResourceId());
    }
}
