package com.ft.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 时间格式化字符串
	 */
	public static final String timeFormatter = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 日期格式化字符串
	 */
	public static final String dateFormatter = "yyyy-MM-dd";

	/**
	 * 日期格式化字符串
	 */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * 年格式化字符串
	 */
	public static final String YEAR_PATTERN = "yyyy";

	/**
	 * 月格式化字符串
	 */
	public static final String MONTHOFYEAR_PATTERN = "MM";

	/**
	 * 日格式化字符串
	 */
	public static final String DAYOFMONTH_PATTERN = "dd";

	/**
	 * 定义一天的毫妙数
	 */
	public static final long MILLSECOND_OF_DAY = 86400000;

	/**
	 * 根据传入的日期，欲转换成的日期样式，返回转换后的日期字符串
	 * 
	 * @param ldate
	 *            - 日期
	 * @param pattern
	 *            - 具体的值范围见java.text.SimpleDateFormat的说明
	 * @see java.text.SimpleDateFormat
	 * @return String 转换后的日期字符串
	 */
	public static String format(Date ldate, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(ldate);
	}

	/**
	 * 根据传入的字符串，欲转换成的日期样式，返回转换后的日期
	 * 
	 * @param ldate
	 *            - 日期
	 * @param pattern
	 *            - 具体的值范围见java.text.SimpleDateFormat的说明
	 * @see java.text.SimpleDateFormat
	 * @return Date 转换后的日期
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
	 * 根据传入的日期，欲转换成的日期样式，返回转换后的日期字符串数组
	 * 
	 * @param ldate
	 *            - 日期
	 * @return String[] 转换后的日期字符串数组 <br>
	 *         Examples: ldate="2006-10-01";返回的是String{"2006","10","01"};
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
	 * 格式化长日期,日期格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期
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
	 * 格式化短日期,日期格式为yyyy-MM-dd
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期
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
	 * 得到当前月份的周期开始日期
	 * 
	 * @param currentDate
	 *            当前日期
	 * @return 当前月份的周期开始日期
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
	 * 取得当前周期的周期结束日期
	 * 
	 * @param currentDate
	 *            当前日期
	 * @return 当前周期的周期结束日期
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
	 * 得到下nextCycle周期的月份
	 * 
	 * @param currentDate
	 *            当前日期
	 * @param nextCycle
	 *            下nextCycle周期
	 * @return 下nextCycle周期
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
	 * 获取开始和结束日期之间的间隔日期
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param roundingMode
	 *            舍入方式 见 BigDecimal的定义
	 * @return 相隔的日期数
	 */
	public static long getDaysBetweenDate(Date startDate, Date endDate,
			int roundingMode) {

		BigDecimal bStart = new BigDecimal(startDate.getTime());
		BigDecimal bEnd = new BigDecimal(endDate.getTime());
		BigDecimal bUnit = new BigDecimal(MILLSECOND_OF_DAY);
		return (bEnd.subtract(bStart)).divide(bUnit, roundingMode).longValue();
	}

	/**
	 * 获取两个日期之间相差的月份数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param flag
	 *            false 为全月舍
	 * @return 返回的月份数
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
	 * 获取两个日期之间相差的月份数
	 * 
	 * @param cal1
	 *            开始日期
	 * @param cal2
	 *            结束日期
	 * @param flag
	 *            false 为全月舍
	 * @return 返回的月份数
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
	 * 返回指定日期的下一月
	 * 
	 * @param date
	 *            需要转换的日期
	 * @param field
	 *            需要更改的域-目前只对月份域起作用
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
