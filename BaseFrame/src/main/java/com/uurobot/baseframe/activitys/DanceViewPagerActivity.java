package com.uurobot.baseframe.activitys;

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
import com.uurobot.baseframe.fragment.shangcheng.home.Utils;
import com.uurobot.baseframe.view.BorderImageView;

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
        private int prePointPos = -1;
        private int preLayoutPos = -1;
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
                                Log.e(TAG, "instantiateItem: " + position%imageViews.size() );
                                RelativeLayout relativeLayout =imageViews.get(position% imageViews.size());
                                container.addView(relativeLayout);
                                return relativeLayout;
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
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                                int realPos = position % title.length;
                                Log.e(TAG, "onPageSelected: " + position+ "   realPos="+realPos+"   "+linearLayout.getChildCount());
                                if (prePointPos != -1){
                                        linearLayout.getChildAt(prePointPos).setSelected(false);
                                }
                                linearLayout.getChildAt(realPos).setSelected(true);
                                textView.setText(title[realPos]);
                                prePointPos = realPos;

                                int layouts = position % imageViews.size();
                                setBorader(layouts, true);
                                preLayoutPos = layouts ;
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
                                float scale;
                                //执行缩放
                                float scaleFactor = Utils.getPageScale(position);
                                page.setScaleX(scaleFactor);
                                page.setScaleY(scaleFactor);

                                Utils.transLationX(page, position);
                                Utils.setPivotXY(page, position);
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
                preLayoutPos = layouts ;
        }

        private void setBorader(int item, boolean state) {
                Log.e(TAG, "setBorader: ====== " + prePointPos +"   " + item);
                if (preLayoutPos != -1){
                        RelativeLayout relativeLayout = imageViews.get(preLayoutPos);
                        BorderImageView imageView = (BorderImageView) relativeLayout.getChildAt(1);
                        imageView.drawBorder(false);
                }
                RelativeLayout relativeLayout1 = imageViews.get(item);
                BorderImageView imageView1 = (BorderImageView) relativeLayout1.getChildAt(1);
                imageView1.drawBorder(true);
        }

        private void initData() {
                imageViews = new ArrayList<>();
                for (int i = 0; i < imgs.length*3 ; i++) {
                        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.viewpager_dance_item, null);
                        BorderImageView imageView = relativeLayout.findViewById(R.id.image_dance_icon);
                        imageView.setBackgroundResource(imgs[i%imgs.length]);
                        imageView.setTag(i);
                        imageViews.add(relativeLayout);
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
}
