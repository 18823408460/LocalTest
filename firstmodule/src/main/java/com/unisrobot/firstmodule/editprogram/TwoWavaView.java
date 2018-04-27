package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/27.
 */

public class TwoWavaView extends View implements Runnable {

        private static int WAVE_PAINT_COLOR = -2011262721;

        private static int STRETCH_FACTOR_A;

        private static int OFFSET_Y = 0;

        private static int WaveHeight = 0;

        private static int TRANSLATE_X_SPEED_ONE;

        private static int TRANSLATE_X_SPEED_TWO;

        private int mXOffsetSpeedOne;

        private int mXOffsetSpeedTwo;

        private float mCycleFactorW;

        private int mTotalWidth;

        private int mTotalHeight;

        private float[] mYPositions;

        private int mXOneOffset;

        private int mXTwoOffset;

        private boolean isAnim = true;

        private Paint mWavePaint;

        private DrawFilter mDrawFilter;

        private Runnable mRunnable;

        public TwoWavaView(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public TwoWavaView(Context context) {
                this(context, (AttributeSet) null);
        }

        public TwoWavaView(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
                this.isAnim = true;
                this.mRunnable = new Runnable() {
                        public void run() {
                                try {
                                        TwoWavaView.this.postInvalidate();
                                        Thread.sleep(10L);
                                } catch (InterruptedException var2) {
                                        var2.printStackTrace();
                                }
                        }
                };
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.first_module_DoubleWaveView, defStyle, 0);
                int n = a.getIndexCount();

                for (int i = 0; i < n; ++i) {
                        int attr = a.getIndex(i);
                        if (attr == R.styleable.first_module_DoubleWaveView_first_module_peakValue) {
                                STRETCH_FACTOR_A = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(1, 30.0F, this.getResources().getDisplayMetrics()));
                        } else if (attr == R.styleable.first_module_DoubleWaveView_first_module_waveColor) {
                                WAVE_PAINT_COLOR = a.getColor(attr, 222);
                        } else if (attr == R.styleable.first_module_DoubleWaveView_first_module_speedOne) {
                                TRANSLATE_X_SPEED_ONE = a.getInteger(attr, 7);
                        } else if (attr == R.styleable.first_module_DoubleWaveView_first_module_speedTwo) {
                                TRANSLATE_X_SPEED_TWO = a.getInteger(attr, 5);
                        } else if (attr == R.styleable.first_module_DoubleWaveView_first_module_waveHeight) {
                                WaveHeight = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(1, 100.0F, this.getResources().getDisplayMetrics()));
                        }
                }

                a.recycle();
                this.mXOffsetSpeedOne = ScreenUtil.dip2px(context, (float) TRANSLATE_X_SPEED_ONE);
                this.mXOffsetSpeedTwo = ScreenUtil.dip2px(context, (float) TRANSLATE_X_SPEED_TWO);
                this.mWavePaint = new Paint();
                this.mWavePaint.setAntiAlias(true);
                this.mWavePaint.setStyle(Paint.Style.FILL);
                this.mWavePaint.setColor(WAVE_PAINT_COLOR);
                this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        }

        private static final String TAG = "TwoWavaView";
        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                Log.e(TAG, "onDraw: -----------------" );
                canvas.setDrawFilter(this.mDrawFilter);
                int i = 0;
                int j = 0;

                for (int k = 0; i < this.mTotalWidth; ++i) {
                        if (i + this.mXOneOffset < this.mTotalWidth) {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositions[this.mXOneOffset + i] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                        } else {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositions[j] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                                ++j;
                        }

                        if (i + this.mXTwoOffset < this.mTotalWidth) {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositions[this.mXTwoOffset + i] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                        } else {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositions[k] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                                ++k;
                        }
                }

                this.mXOneOffset += this.mXOffsetSpeedOne;
                this.mXTwoOffset += this.mXOffsetSpeedTwo;
                if (this.mXOneOffset >= this.mTotalWidth) {
                        this.mXOneOffset = 0;
                }

                if (this.mXTwoOffset > this.mTotalWidth) {
                        this.mXTwoOffset = 0;
                }
                if(this.isAnim) {
                        (new Thread(this.mRunnable)).start();
                }
        }

        public void setAnim(Boolean isAnim) {
                if (!this.isAnim == isAnim.booleanValue()) {
                        this.isAnim = isAnim.booleanValue();
                        this.postInvalidate();
                }

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                super.onSizeChanged(w, h, oldw, oldh);
                this.mTotalWidth = w;
                this.mTotalHeight = h;
                this.mYPositions = new float[this.mTotalWidth];
                this.mCycleFactorW = (float) (6.283185307179586D / (double) this.mTotalWidth);

                for (int i = 0; i < this.mTotalWidth; ++i) {
                        this.mYPositions[i] = (float) ((double) STRETCH_FACTOR_A * Math.sin((double) (this.mCycleFactorW * (float) i)) + (double) OFFSET_Y);
                }

        }

        @Override
        public void run() {

        }
}

