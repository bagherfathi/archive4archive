/**
 * @{#} ConsignPermit.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_CONSIGN_PERMIT table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_CONSIGN_PERMIT"
 */

public class ConsignPermit implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "ConsignPermit";

    public static String PROP_CONSIGNEE_ID = "consigneeId";

    public static String PROP_CONSIGN_ID = "consignId";

    public static String PROP_CONSIGN_TIME = "consignTime";

    public static String PROP_RESOURCE_TYPE = "resourceType";

    public static String PROP_EXPIRE_TIME = "expireTime";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_CONSIGNEE_ORG_ID = "consigneeOrgId";

    public static String PROP_VALID_TIME = "validTime";

    public static String PROP_RESOURCE_ID = "resourceId";

    public static String PROP_CONSIGNER_ID = "consignerId";

    public static String PROP_CONSIGNER_ORG_ID = "consignerOrgId";

    public ConsignPermit() {

    }

    // primary key
    private long consignId;

    // fields
    private long consignerId;

    private long consignerOrgId;

    private long consigneeId;

    private long consigneeOrgId;

    private long resourceId;

    private long resourceType;

    private long orgId;

    private java.util.Date validTime;

    private java.util.Date expireTime;

    private java.util.Date consignTime;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="CONSIGN_ID"
     */
    public long getConsignId() {
        return consignId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param consignId
     *                the new ID
     */
    public void setConsignId(long consignId) {
        this.consignId = consignId;
    }

    /**
     * Return the value associated with the column: CONSIGNER_ID
     */
    public long getConsignerId() {
        return consignerId;
    }

    /**
     * Set the value related to the column: CONSIGNER_ID
     * 
     * @param consignerId
     *                the CONSIGNER_ID value
     */
    public void setConsignerId(long consignerId) {
        this.consignerId = consignerId;
    }

    /**
     * Return the value associated with the column: CONSIGNER_ORG_ID
     */
    public long getConsignerOrgId() {
        return consignerOrgId;
    }

    /**
     * Set the value related to the column: CONSIGNER_ORG_ID
     * 
     * @param consignerOrgId
     *                the CONSIGNER_ORG_ID value
     */
    public void setConsignerOrgId(long consignerOrgId) {
        this.consignerOrgId = consignerOrgId;
    }

    /**
     * Return the value associated with the column: CONSIGNEE_ID
     */
    public long getConsigneeId() {
        return consigneeId;
    }

    /**
     * Set the value related to the column: CONSIGNEE_ID
     * 
     * @param consigneeId
     *                the CONSIGNEE_ID value
     */
    public void setConsigneeId(long consigneeId) {
        this.consigneeId = consigneeId;
    }

    /**
     * Return the value associated with the column: CONSIGNEE_ORG_ID
     */
    public long getConsigneeOrgId() {
        return consigneeOrgId;
    }

    /**
     * Set the value related to the column: CONSIGNEE_ORG_ID
     * 
     * @param consigneeOrgId
     *                the CONSIGNEE_ORG_ID value
     */
    public void setConsigneeOrgId(long consigneeOrgId) {
        this.consigneeOrgId = consigneeOrgId;
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
     * Return the value associated with the column: RESOURCE_TYPE
     */
    public long getResourceType() {
        return resourceType;
    }

    /**
     * Set the value related to the column: RESOURCE_TYPE
     * 
     * @param resourceType
     *                the RESOURCE_TYPE value
     */
    public void setResourceType(long resourceType) {
        this.resourceType = resourceType;
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

    /**
     * Return the value associated with the column: VALID_TIME
     */
    public java.util.Date getValidTime() {
        return validTime;
    }

    /**
     * Set the value related to the column: VALID_TIME
     * 
     * @param validTime
     *                the VALID_TIME value
     */
    public void setValidTime(java.util.Date validTime) {
        this.validTime = validTime;
    }

    /**
     * Return the value associated with the column: EXPIRE_TIME
     */
    public java.util.Date getExpireTime() {
        return expireTime;
    }

    /**
     * Set the value related to the column: EXPIRE_TIME
     * 
     * @param expireTime
     *                the EXPIRE_TIME value
     */
    public void setExpireTime(java.util.Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Return the value associated with the column: CONSIGN_TIME
     */
    public java.util.Date getConsignTime() {
        return consignTime;
    }

    /**
     * Set the value related to the column: CONSIGN_TIME
     * 
     * @param consignTime
     *                the CONSIGN_TIME value
     */
    public void setConsignTime(java.util.Date consignTime) {
        this.consignTime = consignTime;
    }

    public String toString() {
        return super.toString();
    }

}