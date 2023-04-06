package com.xia.util.salt;

import com.xia.pojo.UserInfo;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Saltes {

    private static final int SALT_LENGTH = 16; // 盐的长度

    private static final int ITERATIONS = 10000; // 迭代次数

    private static final int KEY_LENGTH = 256; // 生成的密钥长度


    /**
     * 生成随机盐值
     *
     * @return 盐值
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }


    /**
     * 使用PBKDF2算法对密码进行加盐哈希处理
     *
     * @param password 明文密码
     * @param salt 盐值
     * @return 带盐哈希密码
     */
    public static byte[] hashPassword(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey key = factory.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 效验密码
     * @param users 用户对象
     * @param Password 输入的密码
     * @return  比较结果
     */
    public static boolean compare(UserInfo users,String Password){

        byte[] storedSalt = Base64.getDecoder().decode(users.getSalt()); // 解码存储的盐值
        byte[] storedHashedPassword = Base64.getDecoder().decode(users.getUserPwd()); // 解码存储的哈希密码
        byte[] hashedPassword = Saltes.hashPassword(Password, storedSalt); // 计算提供的密码的哈希值
        return MessageDigest.isEqual(storedHashedPassword, hashedPassword);// 比较哈希值
    }

}
