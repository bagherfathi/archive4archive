package com.ft.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Struts ����Ϣ��������࣬��Struts�б�����Ϣ�ķ�����
 *
 */
public class ActionMessagesHelper {
	/**
	 * ������Ϣ��ָ���Ŀռ�
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 * @param scope ����ķ�Χ(request ,session)
	 */
    public static void saveMessage(
        HttpServletRequest request, String key, String scope) {
        saveMessage(request, key, null, scope);
    }
	/**
	 * ������Ϣ��ָ����һ��Session��
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 */
    public static void saveSessionMessage(
        HttpServletRequest request, String key) {
        saveMessage(request, key, null, "session");
    }
	/**
	 * ������Ϣ��ָ����һ��Request������
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 */
    public static void saveRequestMessage(
        HttpServletRequest request, String key) {
        saveMessage(request, key, null, "request");
    }
	/**
	 * ������Ϣ��ָ����һ��Request������
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 * @param args ��Ϣ����
	 */
    public static void saveSessionMessage(
        HttpServletRequest request, String key, Object[] args) {
        saveMessage(request, key, args, "session");
    }
	/**
	 * ������Ϣ��ָ����һ��Request������
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 * @param args ��Ϣ����
	 */
    public static void saveRequestMessage(
        HttpServletRequest request, String key, Object[] args) {
        saveMessage(request, key, args, "request");
    }
	/**
	 * ������Ϣ��ָ����һ��Request������
	 * @param request HttpServletRequest
	 * @param key  ��Ϣ��Key 
	 * @param args ��Ϣ����
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
