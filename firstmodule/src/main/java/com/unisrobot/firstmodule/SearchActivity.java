package com.unisrobot.firstmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.unisrobot.firstmodule.view.VerticalProgress;

/**
 * Created by WEI on 2018/4/22.
 */

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.first_module_activity_search);
        setContentView(new VerticalProgress(this));
    }
}
