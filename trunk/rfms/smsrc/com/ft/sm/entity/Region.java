/**
 * @{#} Region.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_REGION table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_REGION"
 */

public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Region";

    public static String PROP_STATUS = "status";

    public static String PROP_PARENT_ID = "parentId";

    public static String PROP_REGION_NAME = "regionName";

    public static String PROP_REGION_TYPE = "regionType";

    public static String PROP_REGION_PATH = "regionPath";

    public static String PROP_REGION_CODE = "regionCode";

    public static String PROP_REGION_ID = "regionId";

    public static String PROP_YSNAME = "ysname";

    public static String PROP_YSCODE = "yscode";
    
    public Region() {

    }

    // primary key
    private long regionId;

    // fields
    private java.lang.String regionName;

    private java.lang.String regionCode;

    private long parentId;

    private java.lang.String regionPath;

    private long regionType;

    private long status;
    
    private java.lang.String ysname;

    private java.lang.String yscode;

    /**
	 * @return the yscode
	 */
	public java.lang.String getYscode() {
		return yscode;
	}

	/**
	 * @param yscode the yscode to set
	 */
	public void setYscode(java.lang.String yscode) {
		this.yscode = yscode;
	}

	/**
	 * @return the ysname
	 */
	public java.lang.String getYsname() {
		return ysname;
	}

	/**
	 * @param ysname the ysname to set
	 */
	public void setYsname(java.lang.String ysname) {
		this.ysname = ysname;
	}

	/**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="REGION_ID"
     */
    public long getRegionId() {
        return regionId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param regionId
     *                the new ID
     */
    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    /**
     * Return the value associated with the column: REGION_NAME
     */
    public java.lang.String getRegionName() {
        return regionName;
    }

    /**
     * Set the value related to the column: REGION_NAME
     * 
     * @param regionName
     *                the REGION_NAME value
     */
    public void setRegionName(java.lang.String regionName) {
        this.regionName = regionName;
    }

    /**
     * Return the value associated with the column: REGION_CODE
     */
    public java.lang.String getRegionCode() {
        return regionCode;
    }

    /**
     * Set the value related to the column: REGION_CODE
     * 
     * @param regionCode
     *                the REGION_CODE value
     */
    public void setRegionCode(java.lang.String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * Return the value associated with the column: PARENT_ID
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * Set the value related to the column: PARENT_ID
     * 
     * @param parentId
     *                the PARENT_ID value
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * Return the value associated with the column: REGION_PATH
     */
    public java.lang.String getRegionPath() {
        return regionPath;
    }

    /**
     * Set the value related to the column: REGION_PATH
     * 
     * @param regionPath
     *                the REGION_PATH value
     */
    public void setRegionPath(java.lang.String regionPath) {
        this.regionPath = regionPath;
    }

    /**
     * Return the value associated with the column: REGION_TYPE
     */
    public long getRegionType() {
        return regionType;
    }

    /**
     * Set the value related to the column: REGION_TYPE
     * 
     * @param regionType
     *                the REGION_TYPE value
     */
    public void setRegionType(long regionType) {
        this.regionType = regionType;
    }

    /**
     * Return the value associated with the column: STATUS
     */
    public long getStatus() {
        return status;
    }

    /**
     * Set the value related to the column: STATUS
     * 
     * @param status
     *                the STATUS value
     */
    public void setStatus(long status) {
        this.status = status;
    }

    public String toString() {
        return super.toString();
    }

}