/**
 * 
 */
package com.ft.rfcs.app.filter;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ft.frmwk.tcp.FTTCPException;
import com.ft.frmwk.tcp.SessionContext;
import com.ft.frmwk.tcp.TCPRequest;
import com.ft.frmwk.tcp.TCPResponse;
import com.ft.frmwk.tcp.filter.TCPFilter;
import com.ft.protocol.cipher.CipherException;
import com.ft.protocol.cipher.mac.IMacProcessor;
import com.ft.protocol.common.FormatUtility;
import com.ft.protocol.common.PackageMac;
import com.ft.rfcs.ParaNameConstant;
import com.ft.rfcs.ResponseCodeConstant;
import com.ft.rfcs.ServerParamConstant;
import com.ft.rfms.entity.RfmsMerchantPos;

/**
 * @author lch
 *
 */


public class PosFilter implements TCPFilter {
	
	private Logger log = Logger.getLogger(PosFilter.class);
	private IMacProcessor macProcessor; 

	

	/**
	 * @param macProcessor the macProcessor to set
	 */
	public void setMacProcessor(IMacProcessor macProcessor) {
		this.macProcessor = macProcessor;
	}
	

	public void doFilter(TCPRequest request, TCPResponse response,
			SessionContext sessionContext) throws FTTCPException {
		String posCode = (String)request.getParaByIndex(ParaNameConstant.POS_CODE);
		log.info("POS终端号:"+posCode);
		//查询POS
/********测试数据****/
		//Pos pos = posManager.getPos(posCode);
		RfmsMerchantPos pos = new RfmsMerchantPos();
/********测试数据****/
		pos.setSysPosCode(posCode);
		if(pos == null){
			throw new FTTCPException(ResponseCodeConstant.SYSTEM_MERCHANTPOS_ERROR,"商户号或终端号不正确");
		}
		//检查MAC
		if(request.isMacFlag()){
			log.debug("check mac...");
			ArrayList<PackageMac> macList = request.getMacList();
			for(int i=0;i<macList.size();i++){
				PackageMac packageMac = macList.get(i);
				boolean temp = true;
				try {
					log.debug("mac data:["+FormatUtility.bytesToHexString(packageMac.getMacdata())+"]");
					log.debug("mac:["+FormatUtility.bytesToHexString(packageMac.getMac())+"]");
					temp = this.macProcessor.checkMac(packageMac.getMacdata(), packageMac.getMac(),pos.getMackey().getBytes());
				} catch (CipherException e) {
					throw new FTTCPException(ResponseCodeConstant.EXCEPTION,"SystemException",e);
				}
				if(!temp){
					throw new FTTCPException(ResponseCodeConstant.SYSTEM_MAC_ERROR,"验证 MAC 错误");
				}
			}		
		}

		if(pos.getMackey() != null){
			response.setMacKey(pos.getMackey().getBytes());
		}
		if(pos.getPinkey() != null)
			response.setPinkey(pos.getPinkey().getBytes());
		
		sessionContext.setParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM, pos);
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
