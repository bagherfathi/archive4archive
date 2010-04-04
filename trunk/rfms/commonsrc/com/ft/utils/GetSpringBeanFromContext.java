/**
 * 
 */
package com.ft.utils;

import com.ft.commons.template.TemplateEngine;
import com.ft.spring.web.SpringContextUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author adminxp
 * 
 */
public class GetSpringBeanFromContext {

	/**
	 * ��spring Context�л�ȡTemplateEngine��spring Beanʵ��
	 * 
	 * @return
	 */
	public static TemplateEngine getTemplateEngine() {
		return (TemplateEngine) SpringContextUtils.getBean("templateEngine");
	}

	/**
	 * ��spring Context�л�ȡHibernateTemplate��spring Beanʵ��
	 * 
	 * @return
	 */
	public static HibernateTemplate getHibernateTemplate() {
		return (HibernateTemplate) SpringContextUtils
				.getBean("hibernateTemplate");
	}

	/**
	 * ��spring Context�л�ȡJdbcTemplate��spring Beanʵ��
	 * 
	 * @return JdbcTemplate
	 */
	public static JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtils
				.getBean("jdbcTemplate");
		return jdbcTemplate;
	}
}
