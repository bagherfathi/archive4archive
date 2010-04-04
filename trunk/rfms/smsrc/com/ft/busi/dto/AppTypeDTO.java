package com.ft.busi.dto;

import com.ft.sm.entity.AppType;


/**
 * AppTypeʵ������װ��
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
     * ����ʵ�����AppType�ķ�װ��
     * 
     * @param appType ʵ���������
     *            
     */
    public  AppTypeDTO(AppType appType) {
        this.appType = appType;
    }
    
    /**
     * ���ط�װ��ʵ�����AppType
     * 
     */
    public AppType getAppType(){
    	return this.appType;
    }
    
    /**
     * ���÷�װʵ�����AppType����
     * 
     */
    public void setAppType(AppType appType){
    	this.appType = appType;
    }
    
}
