package com.ft.busi.sm.impl.advice;

import java.lang.reflect.Method;

import com.ft.common.event.SMEvent;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.TemplateDTO;

/**
 * 用于发送模板更新的jms消息.
 * 
 * @version 1.0
 */
public class TemplateAdvice extends MessageAdvice{

    /* (non-Javadoc)
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        TemplateDTO template;
        String methodName = method.getName();
        if(methodName.equals("addTemplateFile")
                || methodName.equals("publisTemplateFile")
                || methodName.equals("saveTempate")){
            template = (TemplateDTO)args[0];
            SMEvent event = new SMEvent();
            event.setAction(EventConstants.EVENT_UPDATE_TEMPLATE);
            event.setType(EventConstants.EVENT_TYPE_TEMPLATE);
            event.setKey(template.getTemplate().getTemplateCode());
            this.sendMessage(event);
        }
    }

}
