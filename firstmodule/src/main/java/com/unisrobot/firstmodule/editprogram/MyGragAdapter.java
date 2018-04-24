package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.metrial.adapter.MyHodler;

import java.util.ArrayList;

/**
 * Created by WEI on 2018/4/24.
 */

class MyGragAdapter extends RecyclerView.Adapter<MyHodler> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private ArrayList<String> arrayList;
    private View mHeaderView;


    public MyGragAdapter(Context context) {
        Log.e(TAG, "MyGragAdapter: " );
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.first_module_recycleview_head, null, false);
        arrayList = new ArrayList<>();
        arrayList.add("Item 1");
        arrayList.add("Item 2");

    }

    private static final String TAG = "MyGragAdapter";
    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: "+position );
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @NonNull
    @Override
    public MyHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: "+viewType );
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyHodler(mHeaderView, true);
        }
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_module_recycleview_item, parent, false);
        MyHodler myHodler = new MyHodler(inflate);
        return myHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodler holder, int position) {
        Log.e(TAG, "onBindViewHolder: "+position);
        if (getItemViewType(position) == TYPE_NORMAL) {
            holder.textView.setText(arrayList.get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " );
        if (mHeaderView == null) {
            return arrayList.size();
        } else {
            return arrayList.size() + 1;
        }
    }
}
