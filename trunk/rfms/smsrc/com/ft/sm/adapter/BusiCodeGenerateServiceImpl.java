package com.ft.sm.adapter;

import java.util.Map;

import com.ft.busi.sm.model.BusiCodeGenerateManager;
import com.ft.busi.sm.service.BusiCodeGenerateService;
import com.ft.common.exception.CommonRuntimeException;

/**
 * 
 * 业务编码生成类。根据业务编码规则的配置生成业务编码。
 * 
 * @spring.bean id="busiCodeGenerateService"
 * 
 */
public class BusiCodeGenerateServiceImpl implements BusiCodeGenerateService {

    private BusiCodeGenerateManager busiCodeGenerateManager;

    /**
     * 获取当前可用的业务编码 内置变量currentDay 当天日期 格式: yyyyMMdd
     * 
     * 
     * @param busiCodeRuleCode
     *                业务编码
     * @param paramMap
     *                参数列表信息
     */
    public String generateBusiCode(String busiCodeRuleCode, Map<Object,Object> paramMap) {
        try {
            return busiCodeGenerateManager.generateBusiCode(busiCodeRuleCode,
                    paramMap);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
    }

    /**
     * @spring.property ref="busiCodeGenerateManager"
     * @param busiCodeGenerateManager
     */
    public void setBusiCodeGenerateManager(
            BusiCodeGenerateManager busiCodeGenerateManager) {
        this.busiCodeGenerateManager = busiCodeGenerateManager;
    }
}
