/**
 * 
 */
package com.ft.rfms.busi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.struts.util.LabelValueBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ft.busi.common.SpringBeanUtils;
import com.ft.common.busi.BaseServiceImpl;
import com.ft.hibernate.callback.FindByIdsCallback;
import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.model.ReportService;

/**
 * @author soler
 * 
 */
public class ReportServiceImpl extends BaseServiceImpl implements ReportService {

	private BaseDao baseDao;

	DataSource dataSource = (DataSource) SpringBeanUtils.getBean("dataSource");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.ReportService#xlsReports(java.lang.String,
	 *      java.util.Map)
	 */
	public byte[] xlsReports(String fileName, Map params) throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;
		byte[] bytes = null;
		try {
			con = dataSource.getConnection();
			JRXlsExporter exporter = new JRXlsExporter(); // Excel
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			// JasperDesign jasperDesign = JRXmlLoader.load(fileName);
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,
					params, con);
			exporter
					.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.exportReport();
			bytes = oStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} /*
			 * finally { try { if (con != null) { con.close(); } } catch
			 * (Exception ex) { ex.printStackTrace(); } }
			 */
		return bytes;
	}

	public byte[] htmlReports(String fileName, Integer page, Map params)
			throws Exception {

		Connection con = null;
		byte[] bytes = null;
		try {
			con = dataSource.getConnection();
			JRHtmlExporter exporter = new JRHtmlExporter(); // Excel
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			// JasperDesign jasperDesign = JRXmlLoader.load(fileName);
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(fileName,
					params, con);
			int pageIndex = page.intValue();
			int lastPageIndex = 0;
			if (jasperPrint.getPages() != null) {
				lastPageIndex = jasperPrint.getPages().size() - 1;
			}

			if (pageIndex < 0) {
				pageIndex = 1;
			}

			if (pageIndex > lastPageIndex) {
				pageIndex = lastPageIndex;
			}

			exporter
					.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					"../servlets/image?image=");
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(
					pageIndex));
			String x = new File(fileName).getName();
			String urlfileName = x.substring(0, x.lastIndexOf("."));
			String url = "reports.do?act=htmlReport&fileName=" + urlfileName;
			String exporturl = "reports.do?act=xlsReport&fileName=" + urlfileName;
			StringBuffer pagestr = new StringBuffer();
			pagestr
					.append("<table width='100%' cellpadding='0' cellspacing='0' border='0'><tr><td>");
			pagestr.append("<a href='"+exporturl+"'>导出</a></td>");
			pagestr.append("<td>&nbsp;&nbsp;&nbsp;</td>");
			if (pageIndex > 0) {
				pagestr
						.append("<td><a href=\"" + url
								+ "&page=0\">首页</a></td>");
				pagestr.append("<td><a href=\"" + url + "&page="
						+ (pageIndex - 1) + "\">上一页</a></td>");
			} else {
				pagestr.append("<td>首页</td>");
				pagestr.append("<td>上一页</td>");
			}

			if (pageIndex < lastPageIndex) {
				pagestr.append("<td><a href=\"" + url + "&page="
						+ (pageIndex + 1) + "\">下一页</a></td>");
				pagestr.append("<td><a href=\"" + url + "&page="
						+ lastPageIndex + "\">最后一页</a></td>");
			} else {
				pagestr.append("<td>下一页</td>");
				pagestr.append("<td>最后一页</td>");
			}
			pagestr.append("<td>&nbsp;</td></tr></table>");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, pagestr
					.toString());
			exporter.exportReport();
			bytes = oStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return bytes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ft.rfms.model.ReportService#xlsReposts(java.lang.String)
	 */
	public byte[] xlsReposts(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return xlsReports(fileName, new HashMap());
	}

	/**
	 * @param baseDao
	 *            the baseDao to set
	 */
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
		super.baseDao=baseDao;
	}

	/* (non-Javadoc)
	 * @see com.ft.rfms.model.ReportService#searchBySqlAndParams(java.lang.String, java.util.Map)
	 */
	public List searchBySqlAndParams(String sql,Map conditionMap, Map param) throws Exception {
		// TODO Auto-generated method stub
		if(sql==null || sql.trim().length()==0){
			return new ArrayList();
		}
		String orderbyStr="";
		Set conditionSet=conditionMap.keySet();
		for(Iterator it=conditionSet.iterator();it.hasNext();){
			Object key=it.next();
			Object value=param.get(key);
			String valueStr="";
			if(value!=null){
				valueStr=((String[])value)[0];
			}
			if(valueStr!=null && valueStr.toString().length()>0 && !valueStr.equals("null")){
				String tempCondition=(String)conditionMap.get(key);
				tempCondition=tempCondition.replace("["+(String)key+"]", valueStr);
				if(!tempCondition.trim().startsWith("and") && !sql.trim().endsWith("and")){
					tempCondition+=" and ";
				}
				sql+=tempCondition;
			}
		}
		try {
			orderbyStr=(String)conditionMap.get("orderby");
			if(orderbyStr==null||orderbyStr.equals("null"))
				orderbyStr="";
		} catch (Exception e) {
			orderbyStr="";
		}
		sql += orderbyStr;
		
		JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
		List list=template.queryForList(sql);
		return list;
	}

	public List getTreadStatus(){
		StringBuffer sql=new StringBuffer();
		sql.append("select ''||id id,trim(text) text from rfms.dict_trade_status where id in(1,6) order by id");
		JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
		List list=template.query(sql.toString(), new RowMapper() {
			  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	  LabelValueBean bean=new LabelValueBean();
		    	  bean.setLabel(rs.getString(2));
		    	  bean.setValue(""+rs.getInt(1));
		    	  return bean;
		      }
			      });
		return list;
	}
	
	public List getCardType(){
		StringBuffer sql=new StringBuffer();
		sql.append("select id,trim(name) from rfms.tbl_cardType");
		JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
		List list=template.query(sql.toString(), new RowMapper() {
			  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	  LabelValueBean bean=new LabelValueBean();
		    	  bean.setLabel(rs.getString(2));
		    	  bean.setValue(""+rs.getInt(1));
		    	  return bean;
		      }
			      });
		return list;
	}
	
	public LabelValueBean getTreadStatus(Long id){
		StringBuffer sql=new StringBuffer();
		sql.append("select id,trim(text) from rfms.dict_trade_status where id="+id);
		JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
		List list=template.query(sql.toString(), new RowMapper() {
			  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	  LabelValueBean bean=new LabelValueBean();
		    	  bean.setLabel(rs.getString(2));
		    	  bean.setValue(""+rs.getInt(1));
		    	  return bean;
		      }
			      });
		return (LabelValueBean)list.get(0);
	}
	
	/**
	 * 获取组织
	 * @param id
	 * @return
	 */
	public LabelValueBean getRfmsOrg(Long id){
		StringBuffer sql=new StringBuffer();
		sql.append("select id,trim(name) from rfms.tbl_organization where id="+id);
		JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
		List list=template.query(sql.toString(), new RowMapper() {
			  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	  LabelValueBean bean=new LabelValueBean();
		    	  bean.setLabel(rs.getString(2));
		    	  bean.setValue(""+rs.getInt(1));
		    	  return bean;
		      }
			      });
		return (LabelValueBean)list.get(0);
	}
	
   public void confirmPayReport(String[] payIds){
	   StringBuffer sql=new StringBuffer();
	   sql.append("update rfms.rfms_settle_pay p set p.pay_flag=1,p.realpay_date=SYSDATE where p.id in").append(FindByIdsCallback.joinKeys(payIds));
	   JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
	   template.update(sql.toString());
   }
   
   
   public List searchSettlement(Date beginDate,Date endDate,Long[] ids){
	   StringBuffer sql=new StringBuffer();
	   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	   sql.append("select rownum,mer.merchant_name,mer.merchant_id,re.settle_date");
	   sql.append(" from rfms.rfms_settle_record re,rfms.rfms_merchant mer");
	   sql.append(" where re.merchant_id=mer.merchant_id");
	   if(ids!=null && ids.length>0){
	    sql.append(" and re.merchant_id in").append(FindByIdsCallback.joinKeys(ids));
	   }
	   sql.append(" and re.settle_date between ");
	   sql.append("to_date('").append(format.format(beginDate)).append("','yyyy-MM-dd')");
	   sql.append(" and ");
	   sql.append("to_date('").append(format.format(endDate)).append("','yyyy-MM-dd')");
	   sql.append(" and mer.audit_status=8 ");
	   sql.append(" order by re.settle_date ");
	   JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
	   return template.queryForList(sql.toString());
   }

   /**
    * 查询日明细
    * @param beginDate
    * @param endDate
    * @param ids
    * @return
    */
   public List searchDayTrade(Date beginDate,Date endDate,Long[] ids){
	   StringBuffer sql=new StringBuffer();
	   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	   
	   sql.append("select distinct mer.merchant_name, mer.merchant_id, re.recorddate ");
	   sql.append("from rfms.tbl_clearingresults re,  ");
	   sql.append("rfms.rfms_merchant mer, ");
	   sql.append("rfms.tbl_organization org, ");
	   sql.append("rfms.rfms_merchant_branch bran ");
	   sql.append("where re.org_id=org.id ");
	   sql.append("and trim(org.uniqueid)=trim(bran.sys_merchant_code) ");
	   sql.append("and bran.merchant_id=mer.merchant_id ");
	   if(ids!=null && ids.length>0){
		    sql.append(" and mer.merchant_id in").append(FindByIdsCallback.joinKeys(ids));
	   }
	   sql.append("and re.recorddate between to_date('").append(format.format(beginDate)).append("', 'yyyy-MM-dd') and ");
	   sql.append("to_date('").append(format.format(endDate)).append("', 'yyyy-MM-dd') ");
	   sql.append(" and mer.audit_status=8 ");
	   sql.append("order by re.recorddate");
	   System.out.println(sql.toString());
	   JdbcTemplate template=(JdbcTemplate)SpringBeanUtils.getBean("jdbcTemplate");
	   return template.queryForList(sql.toString());
   }
   
}
