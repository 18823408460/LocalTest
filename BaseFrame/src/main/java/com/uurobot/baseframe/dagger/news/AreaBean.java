package com.uurobot.baseframe.dagger.news;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/21.
 */

public class AreaBean {
        private String provice;
        private String city;

        @Inject
        public AreaBean() {
                this.provice = "四川";
                this.city = "成都";
        }

        @Override
        public String toString() {
                return "AreaBean{" +
                        "provice='" + provice + '\'' +
                        ", city='" + city + '\'' +
                        '}';
        }
}
