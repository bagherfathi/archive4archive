/**
 * @{#} BusiCodeGen.java Create on 2006-11-14 17:47:25
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_BUSI_CODE_GEN table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_BUSI_CODE_GEN"
 */

public class BusiCodeGen implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "BusiCodeGen";

    public static String PROP_CREATE_OP = "createOp";

    public static String PROP_BUSI_CODE_GEN_CODE = "busiCodeGenCode";

    public static String PROP_UPDATE_OP = "updateOp";

    public static String PROP_CURRENT_VALUE = "currentValue";

    public static String PROP_BUSI_CODE_RULE_ID = "busiCodeRuleId";

    public static String PROP_BUSI_CODE_GEN_ID = "busiCodeGenId";

    public static String PROP_CREATE_DAY = "createDay";

    public static String PROP_NEXT_VALUE = "nextValue";

    public static String PROP_UPDATE_DAY = "updateDay";

    public BusiCodeGen() {

    }

    // primary key
    private long busiCodeGenId;

    // fields
    private long busiCodeRuleId;

    private java.lang.String currentValue;

    private java.lang.String nextValue;

    private java.util.Date updateDay;

    private java.lang.String updateOp;

    private java.util.Date createDay;

    private java.lang.String createOp;

    private java.lang.String busiCodeGenCode;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="BUSI_CODE_GEN_ID"
     */
    public long getBusiCodeGenId() {
        return busiCodeGenId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param busiCodeGenId
     *                the new ID
     */
    public void setBusiCodeGenId(long busiCodeGenId) {
        this.busiCodeGenId = busiCodeGenId;
    }

    /**
     * Return the value associated with the column: BUSI_CODE_RULE_ID
     */
    public long getBusiCodeRuleId() {
        return busiCodeRuleId;
    }

    /**
     * Set the value related to the column: BUSI_CODE_RULE_ID
     * 
     * @param busiCodeRuleId
     *                the BUSI_CODE_RULE_ID value
     */
    public void setBusiCodeRuleId(long busiCodeRuleId) {
        this.busiCodeRuleId = busiCodeRuleId;
    }

    /**
     * Return the value associated with the column: CURRENT_VALUE
     */
    public java.lang.String getCurrentValue() {
        return currentValue;
    }

    /**
     * Set the value related to the column: CURRENT_VALUE
     * 
     * @param currentValue
     *                the CURRENT_VALUE value
     */
    public void setCurrentValue(java.lang.String currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Return the value associated with the column: NEXT_VALUE
     */
    public java.lang.String getNextValue() {
        return nextValue;
    }

    /**
     * Set the value related to the column: NEXT_VALUE
     * 
     * @param nextValue
     *                the NEXT_VALUE value
     */
    public void setNextValue(java.lang.String nextValue) {
        this.nextValue = nextValue;
    }

    /**
     * Return the value associated with the column: UPDATE_DAY
     */
    public java.util.Date getUpdateDay() {
        return updateDay;
    }

    /**
     * Set the value related to the column: UPDATE_DAY
     * 
     * @param updateDay
     *                the UPDATE_DAY value
     */
    public void setUpdateDay(java.util.Date updateDay) {
        this.updateDay = updateDay;
    }

    /**
     * Return the value associated with the column: UPDATE_OP
     */
    public java.lang.String getUpdateOp() {
        return updateOp;
    }

    /**
     * Set the value related to the column: UPDATE_OP
     * 
     * @param updateOp
     *                the UPDATE_OP value
     */
    public void setUpdateOp(java.lang.String updateOp) {
        this.updateOp = updateOp;
    }

    /**
     * Return the value associated with the column: CREATE_DAY
     */
    public java.util.Date getCreateDay() {
        return createDay;
    }

    /**
     * Set the value related to the column: CREATE_DAY
     * 
     * @param createDay
     *                the CREATE_DAY value
     */
    public void setCreateDay(java.util.Date createDay) {
        this.createDay = createDay;
    }

    /**
     * Return the value associated with the column: CREATE_OP
     */
    public java.lang.String getCreateOp() {
        return createOp;
    }

    /**
     * Set the value related to the column: CREATE_OP
     * 
     * @param createOp
     *                the CREATE_OP value
     */
    public void setCreateOp(java.lang.String createOp) {
        this.createOp = createOp;
    }

    /**
     * Return the value associated with the column: BUSI_CODE_GEN_CODE
     */
    public java.lang.String getBusiCodeGenCode() {
        return busiCodeGenCode;
    }

    /**
     * Set the value related to the column: BUSI_CODE_GEN_CODE
     * 
     * @param busiCodeGenCode
     *                the BUSI_CODE_GEN_CODE value
     */
    public void setBusiCodeGenCode(java.lang.String busiCodeGenCode) {
        this.busiCodeGenCode = busiCodeGenCode;
    }

    public String toString() {
        return super.toString();
    }

}