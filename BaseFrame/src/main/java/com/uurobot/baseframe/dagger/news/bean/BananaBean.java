package com.uurobot.baseframe.dagger.news.bean;

import android.util.Log;

import com.uurobot.baseframe.dagger.news.TodoScope;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/23.
 */

@TodoScope
public class BananaBean {

        @Inject
        public BananaBean() {
                Log.d("test", "BananaBean()");
        }
}