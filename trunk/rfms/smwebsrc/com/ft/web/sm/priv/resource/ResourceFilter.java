package com.ft.web.sm.priv.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.ft.sm.dto.ResourceDTO;

/**
 * 对于角色访问控制列表进行过滤
 * 
 * @version 1.0
 */
public class ResourceFilter {
    @SuppressWarnings("unchecked")
	public static List filter(List resourceList) {
        List result = new ArrayList();

        // 对权限列表进行排序
        Collections.sort(resourceList);

        for (Iterator it = resourceList.iterator(); it.hasNext();) {
            boolean needSave = true;
            ResourceDTO resource = (ResourceDTO) it.next();
            for (Iterator it2 = result.iterator(); it2.hasNext();) {
                ResourceDTO entry = (ResourceDTO) it2.next();
                if (resource.getResourcePath().startsWith(
                        entry.getResourcePath())) {// 如果已经保存了父字串，就不需要保存了
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
