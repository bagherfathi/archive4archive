package com.ft.common.event;
/**
 * �¼��������������¼�����Ȥ��ͨ������ӿ�ʵ�־�����յ��Ķ�����
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public interface SMEventListener {
	/**
	 * �жϼ������Ƿ�Ҫ��������¼�
	 * @param event �¼�
	 * @return ����true��Ҫ��ִ�� onEvent�ķ���
	 */
	public boolean isSupport(SMEvent event);

	/**
	 * �յ��¼�ʱ��ִ�еľ��巽����
	 * @param event
	 */
	public void onEvent(SMEvent event);

}
