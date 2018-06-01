package com.uurobot.baseframe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class RecomendViewHoler extends RecyclerView.ViewHolder {
        private static final String TAG = RecomendViewHoler.class.getSimpleName();
        private GridView gridView;
        private LayoutInflater inflater;

        public RecomendViewHoler(View itemView) {
                super(itemView);
                gridView = itemView.findViewById(R.id.gridview_recomment_fragment);
                inflater = LayoutInflater.from(itemView.getContext());
        }

        public void setData(final List<ResponseBean.ResultBean.RecommendInfoBean> data) {
                Log.e(TAG, "setData: " + data);
                gridView.setNumColumns(3);
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
                                        convertView = inflater.inflate(R.layout.gridview_recommend_item, null);
                                        gridHolder = new GridHolder();
                                        gridHolder.imageView = convertView.findViewById(R.id.imageview_gridview_recommend);
                                        gridHolder.textViewDesc = convertView.findViewById(R.id.tv_desc_gridview_recommend);
                                        gridHolder.textViewPrice = convertView.findViewById(R.id.tv_price_gridview_recommend);
                                        convertView.setTag(gridHolder);
                                } else {
                                        gridHolder = (GridHolder) convertView.getTag();
                                }
                                gridHolder.textViewPrice.setText(data.get(position).getCover_price());
                                gridHolder.textViewDesc.setText(data.get(position).getName());
                                Glide.with(convertView.getContext())
                                        .load(Constans.IMAGE_BASE_URL + data.get(position).getFigure())
                                        .into(gridHolder.imageView);
                                return convertView;
                        }
                });
        }

        private class GridHolder {
                public ImageView imageView;
                public TextView textViewPrice;
                public TextView textViewDesc;
        }
}
