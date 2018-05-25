package com.uurobot.baseframe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ITHolder extends RecyclerView.ViewHolder {
        public ImageView titleImageView;
        public TextView contentTextView;

        public ITHolder(View itemView) {
                super(itemView);
                titleImageView = itemView.findViewById(R.id.im_item_title_il);
                contentTextView = itemView.findViewById(R.id.tv_item_content_il);
        }
}
