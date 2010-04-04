/**
 * 
 */
package com.ft.rfms.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsMemberType;
import com.ft.rfms.model.RfmsMemberTypeService;
import com.ft.singleTable.web.BaseSimpleAction;

public class MemberTypeAction extends BaseSimpleAction {

	private RfmsMemberTypeService rfmsMemberTypeService;

	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		arg2.getSession().removeAttribute("membertypeForm");
		MemberTypeForm aform = (MemberTypeForm) arg1;
		aform.reset(arg0, arg2);
		arg2.getSession().setAttribute("baseEntity.operatorId",
				aform.getCurrentUser().getOperatorId());

		arg2.getSession().setAttribute("membertypeForm", aform);

		return arg0.findForward("edit");
	}

	@Override
	public ActionForward delete(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		return super.delete(arg0, arg1, arg2, arg3);
	}

	public ActionForward view(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		this.edit(arg0, arg1, arg2, arg3);
		return arg0.findForward("view");
	}

	public ActionForward edit(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		arg2.getSession().removeAttribute("membertypeForm");
		super.edit(arg0, arg1, arg2, arg3);

		MemberTypeForm aform = (MemberTypeForm) arg1;
		if (aform.getId() != null && aform.getId().longValue() > 0) {
			RfmsMemberType rfmsMemberType = (RfmsMemberType) aform
					.getBaseEntity();

			aform.setBaseEntity(rfmsMemberType);
		}
		arg2.getSession().setAttribute("baseEntity.operatorId",
				aform.getCurrentUser().getOperatorId());
		arg2.getSession().setAttribute("membertypeForm", aform);

		return arg0.findForward("edit");
	}

	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		super.unspecified(arg0, arg1, arg2, arg3);
		MemberTypeForm aform = (MemberTypeForm) arg1;
		arg2.setAttribute("searchObj.operatorId", aform.getCurrentUser()
				.getOperatorId());
		arg2.getSession().setAttribute("membertypeForm", aform);
		return arg0.getInputForward();
	}

	public RfmsMemberTypeService getRfmsMemberTypeService() {
		return rfmsMemberTypeService;
	}

	public void setRfmsMemberTypeService(
			RfmsMemberTypeService rfmsMemberTypeService) {
		this.rfmsMemberTypeService = rfmsMemberTypeService;
	}

}
