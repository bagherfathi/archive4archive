package com.ft.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * ʱ���ʽ���ַ���
	 */
	public static final String timeFormatter = "yyyy-MM-dd hh:mm:ss";

	/**
	 * ���ڸ�ʽ���ַ���
	 */
	public static final String dateFormatter = "yyyy-MM-dd";

	/**
	 * ���ڸ�ʽ���ַ���
	 */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * ���ʽ���ַ���
	 */
	public static final String YEAR_PATTERN = "yyyy";

	/**
	 * �¸�ʽ���ַ���
	 */
	public static final String MONTHOFYEAR_PATTERN = "MM";

	/**
	 * �ո�ʽ���ַ���
	 */
	public static final String DAYOFMONTH_PATTERN = "dd";

	/**
	 * ����һ��ĺ�����
	 */
	public static final long MILLSECOND_OF_DAY = 86400000;

	/**
	 * ���ݴ�������ڣ���ת���ɵ�������ʽ������ת����������ַ���
	 * 
	 * @param ldate
	 *            - ����
	 * @param pattern
	 *            - �����ֵ��Χ��java.text.SimpleDateFormat��˵��
	 * @see java.text.SimpleDateFormat
	 * @return String ת����������ַ���
	 */
	public static String format(Date ldate, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(ldate);
	}

	/**
	 * ���ݴ�����ַ�������ת���ɵ�������ʽ������ת���������
	 * 
	 * @param ldate
	 *            - ����
	 * @param pattern
	 *            - �����ֵ��Χ��java.text.SimpleDateFormat��˵��
	 * @see java.text.SimpleDateFormat
	 * @return Date ת���������
	 * @throws ParseException
	 */
	public static Date parse(String ldate, String pattern)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(ldate);
	}

	public static long date2MysqlDate(Date date) {
		return date.getTime() / 1000;
	}

	public static Date getDateDf(String name, String df) {
		return getDate(name, df, null);
	}

	public static Date getDate(String name, String df, Date defaultValue) {
		if (name == null) {
			return defaultValue;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(df);

		try {
			return formatter.parse(name);
		} catch (ParseException e) {
		}

		return defaultValue;
	}

	public static String dateFormate(Date date, String formate) {
		if (date != null) {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					formate);
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * ���ݴ�������ڣ���ת���ɵ�������ʽ������ת����������ַ�������
	 * 
	 * @param ldate
	 *            - ����
	 * @return String[] ת����������ַ������� <br>
	 *         Examples: ldate="2006-10-01";���ص���String{"2006","10","01"};
	 * 
	 */
	public static String[] formatYMD(Date ldate) {
		String[] yMds = new String[3];
		yMds[0] = DateUtil.format(ldate, DateUtil.YEAR_PATTERN);
		yMds[1] = DateUtil.format(ldate, DateUtil.MONTHOFYEAR_PATTERN);
		yMds[2] = DateUtil.format(ldate, DateUtil.DAYOFMONTH_PATTERN);
		return yMds;
	}

	/**
	 * ��ʽ��������,���ڸ�ʽΪ yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            ���ϸ�ʽ���ַ���
	 * @return ��ʽ�������
	 */
	public static Date parserLong(String strDate) {
		Date result = null;
		try {
			result = DateUtil.parse(strDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * ��ʽ��������,���ڸ�ʽΪyyyy-MM-dd
	 * 
	 * @param strDate
	 *            ���ϸ�ʽ���ַ���
	 * @return ��ʽ�������
	 * @throws ParseException
	 */
	public static Date parserShort(String strDate) {
		Date result = null;
		try {
			result = DateUtil.parse(strDate, "yyyy-MM-dd");
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �õ���ǰ�·ݵ����ڿ�ʼ����
	 * 
	 * @param currentDate
	 *            ��ǰ����
	 * @return ��ǰ�·ݵ����ڿ�ʼ����
	 */
	public static Date getCurBeginCycleDate(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 1) + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 00:00:00";
		return DateUtil.parserLong(dateStr);
	}

	/**
	 * ȡ�õ�ǰ���ڵ����ڽ�������
	 * 
	 * @param currentDate
	 *            ��ǰ����
	 * @return ��ǰ���ڵ����ڽ�������
	 */
	public static Date getCurEndCycleDate(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 2) + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 23:59:59";
		calendar.setTime(DateUtil.parserLong(dateStr));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * �õ���nextCycle���ڵ��·�
	 * 
	 * @param currentDate
	 *            ��ǰ����
	 * @param nextCycle
	 *            ��nextCycle����
	 * @return ��nextCycle����
	 */
	public static Date getNextCycleDate(Date currentDate, long nextCycle) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		nextCycle++;
		String month = (calendar.get(Calendar.MONTH) + nextCycle) + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 00:00:00";
		return DateUtil.parserLong(dateStr);
	}

	/**
	 * ��ȡ��ʼ�ͽ�������֮��ļ������
	 * 
	 * @param startDate
	 *            ��ʼ����
	 * @param endDate
	 *            ��������
	 * @param roundingMode
	 *            ���뷽ʽ �� BigDecimal�Ķ���
	 * @return �����������
	 */
	public static long getDaysBetweenDate(Date startDate, Date endDate,
			int roundingMode) {

		BigDecimal bStart = new BigDecimal(startDate.getTime());
		BigDecimal bEnd = new BigDecimal(endDate.getTime());
		BigDecimal bUnit = new BigDecimal(MILLSECOND_OF_DAY);
		return (bEnd.subtract(bStart)).divide(bUnit, roundingMode).longValue();
	}

	/**
	 * ��ȡ��������֮�������·���
	 * 
	 * @param startDate
	 *            ��ʼ����
	 * @param endDate
	 *            ��������
	 * @param flag
	 *            false Ϊȫ����
	 * @return ���ص��·���
	 */
	public static long getMonthsBetweenDate(Date startDate, Date endDate,
			boolean flag) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		if (endDate.before(startDate)) {
			cal1.setTime(endDate);
			cal2.setTime(startDate);
		}

		cal1.clear(Calendar.MILLISECOND);
		cal1.clear(Calendar.SECOND);
		cal1.clear(Calendar.MINUTE);
		cal1.clear(Calendar.HOUR_OF_DAY);

		cal2.clear(Calendar.MILLISECOND);
		cal2.clear(Calendar.SECOND);
		cal2.clear(Calendar.MINUTE);
		cal2.clear(Calendar.HOUR_OF_DAY);

		return getMonthsBetweenDate(cal1, cal2, flag);

	}

	/**
	 * ��ȡ��������֮�������·���
	 * 
	 * @param cal1
	 *            ��ʼ����
	 * @param cal2
	 *            ��������
	 * @param flag
	 *            false Ϊȫ����
	 * @return ���ص��·���
	 */
	public static long getMonthsBetweenDate(Calendar cal1, Calendar cal2,
			boolean flag) {
		long month = 0L;
		while (cal1.before(cal2)) {
			cal1.add(Calendar.MONTH, 1);
			month++;
			if (flag) {
				if ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
						&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
						&& (cal1.get(Calendar.DAY_OF_MONTH) > cal2
								.get(Calendar.DAY_OF_MONTH))) {
					month--;
					break;
				}
				if ((cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH))
						&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))

				) {
					month--;
					break;
				}
			}
		}
		return month;
	}

	/**
	 * 
	 * ����ָ�����ڵ���һ��
	 * 
	 * @param date
	 *            ��Ҫת��������
	 * @param field
	 *            ��Ҫ���ĵ���-Ŀǰֻ���·���������
	 * @return
	 */
	public static long getDateField(Date date, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (field == Calendar.MONTH)
			return cal.get(field) + 1;
		else
			return cal.get(field);

	}

}
