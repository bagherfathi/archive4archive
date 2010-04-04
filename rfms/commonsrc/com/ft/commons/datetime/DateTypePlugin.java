package com.ft.commons.datetime;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;

/**
 * 日期转换的插件.
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class DateTypePlugin implements PlugIn {
	protected static Log log = LogFactory.getLog(DateTypePlugin.class);
	public static final int DEFAULT_TIME_LENGTH = 19;
	public static final int DEFAULT_DATE_LENGTH = 10;
	private static Date[] dateArr = new Date[0];
	private static String[] strArr = new String[0];

	/**
	 * 时间格式化字符串
	 */
	private String timeFormatter = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式化字符串
	 */
	private String dateFormatter = "yyyy-MM-dd";
	/**
	 * 时间字符串长度
	 */
	private int timeLength = DEFAULT_TIME_LENGTH;
	/**
	 * 日期字符串长度
	 */
	private int dateLength = DEFAULT_DATE_LENGTH;

	/**
	 * 设置日期格式
	 * 
	 * @param dateFormatter
	 */
	public void setDateFormatter(String dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	/**
	 * 设置时间格式
	 * 
	 * @param timeFormatter
	 *            时间格式
	 */
	public void setTimeFormatter(String timeFormatter) {
		this.timeFormatter = timeFormatter;
	}

	/**
	 * 设置日期字符串长度
	 * 
	 * @param dateLength
	 *            长度值
	 */
	public void setDateLength(int dateLength) {
		this.dateLength = dateLength;
	}

	/**
	 * 设置日间字符串长度
	 * 
	 * @param timeLength
	 *            长度值
	 */
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	/**
	 * 销毁注册的对象
	 */
	public void destroy() {
		ConvertUtils.deregister(java.util.Date.class);
		ConvertUtils.deregister(getDateArrayClass());
	}

	/**
	 * 初使插件时，加载日期转换类
	 * 
	 * @param servlet
	 *            ActionServlet
	 * @param moduleConfig
	 *            ModuleConfig
	 * 
	 * @throws ServletException
	 *             ServletException
	 */
	public void init(ActionServlet servlet, ModuleConfig moduleConfig)
			throws ServletException {
		UtilDateConverter converter = new UtilDateConverter();
		ConvertUtils.register(converter, java.util.Date.class);
		ConvertUtils.register(converter, getDateArrayClass());
	}

	/**
	 *返回日期数组对象
	 * 
	 * @return
	 */
	public static Class getDateArrayClass() {
		return dateArr.getClass();
	}

	/**
	 * 进行日期类型的转换对象
	 * 
	 * 
	 */
	public final class UtilDateConverter implements Converter {
		private Object defaultValue = null;
		private boolean useDefault = true;

		private DateFormat tFormatter = new SimpleDateFormat(timeFormatter);
		private DateFormat dFormatter = new SimpleDateFormat(dateFormatter);

		/**
		 * 日期数组的转换
		 * 
		 * @param clazz
		 *            日期数据类
		 * @param value
		 *            日期字符串数组
		 * @return 日期数组
		 */
		public Object convertDateArray(Class clazz, Object value) {
			// 如果value 为Date[] 类型直接返回value
			if (value.getClass().equals(dateArr.getClass())) {
				return value;
			} else if (value.getClass().equals(strArr.getClass())) {
				// 如果value 为String[] 类型那么进行类型转换
				String[] values = (String[]) value;
				Date[] result = new Date[values.length];

				try {
					for (int i = 0; i < values.length; i++) {
						String item = values[i];
						if (item.length() == timeLength) {
							result[i] = tFormatter.parse(item);
						} else if (value.toString().length() == dateLength) {
							result[i] = dFormatter.parse(item);
						} else {
							log.error("Not found suited formatter.");
							result[i] = null;
						}
					}

					return result;
				} catch (Exception e) {
					if (useDefault) {
						return (defaultValue);
					} else {
						throw new ConversionException(e);
					}
				}
			} else {
				// 其它类型抛出异常
				throw new ConversionException("No value specified");
			}
		}

		/**
		 * 日期换换的方法
		 */
		public Object convert(Class clazz, Object value) {
			if (value == null) {
				if (useDefault) {
					return (defaultValue);
				} else {
					throw new ConversionException("No value specified");
				}
			}

			// 判断是否为数组对象
			if (dateArr.getClass().equals(clazz)) {
				return convertDateArray(clazz, value);
			}

			// value 是否为日期类型
			if (value instanceof Date) {
				return (value);
			}

			// 对value 进行转换为Date类型
			try {
				// 时间格式
				if (value.toString().length() == timeLength) {
					return tFormatter.parse(value.toString());
					// 日期格式
				} else if (value.toString().length() == dateLength) {
					return dFormatter.parse(value.toString());
				} else {
					log.error("Not found suited formatter.");
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();

				if (useDefault) {
					return (defaultValue);
				} else {
					throw new ConversionException(e);
				}
			}
		}
	}
}
