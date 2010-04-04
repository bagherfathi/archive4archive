package com.ft.web.sm.priv.group;

import com.ft.sm.dto.OrganizationDTO;

/**
 * ����Ա�����֯ά��
 * 
 * @version 1.0
 * @struts.form name="groupOrgForm"
 */
public class GroupOrgForm extends GroupBaseForm {

    /**
	 * 
	 */
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
