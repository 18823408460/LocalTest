package com.uurobot.baseframe.adapter.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;
import com.uurobot.baseframe.utils.SizeUtil;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class ActInfoViewHoler extends BaseHolder {
        private static final String TAG = ActInfoViewHoler.class.getSimpleName();
        private ViewPager viewPager;

        public ActInfoViewHoler(View itemView, IHolderLisenter iHolderLisenter) {
                super(itemView);
                viewPager = itemView.findViewById(R.id.viewpager_act_fragment);
                viewPager.setPageMargin(SizeUtil.dp2px(itemView.getContext(), 10));
                viewPager.setPageTransformer(true, new ScaleInTransformer());
                this.iHolderLisenter = iHolderLisenter;
        }

        public void setData(final List<ResponseBean.ResultBean.ActInfoBean> data) {
                Log.e(TAG, "setData: " + data);
                viewPager.setCurrentItem(0);
                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                return data.size();
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                                return view == object;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, final int position) {
                                ImageView imageView = new ImageView(container.getContext());
                                String icon_url = data.get(position).getIcon_url();
                                imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Act, position);
                                        }
                                });
                                Glide.with(container.getContext())
                                        .load(Constans.IMAGE_BASE_URL + icon_url)
                                        .error(R.drawable.icon_search)
                                        .into(imageView);
                                container.addView(imageView);
                                return imageView;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                                container.removeView((View) object);
                        }
                });
        }
}
