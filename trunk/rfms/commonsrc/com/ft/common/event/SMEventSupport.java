package com.ft.common.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �¼�֧�ֽӿڣ���ϵͳ�յ���Ϣ�󣬵��ü�����������Ϣ����
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */

public class SMEventSupport {

	final static Log log = LogFactory.getLog(SMEventSupport.class);

	/**
	 * �������б�
	 */

	private List<SMEventListener> listerners = new ArrayList<SMEventListener>();

	/**
	 * �����¼�������
	 * 
	 * @param event
	 *            SSO�¼�
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
	 * ���ؼ������б�
	 * 
	 * @return �������б�
	 */
	public List<SMEventListener> getListerners() {
		return listerners;
	}

	/**
	 * ���ü������б�
	 * 
	 * @param listerners
	 *            �������б�
	 */
	public void setListerners(List<SMEventListener> listerners) {
		this.listerners = listerners;
	}

	/**
	 * ��Ӽ������������б�
	 * 
	 * @param listener
	 *            SSO�¼�������
	 */
	public void registerListener(SMEventListener listener) {
		this.listerners.add(listener);
	}

}
