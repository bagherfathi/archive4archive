package com.ft.sm.dto;

/**
 * Class comments.
 * 
 */
public interface HisDTO extends DTO {
    /**
     * Set the value related to the column: app_id
     * 
     * @param appId
     *                the app_id value
     */
    public void setAppId(long appId);

    /**
     * Set the value related to the column: create_date
     * 
     * @param createDate
     *                the create_date value
     */
    public void setCreateDate(java.util.Date createDate);


    /**
     * Set the value related to the column: valid_date
     * 
     * @param validDate
     *                the valid_date value
     */
    public void setValidDate(java.util.Date validDate);


    /**
     * Set the value related to the column: expire_date
     * 
     * @param expireDate
     *                the expire_date value
     */
    public void setExpireDate(java.util.Date expireDate);

    /**
     * Set the value related to the column: update_date
     * 
     * @param updateDate
     *                the update_date value
     */
    public void setUpdateDate(java.util.Date updateDate);

    /**
     * Set the value related to the column: operator_id
     * 
     * @param operatorId
     *                the operator_id value
     */
    public void setOperatorId(long operatorId);


    /**
     * Set the value related to the column: org_id
     * 
     * @param orgId
     *                the org_id value
     */
    public void setOrgId(long orgId);

    /**
     * Set the value related to the column: login_org_id
     * 
     * @param loginOrgId
     *                the login_org_id value
     */
    public void setLoginOrgId(long loginOrgId);
}