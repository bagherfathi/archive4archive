package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 操作员维护页面数据类
 * 
 * @version 1.0
 * 
 * @struts.form name="operatorForm"
 */

public class OperatorForm extends BaseValidatorForm {

    private static final long serialVersionUID = 1L;

    public static final long COPY_FUNCTION_ROLE = 1;

    public static final long COPY_DATA_ROLE = 2;

    public static final long COPY_DATA_ORG = 3;

    public static final long COPY_GROUP = 4;

    private OperatorDTO op;

    private OperatorDTO[] ops;

    private OrganizationDTO organization;

    private String password;

    private long[] copyType; // copyType 1.功能角色 2.业务角色 3.可访问组织 4.组

    private Long orgId_s = new Long(0);

    private String loginName = "";

    private String name = "";

    private String listOp_p = ""; // 页数

    private String targetLoginName;

    private String targetName;

    /**
     * @return the listOp_p
     */
    public String getListOp_p() {
        return listOp_p;
    }

    /**
     * @param listOp_p
     *                the listOp_p to set
     */
    public void setListOp_p(String listOp_p) {
        this.listOp_p = listOp_p;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *                the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *                the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @struts.entity-field initial="opIds"
     * @return
     */
    public OperatorDTO[] getOps() {
        return ops;
    }

    public void setOps(OperatorDTO[] ops) {
        this.ops = ops;
    }

    /**
     * @struts.entity-field initial="opId"
     * @return
     */
    public OperatorDTO getOp() {
        return op;
    }

    public void setOp(OperatorDTO operator) {
        this.op = operator;
    }

    /**
     * @struts.entity-field initial="orgId"
     * @return
     */
    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO org) {
        this.organization = org;
    }

    /**
     * @return the targetLoginName
     */
    public String getTargetLoginName() {
        return targetLoginName;
    }

    /**
     * @param targetLoginName
     *                the targetLoginName to set
     */
    public void setTargetLoginName(String targetLoginName) {
        this.targetLoginName = targetLoginName;
    }

    /**
     * @return the targetName
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName
     *                the targetName to set
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public long[] getCopyType() {
        return copyType;
    }

    public void setCopyType(long[] copyType) {
        this.copyType = copyType;
    }

    public Long getOrgId_s() {
        return orgId_s;
    }

    public void setOrgId_s(Long orgId) {
        this.orgId_s = orgId;
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        if (this.op == null) {
            this.op = new OperatorDTO();
        }
        super.reset(arg0, arg1);
    }

}
