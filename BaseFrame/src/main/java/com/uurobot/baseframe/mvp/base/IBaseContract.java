package com.uurobot.baseframe.mvp.base;

/**
 * Created by Administrator on 2018/6/13.
 */

public interface IBaseContract {
        interface IBaseView {
                void showDialog();

                void hideDialog();
        }

        interface IPresenter<T extends IBaseView> {
                void attchView(T view);

                void dettachView();
        }
}
