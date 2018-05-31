package com.uurobot.baseframe.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.view.ReflectionImage;

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
        private List<RelativeLayout> imageViews;
        private int prePos;
        private LayoutInflater layoutInflater;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_dance);
                layoutInflater = LayoutInflater.from(this);
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
                viewPager.setOffscreenPageLimit(imageViews.size());
                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                return Integer.MAX_VALUE;
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                                return view == object;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                                RelativeLayout relativeLayout;
                                if (imageViews != null && imageViews.size() > 0) {
                                        relativeLayout = imageViews.remove(0);
                                } else {
                                        relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager_dance_item, null);
                                }
                                ImageView imageView = relativeLayout.findViewById(R.id.image_dance_icon);
                                imageView.setImageResource(imgs[position % imgs.length]);
                                relativeLayout.setTag(position);
                                relativeLayout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                // Toast.makeText(DanceViewPagerActivity.this, "click="+v.getTag(), Toast.LENGTH_SHORT).show();
                                        }
                                });
                                container.addView(relativeLayout);
                                return relativeLayout;
                        }

                        @Override
                        public float getPageWidth(int position) {
                                return 1f;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                                container.removeView((View) object);
                                imageViews.add((RelativeLayout) object);
                        }
                });
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                Log.e(TAG, "onPageScrolled: position=" + position + "   positionOffset=" + positionOffset);

                        }

                        @Override
                        public void onPageSelected(int position) {
                                int readPos = position % imgs.length;
                                linearLayout.getChildAt(prePos).setSelected(false);
                                linearLayout.getChildAt(readPos).setSelected(true);
                                textView.setText(title[readPos]);
                                prePos = readPos;
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                });
                viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                        public static final float MIN_SCALE = 0.6f;
                        public static final float SENCOND_SCALE = 0.8f;
                        public static final float MAX_SCALE = 1f;

                        @Override
                        public void transformPage(View page, float position) {
                                float scale = -1;
                                if (position <= -2.1) {
                                        scale = MIN_SCALE;

                                } else if (position > -2.1 && position <= -1.05) {
                                        float v = (float) (Math.abs(position) - 1.05);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        scale = (float) (SENCOND_SCALE - 0.2 * v);

                                } else if (position > -1.05 && position <= 0) {
                                        float v = Math.abs(position);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        scale = (float) (MAX_SCALE - 0.2 * v);

                                } else if (position > 0 && position <= 1.05) {
                                        float v = position;
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        scale = (float) (MAX_SCALE - 0.2 * v);

                                } else if (position > 1.05 && position <= 2.1) {
                                        float v = (float) (position - 1.05);
                                        if (v >= 1) {
                                                v = 1;
                                        }
                                        scale = (float) (SENCOND_SCALE - 0.2 * v);

                                } else {
                                        scale = MIN_SCALE;
                                }
                                float abs = Math.abs(position);
                                int tan = (int) ((abs * 900 / 3.0) * Math.tan(40 * Math.PI / 180));
                                int diff = (int) (8000 * Math.sin(abs / 180 * Math.PI));
                                Log.e(TAG, "transformPage: diff===" + diff);
                                Log.e(TAG, "transformPage: tan===" + tan);

                                page.setPivotX(page.getWidth() / 2);
                                page.setPivotY(page.getHeight() / 2 - tan);
                                if (scale != -1) {
                                        page.setScaleX(scale);
                                        page.setScaleY(scale);
                                }
                        }
                });
                int item = Integer.MAX_VALUE / 2;
                viewPager.setCurrentItem(item);
                item = item % imgs.length;
                linearLayout.getChildAt(item).setSelected(true);
                textView.setText(title[item]);
                prePos = item;

        }

        private void initData() {
                imageViews = new ArrayList<>();
                for (int i = 0; i < imgs.length; i++) {
                        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager_dance_item, null);
                        ImageView imageView = relativeLayout.findViewById(R.id.image_dance_icon);
                        imageView.setImageResource(imgs[i]);
                        imageView.setTag(i);
                        imageViews.add(relativeLayout);

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
