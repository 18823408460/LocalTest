package com.uurobot.baseframe.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.uurobot.baseframe.R;

/**
 * Created by WEI on 2018/5/26.
 */

public class TestViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
       // setContentView(new ImageTextView(this));
    }
}
