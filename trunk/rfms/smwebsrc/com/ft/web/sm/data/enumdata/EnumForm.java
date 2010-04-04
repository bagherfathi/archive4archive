package com.ft.web.sm.data.enumdata;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * Enum数据维护页面数据类
 * 
 * @struts.form name="enumForm"
 * 
 * @version 1.0
 */
public class EnumForm extends BaseValidatorForm {
    private static final long serialVersionUID = 8015190939242251965L;

    private EnumDTO enumData;

    private EnumCategoryDTO enumCategory;

    /**
     * Enum数据实体
     * 
     * @struts.entity-field initial="enumId"
     * @return
     */
    public EnumDTO getEnumData() {
        return enumData;
    }

    public void setEnumData(EnumDTO enumData) {
        this.enumData = enumData;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == enumData) {
            enumData = new EnumDTO();
        }
    }

    /**
     * @struts.entity-field initial="categoryId"
     * @return the enumCategory
     */
    public EnumCategoryDTO getEnumCategory() {
        return enumCategory;
    }

    /**
     * @param enumCategory
     *                the enumCategory to set
     */
    public void setEnumCategory(EnumCategoryDTO enumCategory) {
        this.enumCategory = enumCategory;
    }

}
