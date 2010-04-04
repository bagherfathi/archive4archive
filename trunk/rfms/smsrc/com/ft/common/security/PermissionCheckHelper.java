package com.ft.common.security;

import javax.servlet.http.HttpServletRequest;

import com.ft.common.session.OperatorSession;
import com.ft.common.session.OperatorSessionRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * Ȩ�޼�������.
 * 
 * @version 1.0
 */
public class PermissionCheckHelper {
    private static OperatorSessionRepository userSessionRepository;

    /**
     * ��ȡ����Ա�Ự�ֿ⡣
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
     * ��ȡ��ǰ�û��Ự��Ϣ
     * 
     * @param request
     * @return
     */
    public static OperatorSession getOperatorSession(HttpServletRequest request) {
        OperatorSessionRepository sessionRepositoy = getUserSessionRepository(request);
        return sessionRepositoy.getUserSession(request.getSession().getId());
    }

    /**
     * ��鵱ǰ�û��Ƿ��ָ����Ȩ���Ƿ�ӵ��Ȩ��
     * 
     * @param request
     * @param resourceKey
     *                Ȩ�ޱ���
     * @return
     */
    public static boolean checkPermission(HttpServletRequest request,
            String resourceKey) {
        OperatorSession opSession = getOperatorSession(request);

        // �û��Ự��Ϣ������
        if (opSession == null)
            return false;

        // ��ǰ��½��֯
        Long loginOrgId = opSession.getLoginOrgId();

        if (null == loginOrgId)
            return false;

        ResourceRepository resources = (ResourceRepository) SpringWebUtils
                .getBean(request, ResourceRepository.getBeanName());

        // Ȩ�޲ֿ�û���ҵ�
        if (resources == null)
            return false;

        ResourceDTO resource = resources.getResource(resourceKey);

        // Ȩ�޲�����
        if (null == resource)
            return false;

        return opSession.getChecker().checkPermission(loginOrgId,
                resource.getResourcePath());
    }

    /**
     * ��鵱ǰ�û��Ƿ��ж�ָ��Ȩ�޵�viewȨ��
     * 
     * @param request
     * @param resourceKey
     *                Ȩ�ޱ���
     * @return
     */
    public static boolean checkViewPermission(HttpServletRequest request,
            String resourceKey) {
        OperatorSession opSession = getOperatorSession(request);

        // �û��Ự��Ϣ������
        if (opSession == null)
            return false;

        // ��ǰ��½��֯
        Long loginOrgId = opSession.getLoginOrgId();

        if (null == loginOrgId)
            return false;

        ResourceRepository resources = (ResourceRepository) SpringWebUtils
                .getBean(request, ResourceRepository.getBeanName());

        // Ȩ�޲ֿ�û���ҵ�
        if (resources == null)
            return false;

        ResourceDTO resource = resources.getResource(resourceKey);

        // Ȩ�޲�����
        if (null == resource)
            return true;

        return opSession.getChecker().checkViewPermission(loginOrgId,
                resource.getResourcePath());
    }
}
