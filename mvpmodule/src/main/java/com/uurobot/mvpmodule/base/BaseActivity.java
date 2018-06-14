package com.uurobot.mvpmodule.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/14.
 */

public abstract class BaseActivity<T extends IBaseContract.IBasePresenter> extends AppCompatActivity implements IBaseContract.IBaseView {
        private T mPresenter;
        private Unbinder unbinder;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mPresenter = getPreSenter();
                setContentView(getViewLayoutId());
                unbinder = ButterKnife.bind(this);
                initView();
                initData();
                attchView();
        }

        protected abstract void initView();

        private void attchView() {
                if (mPresenter != null) {
                        mPresenter.attachView(this);
                }
        }

        protected abstract void initData();

        protected abstract int getViewLayoutId();

        protected abstract T getPreSenter();

        @Override
        protected void onDestroy() {
                super.onDestroy();
                if (mPresenter != null) {
                        mPresenter.dettachView();
                }
                unbinder.unbind();
        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        /**
         * 初始化 Toolbar
         *
         * @param toolbar
         * @param homeAsUpEnabled
         * @param title
         */
        public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
                toolbar.setTitle(title);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }

        protected void gotoActivity(Class<T> tClass) {
                startActivity(new Intent(this, tClass));
        }
}
