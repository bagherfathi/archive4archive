package com.ft.busi.sm.service;

import java.util.Map;

/**
 * 
 * 业务编码生成规则及获取接口
 * 
 * @version 1.0
 */
public interface BusiCodeGenerateService {

    /**
     * 获取当前业务的编码
     * 
     * @param busiCodeRuleCode
     *                业务编码规则的编码
     * @param paramMap
     *                业务编码规则的参数列表
     * @return 业务编码
     */
    public String generateBusiCode(String busiCodeRuleCode, Map<Object,Object> paramMap);
}
