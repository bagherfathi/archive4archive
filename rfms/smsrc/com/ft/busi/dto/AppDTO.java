package com.ft.busi.dto;

import com.ft.sm.entity.App;


/**
 * App实体对象封装类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appDTO"
 */
public class AppDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3999707669917500874L;
	/** 受理状态 -撤销 -1*/
	public static final long APP_STATUS_CS_CANCEL=1;  
	/** 受理状态 -返销 -2*/
	public static final long APP_STATUS_CS_REVERSE=2;  
	
	/** 受理状态 -正常 -0*/
	public static final long  APP_STATUS_NORMAL=0;

	private App app = null;
    
    /**
     * 构造实体对象App的封装类
     * 
     * @param app 实体对象属性
     *            
     */
    public  AppDTO(App app) {
        this.app = app;
    }
    /**
     * 
     * 构造函数
     */
    public  AppDTO(){
    	this.app=new App();
    }
    
    /**
     * 返回封装的实体对象App
     * 
     */
    public App getApp(){
    	return this.app;
    }
    
    /**
     * 设置封装实体对象App属性
     * 
     */
    public void setApp(App app){
    	this.app = app;
    }

	
    
}
