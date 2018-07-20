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
import com.uurobot.baseframe.fragment.shangcheng.home.SensorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class SensorViewPagerActivity extends BaseActivity {
        private static final String TAG = SensorViewPagerActivity.class.getSimpleName();
        private RelativeLayout viewPager_container;
        private ImageView imageButton;
        private ViewPager viewPager;
        private TextView textView;
        private LinearLayout linearLayout;
        private int imgs[] = {R.drawable.xpg, R.drawable.zumzf, R.drawable.mj, R.drawable.chysx, R.drawable.lq};
        private String title[] = {"左超声波", "右超声波", "左人体感应器", "右人体感应器", "胸口超声波", "后背超声波", "左前超声波", "右前超声波",};
        private List<ViewHolder> imageViews;
        private int prePointPos = -1;
        private int preLayoutPos = -1;
        private LayoutInflater layoutInflater;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sensor);
                layoutInflater = LayoutInflater.from(this);
                linearLayout = findViewById(R.id.ll_dance_points);
                textView = findViewById(R.id.tv_dance_title);
                viewPager = findViewById(R.id.viewpager_dance);
                imageButton = findViewById(R.id.imageview_play);
                imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(SensorViewPagerActivity.this, "==" + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                        }
                });
                viewPager_container = findViewById(R.id.viewPager_container);
                viewPager_container.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                                Log.e(TAG, "onTouch: " + event.getX() + "    " + event.getY() + "  " + event.getAction());
                                return viewPager.dispatchTouchEvent(event);
                        }
                });
                initData();
                viewPager.setOffscreenPageLimit(7);
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
                                Log.e(TAG, "instantiateItem: " + position % imageViews.size());
                                ViewHolder viewHolder = imageViews.get(position % imageViews.size());
                                container.addView(viewHolder.relativeLayout);
                                return viewHolder.relativeLayout;
                        }

                        @Override
                        public float getPageWidth(int position) {
                                return 1f;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                                Log.e(TAG, "destroyItem :    " + position);
                                container.removeView((View) object);
                        }
                });
                viewPager.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Log.e(TAG, "onClick:  view pager");
                        }
                });
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                                int realPos = position % title.length;
                                Log.e(TAG, "onPageSelected: " + position + "   realPos=" + realPos + "   " + linearLayout.getChildCount());
                                if (prePointPos != -1) {
                                        linearLayout.getChildAt(prePointPos).setSelected(false);
                                }
                                linearLayout.getChildAt(realPos).setSelected(true);
                                textView.setText(title[realPos]);
                                prePointPos = realPos;

                                int layouts = position % imageViews.size();
                                setBorader(layouts, true);
                                preLayoutPos = layouts;
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                });
                viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                        public static final float MIN_SCALE = 0.6f;
                        public static final float SENCOND_SCALE = 0.8f;
                        public static final float MAX_SCALE = 1f;
                        private View firstView;

                        @Override
                        public void transformPage(View page, float position) {
                                if (firstView == null) {
                                        firstView = page;
                                }
                                Log.e(TAG, "transformPage: " + position + " view==" + page);
                                if (page == firstView) {
                                }
                                float scale;
                                //执行缩放
                                float scaleFactor = SensorUtils.getPageScale(position);
                                //                                page.setScaleX(scaleFactor);
                                page.setPivotX(page.getWidth());
                                page.setPivotY(page.getHeight());
                                page.setScaleY(scaleFactor);
                                //
                                SensorUtils.transLationX(page, position);
     /*                           if (position > 0) { // right
                                        page.setTranslationX(30);
                                } else if (position < 0) { //left
                                        page.setTranslationX(-30);
                                }else {
                                        page.setTranslationX(0);
                                }*/
                                //SensorUtils.setPivotXY(page, position);
                        }
                });
                int item = Integer.MAX_VALUE / 2;
                viewPager.setCurrentItem(item);
                int points = item % title.length;
                linearLayout.getChildAt(points).setSelected(true);
                textView.setText(title[points]);
                prePointPos = points;

                int layouts = item % imageViews.size();
                setBorader(layouts, true);
                preLayoutPos = layouts;
        }

        private void setBorader(int item, boolean state) {
                Log.e(TAG, "setBorader: ====== " + prePointPos + "   " + item);
                if (preLayoutPos != -1) {
                        ViewHolder viewHolder = imageViews.get(preLayoutPos);
                }
                ViewHolder viewHolder = imageViews.get(item);
        }

        private void initData() {
                imageViews = new ArrayList<>();
                for (int i = 0; i < imgs.length * 4; i++) {
                        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager_sensor_item, null);
                        ImageView imageView = relativeLayout.findViewById(R.id.image_sensor_item_bk);
                        relativeLayout.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                        imageView.setTag(i % imgs.length);
                        imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        Log.e(TAG, "onClick: ==============view=" + v.getTag());
                                }
                        });
                        ViewHolder viewHolder = new ViewHolder();
                        viewHolder.relativeLayout = relativeLayout;
                        viewHolder.daoyingView = imageView;
                        imageViews.add(viewHolder);
                }

                for (int i = 0; i < title.length; i++) {
                        ImageView points = new ImageView(this);
                        points.setBackgroundResource(R.drawable.point_selectors);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(42, 42);
                        if (i != (imgs.length - 1)) { // 最后一个不需要
                                params.rightMargin = 20;
                        }
                        points.setLayoutParams(params);
                        linearLayout.addView(points);
                }
        }

        private class ViewHolder {
                public RelativeLayout relativeLayout;
                public ImageView daoyingView;
        }
}
