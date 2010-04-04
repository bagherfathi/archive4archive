package com.ft.singleTable.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.common.busi.BaseService;
import com.ft.hibernate.support.IBaseEntity;
import com.ft.web.sm.BaseAction;

/**
 * 单表业务中所有控制类的基类
 * @version 1.0
 */

public class BaseSimpleAction extends BaseAction {

	Log log = LogFactory.getLog(BaseSimpleAction.class);

	private BaseService baseService=(BaseService)SpringBeanUtils.getBean("baseService");;

	 

	/**
	 * @return the baseService
	 */
	public BaseService getBaseService() {
		return baseService;
	}

	/**
	 * @param baseService the baseService to set
	 */
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	@Override
	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return arg0.getInputForward();
	}

	public ActionForward save(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		BaseSimpleForm aform = (BaseSimpleForm) arg1;
		Object ob = aform.getBaseEntity();
		this.baseService.saveOrUpdate(ob);
		return unspecified(arg0, arg1, arg2, arg3);
	}

	public ActionForward delete(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		BaseSimpleForm aform = (BaseSimpleForm) arg1;
		Long id = aform.getId();
		Class entityClass=aform.getBaseEntity().getClass();
		String className=entityClass.toString();
		className=className.substring(className.lastIndexOf(".")+1);
		this.baseService.delObject(id, className);

		return unspecified(arg0, arg1, arg2, arg3);
	}

	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return arg0.findForward("create");
	}

	public ActionForward edit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		String className = arg2
				.getParameter(SimpleActionDefination.BASE_OBJECT_CLASS_NAME);
		BaseSimpleForm aform = (BaseSimpleForm) arg1;
		Long id = aform.getId();
		if(id!=null && id.intValue()>0){
		if(className!=null){
			IBaseEntity ob = (IBaseEntity) this.baseService.getObjectById(Class
					.forName(className), id);
			aform.setBaseEntity(ob);
		
		}else{
			IBaseEntity ob = (IBaseEntity) this.baseService.getObjectById(aform.getBaseEntity().getClass(), id);
			aform.setBaseEntity(ob);
		}
		}
		return arg0.findForward("edit");
	}

	public ActionForward search(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return arg0.getInputForward();
	}
}
