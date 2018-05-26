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
import com.uurobot.baseframe.activitys.ImageAnimActivity;
import com.uurobot.baseframe.activitys.SmartDoctorActivity;
import com.uurobot.baseframe.activitys.SmartHomeActivity;
import com.uurobot.baseframe.activitys.SmartTreeActivity;
import com.uurobot.baseframe.adapter.holder.RecycleViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> stringList;
        private static final String TAG = MainAdapter.class.getSimpleName();
        private FragmentManager fragmentManager;
        private Context mContext;

        public void upDateStringList(List<String> stringList) {
                this.stringList = stringList;
                notifyDataSetChanged();
        }

        public MainAdapter(List<String> stringList, FragmentManager fragmentManager) {
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

        private void handlerItemClick(int position) {
                Log.d(TAG, "handlerItemClick: " + position);
                switch (position) {
                        case 0:
                                handlerItemOne();
                                break;
                        case 1:
                                handlerItemTwo();
                                break;
                        case 2:
                                handlerItemThree();
                                break;
                }
        }

        private void handlerItemThree() {
                mContext.startActivity(new Intent(mContext, SmartHomeActivity.class));
        }

        /**
         * 智慧医生
         */
        private void handlerItemTwo() {
                mContext.startActivity(new Intent(mContext, SmartDoctorActivity.class));
        }

        /**
         * 智慧苗圃
         */
        private void handlerItemOne() {
//                SurFaceViewDialog surFaceViewDialog = new SurFaceViewDialog();
//                surFaceViewDialog.show(fragmentManager, null);
//                ImageViewDialog surFaceViewDialog = new ImageViewDialog();
//                surFaceViewDialog.show(fragmentManager, null);
                mContext.startActivity(new Intent(mContext, SmartTreeActivity.class));
        }

        @Override
        public int getItemCount() {
                return stringList.size();
        }
}
