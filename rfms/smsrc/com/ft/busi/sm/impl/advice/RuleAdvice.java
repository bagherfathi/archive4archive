package com.ft.busi.sm.impl.advice;

import java.lang.reflect.Method;

import com.ft.common.event.SMEvent;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.RuleInfoDTO;

/**
 * 用于发送规则更新的jms消息。
 * 
 * @version 1.0
 */
public class RuleAdvice extends MessageAdvice {
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        RuleInfoDTO rule;
        if(method.getName().equals("addRuleFile")
            || method.getName().equals("publishRuleFile")
            || method.getName().equals("updateRule")
            || method.getName().equals("saveRule")){
            rule = (RuleInfoDTO)args[0];
            
            SMEvent event = new SMEvent();
            event.setAction(EventConstants.EVENT_UPDATE_RULE);
            event.setType(EventConstants.EVENT_TYPE_RULE);
            event.setKey(rule.getCode());
            this.sendMessage(event);
        }
    }
}
