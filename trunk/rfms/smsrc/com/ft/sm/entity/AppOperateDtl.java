/**
 * @{#} AppOperateDtl.java Create on 2006-10-1 12:14:03
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CS_APP_OPERATE_DTL table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="CS_APP_OPERATE_DTL"
 */


public class AppOperateDtl implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "AppOperateDtl";
	public static String PROP_APP_ID = "appId";
	public static String PROP_OBJECT_ID = "objectId";
	public static String PROP_OPERATE_DTL_ID = "operateDtlId";
	public static String PROP_CLASS_NAME = "className";
	public static String PROP_OPERATOR_TYPE="operatorType";


	public AppOperateDtl () {
	    
	}


	// primary key
	private long operateDtlId;

	// fields
	private long appId;
	private java.lang.String className;
	private long objectId;
    private long operatorType; 


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="OPERATE_DTL_ID"
     */
	public long getOperateDtlId () {
		return operateDtlId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param operateDtlId the new ID
	 */
	public void setOperateDtlId (long operateDtlId) {
		this.operateDtlId = operateDtlId;
	}




	/**
	 * Return the value associated with the column: APP_ID
	 */
	public long getAppId () {
		return appId;
	}

	/**
	 * Set the value related to the column: APP_ID
	 * @param appId the APP_ID value
	 */
	public void setAppId (long appId) {
		this.appId = appId;
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
	 * Return the value associated with the column: OBJECT_ID
	 */
	public long getObjectId () {
		return objectId;
	}

	/**
	 * Set the value related to the column: OBJECT_ID
	 * @param objectId the OBJECT_ID value
	 */
	public void setObjectId (long objectId) {
		this.objectId = objectId;
	}







	/**
	 * ∑µªÿoperatorType°£
	 * @return Returns the operatorType.
	 */
	public long getOperatorType() {
		return operatorType;
	}

	/**
	 * …Ë÷√operatorType µΩ operatorType
	 * @param operatorType The operatorType to set.
	 */
	public void setOperatorType(long operatorType) {
		this.operatorType = operatorType;
	}

	public String toString () {
		return super.toString();
	}


}