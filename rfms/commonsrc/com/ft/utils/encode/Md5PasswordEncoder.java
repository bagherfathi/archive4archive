package com.ft.utils.encode;

/**
 * MD5�����㷨.
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public final class Md5PasswordEncoder {
    /**
     * �Ը������ַ�������MD5����
     * 
     * @param password
     * @return
     */
    /**
     * ����
     */
    public static String encode(final String password) {
        return CryptoUtils.MD5(password);
    }
    
    public static void main(String[] args){
	System.out.println (Md5PasswordEncoder.encode("1"));
    }
}