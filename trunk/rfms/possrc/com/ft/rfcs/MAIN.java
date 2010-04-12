package com.ft.rfcs;
/*
 * <p>Company:</p>
 * @author :
 * version: 1.0
 */

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class MAIN 
{
	//private static Logger logger = Logger.getLogger(MAIN.class);
	public static ApplicationContext ctx = null;
	
	private static Logger logger;
	static{
		org.apache.log4j.xml.DOMConfigurator.configure("conf/log4j.xml");
		logger = Logger.getLogger(MAIN.class);
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] fileSet = new String[]{"./conf/interface-iso8583.xml",
				"./conf/dispatch-config.xml","./conf/applicationContext.xml"};
		try
		{
			ctx = new FileSystemXmlApplicationContext(fileSet);		
			//String s =(String)ctx.getBean("bean1");
			
		}
		catch(Throwable ex)
		{
			logger.fatal("Throwable",ex);
		
		}
	}

}