/**
 * @{#} LogFactory.java Create on 2006-8-7 16:15:30
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.common.log;

import java.util.Hashtable;

/**
 * Class comments.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 */
public class LogFactory {
    private static final LogFactory logFactory = new LogFactory();

    private Hashtable<Object,Object> instances = new Hashtable<Object,Object>();

    /**
     * 获取Log实例
     * 
     * @param clazz
     * @return
     */
    public static Log getLog(Class clazz) {
        return getLog(clazz.getName());
    }

    /**
     * 获取Log实例
     * 
     * @param className
     * @return
     */
    public static Log getLog(String className) {
        Log log = (Log) logFactory.instances.get(className);

        if (log == null) {
            log = logFactory.getInstance(className);
            logFactory.instances.put(className, log);
        }

        return log;
    }

    /**
     * 创建Log实例
     * 
     * @param className
     * @return
     */
    private Log getInstance(String className) {
        Log log = new Log(className);

        return log;
    }
}
