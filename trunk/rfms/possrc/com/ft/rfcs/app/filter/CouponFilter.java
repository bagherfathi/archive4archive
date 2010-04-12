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
 * 1.����cardFilter<��鿨�źϷ��ԣ��Ƿ���ڣ����Ƿ񶳽�>< view_card ��service�� >
 * 2.��鿨����ĺϷ��ԣ������Ƿ�Ϸ���
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
				.getParaByIndex(ParaNameConstant.ACCOUNT);// ������ʺ�
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
			log.debug("*************�Ż�ȯ������*****************");
			throw new FTTCPException(ResponseCodeConstant.COUPON_CODE_ERROR,
					"�Ż�ȯ������");
		}

		Long cardStruts = detail.getStatus();// ����Ż�ȯ״̬
		if (cardStruts != 2) {
			log.info("************�Ż�ȯ��ʹ��****************");
			throw new FTTCPException(ResponseCodeConstant.COUPON_USED_ERROR,
					"�Ż�ȯ��ʹ�û���δ�·�");
		}

		sessionContext.setParameter(ServerParamConstant.PARAM_CARD, detail);

	}

}
