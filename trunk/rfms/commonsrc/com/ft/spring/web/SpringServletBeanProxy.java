package com.ft.spring.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ft.commons.web.SpringWebUtils;

public class SpringServletBeanProxy extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Servlet target;
	
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {	
		target.service(arg0, arg1);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String name = config.getInitParameter("beanName");
		Object obj = SpringWebUtils.getBean(config.getServletContext(),name);
		if(obj instanceof Servlet){
			target = (Servlet) obj;
		}else{
			throw new IllegalArgumentException(name);
		}
	
	}

	public void destroy() {
		super.destroy();
		target.destroy();
	}
	
}
