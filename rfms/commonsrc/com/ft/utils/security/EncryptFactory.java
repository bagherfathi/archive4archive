/**
 * <p>Title: HZSpell.java</p>
 * <p>Description:
 *  º”√‹À„∑®≥ß
 */

package com.ft.utils.security;




public class EncryptFactory {
    private static EncryptFactory encryptFactory;

    static{
        if(encryptFactory==null)
        {
            encryptFactory=new EncryptFactory();
        }
    }

    public  IEncrypt  getEncrypt(String type)
    {
        IEncrypt encrypt=null;
        if(type.equalsIgnoreCase("MD5"))
        {
            encrypt=(IEncrypt) new MD5();
        }

        return encrypt;
    }

    public static EncryptFactory getInstance()
    {
        return encryptFactory;
    }

    public static void main(String[] argvs)
    {
        IEncrypt encrypt=EncryptFactory.getInstance().getEncrypt("md5");
        if(encrypt!=null)
            System.out.println(encrypt.encrypt("12345678",64));
    }

}