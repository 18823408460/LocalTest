package com.uurobot.baseframe.adapter.jinrong;

import android.view.View;

/**
 * Created by WEI on 2018/6/7.
 */

public abstract class BaseHolderJinRong<T> {
    private View rootView;
    private T data;

    public BaseHolderJinRong() {
        rootView = initRootView();
        rootView.setTag(this);
    }

    public void setData(T data) {
        this.data = data;
        refreshData(data);
    }

    public abstract void refreshData(T data);

    public View getRootView() {
        return rootView;
    }

    public abstract View initRootView();
}
