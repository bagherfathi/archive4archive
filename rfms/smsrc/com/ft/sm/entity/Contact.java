/**
 * @{#} Contact.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.sm.entity;

import java.io.Serializable;

/**
 * 联系人实体。
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = -829386705059497266L;

    private String name;

    private String mobilePhone;

    private String telephone;

    private String postCode;

    private String address;

    public Contact(String name, String mobilePhone, String telephone,
            String postCode, String address) {
        super();
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.telephone = telephone;
        this.postCode = postCode;
        this.address = address;
    }

    public Contact() {
        super();
        this.name = null;
        this.mobilePhone = null;
        this.telephone = null;
        this.postCode = null;
        this.address = null;
    }

    /**
     * 家庭住址。
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 移动电话号码。
     * 
     * @return
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 家庭电话号码。
     * 
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telePhone) {
        this.telephone = telePhone;
    }

    /**
     * 姓名。
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 邮政编码。
     * 
     * @return
     */
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}
