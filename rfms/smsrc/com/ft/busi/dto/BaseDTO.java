package com.ft.busi.dto;

/**
 * @version 1.0
 */
public class BaseDTO implements java.io.Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 封装的实体对象
	protected Object obj = null;

	// 返回实体对象
	public Object getObject() {
		return obj;
	}

	

}
