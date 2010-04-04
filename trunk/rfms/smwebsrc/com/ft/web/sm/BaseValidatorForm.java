package com.ft.web.sm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import com.ft.busi.dto.AppRequest;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;

/**
 * 需要验证表单的基础类.
 * 
 * @version 1.0
 */
public class BaseValidatorForm extends ValidatorForm {
    private static final long serialVersionUID = 5598526084867975391L;

    // 当前操作员
    private OperatorDTO currentUser;

    public AppRequest getAppRequest(HttpServletRequest request,String appAction) {
        AppRequest appRequest = new AppRequest();
        appRequest.setOperatorId(currentUser.getOperatorId().longValue());
        appRequest.setOrgId(currentUser.getOrg().getOrgId());
        appRequest.setLoginOrgId(OperatorSessionHelper.getLoginOrg(request)
                .getOrgId().longValue());
        appRequest.setAppAction(appAction);
        return appRequest;
    }

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        if (arg1.getMethod().equalsIgnoreCase("GET")) {
            return new ActionErrors();
        } else {
            return super.validate(arg0, arg1);
        }
    }

    public String getValidationKey(ActionMapping mapping,
            HttpServletRequest request) {
        String validationKey = request.getParameter("validationKey");
        if (validationKey != null) {
            return validationKey;
        } else {
            return super.getValidationKey(mapping, request);
        }
    }

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
