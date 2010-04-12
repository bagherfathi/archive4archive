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

		// 根据参数判断使用哪个方法
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
	 * 会员注册(用于pos机注册)，只提供手机号码，密码自动生成，并发送密码短信
	 * 
	 * @param mobile
	 * @return 注册错误代码 1001：注册成功 1002：手机号码已经存在 1003：未知错误
	 * @throws Exception
	 */
	public void regMember(String mobile, HttpServletResponse response)
			throws Exception {
		ResultMsg resultMsg = webAndPosService.regMember(mobile);
		JSONObject jo = JSONObject.fromObject(resultMsg);
		response.getWriter().print(jo.toString());
	}

	/**
	 * 会员修改密码，用于web服务
	 * 
	 * @param mobile
	 * @param oldPwd
	 * @param newPwd
	 * @return 1001：修改成功 1002：会员不存在 1003：原密码不正确
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
	 * 会员登录
	 * 
	 * @param mobile
	 *            手机号码
	 * @param pwd
	 *            密码
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
	 * 获取行业信息
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
	 * 获取商户的飞卷信息
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
	 * 飞券查询
	 * 
	 * @param industry
	 *            行业类型 参看商户ENUM定义
	 * @param merchantName
	 *            商户名称 模糊匹配
	 * @param ticketNo
	 *            飞卷编号
	 * @param ticketType
	 *            飞卷类型 查看飞卷类型ENUM定义
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
	 * 按商户编码和飞卷编号查询飞卷
	 * 
	 * @param merchantCode
	 *            商户编号
	 * @param ticketCode
	 *            飞卷编号
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
	 * 根据飞券 申请飞券号码
	 * 
	 * @param ticketCode
	 *            飞卷编号
	 * @throws Exception
	 */
	public void getTicket(String ticketCode, HttpServletResponse response)
			throws Exception {
		RfmsTicket rfmsTicket = webAndPosService.getTicket(ticketCode);
		JSONObject jo = JSONObject.fromObject(rfmsTicket);

		response.getWriter().print(jo.toString());
	}

	/**
	 * pos机签到
	 * 
	 * @param posCode
	 *            pos编号
	 * @param loginName
	 *            操作员登录名
	 * @param pwd
	 *            操作员密码
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
	 * Pos签出
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
	 * 飞卷消费
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
	 * 飞卷充正
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
			// 通过循环遍历的方式获得key和value并set到JSONObject中
			Iterator paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[]) parameterMap.get(key);
				jsonObject.accumulate(key, values[0]);
			}
			System.out.println("从客户端获得json=" + jsonObject.toString());
		} catch (Exception e) {
			System.out.println("获取json数据出错，错误信息如下：nt" + e.getMessage());
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
