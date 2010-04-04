package com.ft.sm.dto;

import com.ft.sm.entity.DataResourceEntry;
import com.ft.sm.entity.RelRoleDataRes;
import com.ft.sm.entity.Role;

/**
 * ��ɫ����ҵ��Ȩ�޷�װ�ࡣ
 * 
 * @version 1.0
 */
public class RelRoleDataResDTO implements java.io.Serializable {
    private static final long serialVersionUID = 7978073469528992686L;

    private Role role;

    private DataResourceEntry dataResourceEntry;

    private RelRoleDataRes relRoleDataRes;

    public RelRoleDataResDTO(RelRoleDataRes res){
        this.relRoleDataRes = res;
    }
    
    public RelRoleDataResDTO(Role role, DataResourceEntry dataResourceEntry) {
        this.role = role;
        this.dataResourceEntry = dataResourceEntry;

        relRoleDataRes = new RelRoleDataRes();
        relRoleDataRes.setEntryId(dataResourceEntry.getEntryId());
        relRoleDataRes.setRoleId(role.getRoleId());
    }

    /**
     * ҵ��Ȩ����Ŀ��
     * 
     * @return Returns the dataResourceEntry.
     */
    public DataResourceEntry getDataResourceEntry() {
        return dataResourceEntry;
    }

    /**
     * @param dataResourceEntry
     *                The dataResourceEntry to set.
     */
    public void setDataResourceEntry(DataResourceEntry dataResourceEntry) {
        this.dataResourceEntry = dataResourceEntry;
    }

    /**
     * ��ɫ��
     * 
     * @return Returns the role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     *                The role to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the relRoleDataRes
     */
    public RelRoleDataRes getRelRoleDataRes() {
        return relRoleDataRes;
    }
}
