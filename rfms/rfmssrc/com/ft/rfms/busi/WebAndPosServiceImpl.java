/**
 * 
 */
package com.ft.rfms.busi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.EnumManager;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.RfcsTrade;
import com.ft.rfms.entity.RfmsMember;
import com.ft.rfms.entity.RfmsSms;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketBind;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.entity.dao.RfmsTicketDAO;
import com.ft.sm.dto.EnumEntryDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.spring.web.SpringContextUtils;
import com.ft.utils.encode.Md5PasswordEncoder;

/**
 * @author solar
 * 
 */
public class WebAndPosServiceImpl extends BaseServiceImpl implements
		com.ft.rfms.model.WebAndPosService {

	private RfmsTicketDAO ticketDao;

	private Map<String, String> posSigninMap = new HashMap<String, String>();

	/**
	 * @param ticketDao
	 *            the ticketDao to set
	 */
	public void setTicketDao(RfmsTicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#findTicket(java.lang.String)
	 */
	public List<RfmsTicket> findTicket(Long merchantCode) throws Exception {
		// this.baseDao.getEntityByIdentityAttribute(RfmsMerchant.class,
		// "merchantCode", merchantCode);
		return this.ticketDao.findTicketsByMerchant(merchantCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#getIndustry()
	 */
	public Map<String, String> getIndustry() throws Exception {
		EnumManager enumManager = (EnumManager) SpringContextUtils
				.getBean("enumManager");
		// 商户行业类型的ENUM
		List list = enumManager.findEnumEntriesByEnum("categoryCode",
				"enumCode");
		Map<String, String> result = new HashMap<String, String>();
		for (Object ob : list) {
			EnumEntryDTO entryDTO = (EnumEntryDTO) ob;
			result.put(entryDTO.getValue(), entryDTO.getLabel());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#getTicket(java.lang.String)
	 */
	public ResultMsg getTicket(String ticketCode) throws Exception {
		ResultMsg resultMsg = null;
		RfmsTicket rfmsTicket = (RfmsTicket) this.baseDao
				.getEntityByIdentityAttribute(RfmsTicket.class, "ticketSerial",
						ticketCode);

		List<RfmsTicketDetail> ticketDetailList = ticketDao
				.getRfmsTicketDetailByStatus(rfmsTicket.getId(), new Long(1));
		if (ticketDetailList != null && ticketDetailList.size() > 0) {
			RfmsTicketDetail ticketDetail = ticketDetailList.get(0);
			resultMsg = new ResultMsg("2001", "恭喜您，申请成功！飞券卡号为："
					+ ticketDetail.getValidatorCode());
			// 已经发放
			ticketDetail.setStatus(new Long(2));
			baseDao.update(ticketDetail);
		} else {
			resultMsg = new ResultMsg("2002", "该券已发放完成，无法申请");
		}

		return resultMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#memberLogin(java.lang.String,
	 * java.lang.String)
	 */
	public MemberLoginDTO memberLogin(String mobile, String pwd)
			throws Exception {
		MemberLoginDTO result = new MemberLoginDTO();
		Object obj = this.baseDao.getEntityByIdentityAttribute(
				RfmsMember.class, "mobile", mobile);
		if (obj == null) {
			result.setResultMsg(new ResultMsg("1002", "会员号（手机号码）不存在！"));
			return result;
		}
		RfmsMember member = (RfmsMember) obj;
		if (!member.getPwd().equals(pwd)) {
			result.setResultMsg(new ResultMsg("1003", "会员密码不正确！"));
			return result;
		}
		result.setResultMsg(new ResultMsg("1001", "登录成功！"));
		result.setMember(member);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#modifyPwd(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public ResultMsg modifyPwd(String mobile, String oldPwd, String newPwd)
			throws Exception {
		Object obj = this.baseDao.getEntityByIdentityAttribute(
				RfmsMember.class, "mobile", mobile);
		if (obj != null) {
			return new ResultMsg("1002", "会员号（手机号码）不存在！");
		}
		RfmsMember member = (RfmsMember) obj;
		if (!member.getPwd().equals(oldPwd)) {
			return new ResultMsg("1003", "原会员密码不正确！");
		}
		member.setPwd(newPwd);
		member.setUpdateDate(new Date());
		this.baseDao.saveOrUpdate(member);
		return new ResultMsg("1001", "密码修改成功！");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#posSignIn(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public ResultMsg posSignIn(String posCode, String loginName, String pwd)
			throws Exception {
		OperatorManager operatorMgmt = (OperatorManager) SpringContextUtils
				.getBean("operatorManager");
		OperatorDTO op = operatorMgmt.findOperatorByLoginName(loginName);

		// 操作员不存在
		if (op == null) {
			return new ResultMsg("1002", "操作员不存在");
		}

		// 禁止状态
		if (OperatorDTO.STATUS_DISABLE == op.getStatus()) {
			return new ResultMsg("1003", "操作员被禁用");
		}

		// 锁定状态
		if (1 == op.getLockStatus()) {
			return new ResultMsg("1004", "操作员被锁定");
		}

		String encodedPass = Md5PasswordEncoder.encode(pwd);

		// 密码错误
		if (!encodedPass.equals(op.getPassword())) {
			new ResultMsg("1005", "操作员密码不正确");
		}
		posSigninMap.put(posCode, loginName);
		return new ResultMsg("1001", "POS机签入成功");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#posSignOut(java.lang.String)
	 */
	public ResultMsg posSignOut(String posCode) throws Exception {
		posSigninMap.remove(posCode);
		return new ResultMsg("1001", "POS机签出成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#regMember(java.lang.String)
	 */
	public ResultMsg regMember(String mobile) throws Exception {
		Object obj = this.baseDao.getEntityByIdentityAttribute(
				RfmsMember.class, "mobile", mobile);
		if (obj != null) {
			return new ResultMsg("1002", "手机号码已经存在！");
		}
		try {
			RfmsMember member = new RfmsMember();
			member.setMemberType(new Long(1));
			member.setMobile(mobile);
			member.setPwd("123456");
			member.setUpdateDate(new Date());
			member.setCreateDate(new Date());
			member.setStatus("1");
			this.baseDao.save(member);
			// 发送密码
			RfmsSms sms = new RfmsSms();
			sms.setMessage("您已成功注册为飞卷会员，您的初始密码是123456");
			sms.setMobile(mobile);
			sms.setOperatorId(member.getId());
			sms.setCreateDate(new Date());
			sms.setUpdateDate(new Date());
			this.baseDao.save(sms);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultMsg("1003", "未知错误！");
		}
		return new ResultMsg("1001", "注册成功！");
	}

	/**
	 * 116114发放卡
	 * 
	 * @param mobile
	 * @param ticketCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg sendTicketMember(String mobile, String ticketCode)
			throws Exception {
		ResultMsg resultMsg = null;
		RfmsMember rfmsMember = (RfmsMember) this.baseDao
				.getEntityByIdentityAttribute(RfmsMember.class, "mobile",
						mobile);
		if (rfmsMember != null) {
			RfmsTicket rfmsTicket = (RfmsTicket) this.baseDao
					.getEntityByIdentityAttribute(RfmsTicket.class,
							"ticketSerial", ticketCode);

			List<RfmsTicketDetail> ticketDetailList = ticketDao
					.getRfmsTicketDetailByStatus(rfmsTicket.getId(),
							new Long(1));
			if (ticketDetailList != null && ticketDetailList.size() > 0) {
				RfmsTicketDetail ticketDetail = ticketDetailList.get(0);
				resultMsg = new ResultMsg("2001", "发放优惠券成功！");
				// 已经发放
				ticketDetail.setMobile(mobile);
				ticketDetail.setSendDate(new Date());
				ticketDetail.setSendOperatorId(rfmsMember.getOperatorId());
				ticketDetail.setStatus(new Long(2));
				baseDao.update(ticketDetail);

				// 发送密码
				RfmsSms sms = new RfmsSms();
				sms.setMessage("您已成功注册为飞卷会员，您的初始密码是123456");
				sms.setMobile(mobile);
				sms.setOperatorId(rfmsMember.getOperatorId());
				sms.setCreateDate(new Date());
				sms.setUpdateDate(new Date());
				this.baseDao.save(sms);
				return resultMsg;
			} else {
				return new ResultMsg("2002", "该券已发放完成，无法申请");
			}

		}
		return new ResultMsg("1003", "手机号码不存在，请先添加用户信息");
		// try {
		// return this.getTicket(ticketCode);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// return new ResultMsg("1003", "未知错误！");
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#regMember(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	public ResultMsg regMember(String mobile, String pwd, String name,
			Long sex, String address) throws Exception {
		Object obj = this.baseDao.getEntityByIdentityAttribute(
				RfmsMember.class, "mobile", mobile);
		if (obj != null) {
			return new ResultMsg("1002", "手机号码已经存在！");
		}
		try {
			RfmsMember member = new RfmsMember();
			member.setMemberType(new Long(1));
			member.setMobile(mobile);
			member.setPwd(pwd);
			member.setAddress(address);
			member.setName(name);
			member.setSex(sex + "");
			member.setUpdateDate(new Date());
			member.setCreateDate(new Date());
			member.setStatus("1");
			this.baseDao.save(member);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultMsg("1003", "未知错误！");
		}
		return new ResultMsg("1001", "注册成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#searchTicket(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<RfmsTicket> searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception {
		return ticketDao.searchTicket(industry, merchantName, ticketNo,
				ticketType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#searchTicket(java.lang.String,
	 * java.lang.String)
	 */
	public RfmsTicket searchTicket(String merchantCode, String ticketCode)
			throws Exception {
		return ticketDao.searchTicket(merchantCode, ticketCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#unuseTicket(java.lang.String,
	 * java.lang.String)
	 */
	public ResultMsg unuseTicket(String posCode, String ticketDetailCode)
			throws Exception {
		RfmsTicketDetail detail = (RfmsTicketDetail) this.baseDao
				.getEntityByIdentityAttribute(RfmsTicketDetail.class,
						"seqNumber", ticketDetailCode);
		if (detail == null) {
			return new ResultMsg("1002", "飞券序列号不存在！");
		}
		if (detail.getStatus() != 3) {
			return new ResultMsg("1003", "该飞券号码未使用或者已经失效");
		}
		detail.setStatus(new Long(2));// 已经消费
		detail.setUseDate(new Date());
		detail.setUserPos(posCode);
		this.baseDao.update(detail);
		Long ticketId = detail.getTicketId();
		String update = "update RfmsTicket ti set ti.useCount=ti.useCount-1 where ti.ticketId="
				+ ticketId;
		this.ticketDao.getSessionFactory().getCurrentSession().createQuery(
				update).executeUpdate();
		return new ResultMsg("1001", "飞卷冲正成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#useTicket(java.lang.String,
	 * java.lang.String)
	 */
	public ResultMsg useTicket(String posCode, String ticketDetailCode,
			RfcsTrade trade) throws Exception {
		RfmsTicketDetail detail = (RfmsTicketDetail) this.baseDao
				.getEntityByIdentityAttribute(RfmsTicketDetail.class,
						"seqNumber", ticketDetailCode);
		if (detail == null) {
			return new ResultMsg("00001", "优惠券序列号不存在！");
		}
		if (detail.getStatus() != 2) {
			return new ResultMsg("00002", "该优惠券号码未激活或者已经失效");
		}
		Long ticketId = detail.getTicketId();
		RfmsTicket ticket = this.ticketDao.getById(ticketId);
		Date endDate = ticket.getEndDate();
		if (endDate.before(new Date())) {
			return new ResultMsg("00003", "该优惠券已经超过使用有效期！");
		}
		boolean isexists = this.ticketDao.posExists(posCode, ticketId);
		if (!isexists) { // 券不能再pos机器上消费
			return new ResultMsg("00004", "该优惠券不能再此pos机器上消费！");
		}

		detail.setStatus(new Long(3));// 已经消费
		detail.setUseDate(new Date());
		detail.setUserPos(posCode);
		this.baseDao.update(detail);
		// Long ticketId = detail.getTicketId();
		String update = "update RfmsTicket ti set ti.useCount=ti.useCount+1 where ti.ticketId="
				+ ticketId;
		this.ticketDao.getSessionFactory().getCurrentSession().createQuery(
				update).executeUpdate();
		if (trade != null)
			this.baseDao.save(trade);
		String msg = "";
		String t = ticket.getType();
		if (t.equals("1")) {
			msg = "折价券，折价：" + ticket.getParValue();
		} else {
			msg = "折扣券，折扣：" + ticket.getParZhekou();

		}
		return new ResultMsg("00000", "优惠券消费成功！" + msg);
	}

}
