package com.unisrobot.javaread.java;

import org.junit.Test;

/**
 * Created by Administrator on 2018/5/24.
 */
//EnumJiJie枚举类 也是一个类，一个特殊的类
public class EnumJiJie implements IShow {
        private String content;
        private String desc;

        public static EnumJiJie CHUN = new EnumJiJie("CHUN", "春天");
        public static EnumJiJie XIA = new EnumJiJie("XIA", "夏天");
        public static EnumJiJie QIU = new EnumJiJie("QIU", "秋天");
        public static EnumJiJie DONG = new EnumJiJie("DONG", "冬天");

        public String getContent() {
                return content;
        }

        public String getDesc() {
                return desc;
        }

        @Override
        public void show() {
                System.out.println("content=" + getContent() + "   desc=" + getDesc());
        }

        private EnumJiJie(String content, String desc) {
                this.content = content;
                this.desc = desc;
        }
}
