package com.ft.web.sm.data.region;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.RegionDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 区域设置数据类
 * 
 * @struts.form name="regionForm"
 * 
 * @version 1.0
 * 
 */
public class RegionForm extends BaseValidatorForm {

    /**
     * 
     */
    private static final long serialVersionUID = 7367759292880225597L;

    /**
     * 存放区域信息
     */
    private RegionDTO region;

    /**
     * 刷新标识
     */
    private boolean refresh;

    /**
     * 父项区域信息
     */
    private RegionDTO parentRegion;

    private String act;

    private String validationKey;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (this.region == null) {
            this.region = new RegionDTO();
        }
        super.reset(mapping, request);
    }

    /**
     * @struts.entity-field initial="regionId"
     * @return Returns the attach.
     */
    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    /**
     * @struts.entity-field initial="parentId"
     * @return Returns the attach.
     */
    public RegionDTO getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(RegionDTO parentRegion) {
        this.parentRegion = parentRegion;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

}
