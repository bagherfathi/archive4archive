/**
 * 
 */
package com.ft.rfms.web.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.busi.PosBindDTO;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketBind;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.model.MerchantService;
import com.ft.rfms.model.RfmsTicketService;
import com.ft.singleTable.web.BaseSimpleAction;

public class TicketAction extends BaseSimpleAction {

	private MerchantService merchantService;

	private RfmsTicketService ticketService;

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
		ticket.setTargetMemberType(aform.getTargetMemberTypes());
		if (ticket.getSendCount() == null) {
			ticket.setSendCount(new Long(0));
		}
		if (ticket.getUseCount() == null) {
			ticket.setUseCount(new Long(0));
		}
		merchantService.saveOrUpdate(ticket);

		// 生成飞券卡详细
		for (int i = 0; i < ticket.getTicketCount(); i++) {
			RfmsTicketDetail td = new RfmsTicketDetail();
			String seqNumber = merchantService.getTicketSysCode(ticket
					.getTicketSerial());
			td.setSeqNumber(seqNumber);// 生成下发卡编号
			td.setMobile("");
			td.setStatus(new Long(1));// 1.等待下发 2.已下发 3.已使用
			td.setTicketId(ticket.getId());
			merchantService.save(td);
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

	/**
	 * 跳转到绑定页面
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward toBind(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		this.edit(arg0, arg1, arg2, arg3);
		TicketForm aform = (TicketForm) arg1;
		List<PosBindDTO> poss = this.ticketService.findPosByTicketId(aform
				.getId());
		arg2.setAttribute("posBindDTOs", poss);
		
		List<BindTreeNode> result=new ArrayList<BindTreeNode>();
		List<RfmsMerchant> merchants=this.merchantService.loadAll(RfmsMerchant.class);
		for(RfmsMerchant merchant:merchants){
			String nodeId="M_"+merchant.getId();
			boolean add=false;
			String name=merchant.getMerchantName().replace('\n', ' ').replace('\r', ' ');
			BindTreeNode node=new BindTreeNode(nodeId,name,merchant.getMerchantCode(),"0");
			List<RfmsMerchantBranch> branchs=this.merchantService.findBranchByMerchantId(merchant.getId());
			for(RfmsMerchantBranch branch:branchs){
				String branchName=branch.getBranchName().replace('\n', ' ').replace('\r', ' ');
				BindTreeNode node1=new BindTreeNode("B_"+branch.getMerchantBranchId(),branchName,"B_"+branch.getMerchantBranchId(),nodeId);
				List<RfmsMerchantPos> posList=this.merchantService.findPosByBranchId(branch.getId());
				if(!add && posList.size()>0){
					result.add(node);
					result.add(node1);
					add=true;
				}
				for(RfmsMerchantPos pos:posList){
					BindTreeNode node2=new BindTreeNode("P_"+pos.getSysPosCode(),"B_"+pos.getSysPosCode(),pos.getSysPosCode(),"B_"+branch.getMerchantBranchId());
					result.add(node2);
				}
			}
		}
		arg2.setAttribute("posTree", result);
		//获取当前已经绑定的POS
		List<RfmsTicketBind> binds=this.ticketService.findBind(aform.getId());
		arg2.setAttribute("binds", binds);
		
		return arg0.findForward("toBind");
	}

	/**
	 * 查询pos，供绑定
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		this.edit(arg0, arg1, arg2, arg3);
		String merchantName = arg2.getParameter("merchantName");
		String branchName = arg2.getParameter("branchName");
		String posCode = arg2.getParameter("posCode");
		List<PosBindDTO> poss = this.ticketService.searchPos(merchantName,
				branchName, posCode);
		arg2.setAttribute("poss", poss);
		
		return arg0.findForward("searchPos");
	}

	public ActionForward tosearchPos(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		this.edit(arg0, arg1, arg2, arg3);
		
		List<BindTreeNode> result=new ArrayList<BindTreeNode>();
		List<RfmsMerchant> merchants=this.merchantService.loadAll(RfmsMerchant.class);
		for(RfmsMerchant merchant:merchants){
			String nodeId="M_"+merchant.getId();
			boolean add=false;
			String name=merchant.getMerchantName().replace('\n', ' ').replace('\r', ' ');
			BindTreeNode node=new BindTreeNode(nodeId,name,merchant.getMerchantCode(),"0");
			List<RfmsMerchantBranch> branchs=this.merchantService.findBranchByMerchantId(merchant.getId());
			for(RfmsMerchantBranch branch:branchs){
				String branchName=branch.getBranchName().replace('\n', ' ').replace('\r', ' ');
				BindTreeNode node1=new BindTreeNode("B_"+branch.getMerchantBranchId(),branchName,"B_"+branch.getMerchantBranchId(),nodeId);
				List<RfmsMerchantPos> posList=this.merchantService.findPosByBranchId(branch.getId());
				if(!add && posList.size()>0){
					result.add(node);
					result.add(node1);
					add=true;
				}
				for(RfmsMerchantPos pos:posList){
					BindTreeNode node2=new BindTreeNode("P_"+pos.getSysPosCode(),"B_"+pos.getSysPosCode(),pos.getSysPosCode(),"B_"+branch.getMerchantBranchId());
					result.add(node2);
				}
			}
		}
		arg2.setAttribute("posTree", result);
		
		return arg0.findForward("searchPos");
	}

	/**
	 * 绑定pos
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws Exception
	 */
	public ActionForward bind(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		TicketForm aform = (TicketForm) arg1;
		String[] posCodes = arg2.getParameterValues("ids");
		this.ticketService.saveBindPos(aform.getId(), posCodes);
		return this.toBind(arg0, arg1, arg2, arg3);
	}
	
	public ActionForward bindDelete(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		TicketForm aform = (TicketForm) arg1;
		this.ticketService.delObject(aform.getBindId(),"RfmsTicketBind");
		return this.toBind(arg0, arg1, arg2, arg3);
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

	/**
	 * @param ticketService
	 *            the ticketService to set
	 */
	public void setTicketService(RfmsTicketService ticketService) {
		this.ticketService = ticketService;
	}

}
