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
	  *method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	  *@param dateString 需要转换为timestamp的字符串
	  *@return dataTime timestamp
	  */
	 public final static java.sql.Timestamp string2Time(String dateString) 
	  throws java.text.ParseException {
	   DateFormat dateFormat;
	  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util类型
	  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型
	  return dateTime;
	 }
	 
	 /**
	  *method 将字符串类型的日期转换为一个Date（java.sql.Date）
	  *@param dateString 需要转换为Date的字符串
	  *@return dataTime Date
	  */
	 public final static java.sql.Date string2Date(String dateString)
	  throws java.lang.Exception {
	  DateFormat dateFormat;
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util类型
	  java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
	  return dateTime;
	 }
	 /**
	  *method 将字符串类型的日期转换为一个Date（java.sql.Date）
	  *@param str 需要转换为Date的字符串
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
	  * 获得两个Date型参数 相差分数
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
	  * 获得两个Timestamp型参数 相差分数
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
	  * 获得两个Timestamp型参数 相差分数
	  * @return
	  */
	 public final static long timestampCancelsTimestamp(Timestamp sToTimestamp,Timestamp sToTimestamp2){

		 long tt = (sToTimestamp.getTime()-sToTimestamp2.getTime())/(1000*60);	
		 return tt;
	 }
	 /**
	  * 获得两个Timestamp型参数 相差24小时后的时间
	  * @return
	  */
	 public final static Timestamp timestampCancels24Timestamp(Timestamp sToTimestamp){
		 long currtime = sToTimestamp.getTime()-(1000*60*60*24);
		 Timestamp currentTimestamp = new Timestamp(currtime);
		 return currentTimestamp;
	 }
	 
	 	/**
		 * 将Date类型转换成String类型
		 * @return String
		 */
		public static String getTime(Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		}
		
		/**
		 * 将Date类型转换成String类型
		 * @return String
		 */
		public static String get2Time(Date date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			return format.format(date);
		}	 
		/**
		 * 将Timestape类型转换成String类型
		 * @return String
		 */
		public static String getTimestamp2Time(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			return format.format(timestamp);
		}
		/**
		 * 将Timestape类型转换成String类型
		 * @return String
		 */
		public static String getTimestamp2OnlyTime(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
			return format.format(timestamp);
		}
		/**
		 * 将Timestape类型转换成String类型
		 * @return String
		 */
		public static String getTimestamp2OnlyDate(Timestamp timestamp) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return format.format(timestamp);
		}
		/**
		 * 将java.sql.Date类型转换成String类型
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
		  //注意：这个地方da.getTime()得到的是一个long型的值
		  System.out.println(da.getTime());
		  
		  //由日期date转换为timestamp
		  
		  //第一种方法：使用new Timestamp(long)
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
			   String sToDate = "2005-8-18";//用于转换成java.sql.Date的字符串
			      String sToTimestamp = "2005-8-18 14:21:12.123";//用于转换成java.sql.Timestamp的字符串
			      java.sql.Date date1 = string2Date(sToDate);
			      Timestamp date2 = string2Time(sToTimestamp);
			   System.out.println("Date:"+date1.toString());//结果显示
			   System.out.println("Timestamp:"+date2.toString());//结果显示
			   System.out.println("--------"+getTimestamp2Time(date2));
			   System.out.println("+++++++="+getDateToString(date1));
			   System.out.println("--------"+getTimestamp2OnlyTime(date2));
			   System.out.println("--------"+getTimestamp2OnlyDate(date2));
			  }catch(Exception e) {
			   e.printStackTrace();
			  }
			  
			  
	
	}

}
