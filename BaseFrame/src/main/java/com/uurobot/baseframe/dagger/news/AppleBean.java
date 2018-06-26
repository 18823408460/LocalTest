package com.uurobot.baseframe.dagger.news;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/21.
 */
//@TodoScope
public class  AppleBean {
        private String name;
        private double price;

        @Inject
        public AppleBean() {
                this.name = "红富士";
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
