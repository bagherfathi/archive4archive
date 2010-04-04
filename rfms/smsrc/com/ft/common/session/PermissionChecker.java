package com.ft.common.session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ft.sm.dto.ResourceDTO;

/**
 * �û�Ȩ�޼����
 * 
 */
public class PermissionChecker {
    private static final Long DEFAULT_ORG_ID = new Long(-1);

    private Map<Long,Set> permissions;

    public PermissionChecker() {
        this.permissions = new HashMap<Long,Set>();
    }

    /**
     * ����û��ɷ�����֯��������
     * 
     * @param resources
     *                ����Ȩ��ʵ���б�
     */
    public void addPermission(List resources) {
        this.addOrgPermission(DEFAULT_ORG_ID, resources);
    }

    /**
     * ����û���ĳ����֯�пɷ��ʵ�Ȩ��
     * 
     * @param orgId
     *                ��֯������ʶ
     * @param resources
     *                �ɷ���Ȩ���б�
     */
    public void addOrgPermission(Long orgId, List resources) {
        for (Iterator iter = resources.iterator(); iter.hasNext();) {
            ResourceDTO resource = (ResourceDTO) iter.next();
            addOrgPermission(orgId, resource.getResourcePath());
        }
    }

    /**
     * ����û���ĳ����֯�пɷ��ʵ�Ȩ��
     * 
     * @param orgId
     *                ��֯����
     * @param targetId
     *                ����Ȩ��·������:1#2#3#
     */
    @SuppressWarnings("unchecked")
	public void addOrgPermission(Long orgId, String targetId) {
        Set targetIds = (Set) this.permissions.get(orgId);
        if (targetIds == null) {
            targetIds = new HashSet();
            this.permissions.put(orgId, targetIds);
        }

        // �ж��Ƿ��Ѿ�����
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
     * ����û���ĳ����֯�ж�ָ��Ȩ���Ƿ���Ȩ��
     * 
     * @param orgId
     *                �ɷ�����֯ID
     * @param resourcePath
     *                ����Ȩ��·��
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
     * ����û���ָ��Ȩ���Ƿ���Ȩ��
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
     * ���˵��Ƿ������ʾ
     * 
     * @param orgId
     *                �ɷ�����֯ID
     * @param resourcePath
     *                ����Ȩ��·��
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
     * ���˵��Ƿ������ʾ. ���ڲ˵�,�û�ӵ�����¼��˵�����Ȩ��ʱ,������ʾ�ϼ��˵�
     * 
     * @param orgId
     *                �ɷ�����֯ID
     * @param resourcePath
     *                ����Ȩ��·��
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
