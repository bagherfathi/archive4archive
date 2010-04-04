package com.ft.common.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import com.ft.common.security.ResourceRepository;
import com.ft.common.session.OperatorSession;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.ResourceDTO;

/**
 * ����MenuRepository.
 * 
 * @spring.bean id = "menuBuilder"
 * 
 */
public class MenuBuilder {
    public static final String SYSTEM_MENU_NAME = "SystemMenu";

    private ResourceRepository resourceRepository;

    /**
     * @spring.property ref = "resourceRepository"
     * 
     * @param resourceRepository
     */
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * �����û��Ự����ϵͳ�˵�
     * 
     * @param userSession
     * @return
     */
    public MenuRepository buildTree(OperatorSession opSession) {
        PermissionChecker checker = opSession.getChecker();
        Long loginOrgId = opSession.getLoginOrgId();
        List resources = this.resourceRepository.iterator();

        Map<Object,Object> components = new HashMap<Object,Object>();
        MenuRepository menuRepository = new MenuRepository();
        MenuComponent root = createRootComponent();

        if (loginOrgId != null) {
            MenuComponent parent = null;
            for (Iterator iter = resources.iterator(); iter.hasNext();) {
                ResourceDTO resource = (ResourceDTO) iter.next();
                // Ȩ��Ϊ�˵����ɼ�
                if (resource.isMenu() && resource.isVisible()) {
                    // ����Ƿ��Ȩ���з���Ȩ��
                    if (checker.checkViewPermission(loginOrgId, resource
                            .getResourcePath())) {
                        MenuComponent child = createComponent(resource);
                        parent = (MenuComponent) components.get(resource
                                .getParentId());

                        // һ���˵�
                        if (parent == null
                                && resource.getResourceId().equals(
                                        resource.getParentId())) {
                            parent = root;
                        }

                        if (parent != null) {

                            components.put(resource.getResourceId(), child);
                            parent.addMenuComponent(child);

                            // ���ڴ��������˵�ʱ,���뽫�����˵���ΪMenu���뵽menuRepository��
                            int level = resource.getLevel();

                            if (level == 2) {
                                menuRepository.addMenu(child);
                            }

                            if (level == 4) {
                                menuRepository.addMenu(parent);
                            }
                        }

                    }
                }
            }
        }

        menuRepository.addMenu(root);

        return menuRepository;
    }
    
    /**
     * �����û��Ự����ϵͳ�˵�
     * 
     * @param userSession
     * @return
     */
    public Map buildDIVTree(OperatorSession opSession) {
        PermissionChecker checker = opSession.getChecker();
        Long loginOrgId = opSession.getLoginOrgId();
        List resources = this.resourceRepository.iterator();

        Map components = new HashMap();
        /*ResourceDTO root = new ResourceDTO();
        List levelOne=new ArrayList();
        List levelTwo=new ArrayList();
        List levelThree=new ArrayList();
        List levelFour=new ArrayList();
        List[] res=new ArrayList[10];*/
        List<ResourceDTO> myResources=new ArrayList<ResourceDTO>();
        int maxLevel=0;
        if (loginOrgId != null) {
            for (Iterator iter = resources.iterator(); iter.hasNext();) {
                ResourceDTO resource = (ResourceDTO) iter.next();
                // Ȩ��Ϊ�˵����ɼ�
                if (resource.isMenu() && resource.isVisible()) {
                    // ����Ƿ��Ȩ���з���Ȩ��
                    if (checker.checkViewPermission(loginOrgId, resource
                            .getResourcePath())) {
                    	myResources.add(resource);
                        if(resource.getLevel()>maxLevel){
                        	maxLevel=resource.getLevel();
                        }
                    }
                }
            }
        }
        
        return components;
    }

    private MenuComponent createRootComponent() {
        MenuComponent component = new MenuComponent();
        component.setName(SYSTEM_MENU_NAME);
        component.setTitle(SYSTEM_MENU_NAME);
        return component;
    }

    private MenuComponent createComponent(ResourceDTO resource) {
        MenuComponent component = new MenuComponent();
        component.setName(resource.getResourceId().toString());
        component.setTitle(resource.getTitle());
        component.setImage(resource.getImage());
        component.setPage(resource.getUrl());
        return component;
    }

    public static String getBeanName() {
        return "menuBuilder";
    }
}
