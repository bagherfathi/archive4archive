/**
 * @{#} BusiCodeRule.java Create on 2006-11-14 17:47:25
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_BUSI_CODE_RULE table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_BUSI_CODE_RULE"
 */

public class BusiCodeRule implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "BusiCodeRule";

    public static String PROP_TYPE = "type";

    public static String PROP_CREATE_OP = "createOp";

    public static String PROP_MAX_VALUE = "maxValue";

    public static String PROP_PREFIX = "prefix";

    public static String PROP_UPDATE_OP = "updateOp";

    public static String PROP_CREATE_DAY = "createDay";

    public static String PROP_MIN_VALUE = "minValue";

    public static String PROP_UPDATE_DAY = "updateDay";

    public static String PROP_SUFFIX = "suffix";

    public static String PROP_BUSI_CODE_RULE_NAME = "busiCodeRuleName";

    public static String PROP_IS_CYC_DAY = "isCycDay";

    public static String PROP_BUSI_CODE_RULE_CODE = "busiCodeRuleCode";

    public static String PROP_NEED_WAD = "needWad";

    public static String PROP_BUSI_CODE_RULE_ID = "busiCodeRuleId";

    public static String PROP_WAD_CHAR = "wadChar";

    public static String PROP_BUSI_CODE_RULE_DESC = "busiCodeRuleDesc";

    public static String PROP_RULE_TYPE = "ruleType";

    public BusiCodeRule() {

    }

    // primary key
    private long busiCodeRuleId;

    // fields
    private java.lang.String busiCodeRuleName;

    private java.lang.String busiCodeRuleCode;

    private java.lang.String busiCodeRuleDesc;

    private long type;

    private long ruleType;

    private java.lang.String prefix;

    private java.lang.String suffix;

    private java.lang.String minValue;

    private java.lang.String maxValue;

    private long needWad;

    private java.lang.String wadChar;

    private long isCycDay;

    private java.util.Date updateDay;

    private java.lang.String updateOp;

    private java.util.Date createDay;

    private java.lang.String createOp;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="BUSI_CODE_RULE_ID"
     */
    public long getBusiCodeRuleId() {
        return busiCodeRuleId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param busiCodeRuleId
     *                the new ID
     */
    public void setBusiCodeRuleId(long busiCodeRuleId) {
        this.busiCodeRuleId = busiCodeRuleId;
    }

    /**
     * Return the value associated with the column: BUSI_CODE_RULE_NAME
     */
    public java.lang.String getBusiCodeRuleName() {
        return busiCodeRuleName;
    }

    /**
     * Set the value related to the column: BUSI_CODE_RULE_NAME
     * 
     * @param busiCodeRuleName
     *                the BUSI_CODE_RULE_NAME value
     */
    public void setBusiCodeRuleName(java.lang.String busiCodeRuleName) {
        this.busiCodeRuleName = busiCodeRuleName;
    }

    /**
     * Return the value associated with the column: BUSI_CODE_RULE_CODE
     */
    public java.lang.String getBusiCodeRuleCode() {
        return busiCodeRuleCode;
    }

    /**
     * Set the value related to the column: BUSI_CODE_RULE_CODE
     * 
     * @param busiCodeRuleCode
     *                the BUSI_CODE_RULE_CODE value
     */
    public void setBusiCodeRuleCode(java.lang.String busiCodeRuleCode) {
        this.busiCodeRuleCode = busiCodeRuleCode;
    }

    /**
     * Return the value associated with the column: BUSI_CODE_RULE_DESC
     */
    public java.lang.String getBusiCodeRuleDesc() {
        return busiCodeRuleDesc;
    }

    /**
     * Set the value related to the column: BUSI_CODE_RULE_DESC
     * 
     * @param busiCodeRuleDesc
     *                the BUSI_CODE_RULE_DESC value
     */
    public void setBusiCodeRuleDesc(java.lang.String busiCodeRuleDesc) {
        this.busiCodeRuleDesc = busiCodeRuleDesc;
    }

    /**
     * Return the value associated with the column: TYPE
     */
    public long getType() {
        return type;
    }

    /**
     * Set the value related to the column: TYPE
     * 
     * @param type
     *                the TYPE value
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     * Return the value associated with the column: RULE_TYPE
     */
    public long getRuleType() {
        return ruleType;
    }

    /**
     * Set the value related to the column: RULE_TYPE
     * 
     * @param type
     *                the ruleType value
     */
    public void setRuleType(long ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * Return the value associated with the column: PREFIX
     */
    public java.lang.String getPrefix() {
        return prefix;
    }

    /**
     * Set the value related to the column: PREFIX
     * 
     * @param prefix
     *                the PREFIX value
     */
    public void setPrefix(java.lang.String prefix) {
        this.prefix = prefix;
    }

    /**
     * Return the value associated with the column: SUFFIX
     */
    public java.lang.String getSuffix() {
        return suffix;
    }

    /**
     * Set the value related to the column: SUFFIX
     * 
     * @param suffix
     *                the SUFFIX value
     */
    public void setSuffix(java.lang.String suffix) {
        this.suffix = suffix;
    }

    /**
     * Return the value associated with the column: MIN_VALUE
     */
    public java.lang.String getMinValue() {
        return minValue;
    }

    /**
     * Set the value related to the column: MIN_VALUE
     * 
     * @param minValue
     *                the MIN_VALUE value
     */
    public void setMinValue(java.lang.String minValue) {
        this.minValue = minValue;
    }

    /**
     * Return the value associated with the column: MAX_VALUE
     */
    public java.lang.String getMaxValue() {
        return maxValue;
    }

    /**
     * Set the value related to the column: MAX_VALUE
     * 
     * @param maxValue
     *                the MAX_VALUE value
     */
    public void setMaxValue(java.lang.String maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Return the value associated with the column: NEED_WAD
     */
    public long getNeedWad() {
        return needWad;
    }

    /**
     * Set the value related to the column: NEED_WAD
     * 
     * @param needWad
     *                the NEED_WAD value
     */
    public void setNeedWad(long needWad) {
        this.needWad = needWad;
    }

    /**
     * Return the value associated with the column: WAD_CHAR
     */
    public java.lang.String getWadChar() {
        return wadChar;
    }

    /**
     * Set the value related to the column: WAD_CHAR
     * 
     * @param wadChar
     *                the WAD_CHAR value
     */
    public void setWadChar(java.lang.String wadChar) {
        this.wadChar = wadChar;
    }

    /**
     * Return the value associated with the column: IS_CYC_DAY
     */
    public long getIsCycDay() {
        return isCycDay;
    }

    /**
     * Set the value related to the column: IS_CYC_DAY
     * 
     * @param isCycDay
     *                the IS_CYC_DAY value
     */
    public void setIsCycDay(long isCycDay) {
        this.isCycDay = isCycDay;
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

    public String toString() {
        return super.toString();
    }

}