package com.unisrobot.javaread.bytes;

import org.junit.Test;

/**
 * Created by Administrator on 2018/6/25.
 */

public class BytesMain {

        @Test
        public void test1(){
             byte a = 0x00 ;
             byte b = 0x01;
             byte state = (byte) (a&b);
             if (state == 0x00){
                     System.out.println("============");
             }else if (state == 0x01){
                     System.out.println("++++++++++++");
             }
             System.out.println("00000000000000000000");
        }
}
