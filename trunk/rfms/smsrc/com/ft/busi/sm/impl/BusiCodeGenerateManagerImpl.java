/**
 * @{#} BusiCodeGenerateManagerImpl.java Created on 2006-11-14 ����11:33:58
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.busi.sm.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ft.busi.sm.impl.dao.BusiCodeGenDao;
import com.ft.busi.sm.impl.dao.BusiCodeRuleDao;
import com.ft.busi.sm.impl.dao.LockObjDao;
import com.ft.busi.sm.model.BusiCodeGenerateManager;
import com.ft.commons.expr.ExpressionEvaluator;
import com.ft.sm.entity.BusiCodeGen;
import com.ft.sm.entity.BusiCodeRule;
import com.ft.sm.entity.LockObj;

/**
 * @author <a href="mailto:yangyh@onewaveinc.com">yangyh</a>
 * @version 1.0
 * 
 * @spring.aop-bean id="busiCodeGenerateManager"
 *                  parent="transactionProxyFactoryBean"
 *                  target="busiCodeGenerateManagerImpl"
 * 
 * 
 * @spring.bean id="busiCodeGenerateManagerImpl"
 */
public class BusiCodeGenerateManagerImpl implements BusiCodeGenerateManager {
    private static final Log log = LogFactory
            .getLog(BusiCodeGenerateManagerImpl.class);

    private int timeOut = 2000;// ��ʱ����

    private static String LOCK_OBJ_CODE = "LOCK_OBJ_BUSI_CODE";

    /**
     * ҵ�����������ݷ��ʶ���
     */
    private BusiCodeRuleDao busiCodeRuleDao;

    /**
     * ҵ�����������ݷ��ʶ���
     */
    private BusiCodeGenDao busiCodeGenDao;

    /**
     * ҵ�����������ݷ��ʶ���
     */
    @SuppressWarnings("unused")
	private LockObjDao lockObjDao;

    /**
     * ����ҵ�����������ݷ��ʶ���
     * 
     * @spring.property ref="busiCodeRuleDao"
     * 
     * @param busiCodeRuleDao
     *            ҵ��������ݷ��ʶ���
     * 
     */
    public void setBusiCodeRuleDao(BusiCodeRuleDao busiCodeRuleDao) {
        this.busiCodeRuleDao = busiCodeRuleDao;
    }

    /**
     * ����ҵ������������ݷ��ʶ���
     * 
     * @spring.property ref="busiCodeGenDao"
     * @param busiCodeGenDao
     *            ҵ������������ݷ��ʶ���
     */

    public void setBusiCodeGenDao(BusiCodeGenDao busiCodeGenDao) {
        this.busiCodeGenDao = busiCodeGenDao;
    }

    /**
     * �������������ݷ��ʶ���
     * 
     * @spring.property ref="lockObjDao"
     * 
     * @param lockObjDao
     */
    public void setLockObjDao(LockObjDao lockObjDao) {
        this.lockObjDao = lockObjDao;
    }

    /**
     * ��ȡ��ǰ���õ�ҵ����� ���ñ���currentDay �������� ��ʽ: yyyyMMdd
     * 
     * 
     * @param busiCodeRuleCode
     *            ҵ�����
     * @param paramMap
     *            �����б���Ϣ
     */
    public synchronized String generateBusiCode(String busiCodeRuleCode,
            Map paramMap) {
        if (paramMap == null)
            paramMap = new Hashtable();
        log.info("�����ҵ�������������" + busiCodeRuleCode);
        for (Iterator ite = paramMap.keySet().iterator(); ite.hasNext();) {
            String key = (String) ite.next();
            if (key == null)
                continue;
            log.info("����Ĳ���Ϊkey=" + key);
            Object obj = paramMap.get(key);
            log.info("����Ĳ���Ϊvalue=" + obj);
        }
        log.info("����Ĳ���Ϊ" + (new ToStringBuilder(paramMap)).toString());
        if (StringUtils.isEmpty(busiCodeRuleCode)) {
            log.info("�������Ϊ�գ����ܱ���....");
            return null;
        }
        // ����ҵ����������߳�����ҵ�����
        final CodeGen codeGen = new CodeGen(busiCodeRuleCode, paramMap);
        new Thread() {
            public synchronized void run() {
                codeGen.gen();
            }
        }.start();
        return codeGen.get();

    }

    public static void main(String[] args) {
        BusiCodeGenerateManagerImpl impl = new BusiCodeGenerateManagerImpl();
        String busiCode = impl.generateBusiCode("PartenerBillBatchNo", null);
        System.out.print(busiCode);
    }

