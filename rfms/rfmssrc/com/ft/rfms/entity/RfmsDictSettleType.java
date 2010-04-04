package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsDictSettleType.java Create on 2008-12-22 16:13:54
  * <p> 
  * 实体类:RfmsDictSettleType
  * <p>
  * 对应表名:RFMS_DICT_SETTLE_TYPE
  */

public class RfmsDictSettleType implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsDictSettleType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsDictSettleType (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsDictSettleType";
	public static String PROP_TEXT = "text";
	public static String PROP_ICONURL = "iconurl";
	public static String PROP_ID = "id";
	public static String PROP_ENGLISH = "english";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.lang.String english;
	private java.lang.String iconurl;
	private java.lang.String text;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="ID"
     */
	public java.lang.Long getId () {
		return id;
	}
 
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}
 


	/**
	 * Return the value associated with the column: ENGLISH
	 */
	public java.lang.String getEnglish () {
		return english;
	}

	/**
	 * Set the value related to the column: ENGLISH
	 * @param english the ENGLISH value
	 */
	public void setEnglish (java.lang.String english) {
		this.english = english;
	}



	/**
	 * Return the value associated with the column: ICONURL
	 */
	public java.lang.String getIconurl () {
		return iconurl;
	}

	/**
	 * Set the value related to the column: ICONURL
	 * @param iconurl the ICONURL value
	 */
	public void setIconurl (java.lang.String iconurl) {
		this.iconurl = iconurl;
	}



	/**
	 * Return the value associated with the column: TEXT
	 */
	public java.lang.String getText () {
		return text;
	}

	/**
	 * Set the value related to the column: TEXT
	 * @param text the TEXT value
	 */
	public void setText (java.lang.String text) {
		this.text = text;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsDictSettleType)) return false;
		else {
			RfmsDictSettleType rfmsDictSettleType = (RfmsDictSettleType) obj;
			if (null == this.getId() || null == rfmsDictSettleType.getId()) return false;
			else return (this.getId().equals(rfmsDictSettleType.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}