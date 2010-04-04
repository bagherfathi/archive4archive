package com.ft.web.sm.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;

import com.ft.common.login.Authentication;
import com.ft.common.login.AuthenticationManager;
import com.ft.common.session.OperatorSessionHandler;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.commons.web.SpringWebUtils;
import com.ft.struts.ActionMessagesHelper;

/**
 * 登陆页面
 * 
 * @version 1.0
 * 
 * @spring.bean name="/login"
 * 
 */
public class LoginAction extends DispatchAction {
    private AuthenticationManager authMgmt;

    private OperatorSessionHandler userSessionHandler;

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 判断是否已经登陆
        OperatorDTO op = OperatorSessionHelper.getLoginOp(request);

        // 未登陆
        if (op == null) {
            return mapping.getInputForward();
            // 已登陆
        } else {
            return mapping.findForward("main");
        }
    }

    public ActionForward login(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String loginName = (String) PropertyUtils.getSimpleProperty(form,
                "loginName");
        String password = (String) PropertyUtils.getSimpleProperty(form,
                "password");

        Identification iden = new Identification(loginName, password);
        Authentication auth = authMgmt.authenticate(iden);

        // 认证成功
        if (auth.isSuccess()) {
            userSessionHandler.init(request, auth.getPrincipal());
            request.getSession().setAttribute("appThemes", "adminDefault");
            //request.getSession().setAttribute("appThemes", "greenTheme");
            return mapping.findForward("main");
            // 认证失败
        } else {
            ActionMessagesHelper.saveRequestMessage(request, auth.getCode());
            return mapping.getInputForward();
        }
    }

    public ActionForward changeOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String loginOrgId = request.getParameter("orgId");
        if (null != loginOrgId && loginOrgId.length() > 0) {
            userSessionHandler.reset(request, response, new Long(loginOrgId));
        }
        return mapping.findForward("main");
    }

    public void setServlet(ActionServlet servlet) {
        super.setServlet(servlet);

        authMgmt = (AuthenticationManager) SpringWebUtils.getBean(servlet
                .getServletContext(), AuthenticationManager.getBeanName());

        userSessionHandler = (OperatorSessionHandler) SpringWebUtils.getBean(
                servlet.getServletContext(), OperatorSessionHandler
                        .getBeanName());
    }

    /**
     * 
     * @return Returns the authMgmt.
     */
    public AuthenticationManager getAuthMgmt() {
        return authMgmt;
    }

    /**
     * @param authMgmt
     *                The authMgmt to set.
     */
    public void setAuthMgmt(AuthenticationManager authMgmt) {
        this.authMgmt = authMgmt;
    }

    /**
     * @return Returns the userSessionHandler.
     */
    public OperatorSessionHandler getUserSessionHandler() {
        return userSessionHandler;
    }

    /**
     * @param userSessionHandler
     *                The userSessionHandler to set.
     */
    public void setUserSessionHandler(OperatorSessionHandler userSessionHandler) {
        this.userSessionHandler = userSessionHandler;
    }

}
