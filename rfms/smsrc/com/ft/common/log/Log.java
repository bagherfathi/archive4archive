package com.ft.common.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * ����Log4jʵ�ֵļ�¼��־��. Log4j�汾��log4j-1.2.13
 * 
 * @version 1.0
 */
public class Log {
    private static final String FQCN = Log.class.getName();

    private transient Logger logger = null;

    private ActionLogger actionLogger = null;

    private String className = null;

    public Log(String className) {
        this.className = className;
    }

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(className);
        }

        return (this.logger);
    }

    @SuppressWarnings("unused")
	private ActionLogger getActionLogger() {
        if (actionLogger == null) {
            actionLogger = new ActionLogger(className);
        }

        return this.actionLogger;
    }

    /**
     * Check whether this category is enabled for the DEBUG Level.
     * 
     * @return
     */
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    /**
     * Check whether this category is enabled for the ERROR Level.
     * 
     * @return
     */
    public boolean isErrorEnabled() {
        return getLogger().isEnabledFor(Level.ERROR);
    }

    /**
     * Check whether this category is enabled for the FATAL Level.
     * 
     * @return
     */
    public boolean isFatalEnabled() {
        return getLogger().isEnabledFor(Level.FATAL);
    }

    /**
     * Check whether this category is enabled for the INFO Level.
     * 
     * @return
     */
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * Check whether this category is enabled for the TRACE Level.
     * 
     * @return
     */
    public boolean isTraceEnabled() {
        return getLogger().isTraceEnabled();
    }

    /**
     * Check whether this category is enabled for the WARN Level.
     * 
     * @return
     */
    public boolean isWarnEnabled() {
        return getLogger().isEnabledFor(Level.WARN);
    }

    /**
     * Log a message object with the TRACE level.
     * 
     * @param message
     */
    public void trace(Object message) {
        getLogger().log(FQCN, Level.TRACE, message, null);
    }

    /**
     * Log a message object with the TRACE level.
     * 
     * @param message
     * @param t
     */
    public void trace(Object message, Throwable t) {
        getLogger().log(FQCN, Level.TRACE, message, t);
    }

    /**
     * Log a message object with the DEBUG level.
     * 
     * @param message
     */
    public void debug(Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, null);
    }

    /**
     * Log a message object with the DEBUG level.
     * 
     * @param message
     * @param t
     */
    public void debug(Object message, Throwable t) {
        getLogger().log(FQCN, Level.DEBUG, message, t);
    }

    /**
     * Log a message object with the INFO level.
     * 
     * @param message
     */
    public void info(Object message) {
        getLogger().log(FQCN, Level.INFO, message, null);
    }

    /**
     * Log a message object with the INFO level.
     * 
     * @param message
     * @param t
     */
    public void info(Object message, Throwable t) {
        getLogger().log(FQCN, Level.INFO, message, t);
    }

    /**
     * Log a message object with the WARN level.
     * 
     * @param message
     */
    public void warn(Object message) {
        getLogger().log(FQCN, Level.WARN, message, null);
    }

    /**
     * Log a message object with the WARN level.
     * 
     * @param message
     * @param t
     */
    public void warn(Object message, Throwable t) {
        getLogger().log(FQCN, Level.WARN, message, t);
    }

    /**
     * Log a message object with the ERROR level.
     * 
     * @param message
     */
    public void error(Object message) {
        getLogger().log(FQCN, Level.ERROR, message, null);
    }

    /**
     * Log a message object with the ERROR level.
     * 
     * @param message
     * @param t
     */
    public void error(Object message, Throwable t) {
        getLogger().log(FQCN, Level.ERROR, message, t);
    }

    /**
     * Log a message object with the FATAL level.
     * 
     * @param message
     */
    public void fatal(Object message) {
        getLogger().log(FQCN, Level.FATAL, message, null);
    }

    /**
     * Log a message object with the FATAL level.
     * 
     * @param message
     * @param t
     */
    public void fatal(Object message, Throwable t) {
        getLogger().log(FQCN, Level.FATAL, message, t);
    }

    /**
     * д�������־
     * 
     * @param request
     * @param actionCode
     *                ��������
     * @param actionSeq
     *                ������ˮ��
     * @param resultCode
     *                �����������
     */
    public void log(HttpServletRequest request, String actionCode,
            String actionSeq, String resultCode) {
        
        //getActionLogger().log(request, actionCode, actionSeq, resultCode);
    }

    /**
     * д��������־
     * 
     * @param request
     * @param actionCode
     *                ��������
     * @param actionSeq
     *                ������ˮ��
     * @param resultCode
     *                �����������
     * @param startTime
     *                ������ʼʱ��
     * @param endTime
     *                ��������ʱ��
     */
    public void log(HttpServletRequest request, String actionCode,
            String actionSeq, String resultCode, Date startTime, Date endTime) {
        //getActionLogger().log(request, actionCode, actionSeq, resultCode,
        //        startTime, endTime);
    }
}
