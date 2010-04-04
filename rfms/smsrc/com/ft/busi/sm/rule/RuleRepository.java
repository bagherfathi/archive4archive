package com.ft.busi.sm.rule;

import java.io.IOException;
import java.io.Reader;

/**
 * �����ļ��ֿ��ࡣ
 * 
 * @version 1.0
 */
public interface RuleRepository {
    /**
     * ���ݹ������õ�һ������
     * 
     * @param code
     *                ������롣
     * @return Reader����
     * @throws IOException
     */
    public Reader getRuleReader(String code) throws IOException, RuleException;
}
