package com.uurobot.baseframe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/5/29.
 */

public class ChartView extends View {
        public ChartView(Context context) {
                this(context, null);
        }

        public ChartView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initTypeValue(attrs);
                initData();
        }

        public void initData() {

        }

        public void initTypeValue(AttributeSet attrs) {

        }

        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

        }
}
