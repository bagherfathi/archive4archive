/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;
import java.util.Map;

import com.ft.common.busi.BaseService;
import com.ft.rfms.busi.MemberLoginDTO;
import com.ft.rfms.busi.ResultMsg;
import com.ft.rfms.entity.RfcsTrade;
import com.ft.rfms.entity.RfmsTicket;

/**
 * @author solar
 * 
 */
public interface WebAndPosService extends BaseService {
	/**
	 * 会员注册(用于pos机注册)，只提供手机号码，密码自动生成，并发送密码短信
	 * 
	 * @param mobile
	 * @return 注册错误代码 1001：注册成功 1002：手机号码已经存在 1003：未知错误
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile) throws Exception;

	/**
	 * 会员注册，用于web注册，提供手机号码和密码
	 * 
	 * @param mobile
	 *            手机号码
	 * @param pwd
	 *            密码
	 * @return 注册错误代码 1001：注册成功 1002：手机号码已经存在 1003：未知错误
	 * @throws Exception
	 */
	public ResultMsg regMember(String mobile, String pwd, String name,
			Long sex, String address) throws Exception;

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
			throws Exception;

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
			throws Exception;

	/**
	 * 获取行业信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getIndustry() throws Exception;

	/**
	 * 获取商户的飞卷信息
	 * 
	 * @param merchantCode
	 * @return
	 * @throws Exception
	 */
	public List<RfmsTicket> findTicket(Long merchantCode) throws Exception;

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
	public List<RfmsTicket> searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception;

	/**
	 * 按商户编码和飞卷编号查询飞卷
	 * 
	 * @param merchantCode
	 *            商户编号
	 * @param ticketCode
	 *            飞卷编号
	 * @return
	 * @throws Exception
	 */
	public RfmsTicket searchTicket(String merchantCode, String ticketCode)
			throws Exception;

	/**
	 * 根据飞券 申请飞券号码
	 * 
	 * @param ticketCode
	 *            飞卷编号
	 * @throws Exception
	 */
	public ResultMsg getTicket(String ticketCode) throws Exception;

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
	public ResultMsg posSignIn(String posCode, String loginName, String pwd)
			throws Exception;

	/**
	 * Pos签出
	 * 
	 * @param posCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg posSignOut(String posCode) throws Exception;

	/**
	 * 飞卷消费
	 * 
	 * @param posCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg useTicket(String posCode, String ticketDetailCode,
			RfcsTrade trade) throws Exception;
	/**
	 * 飞卷消费
	 * 
	 * @param ticketCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg useTicketConsume(String ticketCode, String ticketDetailCode,
			RfcsTrade trade) throws Exception;

	/**
	 * 飞卷充正
	 * 
	 * @param poseCode
	 * @param ticketDetailCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg unuseTicket(String poseCode, String ticketDetailCode)
			throws Exception;

	/**
	 * 116114发放卡
	 * 
	 * @param mobile
	 * @param ticketCode
	 * @return
	 * @throws Exception
	 */
	public ResultMsg sendTicketMember(String mobile, String ticketCode)
			throws Exception;

}
