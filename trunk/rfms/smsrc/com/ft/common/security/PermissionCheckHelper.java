package com.ft.common.security;

import javax.servlet.http.HttpServletRequest;

import com.ft.common.session.OperatorSession;
import com.ft.common.session.OperatorSessionRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 权限检查帮助类.
 * 
 * @version 1.0
 */
public class PermissionCheckHelper {
    private static OperatorSessionRepository userSessionRepository;

    /**
     * 获取操作员会话仓库。
     * 
     * @param request
     * @return
     */
    private static OperatorSessionRepository getUserSessionRepository(
            HttpServletRequest request) {
        if (null == userSessionRepository) {
            userSessionRepository = (OperatorSessionRepository) SpringWebUtils
                    .getBean(request, OperatorSessionRepository.getBeanName());
        }

        return userSessionRepository;
    }

    /**
     * 获取当前用户会话信息
     * 
     * @param request
     * @return
     */
    public static OperatorSession getOperatorSession(HttpServletRequest request) {
        OperatorSessionRepository sessionRepositoy = getUserSessionRepository(request);
        return sessionRepositoy.getUserSession(request.getSession().getId());
    }

    /**
     * 检查当前用户是否对指定的权限是否拥有权限
     * 
     * @param request
     * @param resourceKey
     *                权限编码
     * @return
     */
    public static boolean checkPermission(HttpServletRequest request,
            String resourceKey) {
        OperatorSession opSession = getOperatorSession(request);

        // 用户会话信息不存在
        if (opSession == null)
            return false;

        // 当前登陆组织
        Long loginOrgId = opSession.getLoginOrgId();

        if (null == loginOrgId)
            return false;

        ResourceRepository resources = (ResourceRepository) SpringWebUtils
                .getBean(request, ResourceRepository.getBeanName());

        // 权限仓库没有找到
        if (resources == null)
            return false;

        ResourceDTO resource = resources.getResource(resourceKey);

        // 权限不存在
        if (null == resource)
            return false;

        return opSession.getChecker().checkPermission(loginOrgId,
                resource.getResourcePath());
    }

    /**
     * 检查当前用户是否有对指定权限的view权限
     * 
     * @param request
     * @param resourceKey
     *                权限编码
     * @return
     */
    public static boolean checkViewPermission(HttpServletRequest request,
            String resourceKey) {
        OperatorSession opSession = getOperatorSession(request);

        // 用户会话信息不存在
        if (opSession == null)
            return false;

        // 当前登陆组织
        Long loginOrgId = opSession.getLoginOrgId();

        if (null == loginOrgId)
            return false;

        ResourceRepository resources = (ResourceRepository) SpringWebUtils
                .getBean(request, ResourceRepository.getBeanName());

        // 权限仓库没有找到
        if (resources == null)
            return false;

        ResourceDTO resource = resources.getResource(resourceKey);

        // 权限不存在
        if (null == resource)
            return true;

        return opSession.getChecker().checkViewPermission(loginOrgId,
                resource.getResourcePath());
    }
}
