package com.unisrobot.javaread.encode;

import org.junit.Test;

import java.util.Base64;

/**
 * Created by Administrator on 2018/5/17.
 */

/**
 * Base64编码是基于64个字符(字符分别为：ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxzy0123456789+/)的编码方式
 * 因为2的6次方正好为64，所以我们用6bit就可以表示出64个字符，eg:000000对应'A'，000001对应'B'，111111对应'/'。
 *
 * abc=01100001 01100010 01100011 分成Base64分组后为：011000 010110 001001 100011 即24 22 9 35，对应Base64编码的 YWJj
 */
public class Base64Main {
        @Test
        public void test1() {
                String hello = "AB";
                String hello1 = "B";
                System.out.println("raw===" + hello1.getBytes());
                Base64.Encoder encoder = Base64.getEncoder();
                byte[] encode = encoder.encode(hello.getBytes());
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < encode.length; i++) {
                        stringBuilder.append(Integer.toBinaryString(encode[i]) + "   ");
                }
                System.out.println("encode===" + stringBuilder);

                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decode = decoder.decode(encode);
                String data = new String(decode);
                System.out.println("decode=== " + data);
        }
}
