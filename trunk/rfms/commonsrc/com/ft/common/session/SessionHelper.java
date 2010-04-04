 
package com.ft.common.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面会话帮助类，实现统一的会话管理。
 * 
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class SessionHelper {
    private static final String GLOBAL_SESSION_NAME = "GlobalSession";
    
    /**
     * 在会话中增加一个参数。
     * @param request     HTTP请求句柄。
     * @param paramName   参数名称。
     * @param paramValue  参数值。
     */
    public static void addParameter(HttpServletRequest request,String paramName,Object paramValue){
        HttpSession session = request.getSession();
        addParameter(session,paramName,paramValue);
    }
    
    /**
     * 从会话中获取参数。
     * @param request      HTTP请求句柄。
     * @param paramName    参数名称。
     * @return
     */
    public static Object getParameter(HttpServletRequest request,String paramName){
        HttpSession session = request.getSession();
        return getParameter(session,paramName);
    }
    
    /**
     * 在会话中增加一个参数。
     * @param session      HTTP会话句柄。
     * @param paramName    参数名称。
     * @param paramValue   参数值。
     */
    public static void addParameter(HttpSession session,String paramName,Object paramValue){
        Map<String,Object> parameters = getParameters(session);
        parameters.put(paramName,paramValue);
        updateParameters(session,parameters);
    }
    
    /**
     * 从会话中获取参数。
     * @param session      HTTP会话句柄。
     * @param paramName    参数名称。
     * @return
     */
    public static Object getParameter(HttpSession session,String paramName){
        Map<String,Object> parameters = getParameters(session);
        
        return parameters.get(paramName);
    }
    
    /**
     * 从会话中删除一个参数。
     * @param request      HTTP请求句柄。
     * @param paramName    参数名称。
     */
    public static void removeParameter(HttpServletRequest request,String paramName){
        HttpSession session = request.getSession();
        removeParameter(session,paramName);
    }
    
    /**
     * 从会话中删除一个参数。
     * @param session     HTTP会话句柄。
     * @param paramName   参数名称。
     */
    public static void removeParameter(HttpSession session,String paramName){
        Map<String, Object> parameters = getParameters(session);
        if(parameters.containsKey(paramName)){
            parameters.remove(paramName);
            updateParameters(session,parameters);
        }
    }
    
    /**
     * 清除会话。
     * @param request    HTTP请求句柄。
     */
    public static void clear(HttpServletRequest request){
        HttpSession session = request.getSession();
        clear(session);
    }
    
    /**
     * 清除会话。
     * @param session    HTTP会话句柄。
     */
    public static void clear(HttpSession session){
        Map<String,Object> parameters = getParameters(session);
        parameters.clear();
        parameters = null;
        session.removeAttribute(GLOBAL_SESSION_NAME);
    }
    
    /**
     * 从HTTP会话中获取全局参数。
     * @param session    HTTP会话句柄。
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
     * 更新会话中的全局参数。
     * @param session      HTTP会话句柄。
     * @param parameters   全局参数。
     */
    private static void updateParameters(HttpSession session,Map<String, Object> parameters){
        session.setAttribute(GLOBAL_SESSION_NAME, parameters);
    }
}
