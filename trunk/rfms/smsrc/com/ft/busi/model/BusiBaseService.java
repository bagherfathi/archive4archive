package com.ft.busi.model;

import java.io.Serializable;

/**
 * @version 1.0
 */
public interface BusiBaseService {
	/**
	 * 保存历史对象
	 * 
	 * @param object
	 *            历史对象
	 */
	public abstract void saveHisObject(Object object) throws Exception;

	/**
	 * 根据对象标识，找到实体对象。
	 * 
	 * @param entityClass
	 *            对象类型
	 * @param key
	 *            唯一标识符号
	 * @return Object，如果不存在，返回为NULL。
	 */
	public abstract Object getEntityObject(Class entityClass,
			Serializable identifier) throws Exception;
}
