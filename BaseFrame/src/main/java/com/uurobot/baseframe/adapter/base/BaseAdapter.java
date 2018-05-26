package com.uurobot.baseframe.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String TAG = BaseAdapter.class.getSimpleName();
        protected Context mContext;
        protected LayoutInflater layoutInflater;
        protected List<T> stringList;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                mContext = parent.getContext();
                layoutInflater = LayoutInflater.from(mContext);
                return loadView(parent);
        }

        protected abstract RecyclerView.ViewHolder loadView(ViewGroup parent);

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                setViewData(holder, position);
        }

        protected abstract void setViewData(RecyclerView.ViewHolder holder, int position);

        @Override
        public int getItemCount() {
                return stringList.size();
        }

}
