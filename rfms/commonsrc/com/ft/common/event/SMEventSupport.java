package com.ft.common.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 事件支持接口，在系统收到消息后，调用监听器进行消息处理。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */

public class SMEventSupport {

	final static Log log = LogFactory.getLog(SMEventSupport.class);

	/**
	 * 监听器列表
	 */

	private List<SMEventListener> listerners = new ArrayList<SMEventListener>();

	/**
	 * 触发事件监听器
	 * 
	 * @param event
	 *            SSO事件
	 */
	public synchronized void fireEvent(SMEvent event) {
		for (Iterator<SMEventListener> iter = listerners.iterator(); iter.hasNext();) {
			SMEventListener element = iter.next();
			if (element.isSupport(event)) {
				try {
					element.onEvent(event);
				} catch (Throwable e) {
					log.error(e.getMessage(), e);
				} finally {
				}
			}
		}
	}

	/**
	 * 返回监听器列表
	 * 
	 * @return 监听器列表
	 */
	public List<SMEventListener> getListerners() {
		return listerners;
	}

	/**
	 * 设置监听器列表
	 * 
	 * @param listerners
	 *            监听器列表
	 */
	public void setListerners(List<SMEventListener> listerners) {
		this.listerners = listerners;
	}

	/**
	 * 添加监听器到监听列表
	 * 
	 * @param listener
	 *            SSO事件监听器
	 */
	public void registerListener(SMEventListener listener) {
		this.listerners.add(listener);
	}

}
