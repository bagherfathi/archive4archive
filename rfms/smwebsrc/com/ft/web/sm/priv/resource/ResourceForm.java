package com.ft.web.sm.priv.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.ResourceDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 权限数据表单类
 * 
 * @struts.form name="resourceForm"
 * 
 * @version 1.0
 * 
 */
public class ResourceForm extends BaseValidatorForm {

    private static final long serialVersionUID = 8197158414079036102L;

    private ResourceDTO resource;

    private ResourceDTO parentResource;

    private ResourceDTO[] resources;

    private ResourceDTO addedResource;

    private boolean visible;

    private FormFile resFile;

    private String act;

    private String validationKey;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (null == resource) {
            resource = new ResourceDTO();
            resource.setVisible(true);
        }
        if (null == parentResource) {
            parentResource = new ResourceDTO();
        }
        // this.visible = resource.isVisible();
        super.reset(arg0, arg1);
    }

    /**
     * @struts.entity-field initial="resource.resourceId"
     * @return Returns the domain.
     */
    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    /**
     * @struts.entity-field initial="parentResource.resourceId"
     * @return Returns the attach.
     */
    public ResourceDTO getParentResource() {
        return parentResource;
    }

    public void setParentResource(ResourceDTO parentResource) {
        this.parentResource = parentResource;
    }

    /**
     * @struts.entity-field initial="ids"
     * @return Returns the attach.
     */
    public ResourceDTO[] getResources() {
        return resources;
    }

    public void setResources(ResourceDTO[] resources) {
        this.resources = resources;
    }

    /**
     * @struts.entity-field initial="addedId"
     * @return Returns the domain.
     */
    public ResourceDTO getAddedResource() {
        return addedResource;
    }

    public void setAddedResource(ResourceDTO addedResource) {
        this.addedResource = addedResource;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public FormFile getResFile() {
        return resFile;
    }

    /**
     * @param resFile
     */
    public void setResFile(FormFile resFile) {
        this.resFile = resFile;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }
}
