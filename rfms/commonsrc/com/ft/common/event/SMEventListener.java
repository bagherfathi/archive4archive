package com.ft.common.event;
/**
 * 事件监听器，对于事件感兴趣的通过这个接口实现具体接收到的动作。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public interface SMEventListener {
	/**
	 * 判断监听器是否要处理这个事件
	 * @param event 事件
	 * @return 返回true则要求执行 onEvent的方法
	 */
	public boolean isSupport(SMEvent event);

	/**
	 * 收到事件时，执行的具体方法。
	 * @param event
	 */
	public void onEvent(SMEvent event);

}
