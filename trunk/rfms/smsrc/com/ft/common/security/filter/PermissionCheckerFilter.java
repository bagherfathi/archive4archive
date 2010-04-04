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

import com.ft.common.security.PermissionCheckHelper;
import com.ft.common.session.OperatorSessionHelper;

/**
 * 检查是否有权访问
 * 
 */
public class PermissionCheckerFilter implements Filter {
    private String noPermissionRedirecUrl; // 无权限访问转向页面链接

    private String permissionSkipPattern; // 检查权限过滤条件

    private String loginRedirecUrl;

    private boolean checkPermission = true; // 是否检查

    protected Perl5Util perl;

    public void destroy() {
        perl = null;
    }

    public void doFilter(ServletRequest _request, ServletResponse _response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) _request;
        HttpServletResponse response = (HttpServletResponse) _response;
        String servletPath = request.getServletPath();

        if (this.checkPermission) {
            if (!"".equals(servletPath) && !"/".equals(servletPath)) {
                // 检查权限
                if (!perl.match(permissionSkipPattern, servletPath)) {
                    // 转到登陆页面
                    if (null == OperatorSessionHelper.getLoginOp(request)) {
                        sendRedirect(request, response, this.loginRedirecUrl);
                        return;
                    }

                    String fullPath = servletPath;

                    String act = request.getParameter("act");
                    if (act != null && act.length() > 0) {
                        fullPath += "?";
                        fullPath += "act=";
                        fullPath += act;
                    }

                    if (!PermissionCheckHelper.checkViewPermission(request,
                            servletPath)
                            && !PermissionCheckHelper.checkViewPermission(
                                    request, fullPath)
                            && !PermissionCheckHelper.checkViewPermission(
                                    request, servletPath.substring(1))
                            && !PermissionCheckHelper.checkViewPermission(
                                    request, fullPath.substring(1))) {
                        sendRedirect(request, response,
                                this.noPermissionRedirecUrl);
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        loginRedirecUrl = config.getInitParameter("loginRedirectUrl");

        noPermissionRedirecUrl = config
                .getInitParameter("noPermissionRedirecUrl");
        permissionSkipPattern = config
                .getInitParameter("permissionSkipPattern");
        String checkFlag = config.getInitParameter("checkPermission");
        if (checkFlag != null && checkFlag.length() > 0) {
            checkPermission = Boolean.valueOf(checkFlag).booleanValue();
        }
        perl = new Perl5Util();
    }

    private void sendRedirect(HttpServletRequest request,
            HttpServletResponse response, String url) throws IOException {
        if (!url.startsWith("http")) {
            response.sendRedirect(request.getContextPath() + url);
        } else {
            response.sendRedirect(url);
        }
    }

    public static void main(String[] args) {
        Perl5Util perl = new Perl5Util();
        System.out.println(perl.match("/\\/login/", "/login.do"));
    }
}
