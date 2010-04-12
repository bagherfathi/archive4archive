package com.ft.rfms.web.portal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.busi.MemberLoginDTO;
import com.ft.rfms.busi.ResultMsg;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.model.WebAndPosService;
import com.ft.singleTable.web.BaseSimpleAction;

public class PortalAction extends BaseSimpleAction {
	private WebAndPosService webAndPosService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonObject = readJson(request);
		String serialNumber = jsonObject.getString("serialNumber");
		String mobile = jsonObject.getString("mobile");
		String oldPwd = jsonObject.getString("oldPwd");
		String newPwd = jsonObject.getString("newPwd");
		String pwd = jsonObject.getString("pwd");
		String merchantCode = jsonObject.getString("merchantCode");
		String industry = jsonObject.getString("industry");
		String merchantName = jsonObject.getString("merchantName");
		String ticketNo = jsonObject.getString("ticketNo");
		Long ticketType = jsonObject.getLong("ticketType");
		String ticketCode = jsonObject.getString("ticketCode");
		String posCode = jsonObject.getString("posCode");
		String loginName = jsonObject.getString("loginName");
		String ticketDetailCode = jsonObject.getString("ticketDetailCode");
		String poseCode = jsonObject.getString("poseCode");

		// ���ݲ����ж�ʹ���ĸ�����
		if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A001)) {
			this.regMember(mobile, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A002)) {
			this.modifyPwd(mobile, oldPwd, newPwd, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A003)) {
			this.memberLogin(mobile, pwd, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A004)) {
			this.getIndustry(response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A005)) {
			this.findTicket(merchantCode, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A006)) {
			this.searchTicket(industry, merchantName, ticketNo, ticketType,
					response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A007)) {
			this.searchTicket(merchantCode, ticketCode, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A008)) {
			this.getTicket(ticketCode, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A009)) {
			this.posSignIn(posCode, loginName, pwd, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A010)) {
			this.posSignOut(posCode, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A011)) {
			this.useTicket(posCode, ticketDetailCode, response);
		} else if (serialNumber.equals(PortalForm.SERIAL_NUMBER_METHOD_A012)) {
			this.unuseTicket(poseCode, ticketDetailCode, response);
		}

		return super.execute(mapping, form, request, response);
	}

	/**
	 * ��Աע��(����pos��ע��)��ֻ�ṩ�ֻ����룬�����Զ����ɣ��������������
	 * 
	 * @param mobile
	 * @return ע�������� 1001��ע��ɹ� 1002���ֻ������Ѿ����� 1003��δ֪����
	 * @throws Exception
	 */
	public void regMember(String mobile, HttpServletResponse response)
			throws Exception {
		ResultMsg resultMsg = webAndPosService.regMember(mobile);
		JSONObject jo = JSONObject.fromObject(resultMsg);
		response.getWriter().print(jo.toString());
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
	public void modifyPwd(String mobile, String oldPwd, String newPwd,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService
				.modifyPwd(mobile, oldPwd, newPwd);
		JSONObject jo = JSONObject.fromObject(resultMsg);

		response.getWriter().print(jo.toString());
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
	public void memberLogin(String mobile, String pwd,
			HttpServletResponse response) throws Exception {
		MemberLoginDTO memberLoginDTO = webAndPosService.memberLogin(mobile,
				pwd);
		JSONObject jo = JSONObject.fromObject(memberLoginDTO);

		response.getWriter().print(jo.toString());
	}

	/**
	 * ��ȡ��ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getIndustry(HttpServletResponse response) throws Exception {
		Map map = webAndPosService.getIndustry();
		JSONObject jo = JSONObject.fromObject(map);

		response.getWriter().print(jo.toString());
	}

	/**
	 * ��ȡ�̻��ķɾ���Ϣ
	 * 
	 * @param merchantCode
	 * @throws Exception
	 */
	public void findTicket(String merchantCode, HttpServletResponse response)
			throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService
				.findTicket(merchantCode);

		JSONArray jr = JSONArray.fromObject(rfmsTicketList);
		response.getWriter().print(jr.toString());
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
	public void searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType, HttpServletResponse response)
			throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService.searchTicket(
				industry, merchantName, ticketNo, ticketType);

		JSONArray jr = JSONArray.fromObject(rfmsTicketList);

		response.getWriter().print(jr.toString());
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
	public void searchTicket(String merchantCode, String ticketCode,
			HttpServletResponse response) throws Exception {
		RfmsTicket rfmsTicket = webAndPosService.searchTicket(merchantCode,
				ticketCode);
		JSONObject jo = JSONObject.fromObject(rfmsTicket);

		response.getWriter().print(jo.toString());
	}

	/**
	 * ���ݷ�ȯ �����ȯ����
	 * 
	 * @param ticketCode
	 *            �ɾ���
	 * @throws Exception
	 */
	public void getTicket(String ticketCode, HttpServletResponse response)
			throws Exception {
		RfmsTicket rfmsTicket = webAndPosService.getTicket(ticketCode);
		JSONObject jo = JSONObject.fromObject(rfmsTicket);

		response.getWriter().print(jo.toString());
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
	public void posSignIn(String posCode, String loginName, String pwd,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.posSignIn(posCode, loginName,
				pwd);
		JSONObject jo = JSONObject.fromObject(resultMsg);
		response.getWriter().print(jo.toString());
	}

	/**
	 * Posǩ��
	 * 
	 * @param posCode
	 * @return
	 * @throws Exception
	 */
	public void posSignOut(String posCode, HttpServletResponse response)
			throws Exception {
		ResultMsg resultMsg = webAndPosService.posSignOut(posCode);
		JSONObject jo = JSONObject.fromObject(resultMsg);

		response.getWriter().print(jo.toString());
	}

	/**
	 * �ɾ�����
	 * 
	 * @param posCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public void useTicket(String posCode, String ticketDetailCode,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.useTicket(posCode,
				ticketDetailCode,null);
		JSONObject jo = JSONObject.fromObject(resultMsg);

		response.getWriter().print(jo.toString());
	}

	/**
	 * �ɾ����
	 * 
	 * @param poseCode
	 * @param ticketDetailCode
	 * @throws Exception
	 */
	public void unuseTicket(String poseCode, String ticketDetailCode,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.unuseTicket(poseCode,
				ticketDetailCode);
		JSONObject jo = JSONObject.fromObject(resultMsg);

		response.getWriter().print(jo.toString());
	}

	protected JSONObject readJson(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Map parameterMap = request.getParameterMap();
			// ͨ��ѭ�������ķ�ʽ���key��value��set��JSONObject��
			Iterator paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[]) parameterMap.get(key);
				jsonObject.accumulate(key, values[0]);
			}
			System.out.println("�ӿͻ��˻��json=" + jsonObject.toString());
		} catch (Exception e) {
			System.out.println("��ȡjson���ݳ���������Ϣ���£�nt" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return jsonObject;
	}

	public WebAndPosService getWebAndPosService() {
		return webAndPosService;
	}

	public void setWebAndPosService(WebAndPosService webAndPosService) {
		this.webAndPosService = webAndPosService;
	}

}
