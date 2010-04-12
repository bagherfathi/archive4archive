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
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.model.WebAndPosService;

/**
 * 1.卡号cardFilter<检查卡号合法性，是否存在，卡是否冻结>< view_card 的service层 >
 * 2.检查卡密码的合法性，密码是否合法。
 */
public class CouponFilter implements TCPFilter {

	private static Logger log = Logger.getLogger(CouponFilter.class);

	private WebAndPosService webAndPos;

	/**
	 * @param webAndPos the webAndPos to set
	 */
	public void setWebAndPos(WebAndPosService webAndPos) {
		this.webAndPos = webAndPos;
	}

	public void doFilter(TCPRequest request, TCPResponse response,
			SessionContext sessionContext) throws FTTCPException {
		String couponNo = (String) request
				.getParaByIndex(ParaNameConstant.ACCOUNT);// 获得主帐号
		log.debug("couponNo:" + couponNo);
		RfmsTicketDetail detail=null;
		try{
		detail = (RfmsTicketDetail) webAndPos
				.getEntityByIdentityAttribute(RfmsTicketDetail.class,
						"seqNumber", couponNo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if (detail == null) {
			log.debug("*************优惠券不存在*****************");
			throw new FTTCPException(ResponseCodeConstant.COUPON_CODE_ERROR,
					"优惠券不存在");
		}

		Long cardStruts = detail.getStatus();// 获得优惠券状态
		if (cardStruts != 2) {
			log.info("************优惠券已使用****************");
			throw new FTTCPException(ResponseCodeConstant.COUPON_USED_ERROR,
					"优惠券已使用或者未下发");
		}

		sessionContext.setParameter(ServerParamConstant.PARAM_CARD, detail);

	}

}
