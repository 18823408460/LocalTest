package com.uurobot.baseframe.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class BannerViewHoler extends BaseHolder {
        private static final String TAG = BannerViewHoler.class.getSimpleName();
        public Banner banner;

        public BannerViewHoler(View itemView, IHolderLisenter iHolderLisenter) {
                super(itemView);
                banner = itemView.findViewById(R.id.banner_home_fragment);
                banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE);
                banner.setBannerAnimation(Transformer.Accordion);
                this.iHolderLisenter = iHolderLisenter;
        }

        public void setData(List<String> data) {
                Log.e(TAG, "setData: " + data);
                banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                                Log.e(TAG, "displayImage: " + path);
                                Glide.with(context).load(path).error(R.drawable.icon_search).into(imageView);
                        }
                });
                banner.setImages(data);
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                                iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Banner, position);
                        }
                });
        }
}
