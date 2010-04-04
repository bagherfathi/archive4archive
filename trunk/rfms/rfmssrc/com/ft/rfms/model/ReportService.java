/**
 * 
 */
package com.ft.rfms.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import com.ft.common.busi.BaseService;

/**
 * @author soler
 * 
 */
public interface ReportService extends BaseService {

	/**
	 * jasperreport xls导出
	 * @param fileName
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public byte[] xlsReports(String fileName, Map params) throws Exception;

	/**
	 * jasperreport xls导出
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] xlsReposts(String fileName) throws Exception;

	/**
	 * jasperreport html显示
	 * @param fileName
	 * @param page
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public byte[] htmlReports(String fileName,Integer page, Map params) throws Exception;
	
	/**
	 * 根据sql语句和form的参数查询
	 * @param sql
	 * @param param
	 * @throws Exception
	 */
	public List searchBySqlAndParams(String sql,Map conditionMap,Map param)throws Exception;
	
	 public void confirmPayReport(String[] payIds)throws Exception;
	 
	 /**
	  * 结算报表查询
	  * @param beginDate
	  * @param endDate
	  * @param ids
	  * @throws Exception
	  */
	 public List searchSettlement(Date beginDate,Date endDate,Long[] ids)throws Exception;
	 
	 /**
	    * 查询日明细
	    * @param beginDate
	    * @param endDate
	    * @param ids
	    * @return
	    */
	   public List searchDayTrade(Date beginDate,Date endDate,Long[] ids)throws Exception;
	   
	   /**
	    * 获取交易状态
	    * @return
	    * @throws Exception
	    */
	   public List getTreadStatus()throws Exception;
	   
	   public List getCardType()throws Exception;
	   /**
	    * 根据ID获取交易状态
	    * @param id
	    * @return
	    * @throws Exception
	    */
	   public LabelValueBean getTreadStatus(Long id)throws Exception;
	   
	   /**
		 * 获取组织
		 * @param id
		 * @return
		 */
		public LabelValueBean getRfmsOrg(Long id) throws Exception;
}
