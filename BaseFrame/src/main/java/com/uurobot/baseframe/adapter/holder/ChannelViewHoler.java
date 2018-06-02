package com.uurobot.baseframe.adapter.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.shangcheng.GoodInfoActivity;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class ChannelViewHoler extends BaseHolder {
        private static final String TAG = ChannelViewHoler.class.getSimpleName();
        public GridView gridView;
        private LayoutInflater layoutInflater;
        private Context context;

        public ChannelViewHoler(View itemView, IHolderLisenter iHolderLisenter) {
                super(itemView);
                gridView = itemView.findViewById(R.id.gridview_channel_fragment);
                layoutInflater = LayoutInflater.from(itemView.getContext());
                context = itemView.getContext();
                this.iHolderLisenter = iHolderLisenter;
        }

        public void setData(final List<ResponseBean.ResultBean.ChannelInfoBean> data) {
                Log.e(TAG, "setData: " + data.size());
                gridView.setNumColumns(data.size() / 2);
                gridView.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                                return data.size();
                        }

                        @Override
                        public Object getItem(int position) {
                                return data.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                                return position;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                                GridHolder gridHolder;
                                if (convertView == null) {
                                        convertView = layoutInflater.inflate(R.layout.gridview_channel_item, null);
                                        convertView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                        GridHolder gridHolder1 = (GridHolder) v.getTag();
                                                        iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Channel, gridHolder1.postion);
                                                }
                                        });
                                        gridHolder = new GridHolder();
                                        gridHolder.postion = position;
                                        ImageView imageView = convertView.findViewById(R.id.imageview_gridview_channel);
                                        TextView textView = convertView.findViewById(R.id.tv_desc_gridview_channel);
                                        gridHolder.imageView = imageView;
                                        gridHolder.textView = textView;
                                        convertView.setTag(gridHolder);

                                } else {
                                        gridHolder = (GridHolder) convertView.getTag();
                                }
                                gridHolder.textView.setText(data.get(position).getChannel_name());
                                Glide.with(convertView.getContext()).load(Constans.IMAGE_BASE_URL + data.get(position).getImage())
                                        .into(gridHolder.imageView);
                                return convertView;
                        }
                });
        }

        private class GridHolder {
                public ImageView imageView;
                public TextView textView;
                public int postion;
        }
}
