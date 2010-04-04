/**
 * 
 */
package com.ft.rfms.web.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.rfms.model.ReportService;
import com.ft.web.sm.BaseAction;

/**
 * @author soler
 *
 */
public class PayReportAction extends BaseAction {

	private String sql="";
	
	private ReportService reportService;
	
	private Map<String,String> conditionMap;
	
	/**
	 * @return the conditionMap
	 */
	public Map<String, String> getConditionMap() {
		return conditionMap;
	}

	/**
	 * @param conditionMap the conditionMap to set
	 */
	public void setConditionMap(Map<String, String> conditionMap) {
		this.conditionMap = conditionMap;
	}

	/**
	 * @param reportService the reportService to set
	 */
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return arg0.getInputForward();
	}

	public ActionForward search(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		Map param=arg2.getParameterMap();
		List list=this.reportService.searchBySqlAndParams(sql, conditionMap,param);
		arg2.setAttribute("results", list);
		return arg0.getInputForward();
	}
	
	public ActionForward pay(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		String[] ids=arg2.getParameterValues("selectedIds");
		if(ids!=null && ids.length>0){
		this.reportService.confirmPayReport(ids);
		}
		return arg0.getInputForward();
	}
	
	public ActionForward cardTypeRep(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		List list=this.reportService.getCardType();
		arg2.setAttribute("cardTypes", list);
		return arg0.findForward("cardTypeRep");
	}
	
}
