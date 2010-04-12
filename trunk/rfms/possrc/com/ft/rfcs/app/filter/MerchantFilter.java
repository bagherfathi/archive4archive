/**
 * 
 */
package com.ft.rfcs.app.filter;

import org.apache.log4j.Logger;

import com.ft.frmwk.tcp.FTTCPException;
import com.ft.frmwk.tcp.SessionContext;
import com.ft.frmwk.tcp.TCPRequest;
import com.ft.frmwk.tcp.TCPResponse;
import com.ft.frmwk.tcp.filter.TCPFilter;
import com.ft.rfcs.ParaNameConstant;
import com.ft.rfcs.ResponseCodeConstant;
import com.ft.rfcs.ServerParamConstant;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.model.MerchantService;

/**
 * @author lch
 *
 */
public class MerchantFilter implements TCPFilter {
	
	private static Logger logger = Logger.getLogger(MerchantFilter.class);

	private MerchantService merchantService;
	
	/**
	 * @param merchantService the merchantService to set
	 */
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	/* (non-Javadoc)
	 * @see com.ft.frmwk.tcp.filter.TCPFilter#doFilter(com.ft.frmwk.tcp.TCPRequest, com.ft.frmwk.tcp.TCPResponse, com.ft.frmwk.tcp.SessionContext)
	 */
	public void doFilter(TCPRequest request, TCPResponse response,
			SessionContext sessionContext) throws FTTCPException {
		String strMerchantCode = (String) request.getParaByIndex(ParaNameConstant.MERCHANT_CODE);
		logger.info("商户编码:"+strMerchantCode);
		RfmsMerchantPos pos = (RfmsMerchantPos)sessionContext.getParameter(ServerParamConstant.PARAM_EXTERNAL_SYSTEM);
		RfmsMerchant merchant=null;
		try{
		merchant=merchantService.findMerchantByBranchId(pos.getMerchantBranchId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(merchant==null || !merchant.getStatus().equals("1")){
			throw new FTTCPException(ResponseCodeConstant.SYSTEM_MERCHANTPOS_ERROR,"商户不存在或者商户状态错误错误");
		}
		sessionContext.setParameter(ServerParamConstant.PARAM_MERCHANT, merchant);
		

	}

}
