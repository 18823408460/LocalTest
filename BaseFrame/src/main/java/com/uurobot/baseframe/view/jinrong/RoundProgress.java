package com.uurobot.baseframe.view.jinrong;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by WEI on 2018/6/6.
 */

public class RoundProgress extends View {
        private static final String TAG = RoundProgress.class.getSimpleName();
        private int roundWidth;
        private int roundBkColor;
        private int roundProgressColor;
        private int mWidth;
        private int textColor;
        private int textSize;
        private Paint roundPaint;
        private Paint textPaint;
        private int roundRadio;
        private int progress = 90;
        private int angle;
        private int mCurrentAngle;
        private int mCurrentProgress;

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(mWidth, mWidth);
        }

        public RoundProgress(Context context) {
                this(context, null);
        }

        public RoundProgress(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                mWidth = SizeUtil.dp2px(getContext(), 200);
                roundWidth = SizeUtil.dp2px(getContext(), 10);
                roundBkColor = Color.parseColor("#44ffffff");
                roundProgressColor = Color.parseColor("#0000ff");
                textColor = Color.parseColor("#ff0000");
                textSize = SizeUtil.dp2px(getContext(), 40);

                roundPaint = new Paint();

                roundRadio = mWidth / 2 - roundWidth / 2;

                textPaint = new Paint();
                textPaint.setTextSize(textSize);
                textPaint.setColor(textColor);

                angle = progress * 360 / 100;
                initAnim();
        }

        private void initAnim() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                                float animatedValue = (float) animation.getAnimatedValue();
                                mCurrentAngle = (int) (animatedValue * angle);
                                mCurrentProgress = (int) (animatedValue * progress);
                                Log.e(TAG, "onAnimationUpdate: " + mCurrentAngle);
                                invalidate();
                        }
                });
                valueAnimator.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
                roundPaint.setStyle(Paint.Style.STROKE);
                roundPaint.setStrokeWidth(roundWidth);
                roundPaint.setAntiAlias(true);

                roundPaint.setColor(roundBkColor);
                canvas.drawCircle(mWidth / 2, mWidth / 2, roundRadio, roundPaint);

                RectF rectF = new RectF();
                rectF.left = roundWidth / 2;
                rectF.top = roundWidth / 2;
                rectF.right = mWidth / 2 + roundRadio;
                rectF.bottom = mWidth / 2 + roundRadio;
                roundPaint.setColor(roundProgressColor);
                canvas.drawArc(rectF, 0, mCurrentAngle, false, roundPaint);
                roundPaint.reset();

                String text = String.valueOf(mCurrentProgress + "%");
                float textWidth = textPaint.measureText(text);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float textHeight = (fontMetrics.descent + fontMetrics.ascent);
                canvas.drawText(text, mWidth / 2 - textWidth / 2, mWidth / 2 - textHeight / 2, textPaint);
        }
}
