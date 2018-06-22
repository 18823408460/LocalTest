package com.uurobot.baseframe.dagger.news;

/**
 * Created by Administrator on 2018/6/21.
 */

public class AppleBean2 {
        private String name;
        private double price;

        public AppleBean2() {
                this.name = "红富士";
                this.price = 1;
        }

        public AppleBean2(String name) {
                this.name = name;
                this.price = 1;
        }

        @Override
        public String toString() {
                return "AppleBean{" +
                        "name='" + name + '\'' +
                        ", price=" + price +
                        '}';
        }
}
