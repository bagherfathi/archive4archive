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
 * ����Velocity���ʵ�ֹ������档
 * 
 * 
 * @spring.bean id="vmRuleEngine"
 */
public class VelocityRuleEngine {

    private RuleRepository ruleRepository;

    private VelocityEngine velocityEngine;

    //private boolean isDebug;

    /**
     * ִ�й������档
     * 
     * @param ruleCode
     *                �������
     * @param engineContext
     *                ����������
     * @throws IOException
     */
    public void execute(String ruleCode, RuleEngineContext engineContext)
            throws IOException, RuleException {
        Reader reader = ruleRepository.getRuleReader(ruleCode);
        VelocityContext context = new VelocityContext();

        // ����ȫ�ֲ���
        for (Iterator iter = engineContext.getGlobalAttributes().entrySet()
                .iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            context.put((String) element.getKey(), element.getValue());

        }
        if (engineContext.getReturnType() != null) {
            context.put(engineContext.getResultName(), engineContext
                    .createResult());
        }
        // ����asset����
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
