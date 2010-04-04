package com.ft.spring.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SpringSessionListenerBeanProxy implements HttpSessionListener{
	HttpSessionListener target;
	public void sessionCreated(HttpSessionEvent arg0) {
		target.sessionCreated(arg0);
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		target.sessionDestroyed(arg0);
		
	}

}
