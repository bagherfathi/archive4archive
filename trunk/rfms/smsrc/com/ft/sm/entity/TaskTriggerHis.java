/**
 * @{#} TaskTriggerHis.java Create on 2006-12-30 12:08:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_TASK_TRIGGER_HIS table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_TASK_TRIGGER_HIS"
 */


public class TaskTriggerHis implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "TaskTriggerHis";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_METHOD_NAME = "methodName";
	public static String PROP_START_TIME = "startTime";
	public static String PROP_TRIGGER_DESC = "triggerDesc";
	public static String PROP_TASK_NAME = "taskName";
	public static String PROP_CLASS_NAME = "className";
	public static String PROP_TASK_ID = "taskId";
	public static String PROP_TRIGGER_ID = "triggerId";
	public static String PROP_HIS_ID = "hisId";


	public TaskTriggerHis () {
	    
	}


	// primary key
	private long hisId;

	// fields
	private long triggerId;
	private long taskId;
	private java.lang.String taskName;
	private java.lang.String className;
	private java.lang.String methodName;
	private java.lang.String triggerDesc;
	private java.util.Date endTime;
	private java.util.Date startTime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="HIS_ID"
     */
	public long getHisId () {
		return hisId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param hisId the new ID
	 */
	public void setHisId (long hisId) {
		this.hisId = hisId;
	}




	/**
	 * Return the value associated with the column: TRIGGER_ID
	 */
	public long getTriggerId () {
		return triggerId;
	}

	/**
	 * Set the value related to the column: TRIGGER_ID
	 * @param triggerId the TRIGGER_ID value
	 */
	public void setTriggerId (long triggerId) {
		this.triggerId = triggerId;
	}



	/**
	 * Return the value associated with the column: TASK_ID
	 */
	public long getTaskId () {
		return taskId;
	}

	/**
	 * Set the value related to the column: TASK_ID
	 * @param taskId the TASK_ID value
	 */
	public void setTaskId (long taskId) {
		this.taskId = taskId;
	}



	/**
	 * Return the value associated with the column: TASK_NAME
	 */
	public java.lang.String getTaskName () {
		return taskName;
	}

	/**
	 * Set the value related to the column: TASK_NAME
	 * @param taskName the TASK_NAME value
	 */
	public void setTaskName (java.lang.String taskName) {
		this.taskName = taskName;
	}



	/**
	 * Return the value associated with the column: CLASS_NAME
	 */
	public java.lang.String getClassName () {
		return className;
	}

	/**
	 * Set the value related to the column: CLASS_NAME
	 * @param className the CLASS_NAME value
	 */
	public void setClassName (java.lang.String className) {
		this.className = className;
	}



	/**
	 * Return the value associated with the column: METHOD_NAME
	 */
	public java.lang.String getMethodName () {
		return methodName;
	}

	/**
	 * Set the value related to the column: METHOD_NAME
	 * @param methodName the METHOD_NAME value
	 */
	public void setMethodName (java.lang.String methodName) {
		this.methodName = methodName;
	}



	/**
	 * Return the value associated with the column: TRIGGER_DESC
	 */
	public java.lang.String getTriggerDesc () {
		return triggerDesc;
	}

	/**
	 * Set the value related to the column: TRIGGER_DESC
	 * @param triggerDesc the TRIGGER_DESC value
	 */
	public void setTriggerDesc (java.lang.String triggerDesc) {
		this.triggerDesc = triggerDesc;
	}



	/**
	 * Return the value associated with the column: END_TIME
	 */
	public java.util.Date getEndTime () {
		return endTime;
	}

	/**
	 * Set the value related to the column: END_TIME
	 * @param endTime the END_TIME value
	 */
	public void setEndTime (java.util.Date endTime) {
		this.endTime = endTime;
	}



	/**
	 * Return the value associated with the column: START_TIME
	 */
	public java.util.Date getStartTime () {
		return startTime;
	}

	/**
	 * Set the value related to the column: START_TIME
	 * @param startTime the START_TIME value
	 */
	public void setStartTime (java.util.Date startTime) {
		this.startTime = startTime;
	}







	public String toString () {
		return super.toString();
	}


}