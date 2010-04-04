package com.ft.common.session;

import java.util.HashMap;
import java.util.Map;

/**
 * �û��Ự����
 * 
 * @spring.bean id = "opSessionRepository"
 */
public class OperatorSessionRepository {
    private Map userSessions;

    public OperatorSessionRepository() {
        userSessions = new HashMap();
    }

    /**
     * ����û��Ự
     * 
     * @param sessionId
     *                �Ựkey
     * @param userSession
     *                �û��Ự
     */
    @SuppressWarnings("unchecked")
	public void addUserSession(OperatorSession userSession) {
        this.userSessions.put(userSession.getSessionId(), userSession);
    }

    /**
     * ɾ���û��Ự
     * 
     * @param sessionId
     *                �Ựkey
     */
    public void removeUserSession(String sessionId) {
        if (userSessions.containsKey(sessionId)) {
            userSessions.remove(sessionId);
        }
    }

    /**
     * ��ȡ�û��Ự
     * 
     * @param sessionId
     *                �Ựkey
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
     * �����û��Ự
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
