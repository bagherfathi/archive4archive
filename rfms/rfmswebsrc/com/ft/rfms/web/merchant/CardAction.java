/**
 * 
 */
package com.ft.rfms.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsCard;
import com.ft.rfms.model.RfmsCardService;
import com.ft.singleTable.web.BaseSimpleAction;

public class CardAction extends BaseSimpleAction {

	private RfmsCardService rfmsCardService;

	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		arg2.getSession().removeAttribute("cardForm");
		CardForm aform = (CardForm) arg1;
		aform.reset(arg0, arg2);
		arg2.getSession().setAttribute("baseEntity.operatorId",
				aform.getCurrentUser().getOperatorId());

		arg2.getSession().setAttribute("cardForm", aform);

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
		arg2.getSession().removeAttribute("cardForm");
		super.edit(arg0, arg1, arg2, arg3);

		CardForm aform = (CardForm) arg1;
		if (aform.getId() != null && aform.getId().longValue() > 0) {
			RfmsCard rfmsCard = (RfmsCard) aform.getBaseEntity();

			aform.setBaseEntity(rfmsCard);
		}
		
		arg2.getSession().setAttribute("cardForm", aform);

		return arg0.findForward("edit");
	}

	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		super.unspecified(arg0, arg1, arg2, arg3);
		CardForm aform = (CardForm) arg1;
		arg2.setAttribute("searchObj.operatorId", aform.getCurrentUser()
				.getOperatorId());
		arg2.getSession().setAttribute("cardForm", aform);
		return arg0.getInputForward();
	}

	public RfmsCardService getRfmsCardService() {
		return rfmsCardService;
	}

	public void setRfmsCardService(RfmsCardService rfmsCardService) {
		this.rfmsCardService = rfmsCardService;
	}

}
