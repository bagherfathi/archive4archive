package com.ft.web.sm.param;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 系统参数配置Form类
 * 
 * @version 1.0
 * 
 * @struts.form name="configParamForm"
 */

public class ConfigParamForm extends BaseValidatorForm {

    private static final long serialVersionUID = -371423811755066886L;

    private ConfigParamDTO parent;

    private int type;

    private ConfigParamDTO configParam;

    private List params;

    private String parentId;

    private String paramId;

    private boolean refresh;

    /**
     * @return Returns the refresh.
     */
    public boolean isRefresh() {
        return refresh;
    }

    /**
     * @param refresh
     *                The refresh to set.
     */
    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    /**
     * @return Returns the paramId.
     */
    public String getParamId() {
        return paramId;
    }

    /**
     * @param paramId
     *                The paramId to set.
     */
    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    /**
     * @return Returns the parentId.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *                The parentId to set.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return Returns the params.
     */
    public List getParams() {
        return params;
    }

    /**
     * @param params
     *                The params to set.
     */
    public void setParams(List params) {
        this.params = params;
    }

    /**
     * @struts.entity-field initial="paramId"
     * @return Returns the configParam.
     */
    public ConfigParamDTO getConfigParam() {
        return configParam;
    }

    /**
     * @param configParam
     *                The configParam to set.
     */
    public void setConfigParam(ConfigParamDTO configParam) {
        this.configParam = configParam;
    }

    /**
     * @struts.entity-field initial="parentId"
     * @return Returns the parent.
     */
    public ConfigParamDTO getParent() {
        return parent;
    }

    /**
     * @param parent
     *                The parent to set.
     */
    public void setParent(ConfigParamDTO parent) {
        this.parent = parent;
    }

    /**
     * @return Returns the type.
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *                The type to set.
     */
    public void setType(int type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.web.sm.BaseValidatorForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (configParam == null) {
            this.configParam = new ConfigParamDTO();
        }
        super.reset(mapping, request);
    }

}
