package com.ft.busi.sm.service;

import java.util.Map;

/**
 * 
 * ҵ��������ɹ��򼰻�ȡ�ӿ�
 * 
 * @version 1.0
 */
public interface BusiCodeGenerateService {

    /**
     * ��ȡ��ǰҵ��ı���
     * 
     * @param busiCodeRuleCode
     *                ҵ��������ı���
     * @param paramMap
     *                ҵ��������Ĳ����б�
     * @return ҵ�����
     */
    public String generateBusiCode(String busiCodeRuleCode, Map<Object,Object> paramMap);
}
