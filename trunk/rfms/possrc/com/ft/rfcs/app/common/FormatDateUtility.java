package com.ft.rfcs.app.common;

import java.util.Date;
import java.util.Calendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
public class FormatDateUtility {

	 /**
	  *method ���ַ������͵�����ת��Ϊһ��timestamp��ʱ�����java.sql.Timestamp��
	  *@param dateString ��Ҫת��Ϊtimestamp���ַ���
	  *@return dataTime timestamp
	  */
	 public final static java.sql.Timestamp string2Time(String dateString) 
	  throws java.text.ParseException {
	   DateFormat dateFormat;
	  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//�趨��ʽ
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util����
	  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp����,timeDate.getTime()����һ��long��
	  return dateTime;
	 }
	 
	 /**
	  *method ���ַ������͵�����ת��Ϊһ��Date��java.sql.Date��
	  *@param dateString ��Ҫת��ΪDate���ַ���
	  *@return dataTime Date
	  */
	 public final static java.sql.Date string2Date(String dateString)
	  throws java.lang.Exception {
	  DateFormat dateFormat;
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util����
	  java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql����
	  return dateTime;
	 }
	 /**
	  *method ���ַ������͵�����ת��Ϊһ��Date��java.sql.Date��
	  *@param str ��Ҫת��ΪDate���ַ���
	  *@return dataTime Date
	  */
	 public final static java.sql.Date stringToDate(String str){
		 
		 //str = "2009-01-05 12:23:34"; // test date
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  java.util.Date d = null;
		  try {
			d = format.parse(str);
		  } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  if(d == null) return null;
		  java.sql.Date date = new java.sql.Date(d.getTime());
		  if(date == null) return null;
		  System.out.print(date.toLocaleString());
		  
		  
		 
		 return date;
	 }
	 /**
	  * �������Date�Ͳ��� ������
	  * @return
	  */
	 public final static long dateCancelsDate(){
		 	String sToTimestamp = "2009-05-11 14:21:12.123";
			String sToTimestamp2 = "2009-05-11 16:42:12.123";
			
			java.sql.Date date1 = null; 
			java.sql.Date date2 = null; 
			try {
				date1 = FormatDateUtility.stringToDate(sToTimestamp);
				date2 = FormatDateUtility.stringToDate(sToTimestamp2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long tt = (date2.getTime()-date1.getTime())/(1000*60);
			
		 return tt;
	 }
	 /**
	  * �������Timestamp�Ͳ��� ������
	  * @return
	  */
	 public final static long timestampCancelsTimestamp(String sToTimestamp,String sToTimestamp2){
		 	//String sToTimestamp = "2009-05-11 14:21:12.123"; // Test data
			//String sToTimestamp2 = "2009-05-11 16:42:12.123"; //Test data
			
			java.sql.Timestamp date1 = null; 
			java.sql.Timestamp date2 = null; 
			try {
				date1 = FormatDateUtility.string2Time(sToTimestamp);
				date2 = FormatDateUtility.string2Time(sToTimestamp2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long tt = (date2.getTime()-date1.getTime())/(1000*60);
			
		 return tt;
	 }
	 
	 /**
	  * �������Timestamp�Ͳ��� ������
	  * @return
	  */
	 public final static long timestampCancelsTimestamp(Timestamp sToTimestamp,Timestamp sToTimestamp2){

		 long tt = (sToTimestamp.getTime()-sToTimestamp2.getTime())/(1000*60);	
		 return tt;
	 }
	 /**
	  * �������Timestamp�Ͳ��� ���24Сʱ���ʱ��
	  * @return
	  */
	 public final static Timestamp timestampCancels24Timestamp(Timestamp sToTimestamp){
		 long currtime = sToTimestamp.getTime()-(1000*60*60*24);
		 Timestamp currentTimestamp = new Timestamp(currtime);
		 return currentTimestamp;
	 }
	 
	 	/**
		 * ��Date����ת����String����
		 * @return String
		 */
		public static String getTime(Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		}
		
		/**
		 * ��Date����ת����String����
		 * @return String
		 */
		public static String get2Time(Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			return format.format(date);
		}	 
		/**
		 * ��Timestape����ת����String����
		 * @return String
		 */
		public static String getTimestamp2Time(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			return format.format(timestamp);
		}
		/**
		 * ��Timestape����ת����String����
		 * @return String
		 */
		public static String getTimestamp2OnlyTime(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
			return format.format(timestamp);
		}
		/**
		 * ��Timestape����ת����String����
		 * @return String
		 */
		public static String getTimestamp2OnlyDate(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return format.format(timestamp);
		}
		/**
		 * ��java.sql.Date����ת����String����
		 * @return String
		 */
		public static String getDateToString(java.sql.Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return format.format(date);
		}	 

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		  Date da = new Date();
		  //ע�⣺����ط�da.getTime()�õ�����һ��long�͵�ֵ
		  System.out.println(da.getTime());
		  
		  //������dateת��Ϊtimestamp
		  
		  //��һ�ַ�����ʹ��new Timestamp(long)
		  Timestamp t = new Timestamp(new Date().getTime());
		  System.out.println(t);
		  
		  Timestamp tt = new Timestamp(Calendar.getInstance().get(Calendar.YEAR) - 1900, Calendar.getInstance().get(
			      Calendar.MONTH), Calendar.getInstance().get(
			      Calendar.DATE), Calendar.getInstance().get(
			      Calendar.HOUR), Calendar.getInstance().get(
			      Calendar.MINUTE), Calendar.getInstance().get(
			      Calendar.SECOND), 0);
			  System.out.println(tt);

			  try {
			   String sToDate = "2005-8-18";//����ת����java.sql.Date���ַ���
			      String sToTimestamp = "2005-8-18 14:21:12.123";//����ת����java.sql.Timestamp���ַ���
			      java.sql.Date date1 = string2Date(sToDate);
			      Timestamp date2 = string2Time(sToTimestamp);
			   System.out.println("Date:"+date1.toString());//�����ʾ
			   System.out.println("Timestamp:"+date2.toString());//�����ʾ
			   System.out.println("--------"+getTimestamp2Time(date2));
			   System.out.println("+++++++="+getDateToString(date1));
			   System.out.println("--------"+getTimestamp2OnlyTime(date2));
			   System.out.println("--------"+getTimestamp2OnlyDate(date2));
			  }catch(Exception e) {
			   e.printStackTrace();
			  }
			  
			  
	
	}

}
