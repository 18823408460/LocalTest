package com.uurobot.baseframe.activitys.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class AdsViewPagerActivity extends BaseActivity {
        private static final String TAG = AdsViewPagerActivity.class.getSimpleName();
        private ViewPager viewPager;
        private TextView textView;
        private LinearLayout linearLayout;
        private int[] pagerIds = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
        private String[] strs = {"111111", "222222", "333333", "444444", "555555"};
        private List<ImageView> imageViewList = new ArrayList<>();

        private static class MyHandler extends Handler {
                WeakReference<AdsViewPagerActivity> weakReference;

                public MyHandler(AdsViewPagerActivity adsViewPagerActivity) {
                        weakReference = new WeakReference<>(adsViewPagerActivity);
                }

                @Override
                public void handleMessage(Message msg) {
                        AdsViewPagerActivity adsViewPagerActivity = weakReference.get();
                        if (adsViewPagerActivity != null) {
                                adsViewPagerActivity.next();
                                sendEmptyMessageDelayed(0, 1000);
                        }
                }
        }

        MyHandler myHandler;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ads_viewpageractivity);
                myHandler = new MyHandler(this);
                viewPager = findViewById(R.id.viewpager_ads);
                textView = findViewById(R.id.tv_viewpager);
                linearLayout = findViewById(R.id.ll_points);
                initPoints();
                initViewPager();
        }

        private void initPoints() {
                for (int i = 0; i < pagerIds.length*4; i++) {
                        ImageView imageView = new ImageView(this);
                        imageView.setBackgroundResource(R.drawable.point_selectors);
                        // 构造参数是  view  的宽高
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(62, 62);
                        if (i != (pagerIds.length - 1)) { // 最后一个不需要
                                params.rightMargin = 40;
                        }
                        imageView.setLayoutParams(params);
                        imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        Toast.makeText(AdsViewPagerActivity.this, "v", Toast.LENGTH_SHORT).show();
                                }
                        });
                        linearLayout.addView(imageView);

                        ImageView imageView1 = new ImageView(this);
                        imageView1.setBackgroundResource(pagerIds[i%pagerIds.length]);
                        imageViewList.add(imageView1);
                }
        }

        private void initViewPager() {
                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                //                                return pagerIds.length;
                                return Integer.MAX_VALUE; //6亿
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                                return view == object;
                        }

                        // container == Viewpager
                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                                ImageView imageView = imageViewList.get(position % imageViewList.size());
                                Log.e(TAG, "instantiateItem: ==== " + (position % pagerIds.length));
                                imageView.setBackgroundResource(pagerIds[position % pagerIds.length]);
                                imageView.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                                int action = event.getAction();
                                                switch (action) {
                                                        case MotionEvent.ACTION_DOWN:
                                                                myHandler.removeCallbacksAndMessages(null);
                                                                Log.e(TAG, "onTouch: down");
                                                                break;
                                                        case MotionEvent.ACTION_CANCEL: //down 之后，当 页面滑出一定范围，就会收到cancel事件，
                                                                Log.e(TAG, "onTouch: cancle====");
                                                                break;
                                                        case MotionEvent.ACTION_UP: // up 事件可能 接受 不到，
                                                                Log.e(TAG, "onTouch: up");
                                                                myHandler.sendEmptyMessageDelayed(0, 2000);
                                                                break;
                                                }
                                                return true;
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
                        // positionOffset=单个页面滑动位置百分比 ，
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageSelected(int position) {
                                Log.e(TAG, "onPageSelected: " + position);
                                textView.setText(strs[position % strs.length]);
                                linearLayout.getChildAt(prePosition).setSelected(false);
                                prePosition = position % strs.length;
                                linearLayout.getChildAt(prePosition).setSelected(true);
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                                //SCROLL_STATE_IDLE 滑动松开后进入 这个状态
                                if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                                        isDragging = false;
                                        myHandler.removeCallbacksAndMessages(null);
                                        myHandler.sendEmptyMessageDelayed(0, 2000);
                                        Log.e(TAG, "onPageScrollStateChanged: SCROLL_STATE_IDLE");
                                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                                        isDragging = true;
                                        Log.e(TAG, "onPageScrollStateChanged: SCROLL_STATE_DRAGGING");
                                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                                        Log.e(TAG, "onPageScrollStateChanged: SCROLL_STATE_SETTLING");
                                }
                        }
                });
                int item = 0; // 可以无限又滑动
                prePosition = item;
                textView.setText(strs[prePosition % strs.length]);
                viewPager.setCurrentItem(prePosition);
                linearLayout.getChildAt(prePosition % strs.length).setSelected(true);
               // myHandler.sendEmptyMessageDelayed(0, 2000);
        }

        private int prePosition;
        private boolean isDragging = false;

        private void next() {
                // 这样会循环滑动，==== 当向右滑动到最后一个，然后又会左滑到第一个。但是这种体验不好
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
}
