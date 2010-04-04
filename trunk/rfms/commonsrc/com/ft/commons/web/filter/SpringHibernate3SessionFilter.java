package com.ft.commons.web.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.dao.DataAccessResourceFailureException;

import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

import org.springframework.util.StringUtils;

import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @web.filter display-name="OpenSessionInViewFilter"
 *      name="OpenSessionInViewFilter"
 * @web.filter-mapping url-pattern=".do"
 */
public class SpringHibernate3SessionFilter extends OpenSessionInViewFilter {
    private HashSet excludes = new HashSet();

    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            URI uri = new URI(request.getRequestURI());
            pathInfo = uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (!excludes.contains(pathInfo)) {
            super.doFilterInternal(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    protected Session getSession(SessionFactory sessionFactory)
        throws DataAccessResourceFailureException {
        Session session = super.getSession(sessionFactory);
        session.setFlushMode(FlushMode.AUTO);

        return session;
    }

    @SuppressWarnings("unchecked")
	public void setExclude(String str) {
        String[] paths =
            StringUtils.tokenizeToStringArray(
                str,
                ConfigurableWebApplicationContext.CONFIG_LOCATION_DELIMITERS);

        for (int i = 0; i < paths.length; i++) {
            this.excludes.add(paths[i]);
        }
    }

    protected void closeSession(
        Session session, SessionFactory sessionFactory) {
        session.flush();
        super.closeSession(session, sessionFactory);
    }
}
