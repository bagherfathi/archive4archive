package com.ft.spring.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ft.commons.web.SpringWebUtils;

public class SpringFilterBeanProxy  implements Filter {
	Filter target;
	public void init(FilterConfig arg0) throws ServletException {
		String name = arg0.getInitParameter("target");
		Object obj = SpringWebUtils.getBean(arg0.getServletContext(),name);
		if(obj instanceof Filter){
			target = (Filter) obj;
		}else{
			throw new IllegalArgumentException(name);
		}
		target.init(arg0);
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		target.doFilter(arg0,arg1,arg2);
	}

	public void destroy() {
	target.destroy();
		
	}

}
