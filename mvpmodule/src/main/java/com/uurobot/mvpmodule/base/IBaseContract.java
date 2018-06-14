package com.uurobot.mvpmodule.base;

/**
 * Created by Administrator on 2018/6/14.
 */

public interface IBaseContract {
        interface IBaseView {
                void showLoading();

                void hideLoading();
        }

        interface IBasePresenter<T extends IBaseView> {
                void attachView(T view);

                void dettachView();
        }
}
