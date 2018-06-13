package com.uurobot.baseframe.mvp.base;

/**
 * Created by Administrator on 2018/6/13.
 */

/**
 * 基类 Presenter，，所有其他的 Presenter都继承它，然后实现自己的Presenter;;;
 * @param <T>
 */
public class BasePresenter<T extends IBaseContract.IBaseView> implements IBaseContract.IPresenter<T> {
        private T rootView;

        @Override
        public void attchView(T view) {
                this.rootView = view;
        }

        @Override
        public void dettachView() {
                if (rootView != null) {
                        rootView = null;
                }
        }
}
