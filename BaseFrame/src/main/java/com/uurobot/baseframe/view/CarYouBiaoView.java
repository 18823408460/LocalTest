package com.uurobot.baseframe.view;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.uurobot.baseframe.utils.SizeUtil;
import com.zhy.http.okhttp.utils.L;

/**
 * Created by Administrator on 2018/6/7.
 */

public class CarYouBiaoView extends View {
        private static final String TAG = CarYouBiaoView.class.getSimpleName();
        private int mViewWidth, mViewHeight;
        private Paint paint;
        private int biaoPanRadio; //表盘外圆的半径
        private Point centerPoint;

        public CarYouBiaoView(Context context) {
                this(context, null);
        }

        public CarYouBiaoView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public CarYouBiaoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(mViewWidth, mViewWidth);
        }

        private void initData() {
                Point screen = SizeUtil.getScreen();
                mViewWidth = screen.x;
                mViewHeight = screen.y / 3;
                centerPoint = new Point(mViewWidth / 2, mViewHeight / 3 * 2);
                paint = new Paint();
                biaoPanRadio = mViewHeight / 2;
                initAnim();
        }


        private void initAnim() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                                float value = (float) animation.getAnimatedValue();
                                currentAngle = (int) (angle * value);
                                invalidate();
                        }
                });
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setDuration(4000);
                valueAnimator.start();
        }

        /**
         * 圆上任意一点的坐标 ==  3点钟（0度） 12点钟（-90度）
         * x1 = x0 + r * cos(angle * PI / 180)
         * y1 = y0 + r * sin(angle * PI /180)
         */
        private int count = 12;
        private int AllAngle = 180;
        private int keduLength = 100;
        private int angle = 115;
        private int currentAngle = 0;
        private int hengXian = keduLength / 4;

        @Override
        protected void onDraw(Canvas canvas) {
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.parseColor("#2E70B3"));
                canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

                paint.setStrokeWidth(8);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.parseColor("#2B2B2B"));
                RectF rectF = new RectF();
                rectF.left = centerPoint.x - biaoPanRadio;
                rectF.top = centerPoint.y - biaoPanRadio;
                rectF.right = centerPoint.x + biaoPanRadio;
                rectF.bottom = centerPoint.y + biaoPanRadio;
                Log.e(TAG, "onDraw: " + rectF);
                // -  = 逆时针
                canvas.drawArc(rectF, 0, -180, false, paint);

                int angle = AllAngle / count;
                //canvas.save(); //必须在 rotate 之前调用
                for (int i = 0; i < angle - 2; i++) {
                        float endX = (float) (centerPoint.x + biaoPanRadio * Math.cos(-angle * i * Math.PI / 180));
                        float endY = (float) (centerPoint.y + biaoPanRadio * Math.sin(-angle * i * Math.PI / 180));

                        float startX = (float) (centerPoint.x + (biaoPanRadio - keduLength) * Math.cos(-angle * i * Math.PI / 180));
                        float startY = (float) (centerPoint.y + (biaoPanRadio - keduLength) * Math.sin(-angle * i * Math.PI / 180));
                        if (i % 3 == 0) {
                                String text = String.valueOf(12 - i);
                                paint.setTextSize(60);
                                paint.setStyle(Paint.Style.FILL);
                                float textW = paint.measureText(text);
                                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                                float textH = fontMetrics.descent + fontMetrics.ascent;

                                float startTextX = (float) (centerPoint.x + (biaoPanRadio - keduLength - 20) * Math.cos(-angle * i * Math.PI / 180));
                                float startTextY = (float) (centerPoint.y + (biaoPanRadio - keduLength - 20) * Math.sin(-angle * i * Math.PI / 180));

                                Path path = new Path();
                                if (i == 0) {
                                        canvas.drawText(text, startTextX - textW, startTextY - textH / 2, paint);

                                        float endX1 = (float) (centerPoint.x + biaoPanRadio * Math.cos(-(angle * i + 5) * Math.PI / 180));
                                        float endY1 = (float) (centerPoint.y + biaoPanRadio * Math.sin(-(angle * i + 5) * Math.PI / 180));
                                        path.moveTo(endX, endY);
                                        path.lineTo(startX, startY);
                                        path.lineTo(startX, startY - hengXian);
                                        path.lineTo(endX1, endY1);
                                        canvas.drawPath(path, paint);

                                } else if (i == (angle - 3)) {
                                        canvas.drawText(text, startTextX, startTextY - textH / 2, paint);

                                        float endX1 = (float) (centerPoint.x + biaoPanRadio * Math.cos(-(angle * i - 5) * Math.PI / 180));
                                        float endY1 = (float) (centerPoint.y + biaoPanRadio * Math.sin(-(angle * i - 5) * Math.PI / 180));
                                        path.moveTo(endX, endY);
                                        path.lineTo(startX, startY);
                                        path.lineTo(startX, startY - hengXian);
                                        path.lineTo(endX1, endY1);
                                        canvas.drawPath(path, paint);

                                } else {
                                        canvas.drawText(text, startTextX - textW / 2, startTextY - textH, paint);

                                        float endX1 = (float) (centerPoint.x + biaoPanRadio * Math.cos(-(angle * i + 2.5) * Math.PI / 180));
                                        float endY1 = (float) (centerPoint.y + biaoPanRadio * Math.sin(-(angle * i + 2.5) * Math.PI / 180));
                                        float endX2 = (float) (centerPoint.x + biaoPanRadio * Math.cos(-(angle * i - 2.5) * Math.PI / 180));
                                        float endY2 = (float) (centerPoint.y + biaoPanRadio * Math.sin(-(angle * i - 2.5) * Math.PI / 180));
                                        float startX1 = (float) (centerPoint.x + (biaoPanRadio - keduLength) * Math.cos(-(angle * i + 2.5) * Math.PI / 180));
                                        float startY1 = (float) (centerPoint.y + (biaoPanRadio - keduLength) * Math.sin(-(angle * i + 2.5) * Math.PI / 180));
                                        float startX2 = (float) (centerPoint.x + (biaoPanRadio - keduLength) * Math.cos(-(angle * i - 2.5) * Math.PI / 180));
                                        float startY2 = (float) (centerPoint.y + (biaoPanRadio - keduLength) * Math.sin(-(angle * i - 2.5) * Math.PI / 180));
                                        path.moveTo(endX1, endY1);
                                        path.lineTo(startX1, startY1);
                                        path.lineTo(startX2, startY2);
                                        path.lineTo(endX2, endY2);
                                        canvas.drawPath(path, paint);
                                }

                                //paint.setStrokeWidth(20); //这个会影响字体

                        } else {
                                paint.setStrokeWidth(10);
                                canvas.drawLine(startX, startY, endX, endY, paint);
                        }
                        // 首位置的线宽怎么修改  +   末尾位置的线宽怎么修改 (  正确的方法通过Path 来做)

                        // 默认是左上角旋转  - = 逆时针( 旋转画布，，text 绘制有点麻烦，)
                        // canvas.rotate(-angle, centerPoint.x, centerPoint.y);
                }
                // canvas.restore();//必须在 rotate 之后调用


                int centerPaintRadio = 50;
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.parseColor("#00ff00"));
                Path path = new Path();

                int xianW = 230;
                path.moveTo(centerPoint.x - xianW, centerPoint.y);
                path.lineTo(centerPoint.x, centerPoint.y - centerPaintRadio);
                path.lineTo(centerPoint.x, centerPoint.y + centerPaintRadio);

                canvas.save();
                canvas.rotate(currentAngle, centerPoint.x, centerPoint.y);
                canvas.drawPath(path, paint);
                canvas.restore();

                paint.setColor(Color.parseColor("#ff0000"));
                canvas.drawCircle(centerPoint.x, centerPoint.y, centerPaintRadio, paint);


                String textE = "E";
                String textF = "F";
                paint.setTextSize(130);
                float textW = paint.measureText(textE);
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                float textH = fontMetrics.descent + fontMetrics.ascent;
                float textPadding = 30;
                canvas.drawText(textE, centerPoint.x - biaoPanRadio, centerPoint.y - textH + textPadding, paint);
                canvas.drawText(textF, centerPoint.x + biaoPanRadio - textW, centerPoint.y - textH + textPadding, paint);


                paint.setStyle(Paint.Style.STROKE);
                float yinyinWidth = 100;
                paint.setColor(Color.parseColor("#88F6530E"));
                paint.setStrokeWidth(yinyinWidth);
                RectF rectF2 = new RectF();
                rectF2.left = centerPoint.x - biaoPanRadio + yinyinWidth / 2;
                rectF2.top = centerPoint.y - biaoPanRadio + yinyinWidth / 2;
                rectF2.right = centerPoint.x + biaoPanRadio - yinyinWidth / 2;
                rectF2.bottom = centerPoint.y + biaoPanRadio - yinyinWidth / 2;
                Log.e(TAG, "onDraw: " + rectF2);
                canvas.drawArc(rectF2, -180, currentAngle, false, paint);

                paint.reset();
        }
}
