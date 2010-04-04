package com.ft.sm.dto;

import com.ft.sm.entity.Attach;
import com.ft.sm.entity.InfoShared;

/**
 * 共享信息附件实体封装类。
 * 
 */
public class AttachDTO implements DTO {
    private static final long serialVersionUID = -432177241767325219L;

    private InfoShared info;

    private Attach attach;

    public AttachDTO() {
        this.attach = new Attach();
    }

    public AttachDTO(Attach attach) {
        this.attach = attach;
    }

    public AttachDTO(InfoShared info, Attach attach) {
        this.info = info;
        this.attach = attach;
    }

    /**
     * 附件id。
     * 
     * @return
     */
    public Long getAttachId() {
        return new Long(this.attach.getAttachId());
    }

    public void setAttachId(Long attachId) {
        this.attach.setAttachId(attachId.longValue());
    }

    /**
     * 文件名。
     * 
     * @return
     */
    public String getFileName() {
        return this.attach.getFileName();
    }

    public void setFileName(String fileName) {
        this.attach.setFileName(fileName);
    }

    /**
     * 文件地址。
     * 
     * @return
     */
    public String getFilePath() {
        return this.attach.getFilePath();
    }

    public void setFilePath(String filePath) {
        this.attach.setFilePath(filePath);
    }

    /**
     * 共享信息关联。
     * 
     * @return
     */
    public InfoShared getInfo() {
        return info;
    }

    public void setInfo(InfoShared info) {
        this.info = info;

        if (info != null) {
            this.attach.setInfoId(info.getInfoId());
        }
    }

    public Long getInfoId() {
        return new Long(this.attach.getInfoId());
    }

    public void setInfoId(Long infoId) {
        this.attach.setInfoId(infoId.longValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.attach;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.attach = (Attach) target;
    }
}
