package com.ft.web.sm.rule;

import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 规则文件页面Form Bean.
 * 
 * @version 1.0
 * 
 * @struts.form name="ruleFileForm"
 */
public class RuleFileForm extends BaseValidatorForm{
    private static final long serialVersionUID = 7510261786247190635L;

    private RuleInfoDTO rule;
    private RuleFileDTO ruleFile;
    
    private FormFile file;
    private String fileContent;
    private boolean isPublish;
    
    /**
     * @struts.entity-field initial="ruleId"
     * @return the rule
     */
    public RuleInfoDTO getRule() {
        return rule;
    }
    /**
     * @param rule the rule to set
     */
    public void setRule(RuleInfoDTO rule) {
        this.rule = rule;
    }
    /**
     * @struts.entity-field initial="fileId"
     * @return the ruleFile
     */
    public RuleFileDTO getRuleFile() {
        return ruleFile;
    }
    /**
     * @param ruleFile the ruleFile to set
     */
    public void setRuleFile(RuleFileDTO ruleFile) {
        this.ruleFile = ruleFile;
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
}
