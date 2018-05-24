package com.unisrobot.javaread.java;

/**
 * Created by Administrator on 2018/5/24.
 */
//EnumJiJie枚举类 也是一个类，一个特殊的类
public enum EnumJiJie2 implements IShow{
        CHUN("CHUN", "春天"),
        XIA("XIA", "夏天"),
        QIU("QIU", "秋天"),
        DONG("DONG", "冬天");
        private String content;
        private String desc;

        public String getContent() {
                return content;
        }

        public String getDesc() {
                return desc;
        }

        private EnumJiJie2(String content, String desc) {
                this.content = content;
                this.desc = desc;
        }

        @Override
        public void show() {
                System.out.println("content=" + getContent() + "   desc=" + getDesc());
        }
}
