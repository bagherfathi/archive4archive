/**
 * @{#} RelOperGroup.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_REL_OPER_GROUP table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_REL_OPER_GROUP"
 */

public class RelOperGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "RelOperGroup";

    public static String PROP_GROUP_ID = "groupId";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_REL_ID = "relId";

    public RelOperGroup() {

    }

    // primary key
    private long relId;

    // fields
    private long operatorId;

    private long groupId;

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
     * Return the value associated with the column: OPERATOR_ID
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Set the value related to the column: OPERATOR_ID
     * 
     * @param operatorId
     *                the OPERATOR_ID value
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * Return the value associated with the column: GROUP_ID
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * Set the value related to the column: GROUP_ID
     * 
     * @param groupId
     *                the GROUP_ID value
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String toString() {
        return super.toString();
    }

}