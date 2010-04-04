package com.ft.busi.dto;

import com.ft.sm.entity.AppOperateDtl;


/**
 * AppOperateDtl实体对象封装类
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
    /**对象名称对应的枚举描述*/
	private String classNameEnumDesc;
	/**受理标识对应的受理编号*/
	private String appCode;
    /**
     * 构造实体对象AppOperateDtl的封装类
     * 
     * @param appOperateDtl 实体对象属性
     *            
     */
    public  AppOperateDtlDTO(AppOperateDtl appOperateDtl) {
        this.appOperateDtl = appOperateDtl;
    }
    
    /**
     * 返回封装的实体对象AppOperateDtl
     * 
     */
    public AppOperateDtl getAppOperateDtl(){
    	return this.appOperateDtl;
    }
    
    /**
     * 设置封装实体对象AppOperateDtl属性
     * 
     */
    public void setAppOperateDtl(AppOperateDtl appOperateDtl){
    	this.appOperateDtl = appOperateDtl;
    }

	/**
	 * 返回appCode。
	 * @return Returns the appCode.
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * 设置appCode 到 appCode
	 * @param appCode The appCode to set.
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * 返回classNameEnumDesc。
	 * @return Returns the classNameEnumDesc.
	 */
	public String getClassNameEnumDesc() {
		return classNameEnumDesc;
	}

	/**
	 * 设置classNameEnumDesc 到 classNameEnumDesc
	 * @param classNameEnumDesc The classNameEnumDesc to set.
	 */
	public void setClassNameEnumDesc(String classNameEnumDesc) {
		this.classNameEnumDesc = classNameEnumDesc;
	}
    
}
