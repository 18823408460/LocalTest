package com.uurobot.baseframe.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uurobot.baseframe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class DanceViewPagerActivity extends BaseActivity {
        private static final String TAG = DanceViewPagerActivity.class.getSimpleName();
        private RelativeLayout viewPager_container;
        private ImageView imageButton;
        private ViewPager viewPager;
        private TextView textView;
        private LinearLayout linearLayout;
        private int imgs[] = {R.drawable.xpg, R.drawable.zumzf, R.drawable.mj, R.drawable.chysx, R.drawable.lq};
        private String title[] = {"小苹果", "最炫名族风", "迈克杰克逊", "沧海一声笑", "龙拳",};
        private List<ImageView> imageViews;
        private int prePos;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_dance);

                linearLayout = findViewById(R.id.ll_dance_points);
                textView = findViewById(R.id.tv_dance_title);
                viewPager = findViewById(R.id.viewpager_dance);
                imageButton = findViewById(R.id.imageview_play);
                imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(DanceViewPagerActivity.this, "==" + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                        }
                });
                viewPager_container = findViewById(R.id.viewPager_container);
                viewPager_container.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                                return viewPager.dispatchTouchEvent(event);
                        }
                });
                initData();
                viewPager.setPageMargin(30);
                viewPager.setOffscreenPageLimit(imageViews.size());
                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                return imageViews.size();
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                                return view == object;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                                ImageView imageView = imageViews.get(position);
                                imageView.setTag(position);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                // Toast.makeText(DanceViewPagerActivity.this, "click="+v.getTag(), Toast.LENGTH_SHORT).show();
                                        }
                                });
                                container.addView(imageView);
                                return imageView;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                                container.removeView((View) object);
                        }
                });
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                Log.e(TAG, "onPageScrolled: position=" + position + "   positionOffset=" + positionOffset);

                        }

                        @Override
                        public void onPageSelected(int position) {
                                linearLayout.getChildAt(prePos).setSelected(false);
                                linearLayout.getChildAt(position).setSelected(true);
                                textView.setText(title[position]);
                                prePos = position;
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                });
                viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                        public static final float MIN_SCALE = 0.5f;
                        public static final float SENCOND_SCALE = 0.75f;
                        public static final float MAX_SCALE = 1f;

                        @Override
                        public void transformPage(View page, float position) {
                                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                                Log.e(TAG, "transformPage: ppsition=" + position + "    tag=" + page.getTag());
                                //position小于等于1的时候，代表page已经位于中心item的最左边，
                                //此时设置为最小的缩放率以及最大的旋转度数
                                if (position <= -2.1) {
                                        page.setScaleX(MIN_SCALE);
                                        page.setScaleY(MIN_SCALE);

                                } else if (position > -2.1 && position <= -1.05) {
                                        float v = (float) (Math.abs(position) - 1.05);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        page.setScaleX((float) (SENCOND_SCALE - 0.25 * v));
                                        page.setScaleY((float) (SENCOND_SCALE - 0.25 * v));

                                } else if (position > -1.05 && position <= 0) {
                                        float v = Math.abs(position);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        page.setScaleX((float) (MAX_SCALE - 0.25 * v));
                                        page.setScaleY((float) (MAX_SCALE - 0.25 * v));

                                } else if (position > 0 && position <= 1.05) {
                                        float v = position;
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        page.setScaleX((float) (MAX_SCALE - 0.25 * v));
                                        page.setScaleY((float) (MAX_SCALE - 0.25 * v));

                                } else if (position > 1.05 && position <= 2.1) {
                                        float v = (float) (position - 1.05);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        page.setScaleX((float) (SENCOND_SCALE - 0.25 * v));
                                        page.setScaleY((float) (SENCOND_SCALE - 0.25 * v));

                                } else {
                                        page.setScaleX(MIN_SCALE);
                                        page.setScaleY(MIN_SCALE);
                                }
                        }
                });
                int item = 2;
                prePos = item;
                viewPager.setCurrentItem(item);
                linearLayout.getChildAt(item).setSelected(true);
                textView.setText(title[item]);

        }

        private void initData() {
                imageViews = new ArrayList<>();
                for (int i = 0; i < imgs.length; i++) {
                        ImageView imageView = new ImageView(this);
                        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(100, 100);
                        imageView.setLayoutParams(layoutParams);
                        imageView.setImageResource(imgs[i]);
                        imageView.setTag(i);
                        imageViews.add(imageView);

                        ImageView points = new ImageView(this);
                        points.setBackgroundResource(R.drawable.point_selectors);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(62, 62);
                        if (i != (imgs.length - 1)) { // 最后一个不需要
                                params.rightMargin = 40;
                        }
                        points.setLayoutParams(params);
                        linearLayout.addView(points);
                }
        }
}
