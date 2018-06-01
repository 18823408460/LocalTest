package com.uurobot.baseframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.holder.ActInfoViewHoler;
import com.uurobot.baseframe.adapter.holder.BannerViewHoler;
import com.uurobot.baseframe.adapter.holder.ChannelViewHoler;
import com.uurobot.baseframe.adapter.holder.HotViewHoler;
import com.uurobot.baseframe.adapter.holder.RecomendViewHoler;
import com.uurobot.baseframe.adapter.holder.SecKillViewHoler;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
        private static final String TAG = HomeFragmentAdapter.class.getSimpleName();
        private ResponseBean.ResultBean resultBean;

        public void setData(ResponseBean.ResultBean result) {
                this.resultBean = result;
                notifyDataSetChanged();
        }

        private enum HolderType {
                Banner(0), Channel(1), Act(2), Seckill(3), Recommend(4), Hot(5);
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
                Log.e(TAG, "onCreateViewHolder: " + viewType);
                if (viewType == HolderType.Banner.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_banner, parent, false);
                        BannerViewHoler bannerViewHoler = new BannerViewHoler(view);
                        return bannerViewHoler;
                } else if (viewType == HolderType.Channel.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_channel, parent, false);
                        ChannelViewHoler channelViewHoler = new ChannelViewHoler(view);
                        return channelViewHoler;
                } else if (viewType == HolderType.Act.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_act, parent, false);
                        ActInfoViewHoler actInfoViewHoler = new ActInfoViewHoler(view);
                        return actInfoViewHoler;
                } else if (viewType == HolderType.Seckill.value) {
                        Log.e(TAG, "onCreateViewHolder: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        View view = layoutInflater.inflate(R.layout.recycleview_item_seckill, parent, false);
                        SecKillViewHoler secKillViewHoler = new SecKillViewHoler(view);
                        return secKillViewHoler;
                } else if (viewType == HolderType.Recommend.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_recommend, parent, false);
                        RecomendViewHoler recomendViewHoler = new RecomendViewHoler(view);
                        return recomendViewHoler;

                } else if (viewType == HolderType.Hot.value) {
                        View view = layoutInflater.inflate(R.layout.recycleview_item_hot, parent, false);
                        HotViewHoler hotViewHoler = new HotViewHoler(view);
                        return hotViewHoler;
                }
                return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (resultBean == null) {
                        return;
                }
                if (holder instanceof BannerViewHoler) {
                        List<ResponseBean.ResultBean.BannerInfoBean> banner_info = resultBean.getBanner_info();
                        List<String> imageUrlList = new ArrayList<>();
                        for (ResponseBean.ResultBean.BannerInfoBean bannerInfoBean : banner_info) {
                                String imageUrl = bannerInfoBean.getImage();
                                imageUrlList.add(Constans.IMAGE_BASE_URL + imageUrl);
                        }
                        ((BannerViewHoler) holder).setData(imageUrlList);

                } else if (holder instanceof ChannelViewHoler) {
                        List<ResponseBean.ResultBean.ChannelInfoBean> channel_info = resultBean.getChannel_info();
                       // ((ChannelViewHoler) holder).setData(channel_info);

                } else if (holder instanceof ActInfoViewHoler) {
                        List<ResponseBean.ResultBean.ActInfoBean> act_info = resultBean.getAct_info();
                        ((ActInfoViewHoler) holder).setData(act_info);

                } else if (holder instanceof SecKillViewHoler) {
                        ResponseBean.ResultBean.SeckillInfoBean seckill_info = resultBean.getSeckill_info();
                        ((SecKillViewHoler) holder).setData(seckill_info);

                } else if (holder instanceof RecomendViewHoler) {
                        List<ResponseBean.ResultBean.RecommendInfoBean> recommend_info = resultBean.getRecommend_info();
                        ((RecomendViewHoler) holder).setData(recommend_info);

                } else if (holder instanceof HotViewHoler) {
                        List<ResponseBean.ResultBean.HotInfoBean> hot_info = resultBean.getHot_info();
                        ((HotViewHoler) holder).setData(hot_info);
                }
        }

        @Override
        public int getItemViewType(int position) {
                HolderType[] values = HolderType.values();
                HolderType value = values[position];
                return value.ordinal();
        }

        @Override
        public int getItemCount() {
                return HolderType.values().length;
        }
}
