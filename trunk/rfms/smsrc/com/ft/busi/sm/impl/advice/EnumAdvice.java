package com.ft.busi.sm.impl.advice;

import java.lang.reflect.Method;

import com.ft.common.event.SMEvent;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.EnumDTO;

/**
 * ���ڷ���ö�����ݸ��µ�jms��Ϣ.
 * @version 1.0
 */
public class EnumAdvice extends MessageAdvice{

    /* (non-Javadoc)
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        String methodName = method.getName();
        
        //�½������ö������
        if(methodName.equals("saveEnumData")
                || methodName.equals("updateEnumData")){
            EnumDTO enumDto = (EnumDTO)args[0];
            this.sendUpdateEnumMessage(enumDto.getEnumId());
            return;
        }
        
        //��������ö������
        if(methodName.equals("disableEnumData") 
                || methodName.equals("enableEnumData")){
            Long enumId = (Long)args[0];
            this.sendUpdateEnumMessage(enumId);
            return;
        }
    }

    private void sendUpdateEnumMessage(Long enumId){
        SMEvent event = new SMEvent();
        event.setAction(EventConstants.EVENT_UPDATE_ENUM);
        event.setType(EventConstants.EVENT_TYPE_ENUM);
        event.setKey(enumId.toString());
        this.sendMessage(event);
    }
}
