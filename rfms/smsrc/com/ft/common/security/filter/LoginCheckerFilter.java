package com.ft.common.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oro.text.perl.Perl5Util;

import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;

/**
 * ����Ƿ�ͨ����֤��Ӧ������ļ����.
 * 
 * @spring.bean id = "loginCheckerFilter"
 * 
 * @version 1.0
 */
public class LoginCheckerFilter implements Filter {
    private String loginRedirectUrl; // δ��¼ת��ҳ������

    private String loginSkipPattern; // ����¼��������

    protected Perl5Util perl;

    public void destroy() {
        perl = null;
    }

    public void doFilter(ServletRequest _request, ServletResponse _response,
            FilterChain chain) throws IOException, ServletException {

        if (perl == null) {
            perl = new Perl5Util();
        }

        HttpServletRequest request = (HttpServletRequest) _request;
        HttpServletResponse response = (HttpServletResponse) _response;
        String servletPath = request.getServletPath();
        if (!"".equals(servletPath) && !"/".equals(servletPath)) {
            if (!perl.match(loginSkipPattern, servletPath)) {
                OperatorDTO currentOperator = OperatorSessionHelper.getLoginOp(request);
                
                // ��ǰSession���޲���Ա��Ϣ����½
                if (currentOperator == null) {
                    if (!loginRedirectUrl.startsWith("http")) {
                        response.sendRedirect(request.getContextPath()
                                + loginRedirectUrl);
                    } else {
                        response.sendRedirect(loginRedirectUrl);
                    }

                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @spring.property value="${login.redirect.url}"
     * @param loginRedirectUrl
     *                The loginRedirectUrl to set.
     */
    public void setLoginRedirectUrl(String loginRedirectUrl) {
        this.loginRedirectUrl = loginRedirectUrl;
    }

    /**
     * @spring.property value="${login.skip.pattern}"
     * @param loginSkipPattern
     *                The loginSkipPattern to set.
     */
    public void setLoginSkipPattern(String loginSkipPattern) {
        this.loginSkipPattern = loginSkipPattern;
    }

    public void init(FilterConfig config) throws ServletException {
        loginRedirectUrl = config.getInitParameter("loginRedirectUrl");
        loginSkipPattern = config.getInitParameter("loginSkipPattern");
        perl = new Perl5Util();
    }
}
