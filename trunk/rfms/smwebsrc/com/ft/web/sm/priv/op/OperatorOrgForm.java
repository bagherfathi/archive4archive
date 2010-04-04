
package com.ft.web.sm.priv.op;

import com.ft.sm.dto.OrganizationDTO;

/**
 * 操作员组织页面控制
 * 
 * @version 1.0
 * @struts.form name="operatorOrgForm"
 * 
 */
public class OperatorOrgForm extends OperatorBaseForm {

	private static final long serialVersionUID = 1L;

	private OrganizationDTO[] orgs;

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

}
