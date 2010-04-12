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
 * ����
 * 
 * 1������ϵͳ��Ŵӡ�POS���л�ȡϵͳ��Ϣ-->��ȡPOSID-->�̻�ID 
 * 2�����ݱ��ȡ��View_coupon 
 * 3������������л�ȡ���׽����û���pin����POS��pinkey�����û�pin�� 
 * 4����֤�Ż�ȯ�����ɽ�����ˮ�ţ�����������Ϣ���뵽�����ױ��С�
 * pos��ˮ�Ŵ�Pos�ĵ�11����
 * 5�����ؽ��׽�����ͻ��ˣ����׽��������
 * ����ʱ��12��
 * ��������13��
 * �����ο���37��
 * ���׷�����39��
 * ������Ϣ40��
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
		log.info("---------------ִ�����ѽ���-------------------");		
		RfmsMerchantPos pos = (RfmsMerchantPos)sessionContext.getParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM);
		RfmsMerchant validMerchant = (RfmsMerchant)sessionContext.getParameter(ServerParamConstant.PARAM_MERCHANT);
		String posTransNo = (String) request.getParaByIndex(ParaNameConstant.POS_TRANS_NO);// ��ȡPOS������ˮ
		log.info("POS��ˮ��:"+posTransNo);
		BigInteger amount = (BigInteger)request.getParaByIndex(ParaNameConstant.AMOUNT);
		log.info("���׽��:"+amount);

		String couponNo = (String) request.getParaByIndex(ParaNameConstant.ACCOUNT);// �ʺ�
		log.info("�Ż�ȯ���:"+couponNo);
		log.debug("���ܿ�PIN");
		byte[] pin = (byte[]) request.getParaByIndex(ParaNameConstant.CARD_PIN);// ������
		String strPin = "";
		try {
			strPin = this.pinProcessor.DecryptPin(pin, pos.getPinkey().getBytes());
		} catch (CipherException e) {
			log.debug("system is error", e);
			throw new FTTCPException(ResponseCodeConstant.EXCEPTION,
					"system is error", e);
		}
		//��ȡ�Ż�ȯ
		
		//��֤�Ż�ȯ��Ϣ����Ч�ڡ�ʹ�÷�Χ��
		
		
		
		//���췵����Ϣ�����ֽ��ۿۣ�
		
		
		// ��ȡ��ǰϵͳ�����ں�ʱ��
		java.sql.Timestamp timestamp =  new java.sql.Timestamp(System.currentTimeMillis());
		String sysTransNo = this.transNoSeq.nextStringValue();
		log.debug("sysTransNo===" + sysTransNo);
		Long payTransNo = Long.parseLong(sysTransNo);// ��ȡϵͳ������ˮ��
		RfcsTrade trade = new RfcsTrade();// ʵ����һ�����ױ����
		String result=ResponseCodeConstant.SUCCESS;
		try{
		trade.setTransNo(payTransNo);// ����ϵͳ������ˮ��
		trade.setSystemId(pos.getId());// ����POSID
		trade.setSystemCode(pos.getSysPosCode());// ����POS���
		trade.setMerchantId(validMerchant.getId());// �����̻�ID
		trade.setMerchantCode(validMerchant.getMerchantCode());// �����̻�����
		trade.setMerchantTransNo(posTransNo);// ����POS��ˮ��
		trade.setTradeType(new Long(request.getOperateType()));// ���潻������
		trade.setTicketNo(couponNo);// �����˺�
		trade.setAmount(new Long(amount.toString()));// �������ѽ��
		trade.setCreateTime(timestamp);// ���洴��ʱ��
		trade.setPosTradeType(PosRequestType.CONSUME);// POS��������--����
		log.debug("����PosBaseMessage");
		trade.setStatus(RfcsTrade.STATUS_SUCCESS);// ���潻��״̬--�ɹ�
		posService.useTicket(pos.getSysPosCode(), couponNo,trade); //ʹ���Ż�ȯ,���������Ϣ�����ױ�
        }catch(Exception ex){
        	result=ResponseCodeConstant.EXCEPTION;
		}
		// ֪ͨPOS���ѽ��
		response.addPara(ParaNameConstant.ACCOUNT, couponNo);// �����˺�
		response.addPara(ParaNameConstant.AMOUNT, amount);
		response.addPara(ParaNameConstant.POS_TRANS_NO,posTransNo);// ���ؽ�����ˮ��,��POS������ˮ��
		response.addPara(ParaNameConstant.TRANS_TIME, FormatDateUtility.getTimestamp2OnlyTime(timestamp));// ���ؽ���ʱ��
		response.addPara(ParaNameConstant.TRANS_DATE, FormatDateUtility.getTimestamp2OnlyDate(timestamp));// ���ؽ�������
		response.addPara(ParaNameConstant.TRANS_NO, payTransNo.toString());// ����ϵͳ������ˮ��
		response.setResponseCode(result);  //
		response.addPara(ParaNameConstant.POS_CODE, pos.getSysPosCode());// ����POS�ն˺�
		response.addPara(ParaNameConstant.MERCHANT_CODE, validMerchant.getMerchantCode());// �����̻����룬���̻�����
		//������ͷ�����Ϣ
		
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
