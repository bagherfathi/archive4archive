package com.ft.sm.rules.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.RuleManager;
import com.ft.busi.sm.rule.RuleException;
import com.ft.busi.sm.rule.RuleRepository;
import com.ft.common.cache.AbstractRepository;
import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;

/**
 * ����ֿ�ʵ����.
 * 
 * 
 * @spring.bean id="ruleRepository" init-method="initialize"
 */
public class RuleRepositoryImpl extends AbstractRepository implements
        RuleRepository, SMEventListener {
    private static final Log logger = LogFactory
            .getLog(RuleRepositoryImpl.class);

    private RuleManager ruleManager;

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.rules.RuleRepository#getRuleReader(java.lang.String)
     */
    public Reader getRuleReader(String code) throws IOException, RuleException {
        // �ӻ����л�ȡָ���������Ĺ����ļ�
        
        RuleFileDTO file = (RuleFileDTO) this.cache.get(code);
        if (null == file) {
            // �����в����ڣ���ѯָ���������Ĺ����ļ�
            try {
                file = ruleManager.findPublishedFileOfRule(code);
            } catch (Exception e) {
                throw new RuleException(
                        "Not found pubished file of rule,code is " + code, e);
            }
            if (file != null) {
                this.cache.put(code, file);
                // ָ������Ĺ����޷����ļ�
            } else {
                throw new RuleException(
                        "Not found pubished file of rule,code is " + code);
            }
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(file
                .getFileContent());
        InputStreamReader reader = new InputStreamReader(inputStream);

        return reader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.common.event.SMEventListener#isSupport(com.ft.common.event.SMEvent)
     */
    public boolean isSupport(SMEvent event) {
        if (EventConstants.EVENT_TYPE_RULE.equals(event.getType()))
            return true;

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.common.event.SMEventListener#onEvent(com.ft.common.event.SMEvent)
     */
    public void onEvent(SMEvent event) {
        logger.info("Receive message:" + event.toString());
        String key = event.getKey();
        if (EventConstants.EVENT_UPDATE_RULE.equals(event.getAction())) {
            addOrUpdate(key);
        }

    }

    /*
     * ϵͳ����ʱװ�ع�����Ϣ�������С�
     * 
     * @see com.ft.common.cache.AbstractRepository#initialize()
     */
    public void initialize() {
        this.cache.clear();
        //�����߳̽�������װ��
        Thread loadRuleThread = new Thread(){
            public void run(){
                List rules;
                try {
                    rules = ruleManager.findAllRules();
                } catch (Exception e) {
                    logger.error("Load rule data error.",e);
                    return;
                }
                for (Iterator iter = rules.iterator(); iter.hasNext();) {
                    RuleInfoDTO element = (RuleInfoDTO) iter.next();
                    addOrUpdate(element.getCode());
                }
            }
        };
        
        loadRuleThread.start();
    }

    /**
     * ���¹����ļ����档
     * 
     * @param ruleCode
     */
    private void addOrUpdate(String ruleCode) {
        RuleFileDTO ruleFileDTO;
        try {
            ruleFileDTO = this.ruleManager.findPublishedFileOfRule(ruleCode);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        
        this.cache.put(ruleCode, ruleFileDTO);
    }

    /**
     * @spring.property ref="ruleManager"
     * @param ruleManager
     *                the ruleManager to set
     */
    public void setRuleManager(RuleManager ruleManager) {
        this.ruleManager = ruleManager;
    }
}
