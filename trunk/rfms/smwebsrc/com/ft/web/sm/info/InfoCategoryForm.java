package com.ft.web.sm.info;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 信息类别处理类
 * 
 * @struts.form name="infoCategoryForm"
 * 
 * @version 1.0
 */
public class InfoCategoryForm extends BaseValidatorForm {
    private static final long serialVersionUID = -1196991860382691894L;

    private InfoCategoryDTO category;

    private String act;
    
    private String categoryName;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == category) {
            category = new InfoCategoryDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public InfoCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(InfoCategoryDTO category) {
        this.category = category;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
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
