package com.uurobot.baseframe.adapter.jinrong;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by WEI on 2018/6/7.
 */

public abstract class BaseAdapterJinRong<T> extends BaseAdapter {
    private List<T> data;

    public BaseAdapterJinRong(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolderJinRong<T> baseHolderJinRong;
        if (convertView == null) {
            baseHolderJinRong = getHolder();
        } else {
            baseHolderJinRong = (BaseHolderJinRong<T>) convertView.getTag();
        }
        T t = data.get(position);
        baseHolderJinRong.setData(t);
        return baseHolderJinRong.getRootView();
    }

    public abstract BaseHolderJinRong<T> getHolder();
}
