package com.ft.web.sm.priv.commission;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.web.sm.BaseForm;

/**
 * 委托权限管理页面Form Bean.
 * 
 * @struts.form name="commisionManagerForm"
 * 
 * @version 1.0
 */
public class CommisionManagerForm extends BaseForm {
    private static final long serialVersionUID = -1849606906073654121L;

    private String searchConsigneeName;

    private String searchResourceName;

    private Date startDate;

    private Date endDate;

    // 委托记录
    private ConsignPermitDTO consignPrivilege;

    // 功能权限名称
    private String resourceName;

    /**
     * @return Returns the searchConsigneeName.
     */
    public String getSearchConsigneeName() {
        return searchConsigneeName;
    }

    /**
     * @param searchConsigneeName
     *                The searchConsigneeName to set.
     */
    public void setSearchConsigneeName(String searchConsigneeName) {
        this.searchConsigneeName = searchConsigneeName;
    }

    /**
     * @return Returns the searchResourceName.
     */
    public String getSearchResourceName() {
        return searchResourceName;
    }

    /**
     * @param searchResourceName
     *                The searchResourceName to set.
     */
    public void setSearchResourceName(String searchResourceName) {
        this.searchResourceName = searchResourceName;
    }

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
     * @struts.entity-field initial="cpId"
     * @return
     */
    public ConsignPermitDTO getConsignPrivilege() {
        return consignPrivilege;
    }

    public void setConsignPrivilege(ConsignPermitDTO consignPrivilege) {
        this.consignPrivilege = consignPrivilege;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == consignPrivilege) {
            consignPrivilege = new ConsignPermitDTO();
        }
    }
}
