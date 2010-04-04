package com.ft.sm.dto;

import com.ft.sm.entity.RuleCategory;
import com.ft.sm.entity.RuleInfo;

public class RuleInfoDTO implements DTO {

    private static final long serialVersionUID = -6497576931246401497L;

    public static final int DROOLS_TYPE = 1;

    public static final int VELOCITY_TYPE = 2;

    private RuleInfo ruleInfo;

    private RuleCategory ruleCategory;

    public RuleInfoDTO(RuleInfo ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

    public RuleInfoDTO() {
        this.ruleInfo = new RuleInfo();
        this.ruleCategory = new RuleCategory();
    }

    /**
     * 规则编码。
     * 
     * @return
     */
    public String getCode() {
        return this.ruleInfo.getRuleCode();
    }

    public void setCode(String code) {
        this.ruleInfo.setRuleCode(code);
    }

    /**
     * 类别ID
     * 
     * @return
     */
    public long getCategoryId() {
        return this.ruleInfo.getCategoryId();
    }

    public void setCategoryId(Long categoryId) {
        this.ruleInfo.setCategoryId(categoryId.longValue());
    }

    /**
     * 规则描述。
     * 
     * @return
     */
    public String getDesc() {
        return this.ruleInfo.getRuleDesc();
    }

    public void setDesc(String desc) {
        this.ruleInfo.setRuleDesc(desc);
    }

    /**
     * 规则名称。
     * 
     * @return
     */
    public String getName() {
        return this.ruleInfo.getRuleName();
    }

    public void setName(String name) {
        this.ruleInfo.setRuleName(name);
    }

    /**
     * 规则ID。
     * 
     * @return
     */
    public Long getRuleId() {
        return new Long(this.ruleInfo.getRuleId());
    }

    public void setRuleId(Long ruleId) {
        this.ruleInfo.setRuleId(ruleId.longValue());
    }

    /**
     * 规则种类。
     * 
     * @return
     */
    public RuleCategory getCategory() {
        return this.ruleCategory;
    }

    public void setCategory(RuleCategory category) {
        this.ruleCategory = category;
    }

    /**
     * 当前规则文件。
     * 
     * @return
     */
    public Long getRuleFileId() {
        return new Long(this.ruleInfo.getRuleFileId());
    }

    public void setRuleFileId(Long ruleFileId) {
        this.ruleInfo.setRuleFileId(ruleFileId.longValue());
    }

    /**
     * 规则类别。
     * 
     * @return
     */
    public long getType() {
        return this.ruleInfo.getType();
    }

    public void setType(long type) {
        this.ruleInfo.setType(type);
    }

    /**
     * 发布的版本。
     * 
     * @return
     */
    public long getPublishVersion() {
        return this.ruleInfo.getPublishVersion();
    }

    public void setPublishVersion(long publishVersion) {
        this.ruleInfo.setPublishVersion(publishVersion);
    }

    public Object getTarget() {
        return this.ruleInfo;
    }

    public void setTarget(Object target) {
        this.ruleInfo = (RuleInfo) target;
    }

    public RuleInfo getRuleInfo() {
        return this.ruleInfo;
    }

}
