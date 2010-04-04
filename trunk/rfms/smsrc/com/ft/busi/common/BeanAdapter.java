package com.ft.busi.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.ft.common.exception.CommonRuntimeException;

/**
 * @spring.bean id="busiBeanAdapter"
 *  
 * 用于动态加载bean
 *  
 * @version 1.0
 */
public class BeanAdapter implements BeanFactoryAware {
	protected BeanFactory beanFactory;

	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.beanFactory = arg0;
	}

	/**
	 * 动态获取一个Bean
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName) {
		try {
			return beanFactory.getBean(beanName);
		} catch (Exception ex) {
			throw new CommonRuntimeException(ex);
		}
	}

}
