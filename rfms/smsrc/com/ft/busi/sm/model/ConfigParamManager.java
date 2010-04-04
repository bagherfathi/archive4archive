package com.ft.busi.sm.model;

import java.util.List;
import java.util.Properties;

import com.ft.sm.dto.ConfigParamDTO;

/**
 * 系统配置参数管理接口。
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/configParamManager" id="configParamManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface ConfigParamManager extends EntityManager {

    /**
     * 所有配置参数。
     * 
     * @return
     */
    public List findAllParams(long type) throws Exception;

    /**
     * 保存配置参数。
     * 
     * @param param
     * @return
     */
    public Long saveParam(ConfigParamDTO paramDTO, ConfigParamDTO parentDTO)
            throws Exception;

    /**
     * 更新配置参数。
     * 
     * @param param
     * @return
     */
    public Long updateParam(ConfigParamDTO param) throws Exception;

    /**
     * 根据节点ID查找配置参数。
     * 
     * @param parentId
     *                父节点ID。
     * @return ConfigParam实体集合。
     */
    public List findParamsByParentId(Long parentId) throws Exception;

    /**
     * 查找根节点。
     * 
     * @return 配置参数实体。
     */
    public ConfigParamDTO findRootParamNode() throws Exception;

    /**
     * 查找所有参数值。
     * 
     * @return 属性集合。
     */
    public Properties getAllParams() throws Exception;

    /**
     * 查询指定节点下的所有配置参数。
     * 
     * @param nodeCode
     *                配置参数节点编码。
     * @return
     */
    public Properties getParamsOfNode(String nodeCode) throws Exception;

}
