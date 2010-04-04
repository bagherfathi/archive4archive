package com.ft.busi.common;

/**
 * @spring.bean id="busiSpringBeanUtils"
 *  
 * @version 1.0
 */
public class SpringBeanUtils {
	private static BeanAdapter beanAdapter;

	public static Object getBean(String name) {
		return beanAdapter.getBean(name);
	}

	/**
	 * 
	 * @spring.property ref="busiBeanAdapter"
	 * …Ë÷√beanAdapter µΩ beanAdapter
	 * @param beanAdapter
	 *            The beanAdapter to set.
	 */
	public void setBeanAdapter(BeanAdapter beanAdapter) {
		SpringBeanUtils.beanAdapter = beanAdapter;
	}
}
