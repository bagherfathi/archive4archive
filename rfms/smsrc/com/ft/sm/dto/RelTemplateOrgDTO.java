package com.ft.sm.dto;

import com.ft.sm.entity.RelTemplateOrg;

/**
 * 模板适用组织实体封装类.
 * 
 */
public class RelTemplateOrgDTO implements DTO{
    private static final long serialVersionUID = 1064196677132656729L;
    private RelTemplateOrg relTemplateOrg;
    
    public RelTemplateOrgDTO(RelTemplateOrg relTemplateOrg){
        this.relTemplateOrg = relTemplateOrg;
    }
    
    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.relTemplateOrg;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.relTemplateOrg = (RelTemplateOrg)target;
    }

    /**
     * @return the relTemplateOrg
     */
    public RelTemplateOrg getRelTemplateOrg() {
        return relTemplateOrg;
    }

    /**
     * @param relTemplateOrg the relTemplateOrg to set
     */
    public void setRelTemplateOrg(RelTemplateOrg relTemplateOrg) {
        this.relTemplateOrg = relTemplateOrg;
    }
}
