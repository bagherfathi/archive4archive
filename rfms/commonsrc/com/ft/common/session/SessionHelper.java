 
package com.ft.common.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ҳ��Ự�����࣬ʵ��ͳһ�ĻỰ����
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class SessionHelper {
    private static final String GLOBAL_SESSION_NAME = "GlobalSession";
    
    /**
     * �ڻỰ������һ��������
     * @param request     HTTP��������
     * @param paramName   �������ơ�
     * @param paramValue  ����ֵ��
     */
    public static void addParameter(HttpServletRequest request,String paramName,Object paramValue){
        HttpSession session = request.getSession();
        addParameter(session,paramName,paramValue);
    }
    
    /**
     * �ӻỰ�л�ȡ������
     * @param request      HTTP��������
     * @param paramName    �������ơ�
     * @return
     */
    public static Object getParameter(HttpServletRequest request,String paramName){
        HttpSession session = request.getSession();
        return getParameter(session,paramName);
    }
    
    /**
     * �ڻỰ������һ��������
     * @param session      HTTP�Ự�����
     * @param paramName    �������ơ�
     * @param paramValue   ����ֵ��
     */
    public static void addParameter(HttpSession session,String paramName,Object paramValue){
        Map<String,Object> parameters = getParameters(session);
        parameters.put(paramName,paramValue);
        updateParameters(session,parameters);
    }
    
    /**
     * �ӻỰ�л�ȡ������
     * @param session      HTTP�Ự�����
     * @param paramName    �������ơ�
     * @return
     */
    public static Object getParameter(HttpSession session,String paramName){
        Map<String,Object> parameters = getParameters(session);
        
        return parameters.get(paramName);
    }
    
    /**
     * �ӻỰ��ɾ��һ��������
     * @param request      HTTP��������
     * @param paramName    �������ơ�
     */
    public static void removeParameter(HttpServletRequest request,String paramName){
        HttpSession session = request.getSession();
        removeParameter(session,paramName);
    }
    
    /**
     * �ӻỰ��ɾ��һ��������
     * @param session     HTTP�Ự�����
     * @param paramName   �������ơ�
     */
    public static void removeParameter(HttpSession session,String paramName){
        Map<String, Object> parameters = getParameters(session);
        if(parameters.containsKey(paramName)){
            parameters.remove(paramName);
            updateParameters(session,parameters);
        }
    }
    
    /**
     * ����Ự��
     * @param request    HTTP��������
     */
    public static void clear(HttpServletRequest request){
        HttpSession session = request.getSession();
        clear(session);
    }
    
    /**
     * ����Ự��
     * @param session    HTTP�Ự�����
     */
    public static void clear(HttpSession session){
        Map<String,Object> parameters = getParameters(session);
        parameters.clear();
        parameters = null;
        session.removeAttribute(GLOBAL_SESSION_NAME);
    }
    
    /**
     * ��HTTP�Ự�л�ȡȫ�ֲ�����
     * @param session    HTTP�Ự�����
     * @return
     */
    @SuppressWarnings("unchecked")
	private static Map<String,Object> getParameters(HttpSession session){
        Map<String,Object> parameters = (Map<String, Object>)session.getAttribute(GLOBAL_SESSION_NAME);
        
        if(parameters == null){
            parameters = new HashMap<String,Object>();
        }
        
        return parameters;
    }
    
    /**
     * ���»Ự�е�ȫ�ֲ�����
     * @param session      HTTP�Ự�����
     * @param parameters   ȫ�ֲ�����
     */
    private static void updateParameters(HttpSession session,Map<String, Object> parameters){
        session.setAttribute(GLOBAL_SESSION_NAME, parameters);
    }
}
