package com.uurobot.baseframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public RecycleViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv_recycleview);
        }
}
