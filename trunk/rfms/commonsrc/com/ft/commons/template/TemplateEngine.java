package com.ft.commons.template;

import java.io.Writer;

import java.util.Collection;
import java.util.Map;


/**
 * ģ������ӿ�
 */
public interface TemplateEngine {
    /**
     * ��һ�����������ģ��
     *
     * @param obj
     * @param writer
     */
    public abstract void execute(Object obj, Writer writer);

    /**
     * ָ��ģ������,��һ�����������ģ��
     *
     * @param templateName
     * @param obj
     * @param writer
     */
    public abstract void execute(
        String templateName, Object obj, Writer writer);

    /**
     * ��һ����������ģ��
     *
     * @param coll
     * @param writer
     */
    public abstract void execute(Collection coll, Writer writer);

    /**
     * ָ��ģ������,��һ����������ģ��
     *
     * @param props
     * @param vmName
     * @param writer
     */
    public abstract void execute(Map props, String vmName, Writer writer);

    /**
     * ָ��ģ������,��һ����������ģ��
     *
     * @param templateName
     * @param obj
     * @param writer
     */
    public abstract void execute(
        Collection coll, String vmName, Writer writer);

    /**
     * ָ��ģ������,��ָ���Ķ�����ָ������ʽ�����ģ��
     *
     * @param obj
     * @param objectName
     * @param vmName
     * @param writer
     */
    public abstract void execute(
        Object obj, String objectName, String vmName, Writer writer);

    /**
     * ָ��ģ������,��һ��������ָ������ʽ�����ģ��
     *
     * @param obj
     * @param vmName
     * @param writer
     */
    public abstract void execute(Object obj, String vmName, Writer writer);
}
