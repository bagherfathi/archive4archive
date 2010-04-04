/**
 * @{#} RelAfficheOrg.java Create on 2006-12-20 21:40:49
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_REL_AFFICHE_ORG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_REL_AFFICHE_ORG"
 */


public class RelAfficheOrg implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "RelAfficheOrg";
	public static String PROP_AFFICHE_ID = "afficheId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_REL_ID = "relId";


	public RelAfficheOrg () {
	    
	}


	// primary key
	private long relId;

	// fields
	private long orgId;
	private long afficheId;



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



	/**
	 * Return the value associated with the column: AFFICHE_ID
	 */
	public long getAfficheId () {
		return afficheId;
	}

	/**
	 * Set the value related to the column: AFFICHE_ID
	 * @param afficheId the AFFICHE_ID value
	 */
	public void setAfficheId (long afficheId) {
		this.afficheId = afficheId;
	}







	public String toString () {
		return super.toString();
	}


}