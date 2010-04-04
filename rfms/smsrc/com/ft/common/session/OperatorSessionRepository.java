package com.ft.common.session;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户会话管理
 * 
 * @spring.bean id = "opSessionRepository"
 */
public class OperatorSessionRepository {
    private Map userSessions;

    public OperatorSessionRepository() {
        userSessions = new HashMap();
    }

    /**
     * 添加用户会话
     * 
     * @param sessionId
     *                会话key
     * @param userSession
     *                用户会话
     */
    @SuppressWarnings("unchecked")
	public void addUserSession(OperatorSession userSession) {
        this.userSessions.put(userSession.getSessionId(), userSession);
    }

    /**
     * 删除用户会话
     * 
     * @param sessionId
     *                会话key
     */
    public void removeUserSession(String sessionId) {
        if (userSessions.containsKey(sessionId)) {
            userSessions.remove(sessionId);
        }
    }

    /**
     * 获取用户会话
     * 
     * @param sessionId
     *                会话key
     * @return
     */
    public OperatorSession getUserSession(String sessionId) {
        if (userSessions.containsKey(sessionId)) {
            return (OperatorSession) userSessions.get(sessionId);
        } else {
            return null;
        }
    }

    /**
     * 更新用户会话
     * 
     * @param sessionId
     * @param userSession
     */
    @SuppressWarnings("unchecked")
	public void updateUserSession(OperatorSession userSession) {
        if (userSessions.containsKey(userSession.getSessionId())) {
            userSessions.put(userSession.getSessionId(), userSession);
        }
    }

    public static final String getBeanName() {
        return "opSessionRepository";
    }
}
