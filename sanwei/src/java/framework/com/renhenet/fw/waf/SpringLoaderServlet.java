package com.renhenet.fw.waf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renhenet.fw.ServiceLocator;

public class SpringLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 5112751788797044479L;

	private static Log log = LogFactory.getLog(SpringLoaderServlet.class);

	public void init() throws ServletException {
		log.info("now begin to Load spring............");
		ServiceLocator.init();
		log.info("spring load success!!!");
	}

	public void destroy() {
		log.info("now begin to destroy spring............");
		ServiceLocator.destroy();
		log.info("spring destroy success!!!");
	}

}
