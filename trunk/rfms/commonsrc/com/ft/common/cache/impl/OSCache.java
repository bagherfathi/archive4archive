package com.ft.common.cache.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

import com.ft.common.cache.Cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OSCache implements Cache {
	private final Log logger = LogFactory.getLog(OSCache.class);

	// ���������Ϣ����ֹ���һ�������ڵ�groupʱoscache�׳��쳣
	private Map<String, String> groupMap;

	private GeneralCacheAdministrator cache;

	// ������ʱ�䣬��λ����
	private int refreshPeriod;

	// OSCache�����ļ�
	private Resource oscacheConfig;

	public void setRefreshPeriod(int refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	public void setOscacheConfig(Resource config) {
		this.oscacheConfig = config;
	}

	private String toString(Object key) {
		return String.valueOf(key);
	}

	public void init() {
		groupMap = new HashMap<String, String>();
		Properties props = new Properties();
		try {
			props.load(oscacheConfig.getInputStream());
			cache = new GeneralCacheAdministrator(props);
		} catch (IOException e) {
			logger.info("OSCache load oscache config error.");
			logger.error(e);
		}
	}

	public Object get(Object key) {
		try {
			return cache.getFromCache(toString(key), refreshPeriod);
		} catch (NeedsRefreshException e) {
			cache.cancelUpdate(toString(key));
			return null;
		}
	}

	public void put(Object key, Object value) {
		cache.putInCache(toString(key), value);
	}

	public void put(Object key, Object value, String group) {
		if (groupMap.get(group) == null) {
			groupMap.put(group, group);
		}

		cache.putInCache(toString(key), value, new String[] { group });
	}

	/**
	 * remove ĳ������
	 */
	public void remove(Object key) {
		cache.flushEntry(toString(key));
	}

	/**
	 * ��ջ���
	 */
	public void clear() {
		cache.flushAll();
	}

	/**
	 * ���һ����
	 */
	public void clear(String group) {
		if (groupMap.get(group) == null)
			return;

		try {
			cache.flushGroup(group);
			groupMap.remove(group);
		} catch (Exception ex) {
			logger.error("Clear group error.");
		}
	}

	public void destroy() {
		cache.destroy();
	}

	/**
	 * ���û������
	 */
	public void setCacheCapacity(int capacity) {
		this.cache.setCacheCapacity(capacity);
	}
}
