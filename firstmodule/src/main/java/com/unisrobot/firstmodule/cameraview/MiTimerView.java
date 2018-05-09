package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/5/9.
 */

public class MiTimerView extends View {
        public MiTimerView(Context context) {
                this(context, null);
        }

        public MiTimerView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public MiTimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private int padding = 20;
        private int viewRadio;
        private float centerX;
        private float centerY;
        private Paint paint, tuPaint;
        private int startAngle;
        private int singleAngle;
        private int tuLength = 140;

        private void initData() {
                int getwindth = ScreenUtil.getwindth(getContext());
                int getheight = ScreenUtil.getheight(getContext());
                centerX = getwindth / 2;
                centerY = getheight / 2;
                viewRadio = getwindth / 2 - padding * 2;

                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(20);


                tuPaint = new Paint();
                tuPaint.setColor(Color.BLUE);
                tuPaint.setStyle(Paint.Style.FILL);
                tuPaint.setStrokeWidth(30);

                singleAngle = 360 / 360;

                startAngle = 0;
                TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                                count++;
                                count = count % 360;
                                postInvalidate();
                        }
                };
                new Timer().schedule(timerTask, 3000, 100);
        }

        private int count = 0;
        private static final String TAG = "MiTimerView";

        @Override
        protected void onDraw(Canvas canvas) {
                canvas.drawCircle(centerX, centerY, viewRadio, paint);
                Log.e(TAG, "onDraw: " + centerX + "   " + centerY);
                drawMethod1(canvas);


        }

        private void drawMethod2() {

        }

        private void drawMethod1(Canvas canvas) {
                // 弧度到 角度的转换。。。。
                canvas.rotate(count*singleAngle,centerX,centerY);
                float endKeduX = (float) (centerX + (viewRadio - padding) * Math.cos(singleAngle * Math.PI / 180));
                float endKeduY = (float) (centerY + (viewRadio - padding) * Math.sin(singleAngle * Math.PI / 180));
                Log.e(TAG, "onDraw: " + endKeduX + "   " + endKeduY);
                float startX1 = (float) (endKeduX - tuLength * Math.cos(30 * Math.PI / 180));
                float startY1 = (float) (endKeduY - tuLength * Math.sin(30 * Math.PI / 180));
                Log.e(TAG, "onDraw: " + startX1 + "   " + startY1);
                float startX2 = (float) (endKeduX - tuLength * Math.cos(30 * Math.PI / 180));
                float startY2 = (float) (endKeduY + tuLength * Math.sin(30 * Math.PI / 180));
                Log.e(TAG, "onDraw: " + startX2 + "   " + startY2);

                Path path = new Path();
                path.moveTo(endKeduX, endKeduY);
                path.lineTo(startX1, startY1);
                path.lineTo(startX2, startY2);
                canvas.drawPath(path, tuPaint);
        }
}
