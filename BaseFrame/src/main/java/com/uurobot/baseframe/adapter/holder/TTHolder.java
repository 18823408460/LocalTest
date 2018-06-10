package com.uurobot.baseframe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class TTHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;
        public View rootVIew ;
        public TTHolder(View itemView) {
                super(itemView);
                rootVIew = itemView ;
                contentTextView = itemView.findViewById(R.id.tv_item_content_ll);
                titleTextView = itemView.findViewById(R.id.tv_item_title_ll);
        }
}
