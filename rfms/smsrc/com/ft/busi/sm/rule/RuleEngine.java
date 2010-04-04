package com.ft.busi.sm.rule;

import java.io.IOException;
import java.util.List;

/**
 * 规则引擎。
 * 
 * @version 1.0
 */
public interface RuleEngine {
    /**
     * 执行指定规则。
     * 
     * @param ruleCode
     *                规则代码。
     * @param engineContext
     *                执行规则时上下文。
     * @throws IOException
     */
    public abstract void execute(String ruleCode,
            RuleEngineContext engineContext) throws Exception;
    
    
    /**
     * 执行指定规则。
     * 
     * @param ruleCode
     *                规则代码。
     * @param /**
     * 执行指定规则。
     * 
     * @param ruleCode
     *                规则代码。
     * @param engineContextList
     *                执行规则时上下文。
     * @throws IOException
     */
    public abstract void execute(String ruleCode,
            List engineContextList) throws Exception;

}