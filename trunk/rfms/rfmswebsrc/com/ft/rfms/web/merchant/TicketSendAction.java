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
		// 根据飞券卡得到飞券相关信息
		TicketDetailForm aform = (TicketDetailForm) arg1;

		RfmsTicket rfmsTicket = (RfmsTicket) rfmsTicketService.getObjectById(
				RfmsTicket.class, aform.getId());
		aform.setRfmsTicket(rfmsTicket);

		// 得到已下发的飞券卡下发信息 status>1
		ticketDetail = rfmsTicketService.getRfmsTicketDetailByBigThanStatus(
				rfmsTicket.getId(), new Long(1));

		String mobiles1 = "";
		for (int i = 0; i < ticketDetail.size(); i++) {
			if (ticketDetail.get(i) != null) {
				mobiles1 += ticketDetail.get(i).getMobile() + ";";
			}
		}
		aform.setMobiles1(mobiles1);

		// 得到等待下发的飞券卡下发信息 status>0
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

		// 判断是模板下发还是会员下发
		if (aform.getType() == 1) {
			List<RfmsMember> rfmsMemberList = null;
			FormFile file = aform.getStrFile();// 取得上传的文件
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
					// 更新下发飞券状态 更新手机，接收人，下发人，下发时间，status
					ticketDetail = rfmsTicketService
							.getRfmsTicketDetailByStatus(aform.getId(),
									new Long(1));
					int len = rfmsMemberList.size();
					if (len > ticketDetail.size()) {
						// 超过发送限额 返回
						ActionErrors errors = arg1.validate(arg0, arg2);

						errors.add("notSameSize", new ActionMessage(
								"msg.show.merchant.branchSizeEbranchNum"));
						 ActionMessagesHelper.saveMessage(arg2,
						 "msg.show.merchant.branchSizeEbranchNum", "session");
						return this
								.getRedirectForwardAction("ticketsendtem.do");
					}

					// 取得飞券卡的信息 更新卡信息
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

							// 发送短信
							RfmsSms sms = new RfmsSms();
							sms.setCreateDate(new Date());
							sms.setMessage(rfmsTicket.getUseRule());// 短信内容
							sms.setMobile(mobile);
							sms.setOperatorId(operatorId);
							sms.setStatus("1");// 1表示为发送
							rfmsTicketService.save(sms);
						}
					}
				}
			}
		} else {
			// 更新等待下发飞券状态 更新手机，接收人，下发人，下发时间，status
			ticketDetail = rfmsTicketService.getRfmsTicketDetailByStatus(aform
					.getId(), new Long(1));
			String[] str = aform.getMobiles().split(";");
			int len = str.length;
			if (len > ticketDetail.size()) {
				// 超过发送限额 返回
				ActionErrors errors = arg1.validate(arg0, arg2);
				errors.add("notSameSize", new ActionMessage(
						"msg.show.merchant.branchSizeEbranchNum"));
				ActionMessagesHelper.saveMessage(arg2,
						"msg.show.merchant.branchSizeEbranchNum", null);
			}

			// 取得飞券卡的信息 更新卡信息
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

					// 发送短信
					RfmsSms sms = new RfmsSms();
					sms.setCreateDate(new Date());
					sms.setMessage(rfmsTicket.getUseRule());// 短信内容
					sms.setMobile(str[i]);
					sms.setOperatorId(operatorId);
					sms.setStatus("1");// 1表示为发送
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
