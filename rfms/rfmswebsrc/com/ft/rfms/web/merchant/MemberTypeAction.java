/**
 * 
 */
package com.ft.rfms.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.model.RfmsMemberTypeService;
import com.ft.singleTable.web.BaseSimpleAction;

public class MemberTypeAction extends BaseSimpleAction {

	private RfmsMemberTypeService rfmsMemberTypeService;

	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {

		return arg0.findForward("edit");
	}



	public ActionForward view(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		this.edit(arg0, arg1, arg2, arg3);
		return arg0.findForward("view");
	}



	public RfmsMemberTypeService getRfmsMemberTypeService() {
		return rfmsMemberTypeService;
	}

	public void setRfmsMemberTypeService(
			RfmsMemberTypeService rfmsMemberTypeService) {
		this.rfmsMemberTypeService = rfmsMemberTypeService;
	}

}
