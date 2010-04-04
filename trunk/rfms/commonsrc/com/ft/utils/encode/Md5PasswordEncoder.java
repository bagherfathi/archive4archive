package com.ft.utils.encode;

/**
 * MD5加密算法.
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public final class Md5PasswordEncoder {
    /**
     * 对给定的字符串进行MD5加密
     * 
     * @param password
     * @return
     */
    /**
     * 加密
     */
    public static String encode(final String password) {
        return CryptoUtils.MD5(password);
    }
    
    public static void main(String[] args){
	System.out.println (Md5PasswordEncoder.encode("1"));
    }
}