package com.ft.web.sm.priv.resource;

import com.ft.web.sm.BaseForm;

/**
 * Class comments.
 * 
 * @struts.form name = "resQueryForm"
 * 
 * @version 1.0
 */
public class QueryForm extends BaseForm{
    private static final long serialVersionUID = 1423231246680569548L;
    private Long orgId;
    private Long resourceId;
    private Long[] resourceIds;
    
    /**
     * @return the resourceIds
     */
    public Long[] getResourceIds() {
        return resourceIds;
    }
    /**
     * @param resourceIds the resourceIds to set
     */
    public void setResourceIds(Long[] resourceIds) {
        this.resourceIds = resourceIds;
    }
    /**
     * @return the orgId
     */
    public Long getOrgId() {
        return orgId;
    }
    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    /**
     * @return the resourceId
     */
    public Long getResourceId() {
        return resourceId;
    }
    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
