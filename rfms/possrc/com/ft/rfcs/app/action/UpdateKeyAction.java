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
 * 签到
 * 
 * 1.根据接入POS编号从“POS表”获取主密钥 2.生成随机的工作密钥：包括mac密钥和pin密钥。 3.将工作密钥更新到"POS表"中，并记录更新时间
 * 4.按照要求格式返回工作密钥
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
			log.info("-----------执行更换工作密钥交易------------");

			RfmsMerchantPos pos = (RfmsMerchantPos) sessionContext
					.getParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM);
			
			String posCode = (String) request.getParaByIndex(ParaNameConstant.POS_CODE);
//			RfmsMerchantPos pos=(RfmsMerchantPos)this.merchantService.getEntityByIdentityAttribute(RfmsMerchantPos.class, "sysPosCode", posCode);
			/** ******测试数据*** */
			byte[] mainkey = pos.getMainkey().getBytes();// 根据pos号获取主密钥
			//byte[] mainkey = "12345678".getBytes();
			/** ******测试数据*** */
			log.debug("mainkey=" + FormatUtility.bytesToHexString(mainkey));// 打印主密钥
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
			byte[] macB = macdesEncrypt.encodeByDesToByte(key);// 对mackey加密
			DesEncrypt pindesEncrypt = new DesEncrypt(pinkey);
			byte[] pinD = pindesEncrypt.encodeByDesToByte(key);// 对pinkey加密
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
			/** ******测试数据*** */
            this.merchantService.update(pos);
			// posManager.savePos(pos);
			/** ******测试数据*** */
            //记录pos签入记录
			RfcsPosSignin sigin=new RfcsPosSignin();
			sigin.setMacKey(mackeyStr);
			sigin.setPinKey(pinkeyStr);
			sigin.setPosId(pos.getMerchantPosId());
			sigin.setSigninTime(new Date());
			this.merchantService.save(sigin);
			
			// 将更新工作密钥信息返回客户端
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
