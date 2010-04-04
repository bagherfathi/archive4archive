/**
 * @{#} RelRoleOrg.java Create on 2006-12-18 15:18:04
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_REL_ROLE_ORG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_REL_ROLE_ORG"
 */


public class RelRoleOrg implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "RelRoleOrg";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_REL_ID = "relId";
	public static String PROP_ROLE_ID = "roleId";


	public RelRoleOrg () {
	    
	}


	// primary key
	private long relId;

	// fields
	private long roleId;
	private long orgId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="REL_ID"
     */
	public long getRelId () {
		return relId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param relId the new ID
	 */
	public void setRelId (long relId) {
		this.relId = relId;
	}




	/**
	 * Return the value associated with the column: ROLE_ID
	 */
	public long getRoleId () {
		return roleId;
	}

	/**
	 * Set the value related to the column: ROLE_ID
	 * @param roleId the ROLE_ID value
	 */
	public void setRoleId (long roleId) {
		this.roleId = roleId;
	}



	/**
	 * Return the value associated with the column: ORG_ID
	 */
	public long getOrgId () {
		return orgId;
	}

	/**
	 * Set the value related to the column: ORG_ID
	 * @param orgId the ORG_ID value
	 */
	public void setOrgId (long orgId) {
		this.orgId = orgId;
	}







	public String toString () {
		return super.toString();
	}


}