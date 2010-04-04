/**
 * @{#} BusiCodeGenerateManagerImpl.java Created on 2006-11-14 上午11:33:58
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

    private int timeOut = 2000;// 超时设置

    private static String LOCK_OBJ_CODE = "LOCK_OBJ_BUSI_CODE";

    /**
     * 业务编码规则数据访问对象
     */
    private BusiCodeRuleDao busiCodeRuleDao;

    /**
     * 业务编码规则数据访问对象
     */
    private BusiCodeGenDao busiCodeGenDao;

    /**
     * 业务编码规则数据访问对象
     */
    @SuppressWarnings("unused")
	private LockObjDao lockObjDao;

    /**
     * 设置业务编码规则数据访问对象
     * 
     * @spring.property ref="busiCodeRuleDao"
     * 
     * @param busiCodeRuleDao
     *            业务编码数据访问对象
     * 
     */
    public void setBusiCodeRuleDao(BusiCodeRuleDao busiCodeRuleDao) {
        this.busiCodeRuleDao = busiCodeRuleDao;
    }

    /**
     * 设置业务编码生成数据访问对象
     * 
     * @spring.property ref="busiCodeGenDao"
     * @param busiCodeGenDao
     *            业务编码生成数据访问对象
     */

    public void setBusiCodeGenDao(BusiCodeGenDao busiCodeGenDao) {
        this.busiCodeGenDao = busiCodeGenDao;
    }

    /**
     * 设置锁对象数据访问对象
     * 
     * @spring.property ref="lockObjDao"
     * 
     * @param lockObjDao
     */
    public void setLockObjDao(LockObjDao lockObjDao) {
        this.lockObjDao = lockObjDao;
    }

    /**
     * 获取当前可用的业务编码 内置变量currentDay 当天日期 格式: yyyyMMdd
     * 
     * 
     * @param busiCodeRuleCode
     *            业务编码
     * @param paramMap
     *            参数列表信息
     */
    public synchronized String generateBusiCode(String busiCodeRuleCode,
            Map paramMap) {
        if (paramMap == null)
            paramMap = new Hashtable();
        log.info("传入的业务编码规则编码是" + busiCodeRuleCode);
        for (Iterator ite = paramMap.keySet().iterator(); ite.hasNext();) {
            String key = (String) ite.next();
            if (key == null)
                continue;
            log.info("传入的参数为key=" + key);
            Object obj = paramMap.get(key);
            log.info("传入的参数为value=" + obj);
        }
        log.info("传入的参数为" + (new ToStringBuilder(paramMap)).toString());
        if (StringUtils.isEmpty(busiCodeRuleCode)) {
            log.info("传入参数为空，不能编码....");
            return null;
        }
        // 调用业务代码生成线程生成业务代码
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

    // 内部类，主要用于异步生成编码
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
         * 获取当前可用的业务编码 内置变量currentDay 当天日期 格式: yyyyMMdd
         * 
         * 
         * @param busiCodeRuleCode
         *            业务编码
         * @param paramMap
         *            参数列表信息
         */
        public synchronized void gen() {
            Session session = null;
            try {
                BusiCodeRule busiCodeRule = busiCodeRuleDao
                        .getBusiCodeRuleByCode(busiCodeRuleCode);
                if (busiCodeRule == null) {
                    log.info("===根据业务规则编码" + busiCodeRuleDao
                            + "没有找到业务规则对象,不能生成业务编码");
                    return;
                }
                log.info("===>业务规则编码对象标识是" + busiCodeRule.getBusiCodeRuleId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String currentDay = sdf.format(new Date());
                paramMap.put("currentDay", currentDay);
                String prefix = busiCodeRule.getPrefix();
                if (prefix == null)
                    prefix = "";
                String suffix = busiCodeRule.getSuffix();
                if (suffix == null)
                    suffix = "";
                // 替换前缀定义的变量
                prefix = ExpressionEvaluator.evaluate(prefix, paramMap);
                // 替换后缀定义的变量
                suffix = ExpressionEvaluator.evaluate(suffix, paramMap);
                log.info("===>前后缀实例后数据为:" + prefix + "," + suffix);
                BusiCodeGen busiCodeGen = null;
                String busiCodeGenCode = "";
                if (busiCodeRule.getRuleType() == 0) {// 一个规则一个编码
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode();
                }
                if (busiCodeRule.getRuleType() == 1) {// 一个规则和前缀一个编码
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode() + "_"
                            + prefix;
                }
                if (busiCodeRule.getRuleType() == 2) {// 一个规则和后缀一个编码
                    busiCodeGenCode = busiCodeRule.getBusiCodeRuleCode() + "_"
                            + suffix;
                }
                if (busiCodeRule.getRuleType() == 3) {// 一个规则和前后缀一个编码
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
                    query.setLockMode("lo", LockMode.UPGRADE); // 加锁
                    List lockObjList = query.list();// 执行查询，获取数
                    if ((lockObjList != null) && (lockObjList.size() > 0))
                        lockObj = (LockObj) lockObjList.get(0);
                    else
                        lockObj = null;
                    if (lockObj == null) {
                        log.info("===>没有找到编码标识为" + LOCK_OBJ_CODE
                                + "的锁对象,不能生成业务编码");
                        notify();
                        return;
                    }
                    busiCodeGen = busiCodeGenDao.getBusiCodeGenByCode(
                            busiCodeGenCode, busiCodeRule.getBusiCodeRuleId());
                    if (busiCodeGen == null) {
                        log.info("===>业务规则生成编码对象没有找到，重新生成一个");
                        busiCodeGen = new BusiCodeGen();
                        busiCodeGen.setBusiCodeGenCode(busiCodeGenCode);
                        busiCodeGen.setBusiCodeRuleId(busiCodeRule
                                .getBusiCodeRuleId());
                        busiCodeGen.setCreateDay(new Date());
                        busiCodeGen.setUpdateDay(new Date());
                        busiCodeGen.setCurrentValue(busiCodeRule.getMinValue());
                        busiCodeGen.setNextValue(busiCodeRule.getMinValue());
                    } else {
                        log.info("===>业务规则生成编码对象找到标识为"
                                + busiCodeGen.getBusiCodeGenId());
                    }
                    currentValue = busiCodeRule.getMinValue();
                    busiCodeGen.setCurrentValue(busiCodeGen.getNextValue());
                    if (busiCodeRule.getIsCycDay() == 1) {
                        log.info("===>按天循环生成流水号");
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (busiCodeGen.getUpdateDay() == null)
                            busiCodeGen.setUpdateDay(new Date());
                        try {
                            String curDateStr = sdf.format(new Date());
                            String updateDateStr = sdf.format(busiCodeGen
                                    .getUpdateDay());
                            log.info("===>当前时间为[" + curDateStr + "] 最后一次更新时间为["
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
                    log.info("===>当前处理循环日期后的下一个流水数据为" + nextValue);                    
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
                    if (busiCodeRule.getNeedWad() == 1) {// 需要补字符
                        if (StringUtils.isEmpty(wad)) {// 如果需要补字符，但字符是空，则设置黙认字符
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
                    log.info("===>保存前生成的业务编码是" + result);
                    busiCodeGenDao.saveOrUpdate(busiCodeGen);
                    lockObj.setUpdateDate(new Date());
                } catch (Throwable e) {
                    log.error(e);
                    log.info("===>执行事务时出错");
                    tr.rollback();
                }
                tr.commit();
            } catch (Throwable e) {
                log.error(e);
                log.info("===>生成业务代码过程出现异常");
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
                log.info("===>等待处理时超时，不能生成业务编码");
            }
            return result;
        }

        /**
         * 根据字符串生成下一个字符
         * 
         * @param current
         *            当前字符串数组
         * @param index
         *            当前索引值
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
