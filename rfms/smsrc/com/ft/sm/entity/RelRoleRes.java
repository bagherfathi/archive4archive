/**
 * @{#} RelRoleRes.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_REL_ROLE_RES table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_REL_ROLE_RES"
 */

public class RelRoleRes implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "RelRoleRes";

    public static String PROP_REL_ID = "relId";

    public static String PROP_RESOURCE_PATH = "resourcePath";

    public static String PROP_RESOURCE_ID = "resourceId";

    public static String PROP_ROLE_ID = "roleId";

    public RelRoleRes() {

    }

    // primary key
    private long relId;

    // fields
    private long roleId;

    private long resourceId;

    private java.lang.String resourcePath;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="REL_ID"
     */
    public long getRelId() {
        return relId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param relId
     *                the new ID
     */
    public void setRelId(long relId) {
        this.relId = relId;
    }

    /**
     * Return the value associated with the column: ROLE_ID
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Set the value related to the column: ROLE_ID
     * 
     * @param roleId
     *                the ROLE_ID value
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * Return the value associated with the column: RESOURCE_ID
     */
    public long getResourceId() {
        return resourceId;
    }

    /**
     * Set the value related to the column: RESOURCE_ID
     * 
     * @param resourceId
     *                the RESOURCE_ID value
     */
    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Return the value associated with the column: RESOURCE_PATH
     */
    public java.lang.String getResourcePath() {
        return resourcePath;
    }

    /**
     * Set the value related to the column: RESOURCE_PATH
     * 
     * @param resourcePath
     *                the RESOURCE_PATH value
     */
    public void setResourcePath(java.lang.String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String toString() {
        return super.toString();
    }

}