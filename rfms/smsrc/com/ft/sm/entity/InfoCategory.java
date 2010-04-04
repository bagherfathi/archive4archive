/**
 * @{#} InfoCategory.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_INFO_CATEGORY table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_INFO_CATEGORY"
 */

public class InfoCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "InfoCategory";

    public static String PROP_CATEGORY_DESC = "categoryDesc";

    public static String PROP_CATEGORY_ID = "categoryId";

    public static String PROP_CATEGORY_NAME = "categoryName";

    public InfoCategory() {

    }

    // primary key
    private long categoryId;

    // fields
    private java.lang.String categoryName;

    private java.lang.String categoryDesc;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="CATEGORY_ID"
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param categoryId
     *                the new ID
     */
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Return the value associated with the column: CATEGORY_NAME
     */
    public java.lang.String getCategoryName() {
        return categoryName;
    }

    /**
     * Set the value related to the column: CATEGORY_NAME
     * 
     * @param categoryName
     *                the CATEGORY_NAME value
     */
    public void setCategoryName(java.lang.String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Return the value associated with the column: CATEGORY_DESC
     */
    public java.lang.String getCategoryDesc() {
        return categoryDesc;
    }

    /**
     * Set the value related to the column: CATEGORY_DESC
     * 
     * @param categoryDesc
     *                the CATEGORY_DESC value
     */
    public void setCategoryDesc(java.lang.String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String toString() {
        return super.toString();
    }

}