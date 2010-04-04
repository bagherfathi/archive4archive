package com.ft.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.hibernate.SessionFactory;

/**
 * ֱ��ͨ��JDBC�������ݿ�
 * 
 * @version 1.0
 */
public class DBUtils {
	/**
	 * 
	 * @return �������ݿ�ϵͳʱ��
	 */
	public static Date getSysDate(SessionFactory sessionFactory) {
		String sql = "select to_char(sysdate,'yyyymmddhh24miss') systemdate from dual";
		Date resultDate = null;
		List result=query(sql,sessionFactory);
		for (Iterator ite = result.iterator(); ite.hasNext();) {
			DynaBean dBean = (DynaBean) ite.next();
    		String  strdate=(String) dBean.get("systemdate");
    		SimpleDateFormat formatter=new SimpleDateFormat ("yyyyMMddHHmmss");
    		try {
				resultDate=formatter.parse(strdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return resultDate;
	}

	/**
	 * ִ�в�ѯ���
	 * 
	 * @param sql
	 *            ���ݿ��ѯ���
	 * @param sessionFactory
	 *            sessionFactory������
	 * @return ��ѯ�����б� �޽������һ���յ��б�
	 */
	public static List query(String sql, SessionFactory sessionFactory) {
		List resultList = null;
	//	Connection connection = null;
		PreparedStatement aPreparedStatement = null;
		ResultSet rs = null;
		
		try {
			Connection connection = sessionFactory.getCurrentSession().connection();
			aPreparedStatement = connection.prepareStatement(sql);
			rs = aPreparedStatement.executeQuery();
			RowSetDynaClass rsdc = new RowSetDynaClass(rs);
			resultList = rsdc.getRows();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (aPreparedStatement != null) {
					aPreparedStatement.close();
				}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return resultList;

	}
	
	public static String  getArrayAsString(long[] array,String regex){
		  StringBuffer tempStrBuf=new StringBuffer(); 
		  if(array!=null&&array.length>0){
		  for(int i=0;i<array.length;i++){
			   tempStrBuf.append(array[i]);	 
		  if(i!=(array.length-1)) //����������һ���������м�
			   tempStrBuf.append(regex);		   
		  }	  
	     }
		  return tempStrBuf.toString();
	  }
	  public static String  getArrayAsString(Long[] array,String regex){
		  StringBuffer tempStrBuf=new StringBuffer(); 
		  if(array!=null&&array.length>0){
		  for(int i=0;i<array.length;i++){
			   tempStrBuf.append(array[i].longValue());	 
		  if(i!=(array.length-1)) //����������һ���������м�
			   tempStrBuf.append(regex);		   
		  }	  
	     }
		  return tempStrBuf.toString();
	  }

}
