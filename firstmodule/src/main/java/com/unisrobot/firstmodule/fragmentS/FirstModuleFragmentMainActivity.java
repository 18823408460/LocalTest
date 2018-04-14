package com.unisrobot.firstmodule.fragmentS;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/14.
 */
// AppCompatActivity ,FragmentActivity 可以直接使用 v4中的 Fragment。、
public class FirstModuleFragmentMainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_fragment_main);

        TextView viewById = findViewById(R.id.first_module_tv);

    }
}
