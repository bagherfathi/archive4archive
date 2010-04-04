package com.ft.busi.dto;

import com.ft.sm.entity.AppOperateDtl;


/**
 * AppOperateDtlʵ������װ��
 * 
 * @version 1.0
 * 
 * @spring.bean id="appOperateDtlDTO"
 */
public class AppOperateDtlDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -504846904474252156L;
	private AppOperateDtl appOperateDtl = null;
    /**�������ƶ�Ӧ��ö������*/
	private String classNameEnumDesc;
	/**�����ʶ��Ӧ��������*/
	private String appCode;
    /**
     * ����ʵ�����AppOperateDtl�ķ�װ��
     * 
     * @param appOperateDtl ʵ���������
     *            
     */
    public  AppOperateDtlDTO(AppOperateDtl appOperateDtl) {
        this.appOperateDtl = appOperateDtl;
    }
    
    /**
     * ���ط�װ��ʵ�����AppOperateDtl
     * 
     */
    public AppOperateDtl getAppOperateDtl(){
    	return this.appOperateDtl;
    }
    
    /**
     * ���÷�װʵ�����AppOperateDtl����
     * 
     */
    public void setAppOperateDtl(AppOperateDtl appOperateDtl){
    	this.appOperateDtl = appOperateDtl;
    }

	/**
	 * ����appCode��
	 * @return Returns the appCode.
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * ����appCode �� appCode
	 * @param appCode The appCode to set.
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * ����classNameEnumDesc��
	 * @return Returns the classNameEnumDesc.
	 */
	public String getClassNameEnumDesc() {
		return classNameEnumDesc;
	}

	/**
	 * ����classNameEnumDesc �� classNameEnumDesc
	 * @param classNameEnumDesc The classNameEnumDesc to set.
	 */
	public void setClassNameEnumDesc(String classNameEnumDesc) {
		this.classNameEnumDesc = classNameEnumDesc;
	}
    
}
