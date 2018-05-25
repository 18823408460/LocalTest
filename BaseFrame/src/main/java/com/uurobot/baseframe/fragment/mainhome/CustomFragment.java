package com.uurobot.baseframe.fragment.mainhome;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uurobot.baseframe.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/23.
 */

public class CustomFragment extends BaseFragment {
        private static final String TAG = CustomFragment.class.getSimpleName();

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
                textView.setText("自定义Fragment");
                textView.setTextColor(Color.parseColor("#00ff00"));
                return textView;
        }
}
