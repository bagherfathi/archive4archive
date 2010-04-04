package com.ft.busi.sm.rule;

import java.io.IOException;
import java.util.List;

/**
 * �������档
 * 
 * @version 1.0
 */
public interface RuleEngine {
    /**
     * ִ��ָ������
     * 
     * @param ruleCode
     *                ������롣
     * @param engineContext
     *                ִ�й���ʱ�����ġ�
     * @throws IOException
     */
    public abstract void execute(String ruleCode,
            RuleEngineContext engineContext) throws Exception;
    
    
    /**
     * ִ��ָ������
     * 
     * @param ruleCode
     *                ������롣
     * @param /**
     * ִ��ָ������
     * 
     * @param ruleCode
     *                ������롣
     * @param engineContextList
     *                ִ�й���ʱ�����ġ�
     * @throws IOException
     */
    public abstract void execute(String ruleCode,
            List engineContextList) throws Exception;

}