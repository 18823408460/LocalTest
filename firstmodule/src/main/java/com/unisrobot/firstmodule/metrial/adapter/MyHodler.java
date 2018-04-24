package com.unisrobot.firstmodule.metrial.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;

import org.w3c.dom.Text;

/**
 * Created by WEI on 2018/4/15.
 */

public class MyHodler extends RecyclerView.ViewHolder {
    public TextView textView;

    public MyHodler(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.first_module_recycleview_item_tv);
    }

    public MyHodler(View itemView,boolean isHead) {
        super(itemView);
        if (!isHead){
            textView = itemView.findViewById(R.id.first_module_recycleview_item_tv);
        }
    }
}
