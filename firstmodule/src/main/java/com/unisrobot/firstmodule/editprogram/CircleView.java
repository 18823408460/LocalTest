package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CircleView extends View {
        private static final String TAG = "CircleView";

        public CircleView(Context context) {
                this(context, null);
        }

        public CircleView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private Paint paint ;
        private void initData() {
                paint = new Paint();
                paint.setColor(Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawCircle(500,500,100,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                Log.e(TAG, "onTouchEvent: t");
                return true;
        }
}
