package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_AFFICHE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_AFFICHE"
 */


public class Affiche implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "Affiche";
	public static String PROP_AFFICHE_ID = "afficheId";
	public static String PROP_PUBLISH_TIME = "publishTime";
	public static String PROP_PUBLISHER_ID = "publisherId";
	public static String PROP_EXPIRE_TIME = "expireTime";
	public static String PROP_AFFICHE_STATUS = "afficheStatus";
	public static String PROP_AFFICHE_LEVEL = "afficheLevel";
	public static String PROP_VALID_TIME = "validTime";
	public static String PROP_AFFICHE_CONTENT = "afficheContent";
	public static String PROP_CATEGORY_ID = "categoryId";
	public static String PROP_AFFICHE_TITLE = "afficheTitle";


	public Affiche () {
	    
	}


	// primary key
	private long afficheId;

	// fields
	private java.lang.String afficheTitle;
	private java.lang.String afficheContent;
	private long publisherId;
	private long afficheLevel;
	private long afficheStatus;
	private java.util.Date validTime;
	private java.util.Date expireTime;
	private java.util.Date publishTime;
	private long categoryId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="AFFICHE_ID"
     */
	public long getAfficheId () {
		return afficheId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param afficheId the new ID
	 */
	public void setAfficheId (long afficheId) {
		this.afficheId = afficheId;
	}




	/**
	 * Return the value associated with the column: AFFICHE_TITLE
	 */
	public java.lang.String getAfficheTitle () {
		return afficheTitle;
	}

	/**
	 * Set the value related to the column: AFFICHE_TITLE
	 * @param afficheTitle the AFFICHE_TITLE value
	 */
	public void setAfficheTitle (java.lang.String afficheTitle) {
		this.afficheTitle = afficheTitle;
	}



	/**
	 * Return the value associated with the column: AFFICHE_CONTENT
	 */
	public java.lang.String getAfficheContent () {
		return afficheContent;
	}

	/**
	 * Set the value related to the column: AFFICHE_CONTENT
	 * @param afficheContent the AFFICHE_CONTENT value
	 */
	public void setAfficheContent (java.lang.String afficheContent) {
		this.afficheContent = afficheContent;
	}



	/**
	 * Return the value associated with the column: PUBLISHER_ID
	 */
	public long getPublisherId () {
		return publisherId;
	}

	/**
	 * Set the value related to the column: PUBLISHER_ID
	 * @param publisherId the PUBLISHER_ID value
	 */
	public void setPublisherId (long publisherId) {
		this.publisherId = publisherId;
	}



	/**
	 * Return the value associated with the column: AFFICHE_LEVEL
	 */
	public long getAfficheLevel () {
		return afficheLevel;
	}

	/**
	 * Set the value related to the column: AFFICHE_LEVEL
	 * @param afficheLevel the AFFICHE_LEVEL value
	 */
	public void setAfficheLevel (long afficheLevel) {
		this.afficheLevel = afficheLevel;
	}



	/**
	 * Return the value associated with the column: AFFICHE_STATUS
	 */
	public long getAfficheStatus () {
		return afficheStatus;
	}

	/**
	 * Set the value related to the column: AFFICHE_STATUS
	 * @param afficheStatus the AFFICHE_STATUS value
	 */
	public void setAfficheStatus (long afficheStatus) {
		this.afficheStatus = afficheStatus;
	}



	/**
	 * Return the value associated with the column: VALID_TIME
	 */
	public java.util.Date getValidTime () {
		return validTime;
	}

	/**
	 * Set the value related to the column: VALID_TIME
	 * @param validTime the VALID_TIME value
	 */
	public void setValidTime (java.util.Date validTime) {
		this.validTime = validTime;
	}



	/**
	 * Return the value associated with the column: EXPIRE_TIME
	 */
	public java.util.Date getExpireTime () {
		return expireTime;
	}

	/**
	 * Set the value related to the column: EXPIRE_TIME
	 * @param expireTime the EXPIRE_TIME value
	 */
	public void setExpireTime (java.util.Date expireTime) {
		this.expireTime = expireTime;
	}



	/**
	 * Return the value associated with the column: PUBLISH_TIME
	 */
	public java.util.Date getPublishTime () {
		return publishTime;
	}

	/**
	 * Set the value related to the column: PUBLISH_TIME
	 * @param publishTime the PUBLISH_TIME value
	 */
	public void setPublishTime (java.util.Date publishTime) {
		this.publishTime = publishTime;
	}



	/**
	 * Return the value associated with the column: CATEGORY_ID
	 */
	public long getCategoryId () {
		return categoryId;
	}

	/**
	 * Set the value related to the column: CATEGORY_ID
	 * @param categoryId the CATEGORY_ID value
	 */
	public void setCategoryId (long categoryId) {
		this.categoryId = categoryId;
	}







	public String toString () {
		return super.toString();
	}


}