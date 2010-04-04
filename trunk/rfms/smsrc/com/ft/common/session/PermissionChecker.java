package com.ft.common.session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ft.sm.dto.ResourceDTO;

/**
 * 用户权限检查器
 * 
 */
public class PermissionChecker {
    private static final Long DEFAULT_ORG_ID = new Long(-1);

    private Map<Long,Set> permissions;

    public PermissionChecker() {
        this.permissions = new HashMap<Long,Set>();
    }

    /**
     * 添加用户可访问组织到缓存中
     * 
     * @param resources
     *                功能权限实体列表
     */
    public void addPermission(List resources) {
        this.addOrgPermission(DEFAULT_ORG_ID, resources);
    }

    /**
     * 添加用户在某个组织中可访问的权限
     * 
     * @param orgId
     *                组织机构标识
     * @param resources
     *                可访问权限列表
     */
    public void addOrgPermission(Long orgId, List resources) {
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            ResourceDTO resource = (ResourceDTO) iter.next();
            addOrgPermission(orgId, resource.getResourcePath());
        }
    }

    /**
     * 添加用户在某个组织中可访问的权限
     * 
     * @param orgId
     *                组织机构
     * @param targetId
     *                功能权限路径，如:1#2#3#
     */
    @SuppressWarnings("unchecked")
	public void addOrgPermission(Long orgId, String targetId) {
        Set targetIds = (Set) this.permissions.get(orgId);
        if (targetIds == null) {
            targetIds = new HashSet();
            this.permissions.put(orgId, targetIds);
        }

        // 判断是否已经存在
        boolean in = false;
        for (Iterator iter = targetIds.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            if (targetId.equals(element)) {
                in = true;
                break;
            }
        }

        if (!in) {
            targetIds.add(targetId);
        }
    }

    /**
     * 检查用户在某个组织中对指定权限是否有权限
     * 
     * @param orgId
     *                可访问组织ID
     * @param resourcePath
     *                功能权限路径
     * @return
     */
    public boolean checkPermission(Long orgId, String resourcePath) {
        Set targetIds = (Set) this.permissions.get(orgId);
        if (targetIds == null) {
            return false;
        } else {
            for (Iterator iter = targetIds.iterator(); iter.hasNext();) {
                String targetId = (String) iter.next();
                if (resourcePath.startsWith(targetId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查用户对指定权限是否有权限
     * 
     * @param resourcePath
     * @return
     */
    public boolean checkPermission(String resourcePath) {
        return this.checkPermission(DEFAULT_ORG_ID, resourcePath);
    }

    public boolean checkMenuDisplay(String resourcePath) {
        return this.checkViewPermission(DEFAULT_ORG_ID, resourcePath);
    }

    /**
     * 检查菜单是否可以显示
     * 
     * @param orgId
     *                可访问组织ID
     * @param resourcePath
     *                功能权限路径
     * @return
     */
    public boolean checkViewPermission(Long orgId, String resourcePath) {
        if (this.checkPermission(orgId, resourcePath)) {
            return true;
        }

        if (this.checkDisplay(orgId, resourcePath)) {
            return true;
        }

        return false;
    }

    /**
     * 检查菜单是否可以显示. 对于菜单,用户拥有其下级菜单访问权限时,可以显示上级菜单
     * 
     * @param orgId
     *                可访问组织ID
     * @param resourcePath
     *                功能权限路径
     * @return
     */
    private boolean checkDisplay(Long orgId, String resourcePath) {
        Set targetIds = (Set) this.permissions.get(orgId);
        if (targetIds == null) {
            return false;
        } else {
            for (Iterator iter = targetIds.iterator(); iter.hasNext();) {
                String targetId = (String) iter.next();
                if (targetId.startsWith(resourcePath)) {
                    return true;
                }
            }
        }
        return false;
    }
}
