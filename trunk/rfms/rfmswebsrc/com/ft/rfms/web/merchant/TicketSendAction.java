/**
 * 
 */
package com.ft.rfms.web.merchant;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.ft.rfms.entity.RfmsMember;
import com.ft.rfms.entity.RfmsSms;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.model.RfmsMemberService;
import com.ft.rfms.model.RfmsTicketService;
import com.ft.singleTable.web.BaseSimpleAction;
import com.ft.singleTable.web.WebHelper;
import com.ft.struts.ActionMessagesHelper;
import com.ft.utils.DateUtil;

public class TicketSendAction extends BaseSimpleAction {
	private RfmsTicketService rfmsTicketService;
	private RfmsMemberService rfmsMemberService;
	List<RfmsTicketDetail> ticketDetail;

	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// ���ݷ�ȯ���õ���ȯ�����Ϣ
		TicketDetailForm aform = (TicketDetailForm) arg1;

		RfmsTicket rfmsTicket = (RfmsTicket) rfmsTicketService.getObjectById(
				RfmsTicket.class, aform.getId());
		aform.setRfmsTicket(rfmsTicket);

		// �õ����·��ķ�ȯ���·���Ϣ status>1
		ticketDetail = rfmsTicketService.getRfmsTicketDetailByBigThanStatus(
				rfmsTicket.getId(), new Long(1));

		String mobiles1 = "";
		for (int i = 0; i < ticketDetail.size(); i++) {
			if (ticketDetail.get(i) != null) {
				mobiles1 += ticketDetail.get(i).getMobile() + ";";
			}
		}
		aform.setMobiles1(mobiles1);

		// �õ��ȴ��·��ķ�ȯ���·���Ϣ status>0
		String mobiles = "";
		String strMemberType = rfmsTicket.getTargetMemberType();
		if (!StringUtils.isEmpty(strMemberType)) {
			for (int i = 0; i < strMemberType.split(";").length; i++) {
				List<RfmsMember> memberList = rfmsMemberService
						.getRfmsMemberByStatus(strMemberType.split(";")[i]);
				for (RfmsMember rm : memberList) {
					if (mobiles1.indexOf(rm.getMobile()) < 0) {
						mobiles += rm.getMobile() + ";";
					}
				}
			}
		}
		if (!StringUtils.isEmpty(mobiles)) {
			mobiles = mobiles.substring(0, mobiles.length() - 1);
		}
		aform.setMobiles(mobiles);

