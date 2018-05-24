package com.unisrobot.javaread.java;

/**
 * Created by Administrator on 2018/5/24.
 */

public class Test {

        @org.junit.Test
        public void testEnum() {
                EnumJiJie enumJiJie = EnumJiJie.CHUN;
                enumJiJie.show();

                EnumJiJie2 enumJiJie2 = EnumJiJie2.XIA;
                enumJiJie2.show();

                EnumJiJie2 enumJiJie21 = EnumJiJie2.valueOf("QIU");
                enumJiJie21.show();

        }
}
