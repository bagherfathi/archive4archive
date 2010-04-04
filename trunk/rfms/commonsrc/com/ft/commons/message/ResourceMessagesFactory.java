package com.ft.commons.message;

import org.apache.commons.resources.Resources;
import org.apache.commons.resources.impl.ResourceBundleResourcesFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * ResourceMessages �Ĳֿ���󣬸���ʵ����ResourceMessages����
 *
 */
public class ResourceMessagesFactory {
    /** DOCUMENT ME! */
    public static Map resourcesMap = new HashMap();

    /**
     * DOCUMENT ME!
     *
     * @param className DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static ResourceMessages getResourceInstance(Class className) {
        return getResourceInstance(className.getName());
    }

    /**
     * ʵ����һ������
     *
     * @param name
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	public static ResourceMessages getResourceInstance(String name) {
        if (!resourcesMap.containsKey(name)) {
            ResourceMessages result =
                new ResourceMessages(createResource(name));

            resourcesMap.put(name, result);
        }

        return (ResourceMessages) resourcesMap.get(name);
    }

    /**
     * ����Resources ����
     *
     * @param name
     *
     * @return
     */
    public static Resources createResource(String name) {
        ResourceBundleResourcesFactory factory =
            new ResourceBundleResourcesFactory();

        Resources resources = factory.getResources(name, name);
        resources.setReturnNull(true);

        return resources;
    }
}
