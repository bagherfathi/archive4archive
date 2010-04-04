/**
 * 
 */
package com.ft.rfms.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.model.RfmsTicketService;
import com.ft.singleTable.web.BaseSimpleAction;

public class TicketSendAction extends BaseSimpleAction {

	private RfmsTicketService ticketervice;

	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		arg2.getSession().removeAttribute("cardSendForm");
		TicketSendForm aform = (TicketSendForm) arg1;
		aform.reset(arg0, arg2);
		arg2.getSession().setAttribute("baseEntity.operatorId",
				aform.getCurrentUser().getOperatorId());

		arg2.getSession().setAttribute("cardSendForm", aform);

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
		arg2.getSession().removeAttribute("cardSendForm");
		super.edit(arg0, arg1, arg2, arg3);

		return arg0.findForward("edit");
	}

	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		super.unspecified(arg0, arg1, arg2, arg3);
		TicketSendForm aform = (TicketSendForm) arg1;
		arg2.setAttribute("searchObj.operatorId", aform.getCurrentUser()
				.getOperatorId());
		arg2.getSession().setAttribute("cardSendForm", aform);
		return arg0.getInputForward();
	}

	public RfmsTicketService getTicketervice() {
		return ticketervice;
	}

	public void setTicketervice(RfmsTicketService ticketervice) {
		this.ticketervice = ticketervice;
	}

}
