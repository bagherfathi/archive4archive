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
		// �̻���ҵ���͵�ENUM
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
			resultMsg = new ResultMsg("2001", "��ϲ��������ɹ�����ȯ����Ϊ��"
					+ ticketDetail.getValidatorCode());
			// �Ѿ�����
			ticketDetail.setStatus(new Long(2));
			baseDao.update(ticketDetail);
		} else {
			resultMsg = new ResultMsg("2002", "��ȯ�ѷ�����ɣ��޷�����");
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
			result.setResultMsg(new ResultMsg("1002", "��Ա�ţ��ֻ����룩�����ڣ�"));
			return result;
		}
		RfmsMember member = (RfmsMember) obj;
		if (!member.getPwd().equals(pwd)) {
			result.setResultMsg(new ResultMsg("1003", "��Ա���벻��ȷ��"));
			return result;
		}
		result.setResultMsg(new ResultMsg("1001", "��¼�ɹ���"));
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
			return new ResultMsg("1002", "��Ա�ţ��ֻ����룩�����ڣ�");
		}
		RfmsMember member = (RfmsMember) obj;
		if (!member.getPwd().equals(oldPwd)) {
			return new ResultMsg("1003", "ԭ��Ա���벻��ȷ��");
		}
		member.setPwd(newPwd);
		member.setUpdateDate(new Date());
		this.baseDao.saveOrUpdate(member);
		return new ResultMsg("1001", "�����޸ĳɹ���");

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

		// ����Ա������
		if (op == null) {
			return new ResultMsg("1002", "����Ա������");
		}

		// ��ֹ״̬
		if (OperatorDTO.STATUS_DISABLE == op.getStatus()) {
			return new ResultMsg("1003", "����Ա������");
		}

		// ����״̬
		if (1 == op.getLockStatus()) {
			return new ResultMsg("1004", "����Ա������");
		}

		String encodedPass = Md5PasswordEncoder.encode(pwd);

		// �������
		if (!encodedPass.equals(op.getPassword())) {
			new ResultMsg("1005", "����Ա���벻��ȷ");
		}
		posSigninMap.put(posCode, loginName);
		return new ResultMsg("1001", "POS��ǩ��ɹ�");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.WebAndPosService#posSignOut(java.lang.String)
	 */
	public ResultMsg posSignOut(String posCode) throws Exception {
		posSigninMap.remove(posCode);
		return new ResultMsg("1001", "POS��ǩ���ɹ���");
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
			return new ResultMsg("1002", "�ֻ������Ѿ����ڣ�");
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
			// ��������
			RfmsSms sms = new RfmsSms();
			sms.setMessage("���ѳɹ�ע��Ϊ�ɾ��Ա�����ĳ�ʼ������123456");
			sms.setMobile(mobile);
			sms.setOperatorId(member.getId());
			sms.setCreateDate(new Date());
			sms.setUpdateDate(new Date());
			this.baseDao.save(sms);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultMsg("1003", "δ֪����");
		}
		return new ResultMsg("1001", "ע��ɹ���");
	}

	/**
	 * 116114���ſ�
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
				resultMsg = new ResultMsg("2001", "�����Ż�ȯ�ɹ���");
				// �Ѿ�����
				ticketDetail.setMobile(mobile);
				ticketDetail.setSendDate(new Date());
				ticketDetail.setSendOperatorId(rfmsMember.getOperatorId());
				ticketDetail.setStatus(new Long(2));
				baseDao.update(ticketDetail);

				// ��������
				RfmsSms sms = new RfmsSms();
				sms.setMessage("���ѳɹ�ע��Ϊ�ɾ��Ա�����ĳ�ʼ������123456");
				sms.setMobile(mobile);
				sms.setOperatorId(rfmsMember.getOperatorId());
				sms.setCreateDate(new Date());
				sms.setUpdateDate(new Date());
				this.baseDao.save(sms);
				return resultMsg;
			} else {
				return new ResultMsg("2002", "��ȯ�ѷ�����ɣ��޷�����");
			}

		}
		return new ResultMsg("1003", "�ֻ����벻���ڣ���������û���Ϣ");
		// try {
		// return this.getTicket(ticketCode);
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// return new ResultMsg("1003", "δ֪����");
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
			return new ResultMsg("1002", "�ֻ������Ѿ����ڣ�");
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
			return new ResultMsg("1003", "δ֪����");
		}
		return new ResultMsg("1001", "ע��ɹ���");
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
			return new ResultMsg("1002", "��ȯ���кŲ����ڣ�");
		}
		if (detail.getStatus() != 3) {
			return new ResultMsg("1003", "�÷�ȯ����δʹ�û����Ѿ�ʧЧ");
		}
		detail.setStatus(new Long(2));// �Ѿ�����
		detail.setUseDate(new Date());
		detail.setUserPos(posCode);
		this.baseDao.update(detail);
		Long ticketId = detail.getTicketId();
		String update = "update RfmsTicket ti set ti.useCount=ti.useCount-1 where ti.ticketId="
				+ ticketId;
		this.ticketDao.getSessionFactory().getCurrentSession().createQuery(
				update).executeUpdate();
		return new ResultMsg("1001", "�ɾ�����ɹ���");
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
			return new ResultMsg("00001", "�Ż�ȯ���кŲ����ڣ�");
		}
		if (detail.getStatus() != 2) {
			return new ResultMsg("00002", "���Ż�ȯ����δ��������Ѿ�ʧЧ");
		}
		Long ticketId = detail.getTicketId();
		RfmsTicket ticket = this.ticketDao.getById(ticketId);
		Date endDate = ticket.getEndDate();
		if (endDate.before(new Date())) {
			return new ResultMsg("00003", "���Ż�ȯ�Ѿ�����ʹ����Ч�ڣ�");
		}
		boolean isexists = this.ticketDao.posExists(posCode, ticketId);
		if (!isexists) { // ȯ������pos����������
			return new ResultMsg("00004", "���Ż�ȯ�����ٴ�pos���������ѣ�");
		}

		detail.setStatus(new Long(3));// �Ѿ�����
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
			msg = "�ۼ�ȯ���ۼۣ�" + ticket.getParValue();
		} else {
			msg = "�ۿ�ȯ���ۿۣ�" + ticket.getParZhekou();

		}
		return new ResultMsg("00000", "�Ż�ȯ���ѳɹ���" + msg);
	}

}
