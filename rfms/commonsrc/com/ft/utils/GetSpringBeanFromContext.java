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
	 * 从spring Context中获取TemplateEngine的spring Bean实例
	 * 
	 * @return
	 */
	public static TemplateEngine getTemplateEngine() {
		return (TemplateEngine) SpringContextUtils.getBean("templateEngine");
	}

	/**
	 * 从spring Context中获取HibernateTemplate的spring Bean实例
	 * 
	 * @return
	 */
	public static HibernateTemplate getHibernateTemplate() {
		return (HibernateTemplate) SpringContextUtils
				.getBean("hibernateTemplate");
	}

	/**
	 * 从spring Context中获取JdbcTemplate的spring Bean实例
	 * 
	 * @return JdbcTemplate
	 */
	public static JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtils
				.getBean("jdbcTemplate");
		return jdbcTemplate;
	}
}
