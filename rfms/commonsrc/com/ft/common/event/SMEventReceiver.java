package com.ft.common.event;
/**
 * �¼����սӿڣ����ڽ����¼��������ʵ�ֿ��Ե�JMS�����Զ������Ϣ����
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public interface SMEventReceiver  {
	/**
	 * ������Ϣ
	 * @param event ��Ϣ�¼�
	 */
	public void receive(final SMEvent event);
}
