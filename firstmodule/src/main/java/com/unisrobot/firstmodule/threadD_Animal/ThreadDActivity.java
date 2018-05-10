package com.unisrobot.firstmodule.threadD_Animal;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ThreadDActivity extends AppCompatActivity {
        private ImageView left;
        private ImageView right;
        private ViewPager viewPager ;
        private int resId[] = {R.drawable.people,R.drawable.aa,R.drawable.people,R.drawable.bb,R.drawable.people} ;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_threa_d);
                viewPager = findViewById(R.id.viewpager);
                viewPager.setPageTransformer(true,new RotationPageTransformer());
                viewPager.setPageMargin(3);
                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                return resId.length;
                        }

                        @Override
                        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                                return view == object;
                        }

                        @NonNull
                        @Override
                        public Object instantiateItem(@NonNull ViewGroup container, int position) {
                                ImageView imageView = new ImageView(container.getContext());
                                imageView.setImageResource(resId[position]);
                                container.addView(imageView);
                                return imageView;
                        }

                        @Override
                        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                                super.destroyItem(container, position, object);
                                container.removeView(container);
                        }
                });
        }

        private void singTest() {
//                left = findViewById(R.id.img_left);
//                right = findViewById(R.id.img_right);
//
//                //                left.setScaleX(0.75f);
//                //                left.setScaleY(0.75f);
//                left.setZ(-3f); // 设置顶层（>0）
//                left.setScaleType(ImageView.ScaleType.FIT_START);
//                left.setPivotX(left.getWidth());
//                left.setPivotY(left.getHeight()/2);
//                left.setRotationY(-20);
//                right.setPivotX(right.getWidth() / 2);
//                right.setRotationY(30);
//                ThreadDView view = new ThreadDView(this);
//                view.setPivotX(view.getWidth() / 2);
//                view.setRotationY(-20);
                // setContentView(view);
        }
}
