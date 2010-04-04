package com.ft.webui.context;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
* @web.servlet
* name="WebUIContextLoaderServlet"
* load-on-startup="1"
*/
public class WebUIContextLoaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        new WebUIContextImpl(config.getServletContext());
    }

    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
        throws ServletException, IOException {
        super.service(arg0, arg1);
    }
}
