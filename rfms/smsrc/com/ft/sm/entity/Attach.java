/**
 * @{#} Attach.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_ATTACH table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_ATTACH"
 */

public class Attach implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Attach";

    public static String PROP_FILE_PATH = "filePath";

    public static String PROP_ATTACH_ID = "attachId";

    public static String PROP_INFO_ID = "infoId";

    public static String PROP_FILE_NAME = "fileName";

    public Attach() {

    }

    // primary key
    private long attachId;

    // fields
    private long infoId;

    private java.lang.String fileName;

    private java.lang.String filePath;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="ATTACH_ID"
     */
    public long getAttachId() {
        return attachId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param attachId
     *                the new ID
     */
    public void setAttachId(long attachId) {
        this.attachId = attachId;
    }

    /**
     * Return the value associated with the column: INFO_ID
     */
    public long getInfoId() {
        return infoId;
    }

    /**
     * Set the value related to the column: INFO_ID
     * 
     * @param infoId
     *                the INFO_ID value
     */
    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    /**
     * Return the value associated with the column: FILE_NAME
     */
    public java.lang.String getFileName() {
        return fileName;
    }

    /**
     * Set the value related to the column: FILE_NAME
     * 
     * @param fileName
     *                the FILE_NAME value
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }

    /**
     * Return the value associated with the column: FILE_PATH
     */
    public java.lang.String getFilePath() {
        return filePath;
    }

    /**
     * Set the value related to the column: FILE_PATH
     * 
     * @param filePath
     *                the FILE_PATH value
     */
    public void setFilePath(java.lang.String filePath) {
        this.filePath = filePath;
    }

    public String toString() {
        return super.toString();
    }

}