package com.ft.busi.sm.impl.advice;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.ft.common.event.SMEvent;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.ResourceDTO;

/**
 * ���ڷ��͹���Ȩ�޸��µ�jms��Ϣ.
 * 
 * @version 1.0
 */
public class ResourceAdvice extends MessageAdvice{

    /* (non-Javadoc)
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        String methodName = method.getName();
        
        //��������¹���Ȩ��
        if(methodName.equals("addResource")
                || methodName.equals("updateResource")){
            ResourceDTO resource = (ResourceDTO)args[0];
            this.sendResourceMessage(EventConstants.EVENT_UPDATE_RESOURCE,resource.getResourceId());
            return;
        }
        
        
        //ɾ������Ȩ��
        if(methodName.equals("deleteResource")){
            ResourceDTO resource = (ResourceDTO)args[0];
            this.sendResourceMessage(EventConstants.EVENT_REMOVE_RESOURCE,resource.getResourceId());
            return;
        }
        
        //����
        if(methodName.equals("updateResources")){
            List resourceList = (List)args[0];
            for (Iterator iterator = resourceList.iterator(); iterator.hasNext();) {
                ResourceDTO resource = (ResourceDTO) iterator.next();
                this.sendResourceMessage(EventConstants.EVENT_UPDATE_RESOURCE,resource.getResourceId());
            }
            
            return;
        }
        
    }
    
    private void sendResourceMessage(String action,Long resourceId){
        SMEvent event = new SMEvent();
        event.setAction(action);
        event.setType(EventConstants.EVENT_TYPE_RESOURCE);
        event.setKey(resourceId.toString());
        this.sendMessage(event);
    }
}
