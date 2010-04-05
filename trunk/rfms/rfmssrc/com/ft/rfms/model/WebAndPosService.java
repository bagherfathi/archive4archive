/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;
import java.util.Map;

import com.ft.common.busi.BaseService;
import com.ft.rfms.busi.MemberLoginDTO;
import com.ft.rfms.busi.ResultMsg;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;

/**
 * @author solar
 * 
 */
public interface WebAndPosService extends BaseService {

	/**
	 * ��Աע��(����pos��ע��)��ֻ�ṩ�ֻ����룬�����Զ����ɣ��������������
	 * 
	 * @param mobile
	 * @return ע�������� 1001��ע��ɹ� 1002���ֻ������Ѿ����� 1003��δ֪����
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile) throws Exception;

	/**
	 * ��Աע�ᣬ����webע�ᣬ�ṩ�ֻ����������
	 * 
	 * @param mobile
	 *            �ֻ�����
	 * @param pwd
	 *            ����
	 * @return ע�������� 1001��ע��ɹ� 1002���ֻ������Ѿ����� 1003��δ֪����
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile, String pwd, String name, Long sex,
			String address) throws Exception;

	/**
	 * ��Ա�޸����룬����web����
	 * 
	 * @param mobile
	 * @param oldPwd
	 * @param newPwd
	 * @return 1001���޸ĳɹ� 1002����Ա������ 1003��ԭ���벻��ȷ
	 * @throws Exception
	 */
	public ResultMsg modifyPwd(String mobile, String oldPwd, String newPwd)
			throws Exception;

	/**
	 * ��Ա��¼
	 * 
	 * @param mobile
	 *            �ֻ�����
	 * @param pwd
	 *            ����
	 * @throws Exception
	 */
	public MemberLoginDTO memberLogin(String mobile, String pwd) throws Exception;

	/**
	 * ��ȡ��ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getIndustry() throws Exception;

	/**
	 * ��ȡ�̻��ķɾ���Ϣ
	 * 
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	public List<RfmsTicket> findTicket(String merchantCode) throws Exception;

	/**
	 * ��ȯ��ѯ
	 * 
	 * @param industry
	 *            ��ҵ���� �ο��̻�ENUM����
	 * @param merchantName
	 *            �̻����� ģ��ƥ��
	 * @param ticketNo
	 *            �ɾ����
	 * @param ticketType
	 *            �ɾ����� �鿴�ɾ�����ENUM����
	 * @throws Exception
	 */
	public List<RfmsTicket> searchTicket(Long industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception;

	/**
	 * ���ɾ���Ų�ѯ�ɾ�
	 * 
	 * @param ticketNo
	 *            �ɾ����
	 * @return
	 * @throws Exception
	 */
	public RfmsTicket searchTicket(String ticketNo) throws Exception;

	/**
	 * ���̻�����ͷɾ���Ų�ѯ�ɾ�
	 * 
	 * @param merchantCode
	 *            �̻����
	 * @param ticketCode
	 *            �ɾ����
	 * @return
	 * @throws Exception
	 */
	public RfmsTicket searchTicket(String merchantCode, String ticketCode)
			throws Exception;

	/**
	 * ���ݷ�ȯ �����ȯ����
	 * 
	 * @param ticketCode
	 *            �ɾ����
	 * @throws Exception
	 */
	public RfmsTicket getTicket(String ticketCode) throws Exception;

	/**
	 * pos��ǩ��
	 * 
	 * @param posCode
	 *            pos���
	 * @param loginName
	 *            ����Ա��¼��
	 * @param pwd
	 *            ����Ա����
	 * @return
	 * @throws Exception
	 */
	public ResultMsg posSignIn(String posCode, String loginName, String pwd)
			throws Exception;

	/**
	 * Posǩ��
	 * 
	 * @param posCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg posSignOut(String posCode) throws Exception;

	/**
	 * �ɾ�����
	 * 
	 * @param posCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg useTicket(String posCode, String ticketDetailCode)
			throws Exception;

	/**
	 * �ɾ�����
	 * 
	 * @param poseCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg unuseTicket(String poseCode, String ticketDetailCode)
			throws Exception;

}