package com.uurobot.baseframe.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.SmartDoctorActivity;
import com.uurobot.baseframe.activitys.SmartHomeActivity;
import com.uurobot.baseframe.activitys.SmartTreeActivity;
import com.uurobot.baseframe.adapter.holder.RecycleViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public abstract  class FragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> stringList;
        private static final String TAG = FragmentAdapter.class.getSimpleName();
        private FragmentManager fragmentManager;
        private Context mContext;

        public void upDateStringList(List<String> stringList) {
                this.stringList = stringList;
                notifyDataSetChanged();
        }

        public FragmentAdapter(List<String> stringList, FragmentManager fragmentManager) {
                this.stringList = stringList;
                this.fragmentManager = fragmentManager;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
                mContext = parent.getContext();
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                View itemView = layoutInflater.inflate(R.layout.layout_recycleview_item, parent, false);
                RecycleViewHolder recycleViewHolder = new RecycleViewHolder(itemView);
                return recycleViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                if (holder instanceof RecycleViewHolder) {
                        TextView textView = ((RecycleViewHolder) holder).textView;
                        textView.setText(stringList.get(position));
                        textView.setClickable(true);
                        textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        handlerItemClick(position);
                                }
                        });
                }
        }

        public abstract void handlerItemClick(int position) ;

        @Override
        public int getItemCount() {
                return stringList.size();
        }
}
