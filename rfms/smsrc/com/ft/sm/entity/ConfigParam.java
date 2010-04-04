/**
 * @{#} ConfigParam.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_CONFIG_PARAM table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_CONFIG_PARAM"
 */

public class ConfigParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "ConfigParam";

    public static String PROP_CONFIG_NAME = "configName";

    public static String PROP_PARENT_ID = "parentId";

    public static String PROP_CONFIG_ID = "configId";

    public static String PROP_CONFIG_TYPE = "configType";

    public static String PROP_CONFIG_PATH = "configPath";

    public static String PROP_CONFIG_CODE = "configCode";

    public static String PROP_CONFIG_VALUE = "configValue";

    public static String PROP_CONFIG_CATEGORY = "configCategory";

    public ConfigParam() {

    }

    // primary key
    private long configId;

    // fields
    private java.lang.String configName;

    private java.lang.String configValue;

    private java.lang.String configCategory;

    private java.lang.String configCode;

    private long parentId;

    private java.lang.String configPath;

    private long configType;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="CONFIG_ID"
     */
    public long getConfigId() {
        return configId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param configId
     *                the new ID
     */
    public void setConfigId(long configId) {
        this.configId = configId;
    }

    /**
     * Return the value associated with the column: CONFIG_NAME
     */
    public java.lang.String getConfigName() {
        return configName;
    }

    /**
     * Set the value related to the column: CONFIG_NAME
     * 
     * @param configName
     *                the CONFIG_NAME value
     */
    public void setConfigName(java.lang.String configName) {
        this.configName = configName;
    }

    /**
     * Return the value associated with the column: CONFIG_VALUE
     */
    public java.lang.String getConfigValue() {
        return configValue;
    }

    /**
     * Set the value related to the column: CONFIG_VALUE
     * 
     * @param configValue
     *                the CONFIG_VALUE value
     */
    public void setConfigValue(java.lang.String configValue) {
        this.configValue = configValue;
    }

    /**
     * Return the value associated with the column: CONFIG_CATEGORY
     */
    public java.lang.String getConfigCategory() {
        return configCategory;
    }

    /**
     * Set the value related to the column: CONFIG_CATEGORY
     * 
     * @param configCategory
     *                the CONFIG_CATEGORY value
     */
    public void setConfigCategory(java.lang.String configCategory) {
        this.configCategory = configCategory;
    }

    /**
     * Return the value associated with the column: CONFIG_CODE
     */
    public java.lang.String getConfigCode() {
        return configCode;
    }

    /**
     * Set the value related to the column: CONFIG_CODE
     * 
     * @param configCode
     *                the CONFIG_CODE value
     */
    public void setConfigCode(java.lang.String configCode) {
        this.configCode = configCode;
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
     * Return the value associated with the column: CONFIG_PATH
     */
    public java.lang.String getConfigPath() {
        return configPath;
    }

    /**
     * Set the value related to the column: CONFIG_PATH
     * 
     * @param configPath
     *                the CONFIG_PATH value
     */
    public void setConfigPath(java.lang.String configPath) {
        this.configPath = configPath;
    }

    /**
     * Return the value associated with the column: CONFIG_TYPE
     */
    public long getConfigType() {
        return configType;
    }

    /**
     * Set the value related to the column: CONFIG_TYPE
     * 
     * @param configType
     *                the CONFIG_TYPE value
     */
    public void setConfigType(long configType) {
        this.configType = configType;
    }

    public String toString() {
        return super.toString();
    }

}