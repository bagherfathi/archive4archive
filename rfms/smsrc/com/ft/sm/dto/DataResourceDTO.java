package com.ft.sm.dto;

import com.ft.sm.entity.DataResource;
import com.ft.sm.entity.DataResourceEntry;

/**
 * 业务权限实体封装类。
 * 
 */
public class DataResourceDTO implements DTO {
    private static final long serialVersionUID = 5667068078131610538L;

    // 条目数据类型：1：单值；2：区间值
    public static final int DATA_TYPE_SINGLE = 1;

    public static final int DATA_TYPE_INTERZONE = 2;

    // 权限分配方式：1：单选；2：多选
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
     * 数据权限惟一编码。
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
     * 数据权限描述信息。
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
     * 数据权限ID。
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
     * 数据权限显示标题。
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
     * 权限分配方式：单选、多选。
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
     * 权限条目数据类型：单值、区间值。
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
