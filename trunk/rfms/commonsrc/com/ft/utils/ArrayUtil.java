package com.ft.utils;

/**
 * ����ĳ��÷���,��Щ������common-lang.jar��û�ж���ġ�
 * ������Ҫ��������ķ�����ο�common-lang.jar���е�
 * org.apache.commons.lang.ArrayUtils�ࡣ
 * 
 * @version 1.0
 */
public class ArrayUtil {
    
    /**
     * ��long����תΪָ���ַ��ָ����ַ���
     * @param array Ҫת��������
     * @param regex �ָ���
     * @return
     */
    public static String getArrayAsString(long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i]);
                if (i != (array.length - 1)) // ����������һ���������м�
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }
    
    /**
     * ��Long����תΪָ���ַ��ָ����ַ���
     * @param array Ҫת��������
     * @param regex �ָ���
     * @return
     */
    public static String getArrayAsString(Long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i].longValue());
                if (i != (array.length - 1)) // ����������һ���������м�
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }
}
