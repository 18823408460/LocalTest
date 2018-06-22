package com.uurobot.baseframe.dagger.news;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/21.
 */

public class StudentBean {
        private static final String TAG = "StudentBean";
        private int no;
        private String number;

        @Inject
        public AreaBean areaBean;

        @Inject
        public StudentBean() {
                Log.e(TAG, "StudentBean: ............");
                this.no = 1;
                this.number = "张三";
        }

       /* @Inject
        public StudentBean(String aa ) {
                Log.e(TAG, "StudentBean: ............");
                this.no = 1;
                this.number = "张三";
        }*/
        @Override
        public String toString() {
                return "StudentBean{" +
                        "no=" + no +
                        ", number='" + number + '\'' +
                        ", areaBean=" + areaBean +
                        '}';
        }
}
