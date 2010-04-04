package com.ft.common.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

/**
 * 用于读写Cookie中操作员登陆组织信息.
 * @spring.bean id="loginOrgCookieGenerator"
 */
public class LoginOrgCookieGenerator extends CookieGenerator {
    private static final String LOGIN_ORG_COOKIE_NAME = "system.login.org";

    private String cookieValue;

    /**
     * 创建Cookie
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
     * Cookie写入
     * 
     * @param response
     *                Web请求应答
     */
    public void addCookie(final HttpServletResponse response) {
        addCookie(response, this.cookieValue == null ? "" : this.cookieValue);
    }

    /**
     * Cookie写入
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
     * 得到Cookie中存储的信息
     * 
     * @param request
     *                Web请求
     * @return Cookie中存储的信息
     */
    public String getCookieValue(final HttpServletRequest request) {
        final Cookie cookie = WebUtils
                .getCookie(request, LOGIN_ORG_COOKIE_NAME);
        return cookie == null ? null : cookie.getValue();
    }

    /**
     * 得到Cookie中存储的信息
     * 
     * @return Cookie中存储的信息
     */
    public String getCookieValue() {
        return this.cookieValue;
    }

    /**
     * 设置Cookie中药存储的信息
     * 
     * @param cookieValue
     *                要存储的信息
     */
    public void setCookieValue(final String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
