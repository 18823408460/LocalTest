package com.uurobot.baseframe.adapter.shangcheng;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.bean.shangcheng.GoodsCarBean;
import com.uurobot.baseframe.view.GoodsNumSelectView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/4.
 */

public class GoodsCarAdapter extends RecyclerView.Adapter {
        private static final String TAG = GoodsCarAdapter.class.getSimpleName();
        private LayoutInflater layoutInflater;
        private Context mContext;
        private List<GoodsCarBean> goodsCarBeanList;

        public GoodsCarAdapter(Context context, List<GoodsCarBean> allData) {
                mContext = context;
                layoutInflater = LayoutInflater.from(mContext);
                this.goodsCarBeanList = allData;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = layoutInflater.inflate(R.layout.recycleview_item_goods_car, null);
                GoodsCarHolder goodsCarHolder = new GoodsCarHolder(view);
                return goodsCarHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof GoodsCarHolder) {
                        Log.e(TAG, "onBindViewHolder: "+position );
                        GoodsCarBean goodsCarBean = goodsCarBeanList.get(position);
                        ((GoodsCarHolder) holder).checkBox.setSelected(goodsCarBean.isSelect());
                        ((GoodsCarHolder) holder).textViewPrice.setText(goodsCarBean.getCover_price());
                        ((GoodsCarHolder) holder).textViewDesc.setText(goodsCarBean.getName());
                        ((GoodsCarHolder) holder).goodsNumSelectView.setNumber(goodsCarBean.getNumbers());
                }
        }

        @Override
        public int getItemCount() {
                return goodsCarBeanList.size();
        }

        private class GoodsCarHolder extends RecyclerView.ViewHolder {
                public CheckBox checkBox;
                public ImageView imageView;
                public TextView textViewDesc;
                public TextView textViewPrice;
                public GoodsNumSelectView goodsNumSelectView;

                public GoodsCarHolder(View itemView) {
                        super(itemView);
                        checkBox = itemView.findViewById(R.id.checkbox_goods_car_item);
                        imageView = itemView.findViewById(R.id.imagevie_goods_car_item);
                        textViewDesc = itemView.findViewById(R.id.tv_desc_goods_car_item);
                        textViewPrice = itemView.findViewById(R.id.tv_price_goods_car_item);
                        goodsNumSelectView = itemView.findViewById(R.id.googsNumSelectView);
                }
        }
}
