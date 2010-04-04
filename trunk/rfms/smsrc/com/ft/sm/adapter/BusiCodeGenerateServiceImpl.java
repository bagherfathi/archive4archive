package com.ft.sm.adapter;

import java.util.Map;

import com.ft.busi.sm.model.BusiCodeGenerateManager;
import com.ft.busi.sm.service.BusiCodeGenerateService;
import com.ft.common.exception.CommonRuntimeException;

/**
 * 
 * ҵ����������ࡣ����ҵ�����������������ҵ����롣
 * 
 * @spring.bean id="busiCodeGenerateService"
 * 
 */
public class BusiCodeGenerateServiceImpl implements BusiCodeGenerateService {

    private BusiCodeGenerateManager busiCodeGenerateManager;

    /**
     * ��ȡ��ǰ���õ�ҵ����� ���ñ���currentDay �������� ��ʽ: yyyyMMdd
     * 
     * 
     * @param busiCodeRuleCode
     *                ҵ�����
     * @param paramMap
     *                �����б���Ϣ
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
