package com.uurobot.baseframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/5/23.
 */

public abstract class BaseFragment extends Fragment {
        protected Context mContext;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                return initView(inflater,container,savedInstanceState);
        }

        protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                initData();
        }

        protected void initData(){}

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mContext = getActivity();
        }
}
