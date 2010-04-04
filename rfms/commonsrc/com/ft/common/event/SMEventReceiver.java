package com.ft.common.event;
/**
 * 事件接收接口，用于接收事件，具体的实现可以的JMS或是自定义的消息队列
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public interface SMEventReceiver  {
	/**
	 * 接收消息
	 * @param event 消息事件
	 */
	public void receive(final SMEvent event);
}
