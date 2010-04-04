package com.ft.web.sm.priv.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.ResourceDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 功能权限分配查询表单类
 * 
 * @struts.form name="queryResourcePrivilegeForm"
 * 
 * @version 1.0
 */
public class QueryResourcePrivilegeForm extends BaseValidatorForm {

    private static final long serialVersionUID = 8025129415475062964L;

    /**
     * 存放功能权限信息
     */
    private ResourceDTO resource;

    private String act;

    private String resourceTitle;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == resource) {
            resource = new ResourceDTO();
        }
    }

    /**
     * @struts.entity-field initial="rId"
     * @return
     */
    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return the resourceTitle
     */
    public String getResourceTitle() {
        return resourceTitle;
    }

    /**
     * @param resourceTitle
     *                the resourceTitle to set
     */
    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

}
