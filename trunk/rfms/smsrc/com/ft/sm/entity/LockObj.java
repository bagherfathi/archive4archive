/**
 * @{#} LockObj.java Create on 2006-11-15 9:39:36
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_LOCK_OBJ table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_LOCK_OBJ"
 */

public class LockObj implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "LockObj";

    public static String PROP_LOCK_OBJ_NAME = "lockObjName";

    public static String PROP_UPDATE_DATE = "updateDate";

    public static String PROP_LOCK_OBJ_CODE = "lockObjCode";

    public static String PROP_LOCK_OBJ_ID = "lockObjId";

    public LockObj() {

    }

    // primary key
    private long lockObjId;

    // fields
    private java.lang.String lockObjName;

    private java.lang.String lockObjCode;

    private java.util.Date updateDate;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="LOCK_OBJ_ID"
     */
    public long getLockObjId() {
        return lockObjId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param lockObjId
     *                the new ID
     */
    public void setLockObjId(long lockObjId) {
        this.lockObjId = lockObjId;
    }

    /**
     * Return the value associated with the column: LOCK_OBJ_NAME
     */
    public java.lang.String getLockObjName() {
        return lockObjName;
    }

    /**
     * Set the value related to the column: LOCK_OBJ_NAME
     * 
     * @param lockObjName
     *                the LOCK_OBJ_NAME value
     */
    public void setLockObjName(java.lang.String lockObjName) {
        this.lockObjName = lockObjName;
    }

    /**
     * Return the value associated with the column: LOCK_OBJ_CODE
     */
    public java.lang.String getLockObjCode() {
        return lockObjCode;
    }

    /**
     * Set the value related to the column: LOCK_OBJ_CODE
     * 
     * @param lockObjCode
     *                the LOCK_OBJ_CODE value
     */
    public void setLockObjCode(java.lang.String lockObjCode) {
        this.lockObjCode = lockObjCode;
    }

    /**
     * Return the value associated with the column: UPDATE_DATE
     */
    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Set the value related to the column: UPDATE_DATE
     * 
     * @param updateDate
     *                the UPDATE_DATE value
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    public String toString() {
        return super.toString();
    }

}