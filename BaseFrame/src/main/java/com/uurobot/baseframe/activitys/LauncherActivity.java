package com.uurobot.baseframe.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/22.
 */
// AppCompatActivity   java.lang.IllegalStateException: You need to use a Theme.AppCompat theme
public class LauncherActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_launcher);
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                startMainActivity();
                        }
                },2000);
        }

        private void startMainActivity() {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }
}
