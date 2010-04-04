package com.ft.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Struts 的消息保存帮助类，简化Struts中保存消息的方法。
 *
 */
public class ActionMessagesHelper {
	/**
	 * 保存消息到指定的空间
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 * @param scope 保存的范围(request ,session)
	 */
    public static void saveMessage(
        HttpServletRequest request, String key, String scope) {
        saveMessage(request, key, null, scope);
    }
	/**
	 * 保存消息到指定到一个Session中
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 */
    public static void saveSessionMessage(
        HttpServletRequest request, String key) {
        saveMessage(request, key, null, "session");
    }
	/**
	 * 保存消息到指定到一个Request请求中
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 */
    public static void saveRequestMessage(
        HttpServletRequest request, String key) {
        saveMessage(request, key, null, "request");
    }
	/**
	 * 保存消息到指定到一个Request请求中
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 * @param args 消息参数
	 */
    public static void saveSessionMessage(
        HttpServletRequest request, String key, Object[] args) {
        saveMessage(request, key, args, "session");
    }
	/**
	 * 保存消息到指定到一个Request请求中
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 * @param args 消息参数
	 */
    public static void saveRequestMessage(
        HttpServletRequest request, String key, Object[] args) {
        saveMessage(request, key, args, "request");
    }
	/**
	 * 保存消息到指定到一个Request请求中
	 * @param request HttpServletRequest
	 * @param key  消息的Key 
	 * @param args 消息参数
	 */
    public static void saveMessage(
        HttpServletRequest request, String key, Object[] args, String scope) {
        ActionMessages msgs = new ActionMessages();
        ActionMessage msg = null;

        if (args == null) {
            msg = new ActionMessage(key);
        } else {
            msg = new ActionMessage(key, args);
        }

        msgs.add(key, msg);

        if ("session".equals(scope)) {
            request.getSession().setAttribute(Globals.MESSAGE_KEY, msgs);
        } else {
            request.setAttribute(Globals.MESSAGE_KEY, msgs);
        }

        return;
    }

  
}
