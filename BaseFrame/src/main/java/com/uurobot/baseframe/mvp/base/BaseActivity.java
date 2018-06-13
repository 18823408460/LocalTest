package com.uurobot.baseframe.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/6/13.
 */

public abstract class BaseActivity<T extends IBaseContract.IPresenter> extends Activity implements IBaseContract.IBaseView {
        protected T mPresenter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(getLayoutId());
                mPresenter = getPresenter();
                attchView();
                initView();
                initData();
        }

        private void attchView() {
                if (mPresenter != null) {
                        mPresenter.attchView(this);
                }
        }

        protected abstract T getPresenter();

        protected abstract void initData();

        protected abstract void initView();

        protected abstract int getLayoutId();

        @Override
        protected void onDestroy() {
                super.onDestroy();
                dettachView();
        }

        private void dettachView() {
                if (mPresenter != null) {
                        mPresenter.dettachView();
                }
        }

        @Override
        public void showDialog() {

        }

        @Override
        public void hideDialog() {

        }
}
