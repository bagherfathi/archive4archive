package com.ft.sm.rules.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.ft.busi.sm.rule.RuleEngineContext;
import com.ft.busi.sm.rule.RuleException;
import com.ft.busi.sm.rule.RuleRepository;

/**
 * 基于Velocity框架实现规则引擎。
 * 
 * 
 * @spring.bean id="vmRuleEngine"
 */
public class VelocityRuleEngine {

    private RuleRepository ruleRepository;

    private VelocityEngine velocityEngine;

    //private boolean isDebug;

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
            throws IOException, RuleException {
        Reader reader = ruleRepository.getRuleReader(ruleCode);
        VelocityContext context = new VelocityContext();

        // 设置全局参数
        for (Iterator iter = engineContext.getGlobalAttributes().entrySet()
                .iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            context.put((String) element.getKey(), element.getValue());

        }
        if (engineContext.getReturnType() != null) {
            context.put(engineContext.getResultName(), engineContext
                    .createResult());
        }
        // 设置asset参数
        for (Iterator iter = engineContext.getAttribute().entrySet().iterator(); iter
                .hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            context.put((String) element.getKey(), element.getValue());
        }

        try {
            velocityEngine
                    .evaluate(context, new StringWriter(), "rule", reader);
        } catch (Exception e) {
            throw new RuleException(e);

        }
    }

    /**
     * @spring.property ref="ruleRepository"
     * @param ruleRepository
     *                the ruleRepository to set
     */
    public void setRuleRepository(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    /**
     * @spring.property ref="velocityEngine"
     * @param velocityEngine
     *                the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
