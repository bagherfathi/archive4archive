package com.ft.busi.sm.impl.advice;

import java.lang.reflect.Method;

import com.ft.common.event.SMEvent;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.OrganizationDTO;

/**
 * 用于发送组织更新的jms消息.
 * 
 * @version 1.0
 */
public class OrgAdvice extends MessageAdvice{

    /* (non-Javadoc)
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        String methodName = method.getName();
        if(methodName.equals("disableOrg")){
            OrganizationDTO[] orgs = (OrganizationDTO[])args[0];
            for (int i = 0; i < orgs.length; i++) {
                sendUpdateOrgMessage(orgs[i].getOrgId());
            }
            
            return;
        }
        
        if(methodName.equals("enableOrg")){
            OrganizationDTO org = (OrganizationDTO)args[0];
            sendUpdateOrgMessage(org.getOrgId());
            return;
        }
        
        //新建组织
        if(methodName.equals("save")){
            Long orgId = (Long)returnValue;
            sendUpdateOrgMessage(orgId);
            
            //对于so系统发送创建组织的消息
            OrganizationDTO org = (OrganizationDTO)args[0];
            if(org.getType() == OrganizationDTO.ORG_TYPE_COMPANY){
                SMEvent event = new SMEvent();
                event.setAction(EventConstants.EVENT_ADD_ORG);
                event.setType(EventConstants.EVENT_TYPE_ORG);
                event.setKey(org.getCode());
                this.sendMessage(event);
            }
            
            return;
        }
        
        //更新组织
        if(methodName.equals("update")){
            OrganizationDTO org = (OrganizationDTO)args[0];
            sendUpdateOrgMessage(org.getOrgId());
            return;
        }
        
    }
    
    private void sendUpdateOrgMessage(Long orgId){
        SMEvent event = new SMEvent();
        event.setAction(EventConstants.EVENT_UPDATE_ORG);
        event.setType(EventConstants.EVENT_TYPE_ORG);
        event.setKey(orgId.toString());
        this.sendMessage(event);
    }
}
