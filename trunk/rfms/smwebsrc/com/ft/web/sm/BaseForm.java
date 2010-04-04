package com.ft.web.sm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;

/**
 * 不需要验证表单的基础类.
 * 
 * @version 1.0
 */
public class BaseForm extends ActionForm {
    private static final long serialVersionUID = 5598526084867975391L;

    private OperatorDTO currentUser;

    /**
     * @return Returns the currentUser.
     */
    public OperatorDTO getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser
     *                The currentUser to set.
     */
    public void setCurrentUser(OperatorDTO currentUser) {
        this.currentUser = currentUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (this.currentUser == null) {
            currentUser = OperatorSessionHelper.getLoginOp(request);
        }
        super.reset(mapping, request);
    }
}
