/**
 * <p>Title: HZSpell.java</p>
 * <p>Description:
 * �����㷨�ӿ�,���ܳ������ʵ������ӿ�
 * </p>
 */

package com.ft.utils.security;


public interface IEncrypt {
    //���ܣ�ȡ��32λ��������
    public String encrypt(String encryptStr);
    //ȡ�ö�����������
    public String encrypt(String encryptStr,int length);
    //��ʱ�����������
    public String encrypt(String encryptStr,String timeStamp);

}