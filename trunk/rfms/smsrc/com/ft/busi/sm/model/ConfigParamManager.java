package com.ft.busi.sm.model;

import java.util.List;
import java.util.Properties;

import com.ft.sm.dto.ConfigParamDTO;

/**
 * ϵͳ���ò�������ӿڡ�
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/configParamManager" id="configParamManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface ConfigParamManager extends EntityManager {

    /**
     * �������ò�����
     * 
     * @return
     */
    public List findAllParams(long type) throws Exception;

    /**
     * �������ò�����
     * 
     * @param param
     * @return
     */
    public Long saveParam(ConfigParamDTO paramDTO, ConfigParamDTO parentDTO)
            throws Exception;

    /**
     * �������ò�����
     * 
     * @param param
     * @return
     */
    public Long updateParam(ConfigParamDTO param) throws Exception;

    /**
     * ���ݽڵ�ID�������ò�����
     * 
     * @param parentId
     *                ���ڵ�ID��
     * @return ConfigParamʵ�弯�ϡ�
     */
    public List findParamsByParentId(Long parentId) throws Exception;

    /**
     * ���Ҹ��ڵ㡣
     * 
     * @return ���ò���ʵ�塣
     */
    public ConfigParamDTO findRootParamNode() throws Exception;

    /**
     * �������в���ֵ��
     * 
     * @return ���Լ��ϡ�
     */
    public Properties getAllParams() throws Exception;

    /**
     * ��ѯָ���ڵ��µ��������ò�����
     * 
     * @param nodeCode
     *                ���ò����ڵ���롣
     * @return
     */
    public Properties getParamsOfNode(String nodeCode) throws Exception;

}
