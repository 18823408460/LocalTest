package com.unisrobot.firstmodule.metrial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/4/15.
 */

public class RecycleApdater extends RecyclerView.Adapter<MyHodler> {
    private ArrayList<String> arrayList;
    private Context context;

    public RecycleApdater(Context context, List<String> arrayList) {
        this.context = context;
        this.arrayList = (ArrayList<String>) arrayList;
    }

    public void notify(String data){
        arrayList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public MyHodler onCreateViewHolder(final ViewGroup parent, int viewType) {
//          为什么这样会报错？？？？
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_module_recycleview_item, parent);
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_module_recycleview_item, parent,false);
        MyHodler myHodler = new MyHodler(inflate);
        myHodler.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(),"click=",Toast.LENGTH_SHORT).show();
            }
        });
        return myHodler;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
