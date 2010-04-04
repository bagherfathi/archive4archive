package com.ft.sm.dto;

import com.ft.sm.entity.RuleCategory;

public class RuleCategoryDTO implements DTO {

    private static final long serialVersionUID = 5124536682040992090L;

    private RuleCategory ruleCategory;

    public RuleCategoryDTO() {
        this.ruleCategory = new RuleCategory();
    }

    public RuleCategoryDTO(RuleCategory category) {
        this.ruleCategory = category;
    }

    public Long getCategoryId() {
        return new Long(this.ruleCategory.getCategoryId());
    }

    public void setCategoryId(Long categoryId) {
        this.ruleCategory.setCategoryId(categoryId.longValue());
    }

    /**
     * ÃèÊö¡£
     * 
     * @return
     */
    public String getDesc() {
        return this.ruleCategory.getCategoryDesc();
    }

    public void setDesc(String desc) {
        this.ruleCategory.setCategoryDesc(desc);
    }

    /**
     * Ãû³Æ¡£
     * 
     * @return
     */
    public String getName() {
        return this.ruleCategory.getCategoryName();
    }

    public void setName(String name) {
        this.ruleCategory.setCategoryName(name);
    }

    public Object getTarget() {
        return this.ruleCategory;
    }

    public void setTarget(Object target) {
        this.ruleCategory = (RuleCategory) target;
    }

    public RuleCategory getCategory() {
        return this.ruleCategory;
    }

}
