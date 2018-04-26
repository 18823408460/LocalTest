package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 * 继承 LinearLayout， ViewGroup 的区别？？？
 */




public class CircelLayout extends ViewGroup {
        private static final String TAG = "AutoNextLineLinearlayou";

        public CircelLayout(Context context) {
                super(context);
                Log.e(TAG, "AutoNextLineLinearlayout:1 ");
        }

        public CircelLayout(Context context, AttributeSet attrs) { //xml中会创建
                super(context, attrs);
                Log.e(TAG, "AutoNextLineLinearlayout: 2");
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);

                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);

                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


        /**
         * 与当前ViewGroup对应的LayoutParams
         */
        @Override
        public LayoutParams generateLayoutParams(AttributeSet attrs) {
                return new MarginLayoutParams(getContext(), attrs);
        }
}
