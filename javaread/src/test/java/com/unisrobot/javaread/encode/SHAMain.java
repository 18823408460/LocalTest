package com.unisrobot.javaread.encode;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/5/
 *
 *
 *
 *
 因为二者均由MD4导出，SHA-1和MD5彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：
 l 对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对MD5是2^128数量级的操作，而对SHA-1则是2^160数量级的操作。这 样，SHA-1对强行攻击有更大的强度。
 l 对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。
 l 速度：在相同的硬件上，SHA-1的运行速度比MD5慢
 */

public class SHAMain {
        private String key = "md5";
        @Test
        public void test(){

                String a = "dfdsf";
                try {
                        MessageDigest messageDigest = MessageDigest.getInstance(key);
                        messageDigest.update(a.getBytes());
                        byte[] digest = messageDigest.digest();
                        BigInteger bigInteger = new BigInteger(digest);
                        System.out.println("=="+bigInteger.toString(32));

                } catch (NoSuchAlgorithmException e) {


                }
        }
}
