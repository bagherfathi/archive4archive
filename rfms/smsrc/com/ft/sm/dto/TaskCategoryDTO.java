package com.ft.sm.dto;

import com.ft.sm.entity.TaskCategory;

public class TaskCategoryDTO implements DTO{
    
    private static final long serialVersionUID = 5124536682040992090L;

    private TaskCategory taskCategory;

    public TaskCategoryDTO() {
        this.taskCategory = new TaskCategory();
    }

    public TaskCategoryDTO(TaskCategory category) {
        this.taskCategory = category;
    }

    public Long getCategoryId() {
        return new Long(this.taskCategory.getCategoryId());
    }

    public void setCategoryId(Long categoryId) {
        this.taskCategory.setCategoryId(categoryId.longValue());
    }

    /**
     * ÃèÊö¡£
     * 
     * @return
     */
    public String getDesc() {
        return this.taskCategory.getCategoryDesc();
    }

    public void setDesc(String desc) {
        this.taskCategory.setCategoryDesc(desc);
    }

    /**
     * Ãû³Æ¡£
     * 
     * @return
     */
    public String getName() {
        return this.taskCategory.getCategoryName();
    }

    public void setName(String name) {
        this.taskCategory.setCategoryName(name);
    }

    public Object getTarget() {
        return this.taskCategory;
    }

    public void setTarget(Object target) {
        this.taskCategory = (TaskCategory) target;
    }

    public TaskCategory getCategory() {
        return this.taskCategory;
    }

}
