package com.ft.common.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ft.commons.web.SpringWebUtils;

/**
 * 用户会话监听器
 * 
 */
public class OperatorSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {

    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();

        OperatorSessionHandler handler = (OperatorSessionHandler) SpringWebUtils
                .getBean(session.getServletContext(), OperatorSessionHandler
                        .getBeanName());
        handler.destroy(event.getSession());
    }

}
