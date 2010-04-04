package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsMember.java Create on 2010-4-4 19:16:28
  * <p> 
  * 实体类:RfmsMember
  * <p>
  * 对应表名:RFMS_MEMBER
  */

public class RfmsMember implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsMember () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsMember (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsMember";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_PWD = "pwd";
	public static String PROP_BIRTH_DATE = "birthDate";
	public static String PROP_ZIPCODE = "zipcode";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_NAME = "name";
	public static String PROP_STATUS = "status";
	public static String PROP_EMAIL = "email";
	public static String PROP_MEMBER_TYPE = "memberType";
	public static String PROP_ADDRESS = "address";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_TEL = "tel";
	public static String PROP_SEX = "sex";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.lang.String mobile;
	private java.lang.String name;
	private java.lang.String sex;
	private java.util.Date birthDate;
	private java.lang.String tel;
	private java.lang.String email;
	private java.lang.String address;
	private java.lang.String zipcode;
	private java.lang.String status;
	private java.lang.Long memberType;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private java.lang.Long operatorId;
	private java.lang.String pwd;



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
	 * Return the value associated with the column: MOBILE
	 */
	public java.lang.String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: MOBILE
	 * @param mobile the MOBILE value
	 */
	public void setMobile (java.lang.String mobile) {
		this.mobile = mobile;
	}



	/**
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: SEX
	 */
	public java.lang.String getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: SEX
	 * @param sex the SEX value
	 */
	public void setSex (java.lang.String sex) {
		this.sex = sex;
	}



	/**
	 * Return the value associated with the column: BIRTH_DATE
	 */
	public java.util.Date getBirthDate () {
		return birthDate;
	}

	/**
	 * Set the value related to the column: BIRTH_DATE
	 * @param birthDate the BIRTH_DATE value
	 */
	public void setBirthDate (java.util.Date birthDate) {
		this.birthDate = birthDate;
	}



	/**
	 * Return the value associated with the column: TEL
	 */
	public java.lang.String getTel () {
		return tel;
	}

	/**
	 * Set the value related to the column: TEL
	 * @param tel the TEL value
	 */
	public void setTel (java.lang.String tel) {
		this.tel = tel;
	}



	/**
	 * Return the value associated with the column: EMAIL
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: EMAIL
	 * @param email the EMAIL value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: ADDRESS
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: ADDRESS
	 * @param address the ADDRESS value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}



	/**
	 * Return the value associated with the column: ZIPCODE
	 */
	public java.lang.String getZipcode () {
		return zipcode;
	}

	/**
	 * Set the value related to the column: ZIPCODE
	 * @param zipcode the ZIPCODE value
	 */
	public void setZipcode (java.lang.String zipcode) {
		this.zipcode = zipcode;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: MEMBER_TYPE
	 */
	public java.lang.Long getMemberType () {
		return memberType;
	}

	/**
	 * Set the value related to the column: MEMBER_TYPE
	 * @param memberType the MEMBER_TYPE value
	 */
	public void setMemberType (java.lang.Long memberType) {
		this.memberType = memberType;
	}



	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: UPDATE_DATE
	 */
	public java.util.Date getUpdateDate () {
		return updateDate;
	}

	/**
	 * Set the value related to the column: UPDATE_DATE
	 * @param updateDate the UPDATE_DATE value
	 */
	public void setUpdateDate (java.util.Date updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * Return the value associated with the column: OPERATOR_ID
	 */
	public java.lang.Long getOperatorId () {
		return operatorId;
	}

	/**
	 * Set the value related to the column: OPERATOR_ID
	 * @param operatorId the OPERATOR_ID value
	 */
	public void setOperatorId (java.lang.Long operatorId) {
		this.operatorId = operatorId;
	}



	/**
	 * Return the value associated with the column: PWD
	 */
	public java.lang.String getPwd () {
		return pwd;
	}

	/**
	 * Set the value related to the column: PWD
	 * @param pwd the PWD value
	 */
	public void setPwd (java.lang.String pwd) {
		this.pwd = pwd;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsMember)) return false;
		else {
			RfmsMember rfmsMember = (RfmsMember) obj;
			if (null == this.getId() || null == rfmsMember.getId()) return false;
			else return (this.getId().equals(rfmsMember.getId()));
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