package com.ft.common.event.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;

/**
 * 日志监听器，用于记录所有收到所的消息。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class SMEventLoggerListener implements SMEventListener {
	private final static Log log = LogFactory
			.getLog(SMEventLoggerListener.class);

	public boolean isSupport(SMEvent event) {
		return true;
	}

	public void onEvent(SMEvent event) {
		log.info(event.toString());
	}

}
