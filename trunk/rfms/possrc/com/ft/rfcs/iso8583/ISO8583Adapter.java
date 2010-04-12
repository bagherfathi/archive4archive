/**
 * 
 */
package com.ft.rfcs.iso8583;



import java.util.Iterator;

import com.ft.frmwk.tcp.TCPDispatchAction;
import com.ft.frmwk.tcp.TCPRequest;
import com.ft.frmwk.tcp.TCPResponse;
import com.ft.protocol.cipher.mac.IMacProcessor;
import com.ft.rfcs.ParaNameConstant;
import com.ft.rfcs.ResponseCodeConstant;
import com.ft.protocol.common.PackageMac;
import com.ft.protocol.iso8583.ISO8583Message;
import com.ft.protocol.iso8583.tpdu.ITpduProcessor;
import org.apache.log4j.Logger;

/**
 * @author lch
 *
 */
public class ISO8583Adapter {
	
	private TCPDispatchAction dispatchAction;
	
	private IMacProcessor macProcessor; // spring 初始化  
	private ITpduProcessor tpduProcessor; // spring 初始化
	
	private static Logger log = Logger.getLogger(ISO8583Adapter.class);
	/**
	 * 
	 * @param message
	 * @return
	 */
	public ISO8583Message doAdapte(ISO8583Message message){
		
		TCPRequest tcpRequest = new TCPRequest();
		ISO8583Message isoResponse = new ISO8583Message();
		isoResponse.setTpdu(message.getTpdu());
		isoResponse.setTpduProcessor(this.tpduProcessor);
		isoResponse.setProcessCode(message.getProcessCode());
		log.debug("procoessCode:"+isoResponse.getProcessCode());
		tcpRequest.setRemoteIP(message.getIpaddress());
		tcpRequest.setMacFlag(message.isMacFlag());
		if(tcpRequest.isMacFlag()){
			PackageMac packageMac = new PackageMac();
			packageMac.setMac(message.getMac());
			packageMac.setMacdata(message.getMacdata());
			tcpRequest.addMac(packageMac);
		}
		tcpRequest.setOperateType(message.getProcessCode());
		tcpRequest.setParas(message.getFieldValueSet());
		
		TCPResponse tcpResponse = this.dispatchAction.dispatch(tcpRequest);
		if(!tcpResponse.isResponseFlag()){
			log.info("由于数据异常，服务器端不响应客户端响应");
			return null;
		}
		if(tcpResponse.getResponseCode().equals(ResponseCodeConstant.SUCCESS)){
			Iterator paraIt = tcpResponse.getParas().keySet().iterator();
			while(paraIt.hasNext()){
				Integer tempKey = (Integer)paraIt.next();
				isoResponse.addFieldValue(tempKey, tcpResponse.getParaByIndex(tempKey));
			}
			isoResponse.addFieldValue(ParaNameConstant.RETCODE, tcpResponse.getResponseCode());
			isoResponse.addFieldValue(ParaNameConstant.POS_CODE, message.getFieldValue(ParaNameConstant.POS_CODE));
			isoResponse.setUtil(tcpResponse.getMacKey(), tcpResponse.getPinkey(), macProcessor, tpduProcessor);
		}else{
			
			isoResponse.addFieldValue(ParaNameConstant.RETCODE, tcpResponse.getResponseCode());
			isoResponse.addFieldValue(ParaNameConstant.RETMSG, tcpResponse.getErrorInfo());
			isoResponse.addFieldValue(ParaNameConstant.POS_CODE, message.getFieldValue(ParaNameConstant.POS_CODE));
			isoResponse.setMacFlag(false);
		}
		
		return isoResponse;
	}
	
	
	




	/**
	 * @param dispatchAction the dispatchAction to set
	 */
	public void setDispatchAction(TCPDispatchAction dispatchAction) {
		this.dispatchAction = dispatchAction;
	}







	/**
	 * @param macProcessor the macProcessor to set
	 */
	public void setMacProcessor(IMacProcessor macProcessor) {
		this.macProcessor = macProcessor;
	}



	/**
	 * @param tpduProcessor the tpduProcessor to set
	 */
	public void setTpduProcessor(ITpduProcessor tpduProcessor) {
		this.tpduProcessor = tpduProcessor;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
