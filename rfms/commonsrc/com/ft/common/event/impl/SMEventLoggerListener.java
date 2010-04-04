package com.ft.common.event.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;

/**
 * ��־�����������ڼ�¼�����յ�������Ϣ��
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
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
