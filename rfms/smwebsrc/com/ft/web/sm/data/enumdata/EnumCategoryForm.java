package com.ft.web.sm.data.enumdata;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * Class comments.
 * 
 * @version 1.0
 * 
 * @struts.form name="enumCategoryForm"
 */
public class EnumCategoryForm extends BaseValidatorForm {
    private static final long serialVersionUID = -4214187675767414L;

    private EnumCategoryDTO enumCategory;

    private Long groupId;

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

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     *                the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == enumCategory) {
            enumCategory = new EnumCategoryDTO();
        }
    }
}
