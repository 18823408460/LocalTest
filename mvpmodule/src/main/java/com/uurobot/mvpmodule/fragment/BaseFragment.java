package com.uurobot.mvpmodule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.mvpmodule.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/14.
 */

public abstract class BaseFragment extends Fragment {
        private static final String TAG = BaseFragment.class.getSimpleName();
        protected Context mContext;
        //缓存Fragment view
        private View mRootView;
        private boolean mIsMulti = false;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                if (mRootView == null) {
                        mRootView = View.inflate(mContext, getLayoutId(), null);
                        ButterKnife.bind(this,mRootView);
                        initView();
                }
                ViewGroup parent = (ViewGroup) mRootView.getParent();
                if (parent != null) {
                        parent.removeView(mRootView);
                }
                return mRootView;
        }

        protected abstract void initView();

        protected abstract int getLayoutId();

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mContext = getActivity();
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
                        mIsMulti = true;

                } else {

                }
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
                if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
                        mIsMulti = true;
                        Log.e(TAG, "setUserVisibleHint:  show========");
                } else {
                        Log.e(TAG, "setUserVisibleHint: hide ====== ");
                        super.setUserVisibleHint(isVisibleToUser);
                }
        }

        /**
         * 初始化 Toolbar
         *
         * @param toolbar
         * @param homeAsUpEnabled
         * @param title
         */
        protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
                ((BaseActivity)getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
        }
}
