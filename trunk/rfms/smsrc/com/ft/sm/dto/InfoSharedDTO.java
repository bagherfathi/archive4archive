package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.Operator;

/**
 * 共享信息实体封装类。
 * 
 */
public class InfoSharedDTO implements DTO {
    private static final long serialVersionUID = 5548000968961931497L;

    private InfoCategory category;

    private InfoShared info;

    private Operator operator;

    public InfoSharedDTO() {
        this.info = new InfoShared();
    }

    public InfoSharedDTO(InfoShared info) {
        this.info = info;
    }

    public InfoSharedDTO(InfoCategory category, InfoShared info) {
        this.category = category;
        this.info = info;
    }

    public InfoSharedDTO(InfoShared info, Operator operator) {
        this.info = info;
        this.operator = operator;
    }

    public InfoSharedDTO(InfoCategory category, InfoShared info,
            Operator operator) {
        this.category = category;
        this.info = info;
        this.operator = operator;
    }

    /**
     * 种类。
     * 
     * @return
     */
    public InfoCategory getCategory() {
        return category;
    }

    public void setCategory(InfoCategory category) {
        this.category = category;
        if (this.category != null) {
            this.info.setCategoryId(category.getCategoryId());
        }
    }

    /**
     * 内容。
     * 
     * @return
     */
    public String getContent() {
        return this.info.getInfoContent();
    }

    public void setContent(String content) {
        this.info.setInfoContent(content);
    }

    /**
     * 结束时间。
     * 
     * @return
     */
    public Date getExpireTime() {
        return this.info.getExpireTime();
    }

    public void setExpireTime(Date expireTime) {
        this.info.setExpireTime(expireTime);
    }

    /**
     * 接收组织。
     * 
     * @return
     */
    public String getInceptOrgs() {
        return this.info.getInceptOrgs();
    }

    public void setInceptOrgs(String inceptOrgs) {
        this.info.setInceptOrgs(inceptOrgs);
    }

    /**
     * 共享信息id。
     * 
     * @return
     */
    public Long getInfoId() {
        return new Long(this.info.getInfoId());
    }

    public void setInfoId(Long infoId) {
        this.info.setInfoId(infoId.longValue());
    }

    /**
     * 发表人。
     * 
     * @return
     */
    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
        if (operator != null) {
            this.info.setAuthorId(operator.getOperatorId());
        }
    }

    /**
     * 发表时间。
     * 
     * @return
     */
    public Date getPublishTime() {
        return this.info.getPublishTime();
    }

    public void setPublishTime(Date publishTime) {
        this.info.setPublishTime(publishTime);
    }

    /**
     * 标题。
     * 
     * @return
     */
    public String getTitle() {
        return this.info.getInfoTitle();
    }

    public void setTitle(String title) {
        this.info.setInfoTitle(title);
    }

    /**
     * 起始时间。
     * 
     * @return
     */
    public Date getValidTime() {
        return this.info.getValidTime();
    }

    public void setValidTime(Date validTime) {
        this.info.setValidTime(validTime);
    }

    /**
     * 作者ID。
     * 
     * @return
     */
    public Long getAuthorId() {
        return new Long(this.info.getAuthorId());
    }

    public void setAuthorId(Long opId) {
        this.info.setAuthorId(opId.longValue());
    }

    /**
     * 信息所属类别ID。
     * 
     * @return
     */
    public Long getCategoryId() {
        return new Long(this.info.getCategoryId());
    }

    public void setCategoryId(Long categoryId) {
        this.info.setCategoryId(categoryId.longValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.info = (InfoShared) target;
    }
}
