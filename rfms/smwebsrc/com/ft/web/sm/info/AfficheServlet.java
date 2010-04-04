package com.ft.web.sm.info;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ft.busi.sm.impl.AfficheManager;

public class AfficheServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * (non-javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgId = request.getParameter("orgId");
        String opId = request.getParameter("opId");
        if (orgId != null && opId != null) {
            ServletContext context = request.getSession().getServletContext();

            WebApplicationContext appContext = WebApplicationContextUtils
                    .getWebApplicationContext(context);

            AfficheManager afficheManager = (AfficheManager) appContext
                    .getBean("afficheManager");

            List affiches = afficheManager.findAllAfficheByOrg(Long
                    .valueOf(orgId));
            request.setAttribute("affiches", affiches);
            request.setAttribute("opId", opId);
        }
        dispatcher(request, response, "/WEB-INF/jsp/listAffiche.jsp");
        return;
    }

    private void dispatcher(HttpServletRequest request,
            HttpServletResponse response, String redirectUri)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(redirectUri);
        dispatcher.forward(request, response);
    }

}
