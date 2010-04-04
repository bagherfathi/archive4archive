package com.ft.spring.web;

import org.springframework.beans.factory.BeanFactory;

/**
 * 用于获取Spring配置的Bean实例。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class SpringContextUtils {
    private static BeanFactory beanFactory;

    public static void setBeanFactory(BeanFactory factory) {
	beanFactory = factory;
    }
    
    /**
     * 获取Spring配置中的Bean实例。
     * @param name    bean id
     * @return
     */
    public static Object getBean(String name) {
	return beanFactory.getBean(name);
    }
}
