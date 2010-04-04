package com.ft.spring.web;

import org.springframework.beans.factory.BeanFactory;

/**
 * ���ڻ�ȡSpring���õ�Beanʵ����
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class SpringContextUtils {
    private static BeanFactory beanFactory;

    public static void setBeanFactory(BeanFactory factory) {
	beanFactory = factory;
    }
    
    /**
     * ��ȡSpring�����е�Beanʵ����
     * @param name    bean id
     * @return
     */
    public static Object getBean(String name) {
	return beanFactory.getBean(name);
    }
}
