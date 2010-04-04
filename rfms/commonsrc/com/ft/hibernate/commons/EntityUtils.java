package com.ft.hibernate.commons;


/**
 * ʵ�����
 *
 */
public class EntityUtils {
    /**
     * �õ���������ȡ��������һ��"."���ַ���
     *
     * @param clazz
     *
     * @return
     */
    public static String getEntityName(final Class clazz) {
        String className = clazz.getName();
        className = className.substring(className.lastIndexOf(".") + 1);

        return className;
    }
}
