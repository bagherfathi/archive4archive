/*package com.ft.utils.security;

import javax.crypto.Cipher;


import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.io.*;
import java.math.BigInteger;

*//**
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到 http://www.bouncycastle.org下载 bcprov-jdk16-139.jar  	 
 
 * 
 *//* 
public class RSAUtil {

    *//**
     * 生成密钥对
     * @return KeyPair
     * @throws EncryptException
     *//*
    public static KeyPair generateKeyPair() throws EncryptException {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
			//加密块的大小，可以更改，但是不要太大，否则效率会低
            final int KEY_SIZE = 1024;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
    
    *//**
     * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws EncryptException
     *//*
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws EncryptException {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new EncryptException(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new EncryptException(ex.getMessage());
        }
    }
    
    *//**
     * 生成私钥
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws EncryptException
     *//*
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws EncryptException {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new EncryptException(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new EncryptException(ex.getMessage());
        }
    }
    
    *//**
     * 加密
     * @param key 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws EncryptException
     *//*
    public static byte[] encrypt(Key key, byte[] data) throws EncryptException {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();//获得加密块大小，如：加密前数据为128个byte，而key_size=1024 加密块大小为127 byte,加密后为128个byte;因此共有2个加密块，第一个127 byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
//这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。

                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
    
    *//**
     * 解密
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws EncryptException
     *//*
    public static byte[] decrypt(Key key, byte[] raw) throws EncryptException {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new EncryptException(e.getMessage());
        }
    }
    
    *//**
     *
     * @param args
     * @throws Exception
     *//*
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\Test.java");
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] tmpbuf = new byte[1024];
        int count = 0;
        while ((count = in.read(tmpbuf)) != -1) {
            bout.write(tmpbuf, 0, count);
            tmpbuf = new byte[1024];
        }
        in.close();
        byte[] orgData = bout.toByteArray();
		
		String test="12345678901234567890中文";
		byte[] orgData = test.getBytes();
		
		
        KeyPair keyPair = RSAUtil.generateKeyPair();
        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
        
        
        BigInteger bi=new BigInteger("117320608319381395439160246315622326571286274046179171372145720336683689448758467488343368596012322329379115934357747247537901438041950114143276463057289549614256427068819212798434396934423950606972199484924076296718288603315447315901060796720815000245160474843944695198615221868100676249624489126300211531833");        
        byte[] pubModBytes = bi.toByteArray();
        
        BigInteger pubExp=new BigInteger("65537");
        byte[] pubPubExpBytes = pubExp.toByteArray();
		
		
        //byte[] pubModBytes = pubKey.getModulus().toByteArray();
        //byte[] pubPubExpBytes = pubKey.getPublicExponent().toByteArray();
        //byte[] priModBytes = priKey.getModulus().toByteArray();
        //byte[] priPriExpBytes = priKey.getPrivateExponent().toByteArray();
		
        //RSAPublicKey recoveryPubKey = RSAUtil.generateRSAPublicKey(pubModBytes,pubPubExpBytes);
        //RSAPrivateKey recoveryPriKey = RSAUtil.generateRSAPrivateKey(priModBytes,priPriExpBytes);
        
		System.out.println("pubKey:"+pubKey.getModulus());
		System.out.println("pubKey:"+pubKey.getPublicExponent());
		System.out.println("priKey:"+priKey.getModulus());
		System.out.println("priKey:"+priKey.getPrivateExponent());
		
        byte[] raw = RSAUtil.encrypt(priKey, orgData);
        file = new File("d:\\encrypt.java");
        OutputStream out = new FileOutputStream(file);
        out.write(raw);
        out.close();
        
        java.io.ObjectOutputStream outputStream=new java.io.ObjectOutputStream(new java.io.FileOutputStream("d:\\myprikey.dat"));
        outputStream.writeObject(priKey);
        outputStream.close();
        
        byte[] data = RSAUtil.decrypt(recoveryPubKey, orgData);
        System.out.println(new String(data));
        file = new File("d:\\decrypt_Test.java");
        out = new FileOutputStream(file);
        out.write(data);
        out.flush();
        out.close();
        System.out.println("end");
    }
}
*/