package com.ft.busi.sm.impl.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventDispatcher;
import com.ft.spring.web.SpringContextUtils;

/**
 * Class comments.
 * 
 * @version 1.0
 */
public abstract class MessageAdvice implements AfterReturningAdvice{
    private static final Log logger = LogFactory.getLog(MessageAdvice.class);
    protected SMEventDispatcher eventDispatcher;
     
    /**
     * @param eventDispatcher the eventDispatcher to set
     */
    public void setEventDispatcher(SMEventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
    
    /**
     * ·¢ËÍJMSÏûÏ¢.
     * @param event
     */
    public void sendMessage(final SMEvent event){
        logger.info("Send message:" + event.toString());
        SMEventDispatcher dispatcher = this.getEventDispatcher();
        
        if(dispatcher != null)
            dispatcher.dispatcher(event);
    }
    
    private SMEventDispatcher getEventDispatcher(){
        if(this.eventDispatcher == null){
            this.eventDispatcher = (SMEventDispatcher)SpringContextUtils.getBean("smEventTopicDispatcher");
        }
        
        return this.eventDispatcher;
    }
}
