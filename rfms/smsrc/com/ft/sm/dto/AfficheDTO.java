package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.Operator;

/**
 * 公告实体封装类。
 * 
 * @version 1.0
 */
public class AfficheDTO implements DTO {
    private static final long serialVersionUID = -6656726313888896577L;

    // 公告状态：初始化
    public static final long STATUS_INIT = 1;

    // 公告状态：发布
    public static final long STATUS_PUBLISH = 2;

    // 公告状态：过期
    public static final long STATUS_EXPIRE = 3;

    // 公告状态：不可用，禁止
    public static final long STATUS_DISABLED = 4;

    private Affiche affiche;

    private Operator operator;
    
    private List relAfficheOrgs;

    public AfficheDTO() {
        this.affiche = new Affiche();
        this.affiche.setValidTime(new Date());
    }

    public AfficheDTO(Affiche affiche) {
        this.affiche = affiche;
    }

    public AfficheDTO(Affiche affiche, Operator publisher) {
        this.affiche = affiche;
        this.operator = publisher;
    }

    /**
     * 共享信息id。
     * 
     * @return Returns the id.
     */
    public Long getAfficheId() {
        return new Long(this.affiche.getAfficheId());
    }

    public void setAfficheId(Long afficheId) {
        this.affiche.setAfficheId(afficheId.longValue());
    }

    /**
     * 信息内容。
     * 
     * @return
     */
    public String getContent() {
        return this.affiche.getAfficheContent();
    }

    public void setContent(String content) {
        this.affiche.setAfficheContent(content);
    }

    /**
     * 结束日期。
     * 
     * @return
     */
    public Date getExpireTime() {
        return this.affiche.getExpireTime();
    }

    public void setExpireTime(Date expireTime) {
        this.affiche.setExpireTime(expireTime);
    }
    
    /**
     * 信息级别。
     * 
     * @return
     */
    public long getLevel() {
        return this.affiche.getAfficheLevel();
    }

    public void setLevel(long level) {
        this.affiche.setAfficheLevel(level);
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
            this.affiche.setPublisherId(operator.getOperatorId());
        }
    }

    /**
     * 发表时间。
     * 
     * @return
     */
    public Date getPublishTime() {
        return this.affiche.getPublishTime();
    }

    public void setPublishTime(Date publishTime) {
        this.affiche.setPublishTime(publishTime);
    }

    /**
     * 状态。
     * 
     * @return
     */
    public long getStatus() {
        return this.affiche.getAfficheStatus();
    }

    public void setStatus(long status) {
        this.affiche.setAfficheStatus(status);
    }

    /**
     * 标题。
     * 
     * @return
     */
    public String getTitle() {
        return this.affiche.getAfficheTitle();
    }

    public void setTitle(String title) {
        this.affiche.setAfficheTitle(title);
    }

    /**
     * 启始时间。
     * 
     * @return
     */
    public Date getValidTime() {
        return this.affiche.getValidTime();
    }

    public void setValidTime(Date validTime) {
        this.affiche.setValidTime(validTime);
    }

    public Long getPublisherId() {
        return new Long(this.affiche.getPublisherId());
    }

    public void setPublisherId(Long opId) {
        this.affiche.setPublisherId(opId.longValue());
    }
    
    public Long getCategoryId(){
        return new Long(this.affiche.getCategoryId());
    }
    
    public void setCategoryId(Long categoryId){
        this.affiche.setCategoryId(categoryId.longValue());
    }
    
    /**
     * @return the relAfficheOrgs
     */
    public List getRelAfficheOrgs() {
        if(relAfficheOrgs == null)
            relAfficheOrgs = new ArrayList();
        
        return relAfficheOrgs;
    }

    /**
     * @param relAfficheOrgs the relAfficheOrgs to set
     */
    public void setRelAfficheOrgs(List relAfficheOrgs) {
        this.relAfficheOrgs = relAfficheOrgs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.affiche;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.affiche = (Affiche) target;
    }
}
