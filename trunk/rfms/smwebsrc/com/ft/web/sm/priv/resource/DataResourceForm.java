package com.ft.web.sm.priv.resource;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 业务权限数据类 存放业务权限信息和业务权限条目数据信息
 * 
 * @struts.form name = "dataResourceForm"
 * @struts.form name = "dataResourceEntryForm"
 * 
 * @version 1.0
 */
public class DataResourceForm extends BaseValidatorForm {

    private static final long serialVersionUID = 4654611204730543185L;

    /**
     * 存放业务权限对象
     */
    private DataResourceDTO dataResource;

    /**
     * 存放多个业务权限对象
     */
    private DataResourceDTO[] dataResources;

    /**
     * 业务权限条目信息
     */
    private DataResourceEntryDTO entry;

    private String maxValue;

    private String minValue;

    /**
     * 存放多个业务权限条目数据
     */
    private Set dataResourceEntrys;

    private String act;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == dataResource) {
            dataResource = new DataResourceDTO();
        }
        if (null == dataResourceEntrys) {
            dataResourceEntrys = new HashSet();
        }
        if (null == entry) {
            entry = new DataResourceEntryDTO();
        }
    }

    /**
     * @struts.entity-field initial="id"
     * @return Returns the attach.
     */
    public DataResourceDTO getDataResource() {
        return dataResource;
    }

    public void setDataResource(DataResourceDTO dataResource) {
        this.dataResource = dataResource;
    }

    /**
     * @struts.entity-field initial="ids"
     * @return Returns the attach.
     */
    public DataResourceDTO[] getDataResources() {
        return dataResources;
    }

    public void setDataResources(DataResourceDTO[] dataResources) {
        this.dataResources = dataResources;
    }

    /**
     * 多个业务权限条目数据
     * 
     * @return
     */
    public Set getDataResourceEntrys() {
        return dataResourceEntrys;
    }

    public void setDataResourceEntrys(Set dataResourceEntrys) {
        this.dataResourceEntrys = dataResourceEntrys;
    }

    /**
     * 单个业务权限条目数据
     * 
     * @struts.entity-field initial="eId"
     * 
     * @return
     */
    public DataResourceEntryDTO getEntry() {
        return entry;
    }

    public void setEntry(DataResourceEntryDTO entry) {
        this.entry = entry;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

}
