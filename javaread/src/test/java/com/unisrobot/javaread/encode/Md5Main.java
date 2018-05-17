package com.unisrobot.javaread.encode;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/5/17.
 */

/**
 * 常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值
 *
 * 通常我们不直接使用上述MD5加密。通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串
 */
public class Md5Main {

        private String key = "md5";
        @Test
        public void test(){
                String a = "dfdsf";
                try {
                        MessageDigest messageDigest = MessageDigest.getInstance(key);
                        byte[] bytes = a.getBytes();
                        messageDigest.update(bytes);
                        byte[] digest = messageDigest.digest();
                        BigInteger bigInteger = new BigInteger(digest);
                        System.out.println("=="+bigInteger.toString(16));

                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                }
        }
}
