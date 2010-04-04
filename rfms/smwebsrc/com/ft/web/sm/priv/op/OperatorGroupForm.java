package com.ft.web.sm.priv.op;

import com.ft.sm.dto.GroupDTO;

/**
 * 操作员组数据维护
 * 
 * @version 1.0
 * @struts.form name="operatorGroupForm"
 */
public class OperatorGroupForm extends OperatorBaseForm {

	private static final long serialVersionUID = 1L;
	private GroupDTO[] groups;

    /**
     * @struts.entity-field initial="groupIds"
     * @return
     */
    public GroupDTO[] getGroups() {
        return groups;
    }

    public void setGroups(GroupDTO[] groups) {
        this.groups = groups;
    }
}
