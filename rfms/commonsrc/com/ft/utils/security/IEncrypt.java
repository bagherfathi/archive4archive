/**
 * <p>Title: HZSpell.java</p>
 * <p>Description:
 * 加算算法接口,加密程序必须实现这个接口
 * </p>
 */

package com.ft.utils.security;


public interface IEncrypt {
    //加密，取得32位加密密码
    public String encrypt(String encryptStr);
    //取得定长加密密码
    public String encrypt(String encryptStr,int length);
    //以时间戳加密密码
    public String encrypt(String encryptStr,String timeStamp);

}