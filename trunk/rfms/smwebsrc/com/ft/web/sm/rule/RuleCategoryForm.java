package com.ft.web.sm.rule;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 规则种类页面数据类
 * 
 * @struts.form name="ruleCategoryForm"
 * 
 * @version 1.0
 */
public class RuleCategoryForm extends BaseValidatorForm {

    private static final long serialVersionUID = 6603960134735064743L;

    private RuleCategoryDTO category;
    private String categoryName;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == category) {
            category = new RuleCategoryDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public RuleCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(RuleCategoryDTO category) {
        this.category = category;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
