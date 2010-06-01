/**
 * 
 */
package com.ft.rfcs.app.action;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ft.frmwk.tcp.FTTCPException;
import com.ft.frmwk.tcp.SessionContext;
import com.ft.frmwk.tcp.TCPRequest;
import com.ft.frmwk.tcp.TCPResponse;
import com.ft.frmwk.tcp.action.TCPAction;
import com.ft.frmwk.utility.RandamUtility;
import com.ft.protocol.common.DesEncrypt;
import com.ft.protocol.common.FormatUtility;
import com.ft.rfcs.ParaNameConstant;
import com.ft.rfcs.ResponseCodeConstant;
import com.ft.rfcs.ServerParamConstant;
import com.ft.rfms.entity.RfcsPosSignin;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.model.MerchantService;

/**
 * ǩ��
 * 
 * 1.���ݽ���POS��Ŵӡ�POS����ȡ����Կ 2.��������Ĺ�����Կ������mac��Կ��pin��Կ�� 3.��������Կ���µ�"POS��"�У�����¼����ʱ��
 * 4.����Ҫ���ʽ���ع�����Կ
 * 
 * 
 * @author lch
 * 
 */
public class UpdateKeyAction extends TCPAction {

	private static Logger log = Logger.getLogger(UpdateKeyAction.class);

	private MerchantService merchantService;

	/**
	 * @param merchantService
	 *            the merchantService to set
	 */
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	@Override
	public void doAction(TCPRequest request, TCPResponse response,
			SessionContext sessionContext) throws FTTCPException {

		byte[] key = { 0, 0, 0, 0, 0, 0, 0, 0 };
		try {
			log.info("-----------ִ�и���������Կ����------------");

			RfmsMerchantPos pos = (RfmsMerchantPos) sessionContext
					.getParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM);
			
			String posCode = (String) request.getParaByIndex(ParaNameConstant.POS_CODE);
//			RfmsMerchantPos pos=(RfmsMerchantPos)this.merchantService.getEntityByIdentityAttribute(RfmsMerchantPos.class, "sysPosCode", posCode);
			/** ******��������*** */
			byte[] mainkey = pos.getMainkey().getBytes();// ����pos�Ż�ȡ����Կ
			//byte[] mainkey = "12345678".getBytes();
			/** ******��������*** */
			log.debug("mainkey=" + FormatUtility.bytesToHexString(mainkey));// ��ӡ����Կ
			String mackeyStr = RandamUtility.getRandomNum(8);
			String pinkeyStr = RandamUtility.getRandomNum(8);
			byte[] mackey = mackeyStr.getBytes();
			byte[] pinkey = pinkeyStr.getBytes();
			log.debug("randMackey=" + mackey);
			log.debug("mackey=" + FormatUtility.bytesToHexString(mackey));
			DesEncrypt desEncrypt = new DesEncrypt(mainkey);
			byte[] macA = desEncrypt.encodeByDesToByte(mackey);
			byte[] pinC = desEncrypt.encodeByDesToByte(pinkey);
			DesEncrypt macdesEncrypt = new DesEncrypt(mackey);
			byte[] macB = macdesEncrypt.encodeByDesToByte(key);// ��mackey����
			DesEncrypt pindesEncrypt = new DesEncrypt(pinkey);
			byte[] pinD = pindesEncrypt.encodeByDesToByte(key);// ��pinkey����
			log.debug("macA==" + macA.length);
			byte[] ABCD = new byte[24];
			System.arraycopy(macA, 0, ABCD, 0, 8);
			System.arraycopy(macB, 0, ABCD, 8, 4);
			System.arraycopy(pinC, 0, ABCD, 12, 8);
			System.arraycopy(pinD, 0, ABCD, 20, 4);
			log.debug("ABCD=" + FormatUtility.bytesToHexString(ABCD));

			String changeDate = sessionContext.getSessionDate();
			log.debug("changeDate=" + changeDate);
			String changeTime = sessionContext.getSessionTime();
			log.debug("changeTime=" + changeTime);

			pos.setMackey(mackeyStr);
			pos.setPinkey(pinkeyStr);
			pos.setKeyChangeTime(new Timestamp(sessionContext
					.getSessionTimestamp().getTime()));
			/** ******��������*** */
            this.merchantService.update(pos);
			// posManager.savePos(pos);
			/** ******��������*** */
            //��¼posǩ���¼
			RfcsPosSignin sigin=new RfcsPosSignin();
			sigin.setMacKey(mackeyStr);
			sigin.setPinKey(pinkeyStr);
			sigin.setPosId(pos.getMerchantPosId());
			sigin.setSigninTime(new Date());
			this.merchantService.save(sigin);
			
			// �����¹�����Կ��Ϣ���ؿͻ���
			response.addPara(ParaNameConstant.TRANS_TIME, changeTime);// 12
			response.addPara(ParaNameConstant.TRANS_DATE, changeDate);// 13
			response.addPara(ParaNameConstant.EXTERNAL_SYSTEM_WORKKEY, ABCD);// 62
			response.setResponseCode(ResponseCodeConstant.SUCCESS);

		} catch (Exception e) {
			log.debug("system is error", e);
			throw new FTTCPException(ResponseCodeConstant.EXCEPTION,
					"system is error", e);
		}

	}

}
