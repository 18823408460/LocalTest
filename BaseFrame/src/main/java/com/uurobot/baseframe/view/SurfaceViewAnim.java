package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.AnimIds;
import com.uurobot.baseframe.utils.EAnimType;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SurfaceViewAnim extends SurfaceView implements SurfaceHolder.Callback, Runnable {
        private static final String TAG = SurfaceViewAnim.class.getSimpleName();
        private volatile boolean threadAlive;
        private int frameRate = 16; //帧率
        private SurfaceHolder surfaceHolder;
        private Thread drawThread;
        private int index;
        private EAnimType eAnimType;

        public SurfaceViewAnim(Context context) {
                this(context, null);
        }

        public SurfaceViewAnim(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public SurfaceViewAnim(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
                initTypeVuale(attrs);
        }

        private void initTypeVuale(AttributeSet attrs) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SurfaceViewAnim);
                int anInt = typedArray.getInt(R.styleable.SurfaceViewAnim_type, 0);
                boolean aBoolean = typedArray.getBoolean(R.styleable.SurfaceViewAnim_orderOnTop, false);
                EAnimType[] values = EAnimType.values();
                eAnimType = values[anInt];
                Log.e(TAG, "initTypeVuale:  int= " + anInt + "  type=" + eAnimType + "  aBoolean="+aBoolean);

                // 下面两句话，才能使背景透明，不然是个黑色
                setZOrderOnTop(aBoolean);
                surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        }

        private void initData() {
                surfaceHolder = getHolder();
                surfaceHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                threadAlive = true;
                drawThread = new Thread(this, "drawThread");
                drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
                threadAlive = false;
                Log.d(TAG, "surfaceDestroyed: ");
        }

        private boolean startDraw = true ;

        public void setStartDraw(boolean startDraw) {
                this.startDraw = startDraw;
        }

        @Override
        public void run() {
                Log.e(TAG, "run: onDraw start");
                while (threadAlive && !Thread.interrupted()) {
                        doDraw();
                }
                Log.e(TAG, "run: onDraw end");
        }

        private void doDraw() {
                Canvas canvas = null;
                try {
                        canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
                        //每次绘制之前要 清楚屏幕
                        // 这里可能   NullPointerException ， 在退出的时候
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
                        // canvas.drawColor(Color.BLACK); // 这里设置背景
                        drawBitmaps(canvas);
                        Thread.sleep(1000 / frameRate);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e(TAG, "doDraw: e1=" + e);
                        threadAlive = false;
                } catch (Exception e) {
                        Log.e(TAG, "doDraw: e2=" + e);
                } finally {
                        //    java.lang.IllegalArgumentException: canvas object must be the same instance that was previously returned by lockCanvas
                        if (canvas != null) {
                                try {
                                        surfaceHolder.unlockCanvasAndPost(canvas);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(TAG, "doDraw: e3====" + e);
                                }
                        }
                }
        }

        public void updateAnim(EAnimType eAnimType) {
                this.eAnimType = eAnimType;
        }

        private void drawBitmaps(Canvas canvas) {
                //Log.e(TAG, "drawBitmaps: decodeStart=== " + eAnimType);
                int bitmapRes = 0;
                bitmapRes = getBitmapRes(bitmapRes);
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeStream(getContext().getResources().openRawResource(bitmapRes));
                //Log.e(TAG, "drawBitmaps: decodeEnd"); //100ms
                if (bitmap != null) {
                        Matrix matrix = new Matrix();
                        //结果转floag，需要在分子乘以 1.0
                        float x = (float) (getWidth() * 1.0 / bitmap.getWidth());
                        float y = (float) (getHeight() * 1.0 / bitmap.getHeight());
                        matrix.setScale(x, y);
                        canvas.drawBitmap(bitmap, matrix, null);
                }
        }

        private int getBitmapRes(int bitmapRes) {
                if (eAnimType.equals(EAnimType.Dry)) {
                        bitmapRes = AnimIds.Dry[index++ % AnimIds.Dry.length];

                } else if (eAnimType.equals(EAnimType.DryWet)) {
                        bitmapRes = AnimIds.DryWet[index++ % AnimIds.DryWet.length];

                } else if (eAnimType.equals(EAnimType.HighDry)) {
                        bitmapRes = AnimIds.HighDry[index++ % AnimIds.HighDry.length];

                } else if (eAnimType.equals(EAnimType.HighDryWet)) {
                        bitmapRes = AnimIds.HighDryWet[index++ % AnimIds.HighDryWet.length];

                } else if (eAnimType.equals(EAnimType.Moist)) {
                        bitmapRes = AnimIds.Moist[index++ % AnimIds.Moist.length];

                } else if (eAnimType.equals(EAnimType.MoistWet)) {
                        bitmapRes = AnimIds.MoistWet[index++ % AnimIds.MoistWet.length];

                } else if (eAnimType.equals(EAnimType.LowShao)) {
                        bitmapRes = AnimIds.LowShao[index++ % AnimIds.LowShao.length];

                } else if (eAnimType.equals(EAnimType.LowWen)) {
                        bitmapRes = AnimIds.LowWen[index++ % AnimIds.LowWen.length];

                } else if (eAnimType.equals(EAnimType.HighShao)) {
                        bitmapRes = AnimIds.HighShao[index++ % AnimIds.HighShao.length];
                }
                return bitmapRes;
        }
}
