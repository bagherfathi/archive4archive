package com.ft.busi.sm.model;

import java.util.Map;

/**
 * ҵ��������ɹ��򼰻�ȡ�ӿ�
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/busiCodeGenerateManager"
 *             id="busiCodeGenerateManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface BusiCodeGenerateManager {
    /**
     * ��ȡ��ǰҵ��ı���
     * 
     * @param busiCodeRuleCode
     *                ҵ��������ı���
     * @param paramMap
     *                ҵ��������Ĳ����б�
     * @return ҵ�����
     */
    public String generateBusiCode(String busiCodeRuleCode, Map<Object,Object> paramMap)
            throws Exception;
}
