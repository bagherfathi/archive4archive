package com.ft.commons.datetime;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间段对象 这个类是描述了一个开始时间和结束时间的对象.
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class TimeSegment implements java.io.Serializable {
	private static final long serialVersionUID = -794116109980177593L;

	/**
	 * 开始的时间.
	 */
	protected Date beginDate;

	/**
	 * 结束的时间.
	 */
	protected Date endDate;

	/**
	 * Creates a new TimeSegment object.
	 */
	public TimeSegment() {
		beginDate = new Date();
		endDate = new Date();
		endDate.setTime(endDate.getTime() + (1000 * 60 * 60 * 24));
	}

	/**
	 * Creates a new TimeSegment object.
	 * 
	 * @param begin
	 *            开始时间.
	 * @param date
	 *            结束时间.
	 */
	public TimeSegment(Date begin, Date date) {
		this.beginDate = begin;
		this.endDate = date;
	}

	/**
	 * 
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 
	 * @param beginDate
	 *            The beginDate to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 
	 * @param endDate
	 *            The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		if (endDate != null) {
			this.endDate = lastDate(endDate);
		}
	}

	/**
	 * 得到一个时间段对象的数组.
	 * 
	 * @return
	 */
	public Date[] getDates() {
		return new Date[] { this.beginDate, this.endDate };
	}

	/**
	 * 得到静态的时间段对象.
	 * 
	 * @return
	 */
	public static TimeSegment getDaySegment() {
		Calendar day = Calendar.getInstance();
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);

		TimeSegment result = new TimeSegment();
		result.getBeginDate().setTime(day.getTime().getTime());
		day.add(Calendar.DAY_OF_YEAR, 1);
		result.getEndDate().setTime(day.getTime().getTime());

		return result;
	}

	/**
	 * 构造出结束时间.
	 * 
	 * @param date
	 * 
	 * @return Date
	 */
	public static Date lastDate(Date date) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.set(Calendar.HOUR_OF_DAY, 23);
		day.set(Calendar.MINUTE, 59);
		day.set(Calendar.SECOND, 59);
		day.set(Calendar.MILLISECOND, 99);

		return day.getTime();
	}

	/**
	 * 根据结束的时间得到开始的时间.
	 * 
	 * @param date
	 * @param unit
	 * @param amount
	 * 
	 * @return
	 */
	public static Date add(Date date, int unit, int amount) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.add(unit, amount);

		return day.getTime();
	}

	/**
	 * 初始化一个时间段对象.
	 * 
	 * @param value
	 * 
	 * @return
	 */
	public static TimeSegment getInstance(String value) {
		int unit = value.charAt(value.length() - 1);

		switch (unit) {
		case 'D':
			unit = Calendar.DAY_OF_YEAR;

			break;

		case 'M':
			unit = Calendar.MONTH;

			break;

		case 'Y':
			unit = Calendar.YEAR;

			break;
		}

		int amount = Integer.parseInt(value.substring(0, value.length() - 1));
		TimeSegment segment = getDaySegment();

		segment.beginDate = add(segment.endDate, unit, -amount);

		return segment;
	}
}
