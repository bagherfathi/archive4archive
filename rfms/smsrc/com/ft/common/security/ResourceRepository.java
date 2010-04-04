package com.ft.common.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.ResourceDTO;

/**
 * ����Ȩ��ʵ�建��
 * 
 * @spring.bean id = "resourceRepository" init-method="init"
 */
public class ResourceRepository implements SMEventListener{
    private static final Log logger = LogFactory.getLog(ResourceRepository.class);
    // ��Ȩ��IDΪKey,���Ȩ��ʵ��
    private Map<Long,ResourceDTO> resources;

    // Ȩ�ޱ���ΪKey,���Ȩ��ʵ��ID
    private Map<String,Long> codeMap;

    private ResourceManager resourceManager;

    /**
     * @spring.property ref = "resourceManager"
     * 
     * @param resourceManager
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public ResourceRepository() {
        this.resources = new HashMap<Long,ResourceDTO>();
        this.codeMap = new HashMap<String,Long>();
    }

    /**
     * ����ϵͳ����ʱ���г�ʼ��
     * 
     */
    public void init() throws Exception {
        this.resources.clear();
        this.codeMap.clear();
        
        List result = resourceManager.findAllResources();
        for (Iterator iter = result.iterator(); iter.hasNext();) {
            ResourceDTO resource = (ResourceDTO) iter.next();
            this.addResource(resource);
        }

    }

    /**
     * ���Ȩ��
     * 
     * @param resource
     */
    public void addResource(ResourceDTO resource) {
        this.resources.put(resource.getResourceId(), resource);

        String key = resource.getResourceKey();
        if (null != key && key.length() > 0) {
            this.codeMap.put(key, resource.getResourceId());
        }
    }

    /**
     * �������Ȩ��
     * 
     * @param reources
     */
    public void addResource(List resources) {
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            this.addResource(element);
        }
    }

    /**
     * ɾ��Ȩ��
     * 
     * @param resourceId
     */
    public void removeResource(Long resourceId) {
        if (this.resources.containsKey(resourceId)) {
            ResourceDTO resource = (ResourceDTO) this.resources.get(resourceId);

            String key = resource.getResourceKey();
            if (null != key && key.length() > 0) {
                this.codeMap.remove(key);
            }

            this.resources.remove(resourceId);

        }
    }

    /**
     * ����Ȩ��
     * 
     * @param resource
     */
    public void updateResource(ResourceDTO resource) {
        if (resources.containsKey(resource.getResourceId())) {
            // ��ɾ��
            this.removeResource(resource.getResourceId());
            // �������
            this.addResource(resource);
        }
    }

    /**
     * ��������Ȩ��
     * 
     * @param resources
     */
    public void updateResource(List resources) {
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            this.updateResource(element);
        }
    }

    /**
     * ����Ȩ��ID��ȡȨ��
     * 
     * @param resourceId
     * @return
     */
    public ResourceDTO getResource(Long resourceId) {
        return (ResourceDTO) this.resources.get(resourceId);
    }

    /**
     * ����Ȩ�ޱ����ȡȨ��
     * 
     * @param resourceKey
     * @return
     */
    public ResourceDTO getResource(String resourceKey) {
        Long resourceId = (Long) this.codeMap.get(resourceKey);
        if (resourceId != null) {
            return this.getResource(resourceId);
        } else {
            return null;
        }
    }

    /**
     * Ȩ��ʵ���б�
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
	public List iterator() {
        List<ResourceDTO> result = new ArrayList<ResourceDTO>();

        Set keySet = this.resources.keySet();
        for (Iterator iter = keySet.iterator(); iter.hasNext();) {
            Long key = (Long) iter.next();
            result.add(this.resources.get(key));
        }

        Collections.sort(result);

        return result;
    }

    /* (non-Javadoc)
     * @see com.ft.common.event.SMEventListener#isSupport(com.ft.common.event.SMEvent)
     */
    public boolean isSupport(SMEvent event) {
        if (EventConstants.EVENT_TYPE_RESOURCE.equals(event.getType()))
            return true;

        return false;
    }

    /* (non-Javadoc)
     * @see com.ft.common.event.SMEventListener#onEvent(com.ft.common.event.SMEvent)
     */
    public void onEvent(SMEvent event) {
        logger.info("Receive message:" + event.toString());
        String key = event.getKey();
        
        //����������¼�
        if(EventConstants.EVENT_UPDATE_RESOURCE.equals(event.getAction())){
            if(key != null && key.length() > 0){
                Long resourceId = new Long(key);
                this.addOrUpdate(resourceId);
            }
            
            return;
        }
        
        //ɾ���¼�
        if(EventConstants.EVENT_REMOVE_RESOURCE.equals(event.getAction())){
            if(key != null && key.length() > 0){
                Long resourceId = new Long(key);
                this.removeResource(resourceId);
            }
            
            return;
        }
        
    }
    
    /**
     * ��������¹���Ȩ�ޡ�
     * @param resourceId
     */
    private void addOrUpdate(Long resourceId){
        try{
            ResourceDTO resource = (ResourceDTO)this.resourceManager.getEntity(ResourceDTO.class, resourceId);
            if(resource != null){
                this.addResource(resource);
            }
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
    }

    public static String getBeanName() {
        return "resourceRepository";
    }
}
