package com.ft.web.sm.template;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 模版管理Form类
 * 
 * @version 1.0
 * 
 * @struts.form name="templateForm"
 * 
 */
public class TemplateForm extends BaseValidatorForm {
    private static final long serialVersionUID = -8495930919023084861L;
    
    //搜索条件
    private String templateName;
    private String templateCode;
    private String categoryCode;
    
    private boolean isPublish;   
    private String fileContent;
    private FormFile file;
    private TemplateDTO template;
    private TemplateFileDTO templateFile;
    
    /**
     * @struts.entity-field initial="templateId"
     * @return the template
     */
    public TemplateDTO getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the templateCode
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * @param templateCode the templateCode to set
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

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
     * @return the file
     */
    public FormFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(FormFile file) {
        this.file = file;
    }

    
    /**
     * @return the isPublish
     */
    public boolean isPublish() {
        return isPublish;
    }

    /**
     * @param isPublish the isPublish to set
     */
    public void setPublish(boolean isPublish) {
        this.isPublish = isPublish;
    }

    
    /**
     * @struts.entity-field initial="templateFileId"
     * @return the templateFile
     */
    public TemplateFileDTO getTemplateFile() {
        return templateFile;
    }

    /**
     * @param templateFile the templateFile to set
     */
    public void setTemplateFile(TemplateFileDTO templateFile) {
        this.templateFile = templateFile;
    }

    /**
     * @return the fileContent
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.web.sm.BaseValidatorForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (this.template == null) {
            this.template = new TemplateDTO();
        }
        
        super.reset(mapping, request);
    }
}
