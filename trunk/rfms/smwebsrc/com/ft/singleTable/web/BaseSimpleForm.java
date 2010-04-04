/**
 * 
 */
package com.ft.singleTable.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.common.busi.BaseEntity;
import com.ft.hibernate.support.IBaseEntity;
import com.ft.web.sm.BaseValidatorForm;

/**
 * @author soler
 */
public class BaseSimpleForm extends BaseValidatorForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		String className=arg1.getParameter(SimpleActionDefination.BASE_OBJECT_CLASS_NAME);
		if(baseEntity==null&&className!=null){
			try{
				baseEntity=(IBaseEntity)Class.forName(className).newInstance();
			}catch(Exception e){
				e.printStackTrace();
				baseEntity=new BaseEntity();
			}
		}
		if(searchObj==null&&className!=null){
			try{
				searchObj=(IBaseEntity)Class.forName(className).newInstance();
			}catch(Exception e){
				e.printStackTrace();
				searchObj=new BaseEntity();
			}
		}
		super.reset(arg0, arg1);
	}

	private IBaseEntity baseEntity;
	
	private IBaseEntity searchObj;
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IBaseEntity getBaseEntity() {
		return baseEntity;
	}

	public void setBaseEntity(IBaseEntity baseObject) {
		this.baseEntity = baseObject;
	}

	/**
	 * @return the searchObj
	 */
	public IBaseEntity getSearchObj() {
		return searchObj;
	}

	/**
	 * @param searchObj the searchObj to set
	 */
	public void setSearchObj(IBaseEntity searchObj) {
		this.searchObj = searchObj;
	}
	
	
}
