package com.renhenet.fw.dao;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.renhenet.util.SecurityUtil;


public class SecuSessionFactory extends LocalSessionFactoryBean {
	private static final String PRO_PASSWORD = "hibernate.connection.password";

	private static final String PRO_USER = "hibernate.connection.username";
	private static final String PRO_URL = "hibernate.connection.url";

	public void setHibernateProperties(Properties hibernateProperties) {

		String password = hibernateProperties.getProperty(PRO_PASSWORD);
		if (!StringUtils.isEmpty(password)) {
			hibernateProperties.setProperty(PRO_PASSWORD, SecurityUtil
					.decrypt(password));
		}

		hibernateProperties.setProperty(PRO_USER, SecurityUtil
				.decrypt(hibernateProperties.getProperty(PRO_USER)));
		
		hibernateProperties.setProperty(PRO_URL, SecurityUtil
				.decrypt(hibernateProperties.getProperty(PRO_URL)));

		super.setHibernateProperties(hibernateProperties);
	}
}
