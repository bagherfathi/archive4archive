package com.ft.common.event;

/**
 * 系统管理事件基类，用于系统管理中内部消息的传递。
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class SMEvent  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String type; //类型 ，一般指定类
	private String action; //动作,事件发生的动作
	private String key; //事件的值 
	/**
	 * 构造方法。
	 *
	 */
	public SMEvent(){
		super();
	}
	/**
	 * 构造方法。
	 * @param type 数据类型 。
	 * @param action 事件发生的动作。
	 * @param key 事件的值 。
	 */
	public SMEvent(String type, String action, String key) {
		super();
		this.type = type;
		this.action = action;
		this.key = key;
	}
	/**
	 * 事件动作。
	 * @return 应用自定义的事件动作。
	 */
	public String getAction() {
		return action;
	}
	/**
	 * 设置 事件动作。
	 * @param action 事件动作。
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * 事件触发的其它类型 。
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 设置事件触发的键值。 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 得到事件类型 。
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置事件类型 。
	 * @param type 
	 * 			事件类型 。
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return "type=" + this.type +", action="+this.action +",key="+key; 
	}
}
