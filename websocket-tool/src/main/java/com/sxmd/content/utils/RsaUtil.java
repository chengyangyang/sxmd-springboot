package com.sxmd.content.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description:
 *
 * @author cy
 * @date 2019年11月01日 9:14
 * Version 1.0
 */
public class RsaUtil {
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * 编码
     */
    private static String charset = "utf-8";

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes(charset));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes(charset));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes(charset).length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(charset), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(charset), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes(charset));
        return new String(Base64.encodeBase64(signature.sign()),charset);
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes(charset));
        return signature.verify(Base64.decodeBase64(sign.getBytes(charset)));
    }

    /**
     * Description:   生成公钥私钥测试
     *
     * @param :
     * @return void
     * @author cy
     * @date 2019/11/1 9:24
     */
    private static void generateTest(){
        // 生成密钥对
        KeyPair keyPair = null;
        try {
            keyPair = getKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
        String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);
    }

    /**
     * Description:   测试公钥和私钥的使用
     *
     * @param publicKey:  公钥
     * @param privateKey:  私钥
     * @return void
     * @author cy
     * @date 2019/11/1 9:27
     */
    private static void checkTest(String publicKey,String privateKey) {
        if(publicKey == null || publicKey == "" || privateKey == null || privateKey == ""){
            return;
        }
        // RSA加密
        try {
        String data = "12312aawevrdgr312332312312312";
        String encryptData = encrypt(data, getPublicKey(publicKey));
        System.out.println("加密后内容:" + encryptData);
        // RSA解密
        String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
        System.out.println("解密后内容:" + decryptData);
        // RSA签名
        String sign = sign(data, getPrivateKey(privateKey));
        System.out.println("签名："+sign);
        // RSA验签
        boolean result = verify(data, getPublicKey(publicKey), sign);
        System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }


    public static void main(String[] args) {
        // 生成密钥
       // generateTest();

        // 验证生成的密钥
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPBRvHnLGAfsQDcfKLTZ65Ubni55DBg+XJ4FEwUdc3OkQpx99wrWjLtqrHGez9kPr/+nb8ZNjXeHXlZJ+2Z4hcnJfbdkAnmUR5mhiOsAcVG8F+nz9v3kWKDWsSQMoh0dQyJfzALJHgg7qsOFO7VuOGL7fpB9E9Jl27TDyv7CMXoXAgMBAAECgYAYSiqipMRpCJf0+lFC9kO5bnEugU2XpbX2wsyJV9Czf4a8Cn9NuSHAT6feVA7uAYfobyP9BxmlWylNz2x5jvT2nh4i75MSFLQBWdZnS8WlMA2y65cUglJywyQDhV/UCipZvxEr3WFkYNFRYqA3r/kqqZnfW9hBOGKxflb851/QAQJBAP8KODwB1sZdadxqB4zx2i7WHL4Jg/TXPpdVtHwBmSEK5mPRQs9Sl4t9opwWZLagsxurHu4XjnIhUaqwK4ji3lcCQQDxOVST+6AHziBk+b5EAKqnvB8X3CiRPwXlbvc9oz5XzH0qjX8arHRQHFkHYiJVPZ03dRtiyaxuAvizXcqhUWpBAkEAoGnIJw4tDcWRoR5bs24ngpAPsgQAKI7+AmfYxqqmqx7U/HGRqR9UzFrwXWbEYA5BuaRvJuhQIz8Lw+Vc7JNtrwJAaF0HbKbWy8mwVj9+w+kN7FgFk8saegysPmFN8orn9BByrrBdJ2spP2ekVP9uHzbzCAVQfeMHCOmcM+4GNtkEgQJASd1SAovOZfiEjJ4pDO1y4z0XAOR+wA4eOQbHjTMUrTQPXZHMRFtGWAmMVfDeN5c76wJ3Y362Txa0FjO9p7XcJQ==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDwUbx5yxgH7EA3Hyi02euVG54ueQwYPlyeBRMFHXNzpEKcffcK1oy7aqxxns/ZD6//p2/GTY13h15WSftmeIXJyX23ZAJ5lEeZoYjrAHFRvBfp8/b95Fig1rEkDKIdHUMiX8wCyR4IO6rDhTu1bjhi+36QfRPSZdu0w8r+wjF6FwIDAQAB";
        checkTest(publicKey,privateKey);
    }

}
