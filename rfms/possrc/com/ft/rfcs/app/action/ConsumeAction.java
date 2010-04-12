/**
 * 
 */
package com.ft.rfcs.app.action;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

import com.ft.frmwk.tcp.FTTCPException;
import com.ft.frmwk.tcp.SessionContext;
import com.ft.frmwk.tcp.TCPRequest;
import com.ft.frmwk.tcp.TCPResponse;
import com.ft.frmwk.tcp.action.TCPAction;
import com.ft.pos.PosManager;
import com.ft.pos.common.PosRequestType;
import com.ft.pos.sequence.PosSequenceGenerator;
import com.ft.protocol.cipher.CipherException;
import com.ft.protocol.cipher.pin.IPinProcessor;
import com.ft.rfcs.ParaNameConstant;
import com.ft.rfcs.ResponseCodeConstant;
import com.ft.rfcs.ServerParamConstant;
import com.ft.rfcs.app.common.FormatDateUtility;
import com.ft.rfms.entity.RfcsTrade;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.model.WebAndPosService;

/**
 * 消费
 * 
 * 1）根据系统编号从“POS表”中获取系统信息-->获取POSID-->商户ID 
 * 2）根据编号取得View_coupon 
 * 3）从请求对象中获取交易金额和用户的pin，用POS的pinkey解密用户pin。 
 * 4）验证优惠券，生成交易流水号，并将交易信息加入到”交易表“中。
 * pos流水号从Pos的第11域获得
 * 5）返回交易结果给客户端，交易结果包括：
 * 交易时间12域
 * 交易日期13域
 * 检索参考号37域
 * 交易返回码39域
 * 返回信息40域
 * @author lch
 * 
 */
public class ConsumeAction extends TCPAction {

	private static Logger log = Logger.getLogger(ConsumeAction.class);

	private IPinProcessor pinProcessor;
	private OracleSequenceMaxValueIncrementer transNoSeq;
	private PosSequenceGenerator posSequenceGenerator;
	private WebAndPosService posService;

	
	/**
	 * @param posService the posService to set
	 */
	public void setPosService(WebAndPosService posService) {
		this.posService = posService;
	}




	@Override
	public void doAction(TCPRequest request, TCPResponse response,
			SessionContext sessionContext) throws FTTCPException {
		log.info("---------------执行消费交易-------------------");		
		RfmsMerchantPos pos = (RfmsMerchantPos)sessionContext.getParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM);
		RfmsMerchant validMerchant = (RfmsMerchant)sessionContext.getParameter(ServerParamConstant.PARAM_MERCHANT);
		String posTransNo = (String) request.getParaByIndex(ParaNameConstant.POS_TRANS_NO);// 获取POS交易流水
		log.info("POS流水号:"+posTransNo);
		BigInteger amount = (BigInteger)request.getParaByIndex(ParaNameConstant.AMOUNT);
		log.info("交易金额:"+amount);

		String couponNo = (String) request.getParaByIndex(ParaNameConstant.ACCOUNT);// 帐号
		log.info("优惠券编号:"+couponNo);
		log.debug("解密卡PIN");
		byte[] pin = (byte[]) request.getParaByIndex(ParaNameConstant.CARD_PIN);// 卡密码
		String strPin = "";
		try {
			strPin = this.pinProcessor.DecryptPin(pin, pos.getPinkey().getBytes());
		} catch (CipherException e) {
			log.debug("system is error", e);
			throw new FTTCPException(ResponseCodeConstant.EXCEPTION,
					"system is error", e);
		}
		//获取优惠券
		
		//验证优惠券信息（有效期、使用范围）
		
		
		
		//构造返回信息（抵现金额，折扣）
		
		
		// 获取当前系统的日期和时间
		java.sql.Timestamp timestamp =  new java.sql.Timestamp(System.currentTimeMillis());
		String sysTransNo = this.transNoSeq.nextStringValue();
		log.debug("sysTransNo===" + sysTransNo);
		Long payTransNo = Long.parseLong(sysTransNo);// 获取系统交易流水号
		RfcsTrade trade = new RfcsTrade();// 实例化一个交易表对象
		String result=ResponseCodeConstant.SUCCESS;
		try{
		trade.setTransNo(payTransNo);// 保存系统交易流水号
		trade.setSystemId(pos.getId());// 保存POSID
		trade.setSystemCode(pos.getSysPosCode());// 保存POS编号
		trade.setMerchantId(validMerchant.getId());// 保存商户ID
		trade.setMerchantCode(validMerchant.getMerchantCode());// 保存商户编码
		trade.setMerchantTransNo(posTransNo);// 保存POS流水号
		trade.setTradeType(new Long(request.getOperateType()));// 保存交易类型
		trade.setTicketNo(couponNo);// 保存账号
		trade.setAmount(new Long(amount.toString()));// 保存消费金额
		trade.setCreateTime(timestamp);// 保存创建时间
		trade.setPosTradeType(PosRequestType.CONSUME);// POS交易类型--消费
		log.debug("构造PosBaseMessage");
		trade.setStatus(RfcsTrade.STATUS_SUCCESS);// 保存交易状态--成功
		posService.useTicket(pos.getSysPosCode(), couponNo,trade); //使用优惠券,保存错误信息到交易表
        }catch(Exception ex){
        	result=ResponseCodeConstant.EXCEPTION;
		}
		// 通知POS消费结果
		response.addPara(ParaNameConstant.ACCOUNT, couponNo);// 返回账号
		response.addPara(ParaNameConstant.AMOUNT, amount);
		response.addPara(ParaNameConstant.POS_TRANS_NO,posTransNo);// 返回交易流水号,即POS交易流水号
		response.addPara(ParaNameConstant.TRANS_TIME, FormatDateUtility.getTimestamp2OnlyTime(timestamp));// 返回交易时间
		response.addPara(ParaNameConstant.TRANS_DATE, FormatDateUtility.getTimestamp2OnlyDate(timestamp));// 返回交易日期
		response.addPara(ParaNameConstant.TRANS_NO, payTransNo.toString());// 返回系统交易流水号
		response.setResponseCode(result);  //
		response.addPara(ParaNameConstant.POS_CODE, pos.getSysPosCode());// 返回POS终端号
		response.addPara(ParaNameConstant.MERCHANT_CODE, validMerchant.getMerchantCode());// 返回商户代码，即商户编码
		//返回码和返回信息
		
	}




	public IPinProcessor getPinProcessor() {
		return pinProcessor;
	}


	public void setPinProcessor(IPinProcessor pinProcessor) {
		this.pinProcessor = pinProcessor;
	}



	public OracleSequenceMaxValueIncrementer getTransNoSeq() {
		return transNoSeq;
	}


	public void setTransNoSeq(OracleSequenceMaxValueIncrementer transNoSeq) {
		this.transNoSeq = transNoSeq;
	}
	
	public PosSequenceGenerator getPosSequenceGenerator() {
		return posSequenceGenerator;
	}


	public void setPosSequenceGenerator(PosSequenceGenerator posSequenceGenerator) {
		this.posSequenceGenerator = posSequenceGenerator;
	}

	
}
