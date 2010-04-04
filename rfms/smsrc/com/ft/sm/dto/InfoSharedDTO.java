package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.Operator;

/**
 * ������Ϣʵ���װ�ࡣ
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
     * ���ࡣ
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
     * ���ݡ�
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
     * ����ʱ�䡣
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
     * ������֯��
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
     * ������Ϣid��
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
     * �����ˡ�
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
     * ����ʱ�䡣
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
     * ���⡣
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
     * ��ʼʱ�䡣
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
     * ����ID��
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
     * ��Ϣ�������ID��
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
