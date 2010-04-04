package com.ft.busi.sm.model;

import java.util.Map;

/**
 * 业务编码生成规则及获取接口
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/busiCodeGenerateManager"
 *             id="busiCodeGenerateManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface BusiCodeGenerateManager {
    /**
     * 获取当前业务的编码
     * 
     * @param busiCodeRuleCode
     *                业务编码规则的编码
     * @param paramMap
     *                业务编码规则的参数列表
     * @return 业务编码
     */
    public String generateBusiCode(String busiCodeRuleCode, Map<Object,Object> paramMap)
            throws Exception;
}
