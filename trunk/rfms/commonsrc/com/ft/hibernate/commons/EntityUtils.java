package com.ft.hibernate.commons;


/**
 * 实体解析
 *
 */
public class EntityUtils {
    /**
     * 得到对象名字取类名最后的一个"."的字符串
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
