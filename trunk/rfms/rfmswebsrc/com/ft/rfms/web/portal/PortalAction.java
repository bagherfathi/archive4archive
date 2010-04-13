package com.ft.rfms.web.portal;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
	 * (用于pos机注册)，只提供手机号码，密码自动生成，并发送密码短信
	 * 
	 * @param mobile
	 * @return 注册错误代码 1001：注册成功 1002：手机号码已经存在 1003：未知错误
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile) throws Exception {
		ResultMsg resultMsg = webAndPosService.regMember(mobile);
		return resultMsg;
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
	public ResultMsg modifyPwd(String mobile, String oldPwd, String newPwd)
			throws Exception {
		ResultMsg resultMsg = webAndPosService
				.modifyPwd(mobile, oldPwd, newPwd);

		return resultMsg;
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
	public MemberLoginDTO memberLogin(String mobile, String pwd)
			throws Exception {
		MemberLoginDTO memberLoginDTO = webAndPosService.memberLogin(mobile,
				pwd);
		return memberLoginDTO;
	}

	/**
	 * 获取行业信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getIndustry() throws Exception {
		Map map = webAndPosService.getIndustry();

		return map;
	}

	/**
	 * 获取商户的飞卷信息
	 * 
	 * @param merchantCode
	 * @throws Exception
	 */
	public String findTicket(Long merchantCode) throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService
				.findTicket(merchantCode);

		String json = "";
		for (int i = 0; i < rfmsTicketList.size(); i++) {
			RfmsTicket rfmsTicket = rfmsTicketList.get(i);
			json = json + "{" + rfmsTicket.toJSON() + "}";
			if (i != rfmsTicketList.size() - 1) {
				json = json + ",";
			}
		}
		return json;
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
	public String searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception {
		List<RfmsTicket> rfmsTicketList = webAndPosService.searchTicket(
				industry, merchantName, ticketNo, ticketType);

		String json = "";
		for (Object obj : rfmsTicketList) {
			json = json + "{" + obj + "}";
		}
		return json;
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
	public RfmsTicket searchTicket(String merchantCode, String ticketCode,
			HttpServletResponse response) throws Exception {
		RfmsTicket rfmsTicket = webAndPosService.searchTicket(merchantCode,
				ticketCode);

		return rfmsTicket;
	}

	/**
	 * 根据飞券 申请飞券号码
	 * 
	 * @param ticketCode
	 *            飞卷编号
	 * @throws Exception
	 */
	public ResultMsg getTicket(String ticketCode) throws Exception {
		ResultMsg resultMsg = webAndPosService.getTicket(ticketCode);
		return resultMsg;
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
	public ResultMsg posSignIn(String posCode, String loginName, String pwd,
			HttpServletResponse response) throws Exception {
		ResultMsg resultMsg = webAndPosService.posSignIn(posCode, loginName,
				pwd);
		return resultMsg;
	}

	/**
	 * Pos签出
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
	 * 飞卷消费
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
	 * 飞卷充正
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
	// // 通过循环遍历的方式获得key和value并set到JSONObject中
	// Iterator paIter = parameterMap.keySet().iterator();
	// while (paIter.hasNext()) {
	// String key = paIter.next().toString();
	// String[] values = (String[]) parameterMap.get(key);
	// jsonObject.accumulate(key, values[0]);
	// }
	// System.out.println("从客户端获得json=" + jsonObject.toString());
	// } catch (Exception e) {
	// System.out.println("获取json数据出错，错误信息如下：nt" + e.getMessage());
	// e.printStackTrace();
	// throw e;
	// }
	// return jsonObject;
	// }

	public WebAndPosService getWebAndPosService() {
		return webAndPosService;
	}

	public void setWebAndPosService(WebAndPosService webAndPosService) {
		this.webAndPosService = webAndPosService;
	}

}
