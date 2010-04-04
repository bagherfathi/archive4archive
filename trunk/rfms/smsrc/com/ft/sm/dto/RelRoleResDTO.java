package com.ft.sm.dto;

import com.ft.sm.entity.RelRoleRes;
import com.ft.sm.entity.Resource;
import com.ft.sm.entity.Role;

/**
 * ��ɫ���书��Ȩ�޷�װ�ࡣ
 * 
 */
public class RelRoleResDTO implements java.io.Serializable {
    private static final long serialVersionUID = 8062909211123155288L;

    private Resource resource;

    private Role role;

    private String path;

    private RelRoleRes relRoleRes;

    public RelRoleResDTO(Role role, Resource resource) {
        this.resource = resource;
        this.role = role;
        this.path = resource.getResourcePath();

        relRoleRes = new RelRoleRes();
        relRoleRes.setResourceId(resource.getResourceId());
        relRoleRes.setRoleId(role.getRoleId());
        relRoleRes.setResourcePath(path);
    }

    /**
     * ��ӦȨ�ޡ�
     * 
     */
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * ��Ӧ��ɫ��
     */
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the relRoleRes
     */
    public RelRoleRes getRelRoleRes() {
        return relRoleRes;
    }
}
