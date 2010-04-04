package com.ft.sm.dto;

import com.ft.sm.entity.TemplateFile;

/**
 * Class comments.
 * 
 */
public class TemplateFileDTO implements DTO{
    private static final long serialVersionUID = 4790369763422315214L;
    
    private TemplateFile templateFile;
    
    //创建文件的操作员名称
    private String operatorName;
    
    public TemplateFileDTO(){
        this.templateFile = new TemplateFile();
    }
    
    public TemplateFileDTO(TemplateFile file){
        this.templateFile = file;
    }
    
    public TemplateFileDTO(TemplateFile file,String operatorName){
        this.templateFile = file;
        this.operatorName = operatorName;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.templateFile;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.templateFile = (TemplateFile)target;
    }

    /**
     * @return the templateFile
     */
    public TemplateFile getTemplateFile() {
        return templateFile;
    }

    /**
     * @param templateFile the templateFile to set
     */
    public void setTemplateFile(TemplateFile templateFile) {
        this.templateFile = templateFile;
    }

    /**
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName the operatorName to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
