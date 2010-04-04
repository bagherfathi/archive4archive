package com.ft.sm.dto;

import java.io.Serializable;
import java.util.Date;

import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.Operator;

/**
 * 信息分类实体封装。
 * 
 */
public class InfoCategoryBeanDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long sum;

    private InfoCategory category;

    private InfoShared info;

    private Operator op;

    public InfoCategoryBeanDTO() {
        this.category = new InfoCategory();
        this.info = new InfoShared();
        this.op = new Operator();
    }

    public InfoCategoryBeanDTO(InfoCategory category, InfoShared info,
            Operator op) {
        this.category = category;
        this.info = info;
        this.op = op;
    }

    /**
     * 类别ID。
     * 
     * @return
     */
    public long getCategoryInfoId() {
        return this.category.getCategoryId();
    }

    /**
     * 类别名称。
     * 
     * @return
     */
    public String getCategoryName() {
        return this.category.getCategoryName();
    }

    /**
     * 最后发布信息的时间。
     * 
     * @return
     */
    public Date getLastPublishTime() {
        return this.info.getPublishTime();
    }

    /**
     * 最后发布信息的发布者。
     * 
     * @return
     */
    public String getPublisher() {
        return this.op.getOpName();
    }

    /**
     * 最后发布信息的标题。
     * 
     * @return
     */
    public String getTitle() {
        return this.info.getInfoTitle();
    }

    /**
     * 信息总数。
     * 
     * @return
     */
    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    /**
     * 最后发布的信息ID。
     * 
     * @return
     */
    public long getSharedId() {
        return this.info.getInfoId();
    }
}
