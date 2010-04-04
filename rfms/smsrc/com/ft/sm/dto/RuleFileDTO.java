package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.RuleFile;
import com.ft.sm.entity.RuleInfo;

public class RuleFileDTO implements DTO {

    private static final long serialVersionUID = -9012320673870564319L;

    private RuleFile ruleFile;

    private RuleInfo ruleInfo;

    public RuleFileDTO() {
        this.ruleFile = new RuleFile();
        this.ruleInfo = new RuleInfo();
    }

    public RuleFileDTO(RuleFile ruleFile) {
        this.ruleFile = ruleFile;
    }

    public RuleFileDTO(RuleFile ruleFile, RuleInfo ruleInfo) {
        this.ruleFile = ruleFile;
        this.ruleInfo = ruleInfo;
    }

    /**
     * 规则文件id。
     * 
     * @return
     */
    public Long getFileId() {
        return new Long(this.ruleFile.getFileId());
    }

    public void setFileId(Long fileId) {
        this.ruleFile.setFileId(fileId.longValue());
    }

    /**
     * 规则id。
     * 
     * @return
     */
    public Long getRuleId() {
        return new Long(this.ruleFile.getRuleId());
    }

    public void setRuleId(Long ruleId) {
        this.ruleFile.setRuleId(ruleId.longValue());
    }

    /**
     * 规则文件版本。
     * 
     * @return
     */
    public long getVersion() {
        return this.ruleFile.getVersion();
    }

    public void setVersion(long version) {
        this.ruleFile.setVersion(version);
    }

    /**
     * 和规则关联。
     * 
     * @return
     */
    public RuleInfo getRule() {
        return ruleInfo;
    }

    public void setRule(RuleInfo ruleInfo) {
        this.ruleInfo = ruleInfo;
    }

    /**
     * 
     * @return binary array content
     */
    public byte[] getFileContent() {
        return this.ruleFile.getFileContent();
    }

    public void setFileContent(byte[] fileContent) {
        this.ruleFile.setFileContent(fileContent);
    }

    /**
     * 操作员ID。
     * 
     * @return
     */
    public Long getOperatorId() {
        return new Long(this.ruleFile.getOperatorId());
    }

    public void setOperatorId(Long operatorId) {
        this.ruleFile.setOperatorId(operatorId.longValue());
    }

    /**
     * 发布人。
     * 
     * @return
     */
    public String getPublisher() {
        return this.ruleFile.getPublisher();
    }

    public void setPublisher(String publisher) {
        this.ruleFile.setPublisher(publisher);
    }

    /**
     * 上传时间。
     * 
     * @return
     */
    public Date getUploadTime() {
        return this.ruleFile.getUploadTime();
    }

    public void setUploadTime(Date uploadTime) {
        this.ruleFile.setUploadTime(uploadTime);
    }

    public Object getTarget() {
        return this.ruleFile;
    }

    public void setTarget(Object target) {
        this.ruleFile = (RuleFile) target;

    }

    public RuleFile getRuleFile() {
        return this.ruleFile;
    }

}
