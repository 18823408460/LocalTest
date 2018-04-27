package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/27.
 */

public class TwoWavaSurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

        private static int WAVE_PAINT_COLOR = -2011262721;

        private static int STRETCH_FACTOR_A;

        private static int WaveHeight = 0;

        private static int TRANSLATE_X_SPEED_ONE;

        private static int TRANSLATE_X_SPEED_TWO;

        private int mXOffsetSpeedOne;

        private int mXOffsetSpeedTwo;

        private float mCycleFactorW;

        private int mTotalWidth;

        private int mTotalHeight;

        private float[] mYPositionsOne;
        private float[] mYPositionsTwo;

        private int mXOneOffset;

        private int mXTwoOffset;

        private boolean isAnim = true;

        private Paint mWavePaint;

        private DrawFilter mDrawFilter;

        public TwoWavaSurfaceView(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public TwoWavaSurfaceView(Context context) {
                this(context, (AttributeSet) null);
        }

        public TwoWavaSurfaceView(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
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

                mSurfaceHolder = getHolder();
                mSurfaceHolder.addCallback(this);
                setZOrderOnTop(true); //  必须调用
                mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        }

        private static final String TAG = "TwoWavaSurfaceView";
        private int  interval = 1 ;

        protected void onDrawSelf(Canvas canvas) {
                draw(canvas); // 这里必须调用？？？？？？  == canvas.drawColor(0, PorterDuff.Mode.CLEAR) 每刷新一次要先清屏;

                canvas.setDrawFilter(this.mDrawFilter);
                int i = 0;
                int j = 0;

                //两条波纹线，上面sin点 不一样就可以。。。。。
                for (int k = 0; i < this.mTotalWidth; i+=interval) {
                        if (i + this.mXOneOffset < this.mTotalWidth) {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositionsOne[this.mXOneOffset + i] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                        } else {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositionsOne[j] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                                ++j;
                        }

                        if (i + this.mXTwoOffset < this.mTotalWidth) {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositionsTwo[this.mXTwoOffset + i] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                        } else {
                                canvas.drawLine((float) i, (float) this.mTotalHeight - this.mYPositionsTwo[k] - (float) WaveHeight, (float) i, (float) this.mTotalHeight, this.mWavePaint);
                                ++k;
                        }
                }

                this.mXOneOffset += this.mXOffsetSpeedOne;
                this.mXTwoOffset += this.mXOffsetSpeedTwo;
                if (this.mXOneOffset >= this.mTotalWidth) {
                        this.mXOneOffset = 0;
                }
               // Log.e(TAG, "onDrawSelf: mXOneOffset="+mXOneOffset + "   mTotalWidth="+mTotalWidth );
              //  Log.e(TAG, "onDrawSelf: mXTwoOffset="+mXTwoOffset + "   mTotalWidth="+mTotalWidth );
                if (this.mXTwoOffset > this.mTotalWidth) {
                        this.mXTwoOffset = 0;
                }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                super.onSizeChanged(w, h, oldw, oldh);
                this.mTotalWidth = w;
                this.mTotalHeight = h;
                this.mYPositionsOne = new float[this.mTotalWidth];
                this.mYPositionsTwo = new float[this.mTotalWidth];
//                this.mCycleFactorW = 0.0043f;

                this.mCycleFactorW = (float) (6.283185307179586D / (double) this.mTotalWidth); // 0.004363323

                Log.e(TAG, "onSizeChanged: mCycleFactorW="+mCycleFactorW  + "  mTotalWidth="+mTotalWidth);
                for (int i = 0; i < this.mTotalWidth; i+=interval) {
//                        STRETCH_FACTOR_A == sin（） 的系数。。==== 振幅。。。
                        this.mYPositionsOne[i] = (float) ((double) STRETCH_FACTOR_A * Math.sin((double) (this.mCycleFactorW * (float) i)));
                        this.mYPositionsTwo[i] = (float) ((double) STRETCH_FACTOR_A * Math.cos((double) (this.mCycleFactorW * (float) i)));
                }

        }

        @Override
        public void run() {
                while (isAnim) {
                        Canvas canvas = mSurfaceHolder.lockCanvas();
                        try {
                                onDrawSelf(canvas);
                                Thread.sleep(20);
                        } catch (Exception e) {
                                e.printStackTrace();
                        } finally {
                                if (canvas != null) {
                                       // Log.e(TAG, "run: unlock------------" );
                                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                                }
                        }
                }
        }


        protected SurfaceHolder mSurfaceHolder;

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
                Log.e(TAG, "surfaceCreated: ");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.e(TAG, "surfaceChanged: ");
                if (isAnim){
                        new Thread(this).start();
                }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
                Log.e(TAG, "surfaceDestroyed: ");
                isAnim = false;
        }
}

