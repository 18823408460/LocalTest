package com.uurobot.baseframe.view.jinrong;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by WEI on 2018/6/6.
 */

public class HorProgress extends View {
        private static final String TAG = HorProgress.class.getSimpleName();
        private int mViewWidth;
        private int mViewHeight;
        private int mRoundWidth;
        private int mRoundHeight;
        private int colorBk;
        private int colorProgress;
        private int progress = 100;
        private int colorText;
        private int textPadding;
        private int viewPadding;
        private Paint roundPaint;
        private Paint textPaint;

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(mViewWidth, mViewHeight);
        }

        public HorProgress(Context context) {
                this(context, null);
        }

        public HorProgress(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public HorProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                viewPadding = SizeUtil.dp2px(getContext(), 20);
                mViewHeight = SizeUtil.dp2px(getContext(), 100);
                textPadding = SizeUtil.dp2px(getContext(), 3);

                Point screen = SizeUtil.getScreen();
                mViewWidth = screen.x;
                mRoundWidth = mViewWidth - viewPadding * 2;
                mRoundHeight = mViewHeight / 6;

                colorBk = Color.parseColor("#000000");
                colorProgress = Color.parseColor("#ff0000");
                colorText = Color.parseColor("#0000ff");
                roundPaint = new Paint();

                textPaint = new Paint();
                textPaint.setColor(colorText);
                textPaint.setTextSize(SizeUtil.dp2px(getContext(), 40));

                initAnim();
        }

        private int currentProgress = 0 ;
        private int currentText = 0 ;
        private void initAnim() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(4000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                                float animatedValue = (float) animation.getAnimatedValue();
                                currentProgress = (int) (progress*animatedValue);
                                Log.e(TAG, "onAnimationUpdate: "+currentProgress );
                                invalidate();
                        }
                });
                valueAnimator.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
                int width = getWidth();
                int height = getHeight();
                Log.e(TAG, "onDraw: width=" + width + "   height=" + height);
                roundPaint.setAntiAlias(true);
                roundPaint.setColor(Color.parseColor("#FFFFFF"));
                canvas.drawRect(0, 0, width, height, roundPaint);

                roundPaint.setColor(colorBk);
                RectF rectF = new RectF();
                rectF.left = viewPadding;
                rectF.top = height / 2 - mRoundHeight / 2;
                rectF.bottom = rectF.top + mRoundHeight;
                rectF.right = rectF.left + mRoundWidth;
                Log.e(TAG, "onDraw: " + rectF.toString());
                canvas.drawRoundRect(rectF, 100, 100, roundPaint);

                String text = String.valueOf(currentProgress + "%");
                float textW = textPaint.measureText(text);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float textH = fontMetrics.descent + fontMetrics.ascent;

                roundPaint.setColor(colorProgress);
                int endX = currentProgress * mRoundWidth / 100;
                rectF.right = rectF.left + endX;
                canvas.drawRoundRect(rectF, 100, 100, roundPaint);

                Log.e(TAG, "onDraw: end ==== " + endX);
                // text 的左下角坐标
                canvas.drawText(text, endX, height / 2 - textH/2 , textPaint);


                roundPaint.reset();
        }
}
