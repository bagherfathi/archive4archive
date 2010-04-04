package com.ft.web.sm.template;

import java.util.List;

import com.ft.web.sm.BaseValidatorForm;

/**
 * Ä£°æ°ó¶¨FormÀà
 * 
 * @version 1.0
 * 
 * @struts.form name="templateBindForm"
 * 
 */

public class TemplateBindForm extends BaseValidatorForm {
    private static final long serialVersionUID = -1871154643179652863L;
    private String categoryCode;
    private Long orgId;
    private List notBindList;
    private Long templateId;
    private Long delTemplateId;

    /**
     * @return the categoryCode
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode the categoryCode to set
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @return the orgId
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the notBindList
     */
    public List getNotBindList() {
        return notBindList;
    }

    /**
     * @param notBindList the notBindList to set
     */
    public void setNotBindList(List notBindList) {
        this.notBindList = notBindList;
    }

    /**
     * @return the templateId
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the delTemplateId
     */
    public Long getDelTemplateId() {
        return delTemplateId;
    }

    /**
     * @param delTemplateId the delTemplateId to set
     */
    public void setDelTemplateId(Long delTemplateId) {
        this.delTemplateId = delTemplateId;
    }
}
