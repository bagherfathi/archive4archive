package com.renhenet.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanUtils {

	private static Log log = LogFactory.getLog(BeanUtils.class);

	public static void copyProperties(Object dest, Object source) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, source);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			log.error(e);
		}
	}
}
