package com.ft.common.event;
/**
 * �¼������ӿڣ������¼��ķ�����
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public interface SMEventDispatcher {
	/**
	 * �����¼������������ʵ�ֿ��Ը��ݲ�ͬ����Ϣ�ӿ���ʵ�֣�<p>
	 * ������JMS�����Զ�����¼������С�
	 * @param smEvent �¼���
	 */
    public void dispatcher(final SMEvent smEvent);
    
}
