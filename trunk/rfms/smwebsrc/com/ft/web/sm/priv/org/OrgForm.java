package com.ft.web.sm.priv.org;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Region;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 组织维护页面数据类
 * 
 * @version 1.0
 * 
 * @struts.form name="orgForm"
 */
public class OrgForm extends BaseValidatorForm {

    private static final long serialVersionUID = 1L;

    private OrganizationDTO org;

    private OrganizationDTO parentOrg;

    private OrganizationDTO[] orgs;

    private Region[] regions;

    private String act;
    
    /**
     * @struts.entity-field initial="orgIds"
     * @return
     */
    public OrganizationDTO[] getOrgs() {
        return orgs;
    }

    public void setOrgs(OrganizationDTO[] orgs) {
        this.orgs = orgs;
    }

    /**
     * @struts.entity-field initial="regionIds"
     * @return
     */
    public Region[] getRegions() {
        return regions;
    }

    public void setRegions(Region[] regions) {
        this.regions = regions;
    }

    /**
     * @struts.entity-field initial="orgId"
     * @return
     */
    public OrganizationDTO getOrg() {
        return org;
    }

    public void setOrg(OrganizationDTO org) {
        this.org = org;
    }

    /**
     * @struts.entity-field initial="pid"
     * @return
     */
    public OrganizationDTO getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(OrganizationDTO parentOrg) {
        this.parentOrg = parentOrg;
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (this.org == null) {
            this.org = new OrganizationDTO();
        }
        if (this.parentOrg == null) {
            this.parentOrg = new OrganizationDTO();
        }
        super.reset(arg0, arg1);
    }

    /**
     * @return Returns the act.
     */
    public String getAct() {
        return act;
    }

    /**
     * @param act
     *                The act to set.
     */
    public void setAct(String act) {
        this.act = act;
    }

}
