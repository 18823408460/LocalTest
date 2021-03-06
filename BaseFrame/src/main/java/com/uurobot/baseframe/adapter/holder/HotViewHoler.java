package com.uurobot.baseframe.adapter.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class HotViewHoler extends BaseHolder implements View.OnClickListener {
        private static final String TAG = HotViewHoler.class.getSimpleName();
        private RecyclerView recyclerView;
        private LayoutInflater layoutInflater;
        private Context context;

        public HotViewHoler(View itemView, IHolderLisenter iHolderLisenter) {
                super(itemView);
                this.iHolderLisenter = iHolderLisenter;
                context = itemView.getContext();
                recyclerView = itemView.findViewById(R.id.recycleview_hot_fragment);
                recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                layoutInflater = LayoutInflater.from(itemView.getContext());
        }

        public void setData(final List<ResponseBean.ResultBean.HotInfoBean> data) {
                Log.e(TAG, "setData: " + data);
                // 下面的点击不起作用
                //                recyclerView.setOnClickListener(new View.OnClickListener() {
                //                        @Override
                //                        public void onClick(View v) {
                //                                HotSubHolder hotSubHolder = (HotSubHolder) v.getTag();
                //                                iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Hot, hotSubHolder.position);
                //                        }
                //                });
                recyclerView.setAdapter(new RecyclerView.Adapter() {
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                View view = layoutInflater.inflate(R.layout.recycleview_hot_item, parent, false);
                                HotSubHolder hotSubHolder = new HotSubHolder(view);
                                view.setOnClickListener(HotViewHoler.this);
                                return hotSubHolder;
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                                if (holder instanceof HotSubHolder) {
                                        HotSubHolder hotViewHoler = (HotSubHolder) holder;
                                        hotViewHoler.textViewDesc.setText(data.get(position).getName());
                                        hotViewHoler.textViewPrice.setText(data.get(position).getCover_price());
                                        hotViewHoler.rootView.setTag(position);
                                        Glide.with(context)
                                                .load(Constans.IMAGE_BASE_URL + data.get(position).getFigure())
                                                .into(hotViewHoler.imageView);
                                }
                        }

                        @Override
                        public int getItemCount() {
                                return data.size();
                        }
                });
        }

        @Override
        public void onClick(View v) {
                int postion = (int) v.getTag();
                iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Hot, postion);
        }

        private class HotSubHolder extends RecyclerView.ViewHolder {
                public ImageView imageView;
                public TextView textViewPrice;
                public TextView textViewDesc;
                public View rootView;

                public HotSubHolder(View view) {
                        super(view);
                        this.rootView = view;
                        imageView = view.findViewById(R.id.imageview_recycleview_hot);
                        textViewPrice = view.findViewById(R.id.tv_price_recycleview_hot);
                        textViewDesc = view.findViewById(R.id.tv_desc_recycleview_hot);
                }
        }
}
