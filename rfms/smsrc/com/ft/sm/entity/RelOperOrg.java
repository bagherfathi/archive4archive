/**
 * @{#} RelOperOrg.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_REL_OPER_ORG table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_REL_OPER_ORG"
 */

public class RelOperOrg implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "RelOperOrg";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_REL_ID = "relId";

    public RelOperOrg() {

    }

    // primary key
    private long relId;

    // fields
    private long operatorId;

    private long orgId;

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
     * Return the value associated with the column: ORG_ID
     */
    public long getOrgId() {
        return orgId;
    }

    /**
     * Set the value related to the column: ORG_ID
     * 
     * @param orgId
     *                the ORG_ID value
     */
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String toString() {
        return super.toString();
    }

}