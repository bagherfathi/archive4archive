package com.ft.common.event;
/**
 * 事件发布接口，用于事件的发布。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public interface SMEventDispatcher {
	/**
	 * 发布事件方法。具体的实现可以根据不同的消息接口来实现，<p>
	 * 可以是JMS或是自定义的事件队列中。
	 * @param smEvent 事件。
	 */
    public void dispatcher(final SMEvent smEvent);
    
}
