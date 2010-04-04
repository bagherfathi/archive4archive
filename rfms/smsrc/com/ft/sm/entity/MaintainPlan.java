/**
 * @{#} MaintainPlan.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_MAINTAIN_PLAN table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_MAINTAIN_PLAN"
 */

public class MaintainPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "MaintainPlan";

    public static String PROP_CYCLE_UNIT = "cycleUnit";

    public static String PROP_PLAN_DESC = "planDesc";

    public static String PROP_PLAN_CYCLE = "planCycle";

    public static String PROP_PLAN_ID = "planId";

    public static String PROP_PLAN_NAME = "planName";

    public MaintainPlan() {

    }

    // primary key
    private long planId;

    // fields
    private java.lang.String planName;

    private java.lang.String planDesc;

    private long planCycle;

    private long cycleUnit;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="PLAN_ID"
     */
    public long getPlanId() {
        return planId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param planId
     *                the new ID
     */
    public void setPlanId(long planId) {
        this.planId = planId;
    }

    /**
     * Return the value associated with the column: PLAN_NAME
     */
    public java.lang.String getPlanName() {
        return planName;
    }

    /**
     * Set the value related to the column: PLAN_NAME
     * 
     * @param planName
     *                the PLAN_NAME value
     */
    public void setPlanName(java.lang.String planName) {
        this.planName = planName;
    }

    /**
     * Return the value associated with the column: PLAN_DESC
     */
    public java.lang.String getPlanDesc() {
        return planDesc;
    }

    /**
     * Set the value related to the column: PLAN_DESC
     * 
     * @param planDesc
     *                the PLAN_DESC value
     */
    public void setPlanDesc(java.lang.String planDesc) {
        this.planDesc = planDesc;
    }

    /**
     * Return the value associated with the column: PLAN_CYCLE
     */
    public long getPlanCycle() {
        return planCycle;
    }

    /**
     * Set the value related to the column: PLAN_CYCLE
     * 
     * @param planCycle
     *                the PLAN_CYCLE value
     */
    public void setPlanCycle(long planCycle) {
        this.planCycle = planCycle;
    }

    /**
     * Return the value associated with the column: CYCLE_UNIT
     */
    public long getCycleUnit() {
        return cycleUnit;
    }

    /**
     * Set the value related to the column: CYCLE_UNIT
     * 
     * @param cycleUnit
     *                the CYCLE_UNIT value
     */
    public void setCycleUnit(long cycleUnit) {
        this.cycleUnit = cycleUnit;
    }

    public String toString() {
        return super.toString();
    }

}