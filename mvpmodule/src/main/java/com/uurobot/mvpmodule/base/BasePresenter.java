package com.uurobot.mvpmodule.base;

/**
 * Created by Administrator on 2018/6/14.
 */

public class BasePresenter<T extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter<T> {
        private T rootView;

        @Override
        public void attachView(T view) {
                this.rootView = view;
        }

        @Override
        public void dettachView() {
                if (rootView != null) {
                        rootView = null;
                }
        }
}
