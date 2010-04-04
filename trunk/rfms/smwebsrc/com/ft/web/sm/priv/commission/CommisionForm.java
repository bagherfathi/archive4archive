package com.ft.web.sm.priv.commission;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseForm;

/**
 * 委托Form Bean
 * 
 * @struts.form name="commisionForm"
 * 
 * @version 1.0
 * 
 */
public class CommisionForm extends BaseForm {
    private static final long serialVersionUID = -2172544312840671372L;

    private String searchName;

    private String searchLoginName;

    private OperatorDTO consignee;
    
    private Long searchOrgId;

    // 委托开始时间
    private Date startDate;

    // 委托结束时间
    private Date endDate;

    // 记录所选组织的Id
    private Long orgId;
    
    //委托记录
    private ConsignPermitDTO consignPrivilege;

    // 功能权限名称
    private String resourceName;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 查询条件，操作员登陆名
     * 
     * @return Returns the searchLoginName.
     */
    public String getSearchLoginName() {
        return searchLoginName;
    }

    /**
     * @param searchLoginName
     *                The searchLoginName to set.
     */
    public void setSearchLoginName(String searchLoginName) {
        this.searchLoginName = searchLoginName;
    }

    /**
     * 查询条件，操作员名称
     * 
     * @return Returns the searchName.
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName
     *                The searchName to set.
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 委托对象
     * 
     * @struts.entity-field initial="consigneeId"
     * 
     * @return Returns the consignee.
     */
    public OperatorDTO getConsignee() {
        return consignee;
    }

    /**
     * @param consignee
     *                The consignee to set.
     */
    public void setConsignee(OperatorDTO consignee) {
        this.consignee = consignee;
    }

    /**
     * @return the orgId
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *                the orgId to set
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the seachOrgId
     */
    public Long getSearchOrgId() {
        return searchOrgId;
    }

    /**
     * @param seachOrgId the seachOrgId to set
     */
    public void setSearchOrgId(Long searchOrgId) {
        this.searchOrgId = searchOrgId;
    }

    /**
     * @struts.entity-field initial="cpId"
     * @return the consignPrivilege
     */
    public ConsignPermitDTO getConsignPrivilege() {
        return consignPrivilege;
    }

    /**
     * @param consignPrivilege the consignPrivilege to set
     */
    public void setConsignPrivilege(ConsignPermitDTO consignPrivilege) {
        this.consignPrivilege = consignPrivilege;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == this.consignPrivilege) {
            consignPrivilege = new ConsignPermitDTO();
        }
    }
}
