package com.ft.web.sm.priv.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ft.sm.dto.ResourceDTO;

/**
 * ���ڽ�ɫ���ʿ����б���й���
 * 
 * @version 1.0
 */
public class ResourceFilter {
    @SuppressWarnings("unchecked")
	public static List filter(List resourceList) {
        List result = new ArrayList();

        // ��Ȩ���б��������
        Collections.sort(resourceList);

        for (Iterator it = resourceList.iterator(); it.hasNext();) {
            boolean needSave = true;
            ResourceDTO resource = (ResourceDTO) it.next();
            for (Iterator it2 = result.iterator(); it2.hasNext();) {
                ResourceDTO entry = (ResourceDTO) it2.next();
                if (resource.getResourcePath().startsWith(
                        entry.getResourcePath())) {// ����Ѿ������˸��ִ����Ͳ���Ҫ������
                    needSave = false;
                    break;
                }
            }
            if (needSave) {
                result.add(resource);
            }
        }

        return result;
    }
}
