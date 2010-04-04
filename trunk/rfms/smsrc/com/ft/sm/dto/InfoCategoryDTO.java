package com.ft.sm.dto;

import com.ft.sm.entity.InfoCategory;

/**
 * 信息类别实体封装类。
 * 
 */
public class InfoCategoryDTO implements DTO {
    private static final long serialVersionUID = 4208515645722038171L;

    private InfoCategory category;

    public InfoCategoryDTO() {
        this.category = new InfoCategory();
    }

    public InfoCategoryDTO(InfoCategory category) {
        this.category = category;
    }

    /**
     * 种类id。
     * 
     * @return
     */
    public Long getCategoryId() {
        return new Long(this.category.getCategoryId());
    }

    public void setCategoryId(Long categoryId) {
        this.category.setCategoryId(categoryId.longValue());
    }

    /**
     * 描述。
     * 
     * @return
     */
    public String getDesc() {
        return this.category.getCategoryDesc();
    }

    public void setDesc(String desc) {
        this.category.setCategoryDesc(desc);
    }

    /**
     * 名称。
     * 
     * @return
     */
    public String getName() {
        return this.category.getCategoryName();
    }

    public void setName(String name) {
        this.category.setCategoryName(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.category;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.category = (InfoCategory) target;
    }
}
