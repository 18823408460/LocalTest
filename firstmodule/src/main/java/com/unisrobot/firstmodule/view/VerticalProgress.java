package com.unisrobot.firstmodule.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/16.
 */

public class VerticalProgress extends View {

        private static final String TAG = "VerticalProgress";

        private int barWidth;

        private int barHeight;

        private int centerX;

        private int centerY;

        private static final String text = "全程";

        private Context context;

        public VerticalProgress(Context context) {
                this(context, null);
        }

        public VerticalProgress(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public VerticalProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                this.context = context;
                initData();

        }

        private Paint innterPaint;

        private Paint outPaint;

        private Paint circlePaint, errorPaint;

        private TextPaint textPaint;

        private void initData() {
                int getheight = ScreenUtil.getheight(context);
                int getwindth = ScreenUtil.getwindth(context);
                barWidth = getwindth / 26;
                barHeight = getheight / 2;
                centerX = getwindth - barWidth - 20;
                centerY = getheight / 2;

                innterPaint = new Paint();
                innterPaint.setAntiAlias(true);
                innterPaint.setColor(Color.GREEN);
                errorPaint = new Paint();
                errorPaint.setAntiAlias(true);
                errorPaint.setColor(Color.RED);

                outPaint = new Paint();
                outPaint.setAntiAlias(true);
                outPaint.setColor(Color.parseColor("#b5d4f4"));

                circlePaint = new Paint();
                circlePaint.setAntiAlias(true);
                circlePaint.setColor(Color.parseColor("#a3a8ad"));
                textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setTextSize(ScreenUtil.dp2px(context, 10));
                textPaint.setColor(Color.parseColor("#000000"));
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                textHeight = fontMetrics.descent - fontMetrics.ascent;// ascent 是个负值
                textWidth = textPaint.measureText(text);

                inleft = centerX - barWidth / 2;
                intop = centerY - barHeight / 2;
                inright = centerX + barWidth / 2;
                inbottom = centerY + barHeight / 2;

                outleft = inleft - padding;
                outtop = intop;
                outright = inright + padding;
                outbottom = inbottom;

                outRadio = barWidth / 2 + padding;
                innerRadio = barWidth / 2;
                circleX = centerX;
                circleY = inbottom;
                iniAnimData();
        }

        private void iniAnimData() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                                float animatedValue = (float) animation.getAnimatedValue();
                                diffY = animatedValue * barHeight;
//                                if (animatedValue>0.5 && animatedValue<0.6){ // 这种设置有问题
//                                        innterPaint.setColor(Color.RED);
//                                }else {
//                                        innterPaint.setColor(Color.GREEN);
//                                }
//                              Log.e(TAG, "onAnimationUpdate: " + diffY + "   animatedValue=" + animatedValue + "  circleY:" + circleY);
                                invalidate();
                        }
                });
                valueAnimator.setDuration(10 * 1000);
                valueAnimator.setRepeatCount(10000);
                valueAnimator.start();
        }

        private int padding = 12;

        // 这个值会不断的移动
        private float diffY = 0;

        // inbottom=< circleY <= inbottom
        private float circleX, circleY;

        private float inleft, intop, inright, inbottom;

        private int outRadio, innerRadio;

        private float textWidth, textHeight;

        private float outleft, outtop, outright, outbottom;

        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                drawMethod2(canvas);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                Log.e(TAG, "onTouchEvent: action_down" +event.getX() + "  " +event.getRawX());
                                break;
                        case MotionEvent.ACTION_UP:
                                Log.e(TAG, "onTouchEvent: action_up" +event.getX() + "  " +event.getRawX());
                                break;
                        case MotionEvent.ACTION_MOVE:
                                Log.e(TAG, "onTouchEvent: action_move"+event.getX() + "  " +event.getRawX());
                                break;
                }
                return super.onTouchEvent(event);
        }

        private int radio = barWidth / 2;

        private void drawMethod2(Canvas canvas) {
                radio = barWidth / 2;
                canvas.drawRoundRect(centerX - barWidth / 2, centerY - barHeight / 2,
                        centerX + barWidth / 2, centerY + barHeight / 2, radio, radio, innterPaint);
                canvas.drawCircle(circleX, circleY, outRadio, circlePaint);
        }

        private void drawMethod1(Canvas canvas) {
                canvas.drawRect(outleft, outtop, outright, outbottom, outPaint);
                canvas.drawArc(outleft, outtop - outRadio, outright, outtop + outRadio, 0, -180, false, outPaint);
                canvas.drawArc(outleft, outbottom - outRadio, outright, outbottom + outRadio, 0, 180, false, outPaint);
                canvas.drawRect(inleft, intop, inright, inbottom - diffY, innterPaint);
                canvas.drawArc(inleft, intop - innerRadio, inright, intop + innerRadio, 0, -180, false, innterPaint);
                canvas.drawArc(inleft, (inbottom - innerRadio) - diffY, inright, (inbottom + innerRadio) - diffY, 0, 180, false, innterPaint);

                canvas.drawCircle(circleX, circleY - diffY, outRadio, circlePaint);
                canvas.drawText("全程", centerX - textWidth / 2, (outbottom + outRadio) + textHeight + padding, textPaint);
        }
}
