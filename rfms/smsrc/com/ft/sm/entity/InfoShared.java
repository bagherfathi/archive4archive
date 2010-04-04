/**
 * @{#} InfoShared.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_INFO_SHARED table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_INFO_SHARED"
 */

public class InfoShared implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "InfoShared";

    public static String PROP_PUBLISH_TIME = "publishTime";

    public static String PROP_INFO_ID = "infoId";

    public static String PROP_EXPIRE_TIME = "expireTime";

    public static String PROP_INFO_CONTENT = "infoContent";

    public static String PROP_VALID_TIME = "validTime";

    public static String PROP_INCEPT_ORGS = "inceptOrgs";

    public static String PROP_AUTHOR_ID = "authorId";

    public static String PROP_CATEGORY_ID = "categoryId";

    public static String PROP_INFO_TITLE = "infoTitle";

    public InfoShared() {

    }

    // primary key
    private long infoId;

    // fields
    private long categoryId;

    private java.lang.String infoTitle;

    private long authorId;

    private java.lang.String infoContent;

    private java.lang.String inceptOrgs;

    private java.util.Date validTime;

    private java.util.Date expireTime;

    private java.util.Date publishTime;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="INFO_ID"
     */
    public long getInfoId() {
        return infoId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param infoId
     *                the new ID
     */
    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    /**
     * Return the value associated with the column: CATEGORY_ID
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * Set the value related to the column: CATEGORY_ID
     * 
     * @param categoryId
     *                the CATEGORY_ID value
     */
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Return the value associated with the column: INFO_TITLE
     */
    public java.lang.String getInfoTitle() {
        return infoTitle;
    }

    /**
     * Set the value related to the column: INFO_TITLE
     * 
     * @param infoTitle
     *                the INFO_TITLE value
     */
    public void setInfoTitle(java.lang.String infoTitle) {
        this.infoTitle = infoTitle;
    }

    /**
     * Return the value associated with the column: AUTHOR_ID
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * Set the value related to the column: AUTHOR_ID
     * 
     * @param authorId
     *                the AUTHOR_ID value
     */
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    /**
     * Return the value associated with the column: INFO_CONTENT
     */
    public java.lang.String getInfoContent() {
        return infoContent;
    }

    /**
     * Set the value related to the column: INFO_CONTENT
     * 
     * @param infoContent
     *                the INFO_CONTENT value
     */
    public void setInfoContent(java.lang.String infoContent) {
        this.infoContent = infoContent;
    }

    /**
     * Return the value associated with the column: INCEPT_ORGS
     */
    public java.lang.String getInceptOrgs() {
        return inceptOrgs;
    }

    /**
     * Set the value related to the column: INCEPT_ORGS
     * 
     * @param inceptOrgs
     *                the INCEPT_ORGS value
     */
    public void setInceptOrgs(java.lang.String inceptOrgs) {
        this.inceptOrgs = inceptOrgs;
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
     * Return the value associated with the column: PUBLISH_TIME
     */
    public java.util.Date getPublishTime() {
        return publishTime;
    }

    /**
     * Set the value related to the column: PUBLISH_TIME
     * 
     * @param publishTime
     *                the PUBLISH_TIME value
     */
    public void setPublishTime(java.util.Date publishTime) {
        this.publishTime = publishTime;
    }

    public String toString() {
        return super.toString();
    }

}