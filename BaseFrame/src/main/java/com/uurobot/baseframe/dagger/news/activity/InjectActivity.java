package com.uurobot.baseframe.dagger.news.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uurobot.baseframe.dagger.news.AppleBean;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class InjectActivity extends AppCompatActivity {

        @Inject
        AppleBean appleBean;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                AndroidInjection.inject(this);
                super.onCreate(savedInstanceState);
                System.out.println("============ "+appleBean);
        }
}
