package com.uurobot.baseframe.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class BannerViewHoler extends RecyclerView.ViewHolder {
        private static final String TAG = BannerViewHoler.class.getSimpleName();
        public Banner banner;

        public BannerViewHoler(View itemView) {
                super(itemView);
                banner = itemView.findViewById(R.id.banner_home_fragment);
                banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE);
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
        }
}
