package com.uurobot.baseframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.holder.BannerViewHoler;
import com.uurobot.baseframe.utils.EAnimType;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
        private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
        private BannerViewHoler bannerViewHoler;

        public void setBannerData(List<String> bannerData) {
                bannerViewHoler.setData(bannerData);
        }

        private enum HolderType {
                Banner(0), Act(1), Channel(2), Hot(3), Recommend(4), Seckill(5);
                public int value;

                HolderType(int value) {
                        this.value = value;
                }
        }

        private LayoutInflater layoutInflater;

        public HomeFragmentAdapter(Context context) {
                layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == HolderType.Banner.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_banner, parent, false);
                        bannerViewHoler = new BannerViewHoler(view);
                        return bannerViewHoler;
                } else if (viewType == HolderType.Act.value) {

                }
                return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
                HolderType[] values = HolderType.values();
                HolderType value = values[position];
                return value.ordinal();
        }

        @Override
        public int getItemCount() {
                return 1;
        }
}
