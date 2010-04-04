package com.ft.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.QDecoderStream;

public class FormatData {

	/**
	 * 此类构造器方法
	 */
	public FormatData() {
	}

	/**
	 * String型转化成int
	 * 
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		if (str == null) {
			str = "";
		}
		str = str.trim();
		int integer = 0;
		/*
		 * String str1=str; for(int i=0;i<str1.length();i++){ String
		 * temp=str.substring(i,i+1);
		 * 
		 * if(!"0".equals(temp)){ break; }else{ str=str1.substring(i+1); } }
		 */
		try {
			integer = Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return integer;
	}

	public static long strToLong(String str) {
		long lon = 0;
		try {
			if (str.indexOf(".") != -1) {
				str = str.substring(0, str.indexOf("."));
			}
			lon = Long.parseLong(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lon;
	}

	/**
	 * int到String的转化
	 * 
	 * @param integer
	 * @return
	 */
	public static String intToStr(int integer) {
		String str = integer + "";
		return str;
	}

	/**
	 * double转换成int
	 * 
	 * @param temp
	 * @return
	 */
	public static int doubleToInt(double temp) {
		int integer = 0;
		if (temp >= -2147483648 && temp <= 2147483647) {
			integer = (int) temp;
		}
		return integer;

	}

	/**
	 * int转换成double
	 * 
	 * @param integer
	 * @return
	 */
	public static double intToDouble(int integer) {
		double dou = (double) integer;

		return dou;
	}

	/**
	 * Date转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date, String dateMode) {
		if (date != null) {
			SimpleDateFormat dtSimpleFormat = new SimpleDateFormat(dateMode);
			return dtSimpleFormat.format(date);
		} else {
			return null;
		}
	}

	/**
	 * String转换成Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {
		if (checkDate(str)) {
			java.util.StringTokenizer objst = new java.util.StringTokenizer(
					str, "/");
			int nMM, nDD, nYY;
			nYY = Integer.parseInt(objst.nextToken());
			nMM = Integer.parseInt(objst.nextToken());
			nDD = Integer.parseInt(objst.nextToken());
			java.util.Calendar objCal = java.util.Calendar.getInstance();
			objCal.set(java.util.Calendar.YEAR, nYY);
			objCal.set(java.util.Calendar.MONTH, nMM - 1);
			objCal.set(java.util.Calendar.DATE, nDD);
			Date date = new Date(objCal.getTime().getTime());
			return date;
		} else {
			return new Date();
		}
	}

	/**
	 * 检查日期型字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkDate(String str) {
		boolean flag = true;
		str = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
		int i = str.length();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		simpleDateFormat.getCalendar().setLenient(false);
		if (i == 8) {
			try {
				simpleDateFormat.parse(str);
			} catch (Exception e) {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 内码转换
	 * 
	 * @param str
	 * @return
	 */
	public static String n2u(String nstr) {
		String ustr = "";
		if ((nstr == null) || ((nstr.trim()).compareTo("null") == 0)) {
			return ustr;
		}
		try {
			ustr = new String((nstr.trim()).getBytes("ISO8859_1"));
		} catch (IOException e) {
			return ustr;
		}
		return ustr;
	}

	/**
	 * 内码转换
	 * 
	 * @param str
	 * @return
	 */
	public static String u2n(String ustr) {
		// Unicode -->native(SJIS)
		String nstr = "";
		if ((ustr == null) || ((ustr.trim()).compareTo("null") == 0)) {
			return "";
		}
		try {
			nstr = new String((ustr.trim()).getBytes("SJIS"), "ISO8859_1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nstr;
	}

	public static String getSystemDate() {
		// 取得系统日期
		Date date = new Date();
		String strDate = FormatData.dateToStr(date, "yyyy年MM月dd日");
		// strDate=strDate.substring(0,4)+"年"+strDate.substring(4,6)+"月"+strDate.substring(6,8)+"日";
		// 取得星期
		Calendar now = new GregorianCalendar();

		int week = now.get(Calendar.DAY_OF_WEEK);
		String strWeek = "";
		switch (week) {
		case 1:
			strWeek = "星期日";
			break;
		case 2:
			strWeek = "星期一";
			break;
		case 3:
			strWeek = "星期二";
			break;
		case 4:
			strWeek = "星期三";
			break;
		case 5:
			strWeek = "星期四";
			break;
		case 6:
			strWeek = "星期五";
			break;
		case 7:
			strWeek = "星期六";
			break;
		default:
			strWeek = "";
		}
		return strDate + "  " + strWeek;
	}

	/**
	 * 取得星期
	 * 
	 * @return
	 */
	public static int getWeek(String strDate) {
		String strYear = strDate.substring(0, 4);
		String strMonth = strDate.substring(4, 6);
		strDate = strDate.substring(6, 8);
		int year = strToInt(strYear);
		int month = strToInt(strMonth) - 1;
		int date = strToInt(strDate);
		// 取得星期
		Calendar now = new GregorianCalendar(year, month, date);

		int week = now.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * 取得一个月的天数
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getDayOfMonth(String strDate) {
		String strYear = strDate.substring(0, 4);
		String strMonth = strDate.substring(4, 6);
		int year = strToInt(strYear);
		int month = strToInt(strMonth) - 1;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, 1);
		// Calendar calendar=new GregorianCalendar(year,month,1);
		// calendar.set(Calendar.MONTH,month);
		return cal.getActualMaximum(Calendar.DATE);

	}

	/**
	 * 取得一年的天数
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getDayOfYear(String strDate) {

		int days = 0;
		for (int i = 1; i < 13; i++) {
			String strYear = strDate.substring(0, 4);
			if (i < 10) {
				strYear = strYear + "0" + i;
			} else {
				strYear = strYear + "" + i;
			}
			days = days + getDayOfMonth(strYear);

		}
		return days;

	}

	/**
	 * 取得指定日期到年底的天数（包括指定日）
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getDayToEnd(String strDate) {
		String strYear = strDate.substring(0, 4);
		String strMonth = strDate.substring(4, 6);
		String tempDate = strDate.substring(6, 8);
		int month = strToInt(strMonth);
		int day = 0;
		for (int i = 1; i < month; i++) {
			String strDate1 = "";
			if (i < 10) {
				strDate1 = strYear + "0" + i;
			} else {
				strDate1 = strYear + i;
			}
			day = day + getDayOfMonth(strDate1);
		}
		int dayOfYear = getDayOfYear(strDate);

		int date = strToInt(tempDate);

		day = dayOfYear - day - date + 1;
		return day;
	}

	/**
	 * 取得文件大小的字符串
	 * 
	 * @param size
	 * @return
	 */
	public static String getSize(float size) {
		String strSize = "";
		if (size < 1024) {
			float temp = size / 1024;
			strSize = temp + "";
			strSize = strSize.substring(0, 3) + "K";
		} else if (size >= 1024 && size < (1024 * 1024)) {
			int nsize = Math.round(size / 1024);
			strSize = nsize + "K";
		} else {
			float fsize = size / (1024 * 1024);
			strSize = fsize + "";
			strSize = strSize.substring(0, 3) + "M";
		}
		return strSize;
	}

	/**
	 * 取得文件大小的字符串
	 * 
	 * @param size
	 * @return
	 */
	public static String getSize(long size) {
		String strSize = "";
		if (size < 1024) {
			float temp = size / 1024;
			strSize = temp + "";
			strSize = strSize.substring(0, 3) + "K";
		} else if (size >= 1024 && size < (1024 * 1024)) {
			int nsize = Math.round(size / 1024);
			strSize = nsize + "K";
		} else {
			// System.out.println("nsize===zhubj teset");
			// int nsize=(int)size/(1024*1024);
			// System.out.println("nsize==="+nsize);
			// float temp=size/(1024*1024)-nsize;
			// String str=temp+"";
			// System.out.println("str==="+str);
			// str=str.substring(1,3);
			float fsize = size / (1024 * 1024);
			strSize = fsize + "";
			strSize = strSize.substring(0, 3) + "M";
		}
		return strSize;
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String interceptStr(String str, int len) {
		try {
			str = new String(str.getBytes("ISO-8859-1"), "GB2312");
			str = str.substring(0, len);
			char[] arr = str.toCharArray();
			// 汉字的数量
			int count = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > 255) {
					count++;
				}
			}
			str = str.substring(0, len - count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 按空格分离字符串，返回字符串数组
	 */
	public static String[] splitString(String source, String strSplit) {
		if (source == null || source.trim().equals(""))
			return null;
		StringTokenizer commaToker = new StringTokenizer(source, strSplit);
		String[] result = new String[commaToker.countTokens()];
		int i = 0;
		while (commaToker.hasMoreTokens()) {
			result[i] = commaToker.nextToken();
			i++;
		}
		return result;
	}

	/**
	 * 删除字符的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		String returnStr = str;
		if (returnStr == null) {
			returnStr = "";
		} else {
			returnStr = returnStr.trim();
		}
		return returnStr;
	}

	/**
	 * 在日期型字符串中插入'-'or'/'
	 * 
	 * @param strDate
	 * @param inStr
	 * @return
	 */
	public static String insertStr(String strDate, String inStr) {
		String str = "";
		if (strDate == null || "".equals(strDate)) {
			str = "";
		} else {
			str = strDate.substring(0, 4) + inStr + strDate.substring(4, 6)
					+ inStr + strDate.substring(6, 8);
		}
		return str;
	}

	/**
	 * 在日期型字符串中删除'-'or'/'
	 * 
	 * @param strDate
	 * @param inStr
	 * @return
	 */
	public static String deleteStr(String strDate) {
		String str = "";
		if (strDate == null || "".equals(strDate)) {
			str = "";
		} else {
			str = strDate.substring(0, 4) + strDate.substring(5, 7)
					+ strDate.substring(8, 10);
		}
		return str;
	}

	/**
	 * 中文字符的octet-stream转化成中文
	 * 
	 * @param s
	 * @return
	 */
	public static String decodeWord(String s) {
		if (!s.startsWith("=?"))
			return s;
		int i = 2;
		int j;
		if ((j = s.indexOf(63, i)) == -1)
			return s;
		i = j + 1;
		if ((j = s.indexOf(63, i)) == -1)
			return s;
		String s2 = s.substring(i, j);
		i = j + 1;
		if ((j = s.indexOf("?=", i)) == -1)
			return s;
		String s3 = s.substring(i, j);
		try {
			ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
					s3.getBytes());
			Object obj;
			if (s2.equalsIgnoreCase("B"))
				obj = new BASE64DecoderStream(bytearrayinputstream);
			else if (s2.equalsIgnoreCase("Q"))
				obj = new QDecoderStream(bytearrayinputstream);
			else
				return s;
			int k = bytearrayinputstream.available();
			byte abyte0[] = new byte[k];
			k = ((InputStream) (obj)).read(abyte0, 0, k);
			return new String(abyte0, 0, k);
		} catch (Exception ex) {
			return s;
		}
	}

	public static String ChineseStringToAscii(String s) {
		try {
			/*CharToByteConverter toByte = CharToByteConverter
					.getConverter("gb2312");*/
			byte[] orig = s.getBytes("gb2312");//toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		} catch (Exception e) {
			System.out.println(e);
			return s;
		}
	}

	/**
	 * Ascii码转换成中文字符
	 * 
	 * @param s
	 * @return
	 */
	public static String AsciiToChineseString(String s) {
		char[] orig = s.toCharArray();
		byte[] dest = new byte[orig.length];
		for (int i = 0; i < orig.length; i++)
			dest[i] = (byte) (orig[i] & 0xFF);
		try {
			/*ByteToCharConverter toChar = ByteToCharConverter
					.getConverter("gb2312");
			return new String(toChar.convertAll(dest));*/
			return new String(s.getBytes("gb2312"));
		} catch (Exception e) {
			System.out.println(e);
			return s;
		}
	}

	/**
	 * 把字符串中“;”转换成“,”，并去掉字符串结尾的逗号
	 */
	public static String convert(String str) {
		if (str == null) {
			return "";
		}
		if (str.indexOf(";") != -1) {
			str = str.replace(';', ',');
		}
		String temp = str.substring(str.length() - 1);
		if (",".equals(temp)) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	static public String convertUTF8String2Unicode(String instr)
			throws IOException {
		// byte[] strbytes = instr.getBytes();
		int charindex = instr.length();
		int actualValue;
		int inputValue;
		StringBuffer sbtemp = new StringBuffer();

		for (int i = 0; i < charindex;) {

			actualValue = -1;
			inputValue = instr.charAt(i++);

			inputValue &= 0xff;

			if ((inputValue & 0x80) == 0) {
				actualValue = inputValue;
			} else if ((inputValue & 0xF8) == 0xF0) {
				actualValue = (inputValue & 0x1f) << 18;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 12;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xF0) == 0xE0) {
				actualValue = (inputValue & 0x1f) << 12;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xE0) == 0xC0) {
				actualValue = (inputValue & 0x1f) << 6;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			}
			sbtemp.append((char) actualValue);
		}

		return sbtemp.toString();
	}

	/**
	 * 替换串替换
	 * 
	 * @param src
	 * @param oldstr
	 * @param newstr
	 * @return
	 */
	public static String replace(String src, String oldstr, String newstr) {
		StringBuffer dest = new StringBuffer();
		int beginIndex = 0;
		int endIndex = 0;
		while (true) {
			endIndex = src.indexOf(oldstr, beginIndex);
			if (endIndex >= 0) {
				dest.append(src.substring(beginIndex, endIndex));
				dest.append(newstr);
				beginIndex = endIndex + oldstr.length();
			} else {
				dest.append(src.substring(beginIndex));
				break;
			}
		}
		return dest.toString();
	}

	/**
	 * 得到指定日期加减后的日期
	 * 
	 * @return
	 */
	public static String getAddDate(String strDate, int n) {
		String strYear = strDate.substring(0, 4);
		String strMonth = strDate.substring(4, 6);
		String date = strDate.substring(6, 8);
		int year = strToInt(strYear);
		int month = strToInt(strMonth) - 1;
		int idate = strToInt(date);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, idate);
		cal.add(Calendar.DAY_OF_YEAR, n);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(cal.getTime());

	}

	/**
	 * 取得需要插入id值（适用字符型）
	 * 
	 * @param len
	 * @param maxID
	 * @return
	 * @throws Exception
	 */
	public static String getInsertID(int len, String maxID) throws Exception {

		if (maxID == null) {
			maxID = "";
			for (int i = 0; i < len - 1; i++) {
				maxID = maxID + "0";
			}
			maxID = maxID + "1";
		} else {
			int iMaxID = FormatData.strToInt(maxID);
			iMaxID = iMaxID + 1;
			String temp = iMaxID + "";
			maxID = "";
			for (int i = 0; i < len - temp.length(); i++) {
				maxID = maxID + "0";
			}
			maxID = maxID + temp;
		}
		return maxID;
	}

	/**
	 * 字段自增量（适用数字型）
	 * 
	 * @param len
	 * @param maxID
	 * @return
	 * @throws Exception
	 */
	public static int getInsertID(String maxID) throws Exception {
		int iMaxID = FormatData.strToInt(maxID);
		iMaxID = iMaxID + 1;
		return iMaxID;
	}

	/**
	 * 取得系统时间
	 * 
	 * @param mode
	 * @return
	 */
	public static String getSystemDate(String mode) {
		// 取得系统日期
		Date date = new Date();
		String strDate = FormatData.dateToStr(date, mode);
		return strDate;
	}

	/**
	 * 转换成money型（1234.0000----->1,234.00）
	 * 
	 * @param str
	 * @return
	 */
	public static String getMoneyStr(String str) {
		if (str == null || "".equals(str)) {
			str = "0";
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumFractionDigits(2);
		double aa = Double.parseDouble(str);
		String sData = nf.format(aa);
		return sData;
	}

	/**
	 * 将null字符串替换成空字符串
	 * 
	 * @param replaceStr
	 *            需要进行空替换的字符串
	 * @return 如果字符串为null,则返回空字符串
	 */
	public static String replaceNull(String replaceStr) {
		if (replaceStr == null) {
			return "";
		} else {
			return replaceStr;
		}
	}

	/**
	 * 对字符串进行编码
	 * 
	 * @param argEnCode
	 *            需要进行编码的字符串
	 * @return 经过编码的字符串
	 * @throws UnsupportedEncodingException
	 *             未支持的编码方式
	 */
	public static String enCodeTranslate(String argEnCode)
			throws UnsupportedEncodingException {

		if (argEnCode != null) {
			return new String(argEnCode.getBytes("ISO-8859-1"));
		} else {
			return "";
		}
	}
}