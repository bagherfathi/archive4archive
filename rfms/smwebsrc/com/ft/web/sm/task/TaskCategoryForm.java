package com.ft.web.sm.task;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 *  任务类别页面数据类
 * 
 * @struts.form name="taskCategoryForm"
 * 
 * @version 1.0
 */
public class TaskCategoryForm extends BaseValidatorForm {

    private static final long serialVersionUID = 6603960134735064743L;

    private TaskCategoryDTO category;
    private String categoryName;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == category) {
            category = new TaskCategoryDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public TaskCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(TaskCategoryDTO category) {
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
