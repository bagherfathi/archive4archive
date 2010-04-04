package com.ft.web.sm.priv.group;

import com.ft.sm.dto.GroupDTO;

/**
 * 操作员组页面数据类
 * 
 * @struts.form name="groupForm"
 * 
 * @version 1.0
 */
public class GroupForm extends GroupBaseForm {

	private static final long serialVersionUID = 1L;

	private GroupDTO[] groups;
    
    private String groupName;
    
    private Long orgId;
    
    private String opName;
    

    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
     * @struts.entity-field initial="ids"
     * @return
     */
    public GroupDTO[] getGroups() {
        return groups;
    }

    public void setGroups(GroupDTO[] groups) {
        this.groups = groups;
    }

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}


}
