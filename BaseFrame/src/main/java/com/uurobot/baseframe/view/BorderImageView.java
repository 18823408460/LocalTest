package com.uurobot.baseframe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/6/1.
 */

public class BorderImageView extends AppCompatImageView {
        private int color = Color.parseColor("#ff0000");
        private boolean drawBorder = false;

        public BorderImageView(Context context) {
                super(context);
        }

        public BorderImageView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        private static final String TAG = BorderImageView.class.getSimpleName();
        @Override
        protected void onDraw(Canvas canvas) {
                // TODO Auto-generated method stub
                super.onDraw(canvas);
                if (drawBorder) {
                        //画边框
                        int borderWidth = 10;
                        Paint paint = new Paint();
                        paint.setColor(color);
                        paint.setStrokeWidth(borderWidth);
                        paint.setStyle(Paint.Style.STROKE);
                        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - borderWidth / 2, paint);
                        Log.e(TAG, "onDraw: borader===========yes============" );
                }else {
                        Log.e(TAG, "onDraw: borader===========no============" );
                }
        }

        public void drawBorder(boolean b) {
                this.drawBorder = b;
                invalidate();
                Log.e(TAG, "drawBorder: =========================="+b );
        }
}
