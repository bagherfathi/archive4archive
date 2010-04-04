package com.ft.common.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

/**
 * ���ڶ�дCookie�в���Ա��½��֯��Ϣ.
 * @spring.bean id="loginOrgCookieGenerator"
 */
public class LoginOrgCookieGenerator extends CookieGenerator {
    private static final String LOGIN_ORG_COOKIE_NAME = "system.login.org";

    private String cookieValue;

    /**
     * ����Cookie
     * 
     * @see org.springframework.web.util.CookieGenerator#createCookie(java.lang.String)
     */
    protected Cookie createCookie(final String cookieValue) {
        setCookieName(LOGIN_ORG_COOKIE_NAME);
        setCookiePath("/");
        final Cookie cookie = super.createCookie(cookieValue);
        return cookie;
    }

    /**
     * Cookieд��
     * 
     * @param response
     *                Web����Ӧ��
     */
    public void addCookie(final HttpServletResponse response) {
        addCookie(response, this.cookieValue == null ? "" : this.cookieValue);
    }

    /**
     * Cookieд��
     * 
     * @see org.springframework.web.util.CookieGenerator#addCookie(javax.servlet.http.HttpServletResponse,
     *      java.lang.String)
     */
    public void addCookie(HttpServletResponse response, String cookieValue) {
        Cookie cookie = createCookie(cookieValue);
        cookie.setMaxAge(getCookieMaxAge());
        response.addCookie(cookie);
    }

    /**
     * �õ�Cookie�д洢����Ϣ
     * 
     * @param request
     *                Web����
     * @return Cookie�д洢����Ϣ
     */
    public String getCookieValue(final HttpServletRequest request) {
        final Cookie cookie = WebUtils
                .getCookie(request, LOGIN_ORG_COOKIE_NAME);
        return cookie == null ? null : cookie.getValue();
    }

    /**
     * �õ�Cookie�д洢����Ϣ
     * 
     * @return Cookie�д洢����Ϣ
     */
    public String getCookieValue() {
        return this.cookieValue;
    }

    /**
     * ����Cookie��ҩ�洢����Ϣ
     * 
     * @param cookieValue
     *                Ҫ�洢����Ϣ
     */
    public void setCookieValue(final String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
