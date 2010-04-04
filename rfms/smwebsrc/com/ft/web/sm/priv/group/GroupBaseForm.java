package com.ft.web.sm.priv.group;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ft.sm.dto.GroupDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * ²Ù×÷Ô±×é
 * 
 * @version 1.0
 */
public class GroupBaseForm extends BaseValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GroupDTO group;

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (this.group == null) {
            this.group = new GroupDTO();
        }
        super.reset(arg0, arg1);
    }
}
