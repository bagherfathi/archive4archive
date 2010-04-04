package com.ft.sm.dto;

import com.ft.sm.entity.ConfigParam;

/**
 * 系统参数实体封装类。
 * 
 */

public class ConfigParamDTO implements DTO {

    private static final long serialVersionUID = -7730915878527232203L;

    public static final String PATH_SEPARATOR = "#";

    // 参数类型
    public static final long PARAM_TYPE_VALUE = 0; // 可修改参数

    public static final long PARAM_TYPE_NODE = 1; // 参数节点

    public static final long PARAM_TYPE_HIDDEN = 2; // 隐藏参数

    private ConfigParam configParam;

    private ConfigParam parent;

    public ConfigParamDTO() {
        this.configParam = new ConfigParam();
    }

    public ConfigParamDTO(ConfigParam param) {
        this.configParam = param;
    }

    public ConfigParamDTO(ConfigParam param, ConfigParam parent) {
        this.configParam = param;
        this.parent = parent;
    }

    public ConfigParamDTO(String configCode, String configValue) {
        this.configParam.setConfigCode(configCode);
        this.configParam.setConfigValue(configValue);
    }

    public ConfigParamDTO(String configCode, String configValue,
            String configName) {
        this.configParam.setConfigName(configName);
        this.configParam.setConfigValue(configValue);
        this.configParam.setConfigValue(configValue);
    }

    public ConfigParam getConfigParam() {
        return this.configParam;
    }

    public String getConfigCode() {
        return this.configParam.getConfigCode();
    }

    /**
     * @param configDesc
     *                The configCode to set.
     */
    public void setConfigCode(String configCode) {
        this.configParam.setConfigCode(configCode);
    }

    /**
     * 
     */
    public Long getConfigId() {
        return new Long(this.configParam.getConfigId());
    }

    /**
     * @param configId
     *                The configId to set.
     */
    public void setConfigId(Long configId) {
        this.configParam.setConfigId(configId.longValue());
    }

    /**
     * 
     */
    public String getConfigName() {
        return this.configParam.getConfigName();
    }

    /**
     * @param configDesc
     *                The configDesc to set.
     */
    public void setConfigName(String configName) {
        this.configParam.setConfigName(configName);
    }

    /**
     * 
     */
    public String getConfigValue() {
        return this.configParam.getConfigValue();
    }

    /**
     * @param configValue
     *                The configValue to set.
     */
    public void setConfigValue(String configValue) {
        this.configParam.setConfigValue(configValue);
    }

    /**
     * 
     */
    public String getPath() {
        return this.configParam.getConfigPath();
    }

    /**
     * @param path
     *                The path to set.
     */
    public void setPath(String path) {
        this.configParam.setConfigPath(path);
    }

    /**
     * 
     */
    public ConfigParam getParent() {
        return this.parent;
    }

    /**
     * @param parent
     *                The parent to set.
     */
    public void setParent(ConfigParam parent) {
        this.parent = parent;
    }

    /**
     * 
     */
    public long getType() {
        return this.configParam.getConfigType();
    }

    /**
     * @param type
     *                The type to set.
     */
    public void setType(long type) {
        this.configParam.setConfigType(type);
    }

    public void setParnetId(long parentId) {
        this.configParam.setParentId(parentId);
    }

    public long getParentId() {
        return this.configParam.getParentId();
    }

    public Object getTarget() {
        return this.configParam;
    }

    public void setTarget(Object target) {
        this.configParam = (ConfigParam) target;

    }

}
