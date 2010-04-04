package com.ft.common.event;

/**
 * ϵͳ�����¼����࣬����ϵͳ�������ڲ���Ϣ�Ĵ��ݡ�
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class SMEvent  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String type; //���� ��һ��ָ����
	private String action; //����,�¼������Ķ���
	private String key; //�¼���ֵ 
	/**
	 * ���췽����
	 *
	 */
	public SMEvent(){
		super();
	}
	/**
	 * ���췽����
	 * @param type �������� ��
	 * @param action �¼������Ķ�����
	 * @param key �¼���ֵ ��
	 */
	public SMEvent(String type, String action, String key) {
		super();
		this.type = type;
		this.action = action;
		this.key = key;
	}
	/**
	 * �¼�������
	 * @return Ӧ���Զ�����¼�������
	 */
	public String getAction() {
		return action;
	}
	/**
	 * ���� �¼�������
	 * @param action �¼�������
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * �¼��������������� ��
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * �����¼������ļ�ֵ�� 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * �õ��¼����� ��
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * �����¼����� ��
	 * @param type 
	 * 			�¼����� ��
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return "type=" + this.type +", action="+this.action +",key="+key; 
	}
}
