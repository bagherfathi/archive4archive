 
package com.ft.common.exception;

/**
 * @author adminxp
 *
 */
public class JdbcTemplateBeanNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public JdbcTemplateBeanNotFoundException() {
		super("没找到JdbcTemplate对应的spring Bean;请检查spring配置,beanName=jdbcTemplate");
	}

	/**
	 * @param arg0
	 */
	public JdbcTemplateBeanNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public JdbcTemplateBeanNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JdbcTemplateBeanNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
