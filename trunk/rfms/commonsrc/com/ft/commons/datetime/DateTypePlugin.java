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
 * ����ת���Ĳ��.
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class DateTypePlugin implements PlugIn {
	protected static Log log = LogFactory.getLog(DateTypePlugin.class);
	public static final int DEFAULT_TIME_LENGTH = 19;
	public static final int DEFAULT_DATE_LENGTH = 10;
	private static Date[] dateArr = new Date[0];
	private static String[] strArr = new String[0];

	/**
	 * ʱ���ʽ���ַ���
	 */
	private String timeFormatter = "yyyy-MM-dd HH:mm:ss";
	/**
	 * ���ڸ�ʽ���ַ���
	 */
	private String dateFormatter = "yyyy-MM-dd";
	/**
	 * ʱ���ַ�������
	 */
	private int timeLength = DEFAULT_TIME_LENGTH;
	/**
	 * �����ַ�������
	 */
	private int dateLength = DEFAULT_DATE_LENGTH;

	/**
	 * �������ڸ�ʽ
	 * 
	 * @param dateFormatter
	 */
	public void setDateFormatter(String dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	/**
	 * ����ʱ���ʽ
	 * 
	 * @param timeFormatter
	 *            ʱ���ʽ
	 */
	public void setTimeFormatter(String timeFormatter) {
		this.timeFormatter = timeFormatter;
	}

	/**
	 * ���������ַ�������
	 * 
	 * @param dateLength
	 *            ����ֵ
	 */
	public void setDateLength(int dateLength) {
		this.dateLength = dateLength;
	}

	/**
	 * �����ռ��ַ�������
	 * 
	 * @param timeLength
	 *            ����ֵ
	 */
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	/**
	 * ����ע��Ķ���
	 */
	public void destroy() {
		ConvertUtils.deregister(java.util.Date.class);
		ConvertUtils.deregister(getDateArrayClass());
	}

	/**
	 * ��ʹ���ʱ����������ת����
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
	 *���������������
	 * 
	 * @return
	 */
	public static Class getDateArrayClass() {
		return dateArr.getClass();
	}

	/**
	 * �����������͵�ת������
	 * 
	 * 
	 */
	public final class UtilDateConverter implements Converter {
		private Object defaultValue = null;
		private boolean useDefault = true;

		private DateFormat tFormatter = new SimpleDateFormat(timeFormatter);
		private DateFormat dFormatter = new SimpleDateFormat(dateFormatter);

		/**
		 * ���������ת��
		 * 
		 * @param clazz
		 *            ����������
		 * @param value
		 *            �����ַ�������
		 * @return ��������
		 */
		public Object convertDateArray(Class clazz, Object value) {
			// ���value ΪDate[] ����ֱ�ӷ���value
			if (value.getClass().equals(dateArr.getClass())) {
				return value;
			} else if (value.getClass().equals(strArr.getClass())) {
				// ���value ΪString[] ������ô��������ת��
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
				// ���������׳��쳣
				throw new ConversionException("No value specified");
			}
		}

		/**
		 * ���ڻ����ķ���
		 */
		public Object convert(Class clazz, Object value) {
			if (value == null) {
				if (useDefault) {
					return (defaultValue);
				} else {
					throw new ConversionException("No value specified");
				}
			}

			// �ж��Ƿ�Ϊ�������
			if (dateArr.getClass().equals(clazz)) {
				return convertDateArray(clazz, value);
			}

			// value �Ƿ�Ϊ��������
			if (value instanceof Date) {
				return (value);
			}

			// ��value ����ת��ΪDate����
			try {
				// ʱ���ʽ
				if (value.toString().length() == timeLength) {
					return tFormatter.parse(value.toString());
					// ���ڸ�ʽ
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
