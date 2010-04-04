package com.ft.commons.template;

import java.io.Writer;

import java.util.Collection;
import java.util.Map;


/**
 * 模板引擎接口
 */
public interface TemplateEngine {
    /**
     * 将一个对象输出到模板
     *
     * @param obj
     * @param writer
     */
    public abstract void execute(Object obj, Writer writer);

    /**
     * 指定模板名称,将一个对象输出到模板
     *
     * @param templateName
     * @param obj
     * @param writer
     */
    public abstract void execute(
        String templateName, Object obj, Writer writer);

    /**
     * 将一组对象输出到模板
     *
     * @param coll
     * @param writer
     */
    public abstract void execute(Collection coll, Writer writer);

    /**
     * 指定模板名称,将一组对象输出到模板
     *
     * @param props
     * @param vmName
     * @param writer
     */
    public abstract void execute(Map props, String vmName, Writer writer);

    /**
     * 指定模板名称,将一组对象输出到模板
     *
     * @param templateName
     * @param obj
     * @param writer
     */
    public abstract void execute(
        Collection coll, String vmName, Writer writer);

    /**
     * 指定模板名称,将指定的对象按照指定的形式输出到模板
     *
     * @param obj
     * @param objectName
     * @param vmName
     * @param writer
     */
    public abstract void execute(
        Object obj, String objectName, String vmName, Writer writer);

    /**
     * 指定模板名称,将一个对象按照指定的形式输出到模板
     *
     * @param obj
     * @param vmName
     * @param writer
     */
    public abstract void execute(Object obj, String vmName, Writer writer);
}
