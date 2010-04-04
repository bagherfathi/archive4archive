package com.ft.test;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import junit.framework.TestCase;

public class BaseTestCase extends TestCase {

	protected static final Logger logger = Logger.getLogger(BaseTestCase.class);

	protected static String config;

	protected static ApplicationContext appContext;

	private static int testNo = 0;

	private static int testCount;

	protected int getTestMethodCount() {
		Method[] all = this.getClass().getDeclaredMethods();
		int ret = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i].getName().startsWith("test"))
				ret++;
		}
		return ret;
	}

	protected void setUp() throws Exception {
		if (testNo == 0) { // first time to run setUp
			super.setUp();
			this.setUpAction();
			testCount = getTestMethodCount();
			logger.info("TEST test counts is: " + testCount);
		}
		testNo++;
		logger.info("TEST currently running test #" + testNo);
	}

	protected void tearDown() throws Exception {
		logger.info("TEST finished running test #" + testNo);
		if (testNo == testCount) { // last time to run tearDown
			this.tearDownAction();
			super.tearDown();
		}
	}

	protected void setUpAction() throws Exception {
		config = this.getConfigFile();
		String[] configs = config.split(",");
		appContext = new PathSystemXmlApplicationContext(configs);
	}

	protected void tearDownAction() throws Exception {
		if (null != appContext)
			((PathSystemXmlApplicationContext) appContext).close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onewaveinc.media.SpringTestCase#getConfigFile()
	 */
	protected String getConfigFile() {
		//return "/WEB-INF/spring/sm/*-context.xml;/WEB-INF/spring/so/*-context.xml;/WEB-INF/spring/up/*-context.xml;/WEB-INF/spring/uc/*-context.xml;/WEB-INF/spring/cs/*-context.xml;/WEB-INF/spring/bm/*-context.xml;/WEB-INF/spring/bp/*-context.xml;/WEB-INF/spring/busi_common/*-context.xml;/WEB-INF/spring/pm/*-context.xml;/WEB-INF/spring/tr/*-context.xml;/WEB-INF/spring/*-context.xml";
        return "/WEB-INF/spring/common-context.xml";
	}

	final class PathSystemXmlApplicationContext extends
			FileSystemXmlApplicationContext {

		public PathSystemXmlApplicationContext(String[] configLocations)
				throws BeansException {
			super(configLocations);
		}

		public PathSystemXmlApplicationContext(String string) {
			super(string);
		}

		protected Resource getResourceByPath(String path) {
			final String rootPath = "./";
			return super.getResourceByPath(rootPath + File.separator + path);
		}
	}

}
