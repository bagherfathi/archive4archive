package com.ft.web.sm.info;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 信息共享处理类
 * 
 * @struts.form name="infoSharedForm"
 * 
 * @version 1.0
 */
public class InfoSharedForm extends BaseValidatorForm {
    private static final long serialVersionUID = 4005775532033316213L;

    private InfoCategoryDTO category;

    private InfoSharedDTO infoShared;

    private FormFile attachFile;

    private Long categoryId;

    private Long sharedId;

    private List orgList;

    private AttachDTO attach;

    private String title;

    private String publisher;

    private Date beginTime;

    private Date endTime;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == category) {
            category = new InfoCategoryDTO();
        }
        if (null == infoShared) {
            infoShared = new InfoSharedDTO();
        }
    }

    /**
     * @struts.entity-field initial="cId"
     * @return
     */
    public InfoCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(InfoCategoryDTO category) {
        this.category = category;
    }

    /**
     * @struts.entity-field initial="id"
     * @return
     */
    public InfoSharedDTO getInfoShared() {
        return infoShared;
    }

    public void setInfoShared(InfoSharedDTO infoShared) {
        this.infoShared = infoShared;
    }

    public FormFile getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(FormFile attachFile) {
        this.attachFile = attachFile;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List getOrgList() {
        return orgList;
    }

    public void setOrgList(List orgList) {
        this.orgList = orgList;
    }

    public Long getSharedId() {
        return sharedId;
    }

    public void setSharedId(Long sharedId) {
        this.sharedId = sharedId;
    }

    /**
     * @struts.entity-field initial="aId"
     * @return
     */
    public AttachDTO getAttach() {
        return attach;
    }

    public void setAttach(AttachDTO attach) {
        this.attach = attach;
    }

    /**
     * @return Returns the beginTime.
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     *                The beginTime to set.
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return Returns the endTime.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *                The endTime to set.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Returns the publisher.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher
     *                The publisher to set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *                The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
