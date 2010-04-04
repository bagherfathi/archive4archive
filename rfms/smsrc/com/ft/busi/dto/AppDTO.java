package com.ft.busi.dto;

import com.ft.sm.entity.App;


/**
 * Appʵ������װ��
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
	/** ����״̬ -���� -1*/
	public static final long APP_STATUS_CS_CANCEL=1;  
	/** ����״̬ -���� -2*/
	public static final long APP_STATUS_CS_REVERSE=2;  
	
	/** ����״̬ -���� -0*/
	public static final long  APP_STATUS_NORMAL=0;

	private App app = null;
    
    /**
     * ����ʵ�����App�ķ�װ��
     * 
     * @param app ʵ���������
     *            
     */
    public  AppDTO(App app) {
        this.app = app;
    }
    /**
     * 
     * ���캯��
     */
    public  AppDTO(){
    	this.app=new App();
    }
    
    /**
     * ���ط�װ��ʵ�����App
     * 
     */
    public App getApp(){
    	return this.app;
    }
    
    /**
     * ���÷�װʵ�����App����
     * 
     */
    public void setApp(App app){
    	this.app = app;
    }

	
    
}