    // �ڲ��࣬��Ҫ�����첽���ɱ���
    private class CodeGen {
        String result = "";

        String busiCodeRuleCode;

        Map<Object,Object> paramMap;

        @SuppressWarnings("unchecked")
		public CodeGen(String busiCodeRuleCode, Map paramMap) {
            this.busiCodeRuleCode = busiCodeRuleCode;
            this.paramMap = paramMap;
        }

        /**
         * ��ȡ��ǰ���õ�ҵ����� ���ñ���currentDay �������� ��ʽ: yyyyMMdd
         * 
         * 
         * @param busiCodeRuleCode
         *            ҵ�����
         * @param paramMap
         *            �����б���Ϣ
         */
        public synchronized void gen() {
            Session session = null;
            try {
                BusiCodeRule busiCodeRule = busiCodeRuleDao
                        .getBusiCodeRuleByCode(busiCodeRuleCode);
                if (busiCodeRule == null) {
                    log.info("===����ҵ��������" + busiCodeRuleDao
                            + "û���ҵ�ҵ��������,��������ҵ�����");
                    return;
                }
                log.info("===>ҵ������������ʶ��" + busiCodeRule.getBusiCodeRuleId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String currentDay = sdf.format(new Date());
                paramMap.put("currentDay", currentDay);
                String prefix = busiCodeRule.getPrefix();
                if (prefix == null)
                    prefix = "";
                String suffix = busiCodeRule.getSuffix();
                if (suffix == null)
                    suffix = "";
                // �滻ǰ׺����ı���
                prefix = ExpressionEvaluator.evaluate(prefix, paramMap);
                // �滻��׺����ı���
                suffix = ExpressionEvaluator.evaluate(suffix, paramMap);
                log.info("===>ǰ��׺ʵ��������Ϊ:" + prefix + "," + suffix);
                BusiCodeGen busiCodeGen = null;
                String busiCodeGenCode = "";
                if (busiCodeRule.getRuleType() == 0) {// һ������һ������
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode();
                }
                if (busiCodeRule.getRuleType() == 1) {// һ�������ǰ׺һ������
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode() + "_"
                            + prefix;
                }
                if (busiCodeRule.getRuleType() == 2) {// һ������ͺ�׺һ������
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode() + "_"
                            + suffix;
                }
                if (busiCodeRule.getRuleType() == 3) {// һ�������ǰ��׺һ������
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode() + "_"
                            + prefix + "_" + suffix;
                }
                String currentValue = busiCodeRule.getMinValue();
                if ((currentValue != null)
                        && (currentValue.trim().equals("-1"))) {
                    result = prefix + suffix;
                    notify();
                    return;
                }
                session = busiCodeGenDao.getSessionFactory().openSession();
                Transaction tr = session.beginTransaction();
                LockObj lockObj = null;
                try {
                    String hql = " from LockObj lo where lo.lockObjCode='"
                            + LOCK_OBJ_CODE + "'";
                    Query query = session.createQuery(hql);
                    query.setLockMode("lo", LockMode.UPGRADE); // ����
                    List lockObjList = query.list();// ִ�в�ѯ����ȡ��
                    if ((lockObjList != null) && (lockObjList.size() > 0))
                        lockObj = (LockObj) lockObjList.get(0);
                    else
                        lockObj = null;
                    if (lockObj == null) {
                        log.info("===>û���ҵ������ʶΪ" + LOCK_OBJ_CODE
                                + "��������,��������ҵ�����");
                        notify();
                        return;
                    }
                    busiCodeGen = busiCodeGenDao.getBusiCodeGenByCode(
                            busiCodeGenCode, busiCodeRule.getBusiCodeRuleId());
                    if (busiCodeGen == null) {
                        log.info("===>ҵ��������ɱ������û���ҵ�����������һ��");
                        busiCodeGen = new BusiCodeGen();
                        busiCodeGen.setBusiCodeGenCode(busiCodeGenCode);
                        busiCodeGen.setBusiCodeRuleId(busiCodeRule
                                .getBusiCodeRuleId());
                        busiCodeGen.setCreateDay(new Date());
                        busiCodeGen.setUpdateDay(new Date());
                        busiCodeGen.setCurrentValue(busiCodeRule.getMinValue());
                        busiCodeGen.setNextValue(busiCodeRule.getMinValue());
                    } else {
                        log.info("===>ҵ��������ɱ�������ҵ���ʶΪ"
                                + busiCodeGen.getBusiCodeGenId());
                    }
                    currentValue = busiCodeRule.getMinValue();
                    busiCodeGen.setCurrentValue(busiCodeGen.getNextValue());
                    if (busiCodeRule.getIsCycDay() == 1) {
                        log.info("===>����ѭ��������ˮ��");
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (busiCodeGen.getUpdateDay() == null)
                            busiCodeGen.setUpdateDay(new Date());
                        try {
                            String curDateStr = sdf.format(new Date());
                            String updateDateStr = sdf.format(busiCodeGen
                                    .getUpdateDay());
                            log.info("===>��ǰʱ��Ϊ[" + curDateStr + "] ���һ�θ���ʱ��Ϊ["
                                    + updateDateStr+"]");
                            Date date1 = sdf.parse(curDateStr);
                            Date date2 = sdf.parse(updateDateStr);
                            if (date1.after(date2)) {
                                busiCodeGen.setCurrentValue(busiCodeRule
                                        .getMinValue());
                                busiCodeGen.setNextValue(busiCodeRule
                                        .getMinValue());
                            }
                        } catch (Exception e) {
                        }
                    }
                    String wad = busiCodeRule.getWadChar();
                    String nextValue = busiCodeGen.getNextValue();
                    log.info("===>��ǰ����ѭ�����ں����һ����ˮ����Ϊ" + nextValue);                    
                    if (busiCodeRule.getType() == 1) {                        
                        long next = Long.parseLong(busiCodeGen.getNextValue()) + 1;
                        long maxValue=Long.parseLong(busiCodeRule.getMaxValue());                        
                        if (next>maxValue) {
                            busiCodeGen.setNextValue(busiCodeRule.getMinValue());
                            busiCodeGen.setCurrentValue(busiCodeRule.getMinValue());
                        }
                        else
                            busiCodeGen.setNextValue(String.valueOf(next));
                    } else if (busiCodeRule.getType() == 0) {
                        char[] chars = busiCodeGen.getNextValue().toCharArray();
                        String nextValueStr=String.valueOf(nextCharValue(
                                chars, chars.length - 1));
                        if (nextValueStr.compareTo(busiCodeRule.getMaxValue())>0) {
                            busiCodeGen.setNextValue(busiCodeRule.getMinValue());
                            busiCodeGen.setCurrentValue(busiCodeRule.getMinValue());
                        }
                        else
                            busiCodeGen.setNextValue(nextValueStr);                        
                    }
                    busiCodeGen.setUpdateDay(new Date());
                    if (busiCodeRule.getNeedWad() == 1) {// ��Ҫ���ַ�
                        if (StringUtils.isEmpty(wad)) {// �����Ҫ���ַ������ַ��ǿգ��������a���ַ�
                            if (busiCodeRule.getType() == 1)
                                wad = "0";
                            else
                                wad = "A";
                        }
                        busiCodeRule.setWadChar(wad);
                        result = prefix
                                + StringUtils.leftPad(nextValue, busiCodeRule
                                        .getMaxValue().length(), wad) + suffix;
                    } else {
                        result = prefix + nextValue + suffix;
                    }
                    log.info("===>����ǰ���ɵ�ҵ�������" + result);
                    busiCodeGenDao.saveOrUpdate(busiCodeGen);
                    lockObj.setUpdateDate(new Date());
                } catch (Throwable e) {
                    log.error(e);
                    log.info("===>ִ������ʱ����");
                    tr.rollback();
                }
                tr.commit();
            } catch (Throwable e) {
                log.error(e);
                log.info("===>����ҵ�������̳����쳣");
            } finally {
                try {
                    if (session != null)
                        session.close();
                } catch (Exception e1) {

                }
            }
            notify();
        }

        synchronized public String get() {
            try {
                wait(timeOut);
            } catch (InterruptedException e) {
                log.info("===>�ȴ�����ʱ��ʱ����������ҵ�����");
            }
            return result;
        }

        /**
         * �����ַ���������һ���ַ�
         * 
         * @param current
         *            ��ǰ�ַ�������
         * @param index
         *            ��ǰ����ֵ
         * @return
         */
        private char[] nextCharValue(char[] current, int index) {
            if (current.length > 0) {
                if (current[index] == 'z' || current[index] == 'Z') {
                    current[index] = (char) ((int) current[index] - 25);
                    return (index == 0) ? current : nextCharValue(current,
                            index - 1);
                } else {
                    current[index] = (char) ((int) current[index] + 1);
                    return current;
                }
            } else {
                return "".toCharArray();
            }
        }
    }

}
