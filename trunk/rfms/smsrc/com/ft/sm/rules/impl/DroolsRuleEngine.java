package com.ft.sm.rules.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.RuleBase;
import org.drools.WorkingMemory;
import org.drools.compiler.RuleBaseLoader;
import org.drools.event.DebugWorkingMemoryEventListener;

import com.ft.busi.sm.rule.RuleEngine;
import com.ft.busi.sm.rule.RuleEngineContext;
import com.ft.busi.sm.rule.RuleException;
import com.ft.busi.sm.rule.RuleRepository;

/**
 * 基于Drools框架实现规则引擎。
 * 
 * 
 * @spring.bean id="droolsRuleEngine"
 */
public class DroolsRuleEngine implements RuleEngine {
    private static Log logger = LogFactory.getLog(DroolsRuleEngine.class);

    private RuleRepository ruleRepository;

    private boolean isDebug;

    /**
     * 执行规则引擎。
     * 
     * @param ruleCode
     *                规则代码
     * @param engineContext
     *                规则上下文
     * @throws IOException
     */
    public void execute(String ruleCode, RuleEngineContext engineContext)
            throws Exception {
        logger.info("Begin execute rule,rule code " + ruleCode);
        try{
            Reader reader = ruleRepository.getRuleReader(ruleCode);
            RuleBase ruleBase;
            ruleBase = RuleBaseLoader.getInstance().loadFromReader(reader);
            excuteRule(ruleBase, engineContext);
        }catch(Exception ex){
            logger.error("Execute rule failed,rule code " + ruleCode);
            throw ex;
        }
        logger.info("Execute rule success,rule code " + ruleCode);
    }

    /**
     * 执行规则
     * 
     * @param ruleCode
     *                规则代码
     * @param ruleEngionContext
     *                规则上下文
     * @throws IOException
     * @throws RuleException
     */
    public void execute(String ruleCode, List engineContextList)
            throws Exception {
        logger.info("Begin execute rule,rule code " + ruleCode);
        
        try{
            Reader reader = ruleRepository.getRuleReader(ruleCode);
            RuleBase ruleBase = RuleBaseLoader.getInstance().loadFromReader(reader);;
            
            for (Iterator iterator = engineContextList.iterator(); iterator
                    .hasNext();) {
                RuleEngineContext engineContext = (RuleEngineContext) iterator
                        .next();
                excuteRule(ruleBase, engineContext);
            }
        }catch(Exception ex){
            logger.error("Execute rule failed,rule code " + ruleCode);
            throw ex;
        }
        
        logger.info("Execute rule success,rule code " + ruleCode);
    }

    /**
     * 执行规则
     * 
     * @param ruleBase
     * @param engineContext
     */
    private void excuteRule(RuleBase ruleBase, RuleEngineContext engineContext) {
        WorkingMemory workingMemory = ruleBase.newWorkingMemory();
        if (isDebug) {
            workingMemory
                    .addEventListener(new DebugWorkingMemoryEventListener());
        }
        // 设置全局参数
        for (Iterator iter = engineContext.getGlobalAttributes().entrySet()
                .iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            workingMemory.setGlobal((String) element.getKey(), element
                    .getValue());

        }
        if (engineContext.getReturnType() != null) {
            workingMemory.setGlobal(engineContext.getResultName(),
                    engineContext.createResult());
        }
        // 设置asset参数
        for (Iterator iter = engineContext.getAttribute().entrySet().iterator(); iter
                .hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            workingMemory.assertObject(element.getValue());
        }
        workingMemory.fireAllRules();
    }

    /**
     * @spring.property ref="ruleRepository"
     * @param ruleRepository
     *                The ruleRepository to set.
     */
    public void setRuleRepository(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
}