		return arg0.getInputForward();
	}

	public ActionForward save(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		TicketDetailForm aform = (TicketDetailForm) arg1;
		Long operatorId = aform.getCurrentUser().getOperatorId();

		// �ж���ģ���·����ǻ�Ա�·�
		if (aform.getType() == 1) {
			List<RfmsMember> rfmsMemberList = null;
			FormFile file = aform.getStrFile();// ȡ���ϴ����ļ�
			String fName = file.getFileName().toLowerCase();
			if (!fName.endsWith(".xls")) {
				return unspecified(arg0, arg1, arg2, arg3);
			} else {
				try {
					Date now = new Date();
					String seq = DateUtil.date2MysqlDate(now) + "";
					String fileName = seq + ".xls";
					WebHelper.writeUploadFile2Server(fileName, file);
					rfmsMemberList = rfmsMemberService.importMember(fileName,
							operatorId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (rfmsMemberList != null && rfmsMemberList.size() > 0) {
					// �����·���ȯ״̬ �����ֻ��������ˣ��·��ˣ��·�ʱ�䣬status
					ticketDetail = rfmsTicketService
							.getRfmsTicketDetailByStatus(aform.getId(),
									new Long(1));
					int len = rfmsMemberList.size();
					if (len > ticketDetail.size()) {
						// ���������޶� ����
						ActionErrors errors = arg1.validate(arg0, arg2);

						errors.add("notSameSize", new ActionMessage(
								"msg.show.merchant.branchSizeEbranchNum"));
						 ActionMessagesHelper.saveMessage(arg2,
						 "msg.show.merchant.branchSizeEbranchNum", "session");
						return this
								.getRedirectForwardAction("ticketsendtem.do");
					}

					// ȡ�÷�ȯ������Ϣ ���¿���Ϣ
					RfmsTicket rfmsTicket = (RfmsTicket) rfmsTicketService
							.getObjectById(RfmsTicket.class, aform.getId());
					rfmsTicket.setSendCount(rfmsTicket.getSendCount() + len);
					rfmsTicketService.update(rfmsTicket);

					for (int i = 0; i < len; i++) {
						String mobile = rfmsMemberList.get(i).getMobile();
						if (!StringUtils.isEmpty(mobile)) {
							RfmsTicketDetail td = ticketDetail.get(i);
							td.setSendOperatorId(operatorId);
							td.setSendDate(new Date());
							td.setMobile(mobile);
							td.setStatus(new Long(2));
							rfmsTicketService.update(td);

							// ���Ͷ���
							RfmsSms sms = new RfmsSms();
							sms.setCreateDate(new Date());
							sms.setMessage(rfmsTicket.getUseRule());// ��������
							sms.setMobile(mobile);
							sms.setOperatorId(operatorId);
							sms.setStatus("1");// 1��ʾΪ����
							rfmsTicketService.save(sms);
						}
					}
				}
			}
		} else {
			// ���µȴ��·���ȯ״̬ �����ֻ��������ˣ��·��ˣ��·�ʱ�䣬status
			ticketDetail = rfmsTicketService.getRfmsTicketDetailByStatus(aform
					.getId(), new Long(1));
			String[] str = aform.getMobiles().split(";");
			int len = str.length;
			if (len > ticketDetail.size()) {
				// ���������޶� ����
				ActionErrors errors = arg1.validate(arg0, arg2);
				errors.add("notSameSize", new ActionMessage(
						"msg.show.merchant.branchSizeEbranchNum"));
				ActionMessagesHelper.saveMessage(arg2,
						"msg.show.merchant.branchSizeEbranchNum", null);
			}

			// ȡ�÷�ȯ������Ϣ ���¿���Ϣ
			RfmsTicket rfmsTicket = (RfmsTicket) rfmsTicketService
					.getObjectById(RfmsTicket.class, aform.getId());
			rfmsTicket.setSendCount(rfmsTicket.getSendCount() + len);
			rfmsTicketService.update(rfmsTicket);

			for (int i = 0; i < len; i++) {
				if (!StringUtils.isEmpty(str[i])) {
					RfmsTicketDetail td = ticketDetail.get(i);
					td.setSendOperatorId(operatorId);
					td.setSendDate(new Date());
					td.setMobile(str[i]);
					td.setStatus(new Long(2));
					rfmsTicketService.update(td);

					// ���Ͷ���
					RfmsSms sms = new RfmsSms();
					sms.setCreateDate(new Date());
					sms.setMessage(rfmsTicket.getUseRule());// ��������
					sms.setMobile(str[i]);
					sms.setOperatorId(operatorId);
					sms.setStatus("1");// 1��ʾΪ����
					rfmsTicketService.save(sms);
				}
			}
		}

		return this.getRedirectForwardAction("ticket.do");
	}

	public RfmsTicketService getRfmsTicketService() {
		return rfmsTicketService;
	}

	public void setRfmsTicketService(RfmsTicketService rfmsTicketService) {
		this.rfmsTicketService = rfmsTicketService;
	}

	protected List<RfmsTicketDetail> getTicketDetail() {
		return ticketDetail;
	}

	protected void setTicketDetail(List<RfmsTicketDetail> ticketDetail) {
		this.ticketDetail = ticketDetail;
	}

	public RfmsMemberService getRfmsMemberService() {
		return rfmsMemberService;
	}

	public void setRfmsMemberService(RfmsMemberService rfmsMemberService) {
		this.rfmsMemberService = rfmsMemberService;
	}

}
