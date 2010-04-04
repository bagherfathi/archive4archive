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
	 * jasperreport xls����
	 * @param fileName
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public byte[] xlsReports(String fileName, Map params) throws Exception;

	/**
	 * jasperreport xls����
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] xlsReposts(String fileName) throws Exception;

	/**
	 * jasperreport html��ʾ
	 * @param fileName
	 * @param page
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public byte[] htmlReports(String fileName,Integer page, Map params) throws Exception;
	
	/**
	 * ����sql����form�Ĳ�����ѯ
	 * @param sql
	 * @param param
	 * @throws Exception
	 */
	public List searchBySqlAndParams(String sql,Map conditionMap,Map param)throws Exception;
	
	 public void confirmPayReport(String[] payIds)throws Exception;
	 
	 /**
	  * ���㱨���ѯ
	  * @param beginDate
	  * @param endDate
	  * @param ids
	  * @throws Exception
	  */
	 public List searchSettlement(Date beginDate,Date endDate,Long[] ids)throws Exception;
	 
	 /**
	    * ��ѯ����ϸ
	    * @param beginDate
	    * @param endDate
	    * @param ids
	    * @return
	    */
	   public List searchDayTrade(Date beginDate,Date endDate,Long[] ids)throws Exception;
	   
	   /**
	    * ��ȡ����״̬
	    * @return
	    * @throws Exception
	    */
	   public List getTreadStatus()throws Exception;
	   
	   public List getCardType()throws Exception;
	   /**
	    * ����ID��ȡ����״̬
	    * @param id
	    * @return
	    * @throws Exception
	    */
	   public LabelValueBean getTreadStatus(Long id)throws Exception;
	   
	   /**
		 * ��ȡ��֯
		 * @param id
		 * @return
		 */
		public LabelValueBean getRfmsOrg(Long id) throws Exception;
}
