/**
 * 
 */
package com.ft.rfms.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.model.MerchantService;
import com.ft.singleTable.web.BaseSimpleAction;

public class TicketAction extends BaseSimpleAction {

	private MerchantService merchantService;

	public MerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	@Override
	public ActionForward create(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		return arg0.findForward("edit");
	}

	public ActionForward save(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// 保存飞券信息
		TicketForm aform = (TicketForm) arg1;
		RfmsTicket ticket = (RfmsTicket) aform.getBaseEntity();
		if (ticket.getSendCount() == null) {
			ticket.setSendCount(new Long(0));
		}
		if (ticket.getUseCount() == null) {
			ticket.setUseCount(new Long(0));
		}
		merchantService.saveOrUpdate(ticket);

		if (ticket.getId() == null) {
			// 生成飞券卡详细
			for (int i = 0; i < ticket.getTicketCount(); i++) {
				RfmsTicketDetail td = new RfmsTicketDetail();
				String seqNumber = merchantService.getTicketSysCode("");
				td.setSeqNumber(seqNumber);// 生成下发卡编号
				td.setMobile("");
				td.setStatus(new Long(1));// 1.等待下发 2.已下发 3.已使用
				td.setTicketId(ticket.getId());
				merchantService.save(td);
			}
		}
		return unspecified(arg0, arg1, arg2, arg3);
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

		TicketForm aform = (TicketForm) arg1;
		if (aform.getId() != null && aform.getId().longValue() > 0) {
			RfmsTicket RfmsTicket = (RfmsTicket) aform.getBaseEntity();

			aform.setBaseEntity(RfmsTicket);
		}

		arg2.getSession().setAttribute("cardForm", aform);

		return arg0.findForward("edit");
	}

	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		super.unspecified(arg0, arg1, arg2, arg3);

		return arg0.getInputForward();
	}

}
