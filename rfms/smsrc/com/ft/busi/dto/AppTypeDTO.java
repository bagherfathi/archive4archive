package com.ft.busi.dto;

import com.ft.sm.entity.AppType;


/**
 * AppType实体对象封装类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appTypeDTO"
 */
public class AppTypeDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8024592159228498247L;
	private AppType appType = null;
    
    /**
     * 构造实体对象AppType的封装类
     * 
     * @param appType 实体对象属性
     *            
     */
    public  AppTypeDTO(AppType appType) {
        this.appType = appType;
    }
    
    /**
     * 返回封装的实体对象AppType
     * 
     */
    public AppType getAppType(){
    	return this.appType;
    }
    
    /**
     * 设置封装实体对象AppType属性
     * 
     */
    public void setAppType(AppType appType){
    	this.appType = appType;
    }
    
}
