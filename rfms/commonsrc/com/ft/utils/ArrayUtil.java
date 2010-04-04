package com.ft.utils;

/**
 * 数组的常用方法,这些方法在common-lang.jar中没有定义的。
 * 其它需要数组操作的方法请参考common-lang.jar包中的
 * org.apache.commons.lang.ArrayUtils类。
 * 
 * @version 1.0
 */
public class ArrayUtil {
    
    /**
     * 把long数组转为指定字符分隔的字符串
     * @param array 要转换的数组
     * @param regex 分隔符
     * @return
     */
    public static String getArrayAsString(long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i]);
                if (i != (array.length - 1)) // 如果不是最后一个，加上中间
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }
    
    /**
     * 把Long数组转为指定字符分隔的字符串
     * @param array 要转换的数组
     * @param regex 分隔符
     * @return
     */
    public static String getArrayAsString(Long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i].longValue());
                if (i != (array.length - 1)) // 如果不是最后一个，加上中间
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }
}
