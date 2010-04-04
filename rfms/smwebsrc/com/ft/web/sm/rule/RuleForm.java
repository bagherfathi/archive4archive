
package com.ft.web.sm.rule;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.RuleInfoDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 规则数据页面维护类
 * 
 * @struts.form name="ruleForm"
 * 
 * @version 1.0
 */
public class RuleForm extends BaseValidatorForm {

    private static final long serialVersionUID = -9097733408832030364L;
    private RuleInfoDTO rule;
    private Long categoryId;
    
    private FormFile file;
    private String fileContent;
    
    private String ruleName;
    private boolean isPublish;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == rule) {
            rule = new RuleInfoDTO();
        }
    }

    /**
     * @struts.entity-field initial="ruleId"
     * @return
     */
    public RuleInfoDTO getRule() {
        return rule;
    }

    public void setRule(RuleInfoDTO rule) {
        this.rule = rule;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    /**
     * @return Returns the ruleName.
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * @param ruleName
     *                The ruleName to set.
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(boolean isPublish) {
        this.isPublish = isPublish;
    }
}
