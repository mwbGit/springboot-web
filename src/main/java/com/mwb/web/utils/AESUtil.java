package com.mwb.web.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/4
 */
@Slf4j
public class AESUtil {
    private static final String COMMON_KEY = "OBxTFWmYS^BTVB$^GLixZZU!xIru19MW";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法


    public static String encrypt(String content) {
        return encrypt(content, COMMON_KEY);
    }

    public static String encryptId(long id) {
        return encrypt(String.valueOf(id), COMMON_KEY);
    }

    /**
     * AES 加密操作
     *
     * @return 返回Base64转码后的加密数据
     */
    private static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.getEncoder().encodeToString(result);// 通过Base64转码返回
        } catch (Exception ex) {
            log.error("AESUtil.encrypt content={},key={}, err", content, key, ex);
        }

        return null;
    }

    public static String decrypt(String content) {
        return decrypt(content, COMMON_KEY);
    }

    public static long decryptId(String content) {
        String result = decrypt(content, COMMON_KEY);
        if (result == null) {
            return 0;
        }
        return Long.parseLong(result);
    }

    /**
     * AES 解密操作
     */
    private static String decrypt(String content, String key) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("AESUtil.decrypt content={},key={}, err", content, key, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(final String key) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // AES 要求密钥长度为 128
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kg.init(128, secureRandom);

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            log.error("AESUtil.getSecretKey key={}, err", key, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String content = "2";
        System.out.println("原始内容:" + content);
        String s1 = AESUtil.encryptId(22222);
        System.out.println("加密后:" + s1);
        System.out.println("解密后:" + AESUtil.decryptId(s1));
    }
}