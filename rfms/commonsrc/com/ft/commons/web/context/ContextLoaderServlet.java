package com.ft.commons.web.context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WEB应用启动时初始化其上下文
 */
public class ContextLoaderServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ContextLoader contextLoader;


	/**
	 * Initialize the root web application context.
	 */
	public void init() throws ServletException {
		this.contextLoader = createContextLoader();
		this.contextLoader.initAppContext(getServletContext());
	}

	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * @return the new ContextLoader
	 */
	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	/**
	 * Return the ContextLoader used by this servlet. 
	 */
	public ContextLoader getContextLoader() {
		return contextLoader;
	}


	/**
	 * Close the root web application context.
	 */
	public void destroy() {
		if (this.contextLoader != null) {
			this.contextLoader.destroyAppContext(getServletContext());
		}
	}


	/**
	 * This should never even be called since no mapping to this servlet should
	 * ever be created in web.xml. That's why a correctly invoked Servlet 2.3
	 * listener is much more appropriate for initialization work ;-)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().log(
				"Attempt to call service method on ContextLoaderServlet as [" +
				request.getRequestURI() + "] was ignored");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}


	public String getServletInfo() {
		return "ContextLoaderServlet for Servlet API 2.2/2.3 " +
		    "(deprecated in favor of ContextLoaderListener for Servlet API 2.4)";
	}
}
