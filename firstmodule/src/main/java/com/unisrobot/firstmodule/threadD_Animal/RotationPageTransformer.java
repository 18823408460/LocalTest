package com.unisrobot.firstmodule.threadD_Animal;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/5/10.
 */

/**
 * https://www.jianshu.com/p/1f6c5764dd72
 */
public class RotationPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final String TAG = "RotationPageTransformer";


        //这个方法会在 滑动的过程中，一直调用，用于实现动画。。。。
        @Override
        public void transformPage(@NonNull View page, float position) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float rotate = 10 * Math.abs(position);
                Log.e(TAG, "transformPage:  scaleFactor====" + scaleFactor + "   rotate=="+rotate);
                if (position <= -1) {//position小于等于1的时候，代表page已经位于中心item的最左边， //此时设置为最小的缩放率以及最大的旋转度数
                        page.setScaleX(MIN_SCALE);
                        page.setScaleY(MIN_SCALE);
                        page.setRotationY(rotate);
                }//position从0变化到-1，page逐渐向左滑动
                else if (position < 0) {
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);
                        page.setRotationY(rotate);
                }//position从0变化到1，page逐渐向右滑动
                else if (position >= 0 && position < 1) {
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);
                        page.setRotationY(-rotate);
                }//position大于等于1的时候，代表page已经位于中心item的最右边
                else if (position >= 1) {
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);
                        page.setRotationY(-rotate);
                }
        }
}
