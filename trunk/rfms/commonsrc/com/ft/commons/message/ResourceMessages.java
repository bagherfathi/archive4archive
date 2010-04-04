package com.ft.commons.message;

import org.apache.commons.resources.Resources;

import java.text.MessageFormat;

/**
 * 获取资源消息
 */
public class ResourceMessages {
	Resources resources;

	/**
	 * Creates a new ResourceMessages object.
	 * 
	 * @param resources
	 *            DOCUMENT ME!
	 */
	protected ResourceMessages(Resources resources) {
		this.resources = resources;
	}

	/**
	 * 获取消息
	 * 
	 * @param code
	 * @param args
	 * 
	 * @return
	 */
	public String getMessage(String code, Object[] args) {
		String value = resources.getString(code, null, null);

		if (value == null) {
			return code;
		} else {
			return MessageFormat.format(value, args);
		}
	}
}
