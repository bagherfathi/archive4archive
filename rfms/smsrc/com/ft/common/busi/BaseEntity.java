/**
 * 
 */
package com.ft.common.busi;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
 * @author soler
 */
public class BaseEntity implements Serializable,IBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
