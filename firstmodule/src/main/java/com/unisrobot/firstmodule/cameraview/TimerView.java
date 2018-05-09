package com.unisrobot.firstmodule.cameraview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/5/9.
 */

public class TimerView extends View {
        public TimerView(Context context) {
                this(context, null);
        }

        public TimerView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public TimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private int viewRadio;
        private float centerX;
        private float centerY;
        private Paint outPaint, inPaint, timePaint;
        private TextPaint textPaint ;
        private Paint centerPaint;
        private int padding = 10;
        private int hourLen, minuLen, secondLen;
        private int angle;
        private int kedu1 = 150 ,kedu2 =80 ;

        private void initData() {
                int getwindth = ScreenUtil.getwindth(getContext());
                int getheight = ScreenUtil.getheight(getContext());
                centerX = getwindth / 2;
                centerY = getheight / 2;
                viewRadio = getwindth / 2 - padding * 2;

                outPaint = new Paint();
                outPaint.setColor(Color.BLACK);
                outPaint.setAntiAlias(true);
                outPaint.setStyle(Paint.Style.STROKE);
                outPaint.setStrokeWidth(10);

                inPaint = new Paint();
                inPaint.setColor(Color.BLACK);
                inPaint.setAntiAlias(true);
                inPaint.setStyle(Paint.Style.FILL);

                timePaint = new Paint();
                timePaint.setColor(Color.RED);
                timePaint.setStrokeWidth(20);
                timePaint.setAntiAlias(true);

                textPaint = new TextPaint();
                textPaint.setTextSize(100);

                secondLen = viewRadio - 300;
                minuLen = secondLen - 80;
                hourLen = secondLen - 120;

                angle = 360 / 12 / 5;
                startAngle = -angle*3*5;
                TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() { // 这是子线程
                                postInvalidate();
                                count++;
                                count = count % 60;
                        }
                };
                new Timer().schedule(timerTask, 1000, 1000);
        }
        private int startAngle ;
        private int count = 0;
        private static final String TAG = "TimerView";
        private int coutKedu = 0 ;
        private int datas[] = {12,1,2,3,4,5,6,7,8,9,10,11};
        @Override
        protected void onDraw(Canvas canvas) {
                canvas.drawCircle(centerX, centerY, viewRadio, outPaint);
                canvas.drawCircle(centerX, centerY, 20, inPaint);

                float startX = centerX;
                float startY = centerY;
                float endSecondX = (float) (startX + secondLen * Math.cos(angle * count * Math.PI / 180));
                float endSecondY = (float) (startY + secondLen * Math.sin(angle * count * Math.PI / 180));
                Log.e(TAG, "onDraw: " + (angle * count));
                canvas.drawLine(startX, startY, endSecondX, endSecondY, timePaint);

                //canvas.save();
                for (int i = 0; i < 60; i++) {
                        if (i%5==0){
                                float endKeduX = (float) (startX + viewRadio * Math.cos(startAngle   * Math.PI / 180));
                                float endKeduY = (float) (startY + viewRadio * Math.sin(startAngle  * Math.PI / 180));
                                float startKeduX = (float) (startX + (viewRadio - kedu1) * Math.cos(startAngle  * Math.PI / 180));
                                float startKeduY = (float) (startY + (viewRadio - kedu1) * Math.sin(startAngle * Math.PI / 180));
                                canvas.drawLine(startKeduX,startKeduY,endKeduX,endKeduY,outPaint);
                                String text = String.valueOf(datas[coutKedu++%datas.length]);
                                float textW = textPaint.measureText(text);
                                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                                float textH = fontMetrics.ascent + fontMetrics.descent; // 这个才是字体高度计算的正确方法
                                canvas.drawText(text,startKeduX - textW/2,startKeduY - textH +10,textPaint);
                        }else {
                                float endKeduX = (float) (startX + viewRadio * Math.cos(startAngle * Math.PI / 180));
                                float endKeduY = (float) (startY + viewRadio * Math.sin(startAngle  * Math.PI / 180));
                                float startKeduX = (float) (startX + (viewRadio - kedu2) * Math.cos(startAngle  * Math.PI / 180));
                                float startKeduY = (float) (startY + (viewRadio - kedu2) * Math.sin(startAngle * Math.PI / 180));
                                canvas.drawLine(startKeduX,startKeduY,endKeduX,endKeduY,outPaint);
                        }
                        canvas.rotate(angle,centerX,centerY);
                }
                //canvas.restore();

                float endMinuX = (float) (startX + minuLen * Math.cos(angle  * Math.PI / 180));
                float endMinuY = (float) (startY + minuLen * Math.sin(angle  * Math.PI / 180));
                canvas.drawLine(startX, startY, endMinuX, endMinuY, timePaint);

        }
}
