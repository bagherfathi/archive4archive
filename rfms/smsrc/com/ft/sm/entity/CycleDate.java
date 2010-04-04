/**
 * @{#} CycleDate.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 记录变更时间。
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 */
public class CycleDate implements Serializable {
    private static final long serialVersionUID = -3884745526673241395L;

    private Date createDate;

    private Date validDate;

    private Date expireDate;

    private Date modifyDate;

    public CycleDate() {
        super();
        this.createDate = new Date();
        this.modifyDate = this.createDate;
    }

    /**
     * 创建时间。
     * 
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 终止时间。
     * 
     * @return
     */
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 修改时间。
     * 
     * @return
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 有效时间。
     * 
     * @return
     */
    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

}
