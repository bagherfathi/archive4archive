/**
 * 
 */
package com.ft.rfms.web.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.model.MerchantService;
import com.ft.rfms.model.ReportService;
import com.ft.struts.ActionMessagesHelper;
import com.ft.web.sm.BaseAction;

/**
 * @author soler
 *
 */
public class FineReportAction extends BaseAction {

	private ReportService reportService;
	
	private MerchantService merchantService;
	
	
	/**
	 * @param merchantService the merchantService to set
	 */
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	/**
	 * @param reportService the reportService to set
	 */
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.getInputForward();
	}

	/**
	 * 结算报表查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchSettlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		Long[] ids=aform.getMerchantIds();
		if(ids!=null && ids.length>1000){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.selectNumToLarge",new Object[]{1000+""});
			return mapping.getInputForward();
		}
		List result=this.reportService.searchSettlement(aform.getBeginDate(), aform.getEndDate(), ids);
		aform.setTempMerchantIds(aform.getMerchantIds());
		request.setAttribute("results", result);
		return mapping.getInputForward();
	}
	
	/**
	 * 结算报表总表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toSettlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		Long[] ids=aform.getMerchantIds();
		if(ids!=null && ids.length>1000){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.selectNumToLarge",new Object[]{1000+""});
			return mapping.getInputForward();
		}
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer param=new StringBuffer("beginDate="+format.format(aform.getBeginDate()));
		param.append("&endDate=").append(format.format(aform.getEndDate()));
		param.append("&merchantIds=").append(FindByIdsCallback.joinKeys(ids));
		return this.getRedirectForwardAction("../ReportServer?reportlet=/com/ft/settlement.cpt&"+param);
	}
 
 
	public ActionForward searchPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO getParamter
		FineReportForm aform=(FineReportForm)form;
		
		return mapping.findForward("paymentDtl");
	}
	
	/**
	 * 日明细报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchDayTradeDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		Long[] ids=aform.getMerchantIds();
		if(ids!=null && ids.length>1000){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.selectNumToLarge",new Object[]{1000+""});
			return mapping.getInputForward();
		}
		if(aform.getBeginDate()==null && aform.getEndDate()==null){
			return mapping.findForward("dayTrade");
		}
		List result=this.reportService.searchDayTrade(aform.getBeginDate(), aform.getEndDate(), ids);
		request.setAttribute("results", result);
		aform.setTempMerchantIds(aform.getMerchantIds());
		return mapping.findForward("dayTrade");
	}
	
	/**
	 * 商户结算报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchMerchantRep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		String merchantCode=aform.getCurrentUser().getMerchantCode();
		if(merchantCode==null || merchantCode.length()==0){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.notmerchant");
			return null;
		}
		RfmsMerchant mer=(RfmsMerchant)this.reportService.getEntityByIdentityAttribute(RfmsMerchant.class, "sysMerchantCode", merchantCode);
		if(mer==null){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.merchantCode.error");
			return null;
		}
		
		Long[] ids=new Long[]{mer.getMerchantId()};
		aform.setMerchantIds(ids);
		if(ids!=null && ids.length>1000){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.selectNumToLarge",new Object[]{1000+""});
			return mapping.findForward("merchantReport");
		}
		if(aform.getBeginDate()!=null ){
		List result=this.reportService.searchSettlement(aform.getBeginDate(), aform.getEndDate(), ids);
		request.setAttribute("results", result);
		}
		return mapping.findForward("merchantReport");
	}
	
	/**
	 * 商户日明细报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchMerchantDayTradeDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		String merchantCode=aform.getCurrentUser().getMerchantCode();
		if(merchantCode==null || merchantCode.length()==0){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.notmerchant");
			return null;
		}
		RfmsMerchant mer=(RfmsMerchant)this.reportService.getEntityByIdentityAttribute(RfmsMerchant.class, "sysMerchantCode", merchantCode);
		if(mer==null){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.merchantCode.error");
			return null;
		}
		
		Long[] ids=new Long[]{mer.getMerchantId()};
		aform.setMerchantIds(ids);
		if(ids!=null && ids.length>1000){
			ActionMessagesHelper.saveRequestMessage(request, "msg.show.merchant.selectNumToLarge",new Object[]{1000+""});
			return mapping.findForward("merchantReport");
		}
		if(aform.getBeginDate()!=null ){
		List result=this.reportService.searchSettlement(aform.getBeginDate(), aform.getEndDate(), ids);
		request.setAttribute("results", result);
		}
		return mapping.findForward("merchantDayTrade");
	}
	/**
	 * 卡销售报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cardRep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FineReportForm aform=(FineReportForm)form;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer param=new StringBuffer("beginDate="+format.format(aform.getBeginDate()));
		param.append("&endDate=").append(format.format(aform.getEndDate()));
		return this.getRedirectForwardAction("../ReportServer?reportlet=/com/ft/cardSaledReport.cpt&"+param);
	}
 
}
