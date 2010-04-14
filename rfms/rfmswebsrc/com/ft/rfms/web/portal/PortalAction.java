package com.ft.rfms.web.portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ft.rfms.busi.MemberLoginDTO;
import com.ft.rfms.busi.ResultMsg;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.model.WebAndPosService;
import com.ft.singleTable.web.BaseSimpleAction;
import com.ft.spring.web.SpringContextUtils;

public class PortalAction extends BaseSimpleAction {
	WebAndPosService webAndPosService = (WebAndPosService) SpringContextUtils
			.getBean("webAndPosService");

	/**
	 * (����pos��ע��)��ֻ�ṩ�ֻ����룬�����Զ����ɣ��������������
	 * 
	 * @param mobile
	 * @return ע�������� 1001��ע��ɹ� 1002���ֻ������Ѿ����� 1003��δ֪����
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile) throws Exception {
		ResultMsg resultMsg = webAndPosService.regMember(mobile);
		return resultMsg;
	}

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
			throws Exception {
		ResultMsg resultMsg = webAndPosService
				.modifyPwd(mobile, oldPwd, newPwd);

		return resultMsg;
	}

	/**
	 * ��Ա��¼
	 * 
	 * @param mobile
	 *            �ֻ�����
	 * @param pwd
	 *            ����
	 * @throws Exception
	 */
	public MemberLoginDTO memberLogin(String mobile, String pwd)
			throws Exception {
		MemberLoginDTO memberLoginDTO = webAndPosService.memberLogin(mobile,
				pwd);
		return memberLoginDTO;
	}

	/**
	 * ��ȡ��ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getIndustry() throws Exception {
		Map map = webAndPosService.getIndustry();
		JSONObject json = JSONObject.fromObject(map);

		return json.toString();
	}

	/**
	 * ��ȡ�̻��ķɾ���Ϣ
	 * 
	 * @param merchantCode
	 * @throws Exception
	 */
	public String findTicket(Long merchantCode) throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService
				.findTicket(merchantCode);

		JSONArray json = JSONArray.fromObject(rfmsTicketList);
		return json.toString();
	}

	/**
	 * ��ȯ��ѯ
	 * 
	 * @param industry
	 *            ��ҵ���� �ο��̻�ENUM����
	 * @param merchantName
	 *            �̻����� ģ��ƥ��
	 * @param ticketNo
	 *            �ɾ���
	 * @param ticketType
	 *            �ɾ����� �鿴�ɾ�����ENUM����
	 * @throws Exception
	 */
	public String searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService.searchTicket(
				industry, merchantName, ticketNo, ticketType);

		JSONArray js = JSONArray.fromObject(rfmsTicketList);
		return js.toString();
	}

	/**
	 * ���̻�����ͷɾ��Ų�ѯ�ɾ�
	 * 
	 * @param merchantCode
	 *            �̻����
	 * @param ticketCode
	 *            �ɾ���
	 * @throws Exception
	 */
	public RfmsTicket searchTicket(String merchantCode, String ticketCode,
			HttpServletResponse response) throws Exception {
		RfmsTicket rfmsTicket = webAndPosService.searchTicket(merchantCode,
				ticketCode);

		return rfmsTicket;
	}

	/**
	 * ���ݷ�ȯ �����ȯ����
	 * 
	 * @param ticketCode
	 *            �ɾ���
	 * @throws Exception
	 */
	public ResultMsg getTicket(String ticketCode) throws Exception {
		ResultMsg resultMsg = webAndPosService.getTicket(ticketCode);
		return resultMsg;
	}

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
	public ResultMsg posSignIn(String posCode, String loginName, String pwd,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.posSignIn(posCode, loginName,
				pwd);
		return resultMsg;
	}

	/**
	 * Posǩ��
	 * 
	 * @param posCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg posSignOut(String posCode) throws Exception {
		ResultMsg resultMsg = webAndPosService.posSignOut(posCode);

		return resultMsg;
	}

	/**
	 * �ɾ�����
	 * 
	 * @param posCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg useTicket(String posCode, String ticketDetailCode,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.useTicket(posCode,
				ticketDetailCode, null);
		return resultMsg;
	}

	/**
	 * �ɾ����
	 * 
	 * @param poseCode
	 * @param ticketDetailCode
	 * @throws Exception
	 */
	public ResultMsg unuseTicket(String poseCode, String ticketDetailCode,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.unuseTicket(poseCode,
				ticketDetailCode);
		return resultMsg;
	}

	// protected JSONObject readJson(HttpServletRequest request) throws
	// Exception {
	// JSONObject jsonObject = new JSONObject();
	// try {
	// Map parameterMap = request.getParameterMap();
	// // ͨ��ѭ�������ķ�ʽ���key��value��set��JSONObject��
	// Iterator paIter = parameterMap.keySet().iterator();
	// while (paIter.hasNext()) {
	// String key = paIter.next().toString();
	// String[] values = (String[]) parameterMap.get(key);
	// jsonObject.accumulate(key, values[0]);
	// }
	// System.out.println("�ӿͻ��˻��json=" + jsonObject.toString());
	// } catch (Exception e) {
	// System.out.println("��ȡjson���ݳ���������Ϣ���£�nt" + e.getMessage());
	// e.printStackTrace();
	// throw e;
	// }
	// return jsonObject;
	// }

	public static void main(String[] arg) {
		List list = new ArrayList();
		ResultMsg rm = new ResultMsg("1", "2");
		ResultMsg rm1 = new ResultMsg("2", "23");

		list.add(rm);
		list.add(rm1);

		JSONArray js = JSONArray.fromObject(list);
		System.out.println(js.toString());
	}

	public WebAndPosService getWebAndPosService() {
		return webAndPosService;
	}

	public void setWebAndPosService(WebAndPosService webAndPosService) {
		this.webAndPosService = webAndPosService;
	}

}
