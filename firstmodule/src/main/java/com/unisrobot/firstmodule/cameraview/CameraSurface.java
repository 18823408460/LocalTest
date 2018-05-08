package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/5/5.
 */

/**
 * 原来，当SurfaceHolder对象的类型设置为SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS时就只能拍照不能绘制了
 */
public class CameraSurface extends SurfaceView {
        private int defaultSize = 1000;
        private int measuredHeight;
        private int measuredWidth;
        private int mRadius;
        private Bitmap bitmapOut;

        public CameraSurface(Context context) {
                this(context, null);
        }

        public CameraSurface(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public CameraSurface(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        initData();
                }

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int modeW = MeasureSpec.getMode(widthMeasureSpec);
                int sizeW = MeasureSpec.getSize(widthMeasureSpec);

                setMeasuredDimension(modeW == MeasureSpec.EXACTLY ? sizeW : defaultSize, modeW == MeasureSpec.EXACTLY ? sizeW : defaultSize);
        }

        private Paint paint;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void initData() {
                //                setClipToOutline(true);
                //                setOutlineProvider(new ViewOutlineProvider() {
                //                        @Override
                //                        public void getOutline(View view, Outline outline) {
                //                                Rect rect=  new Rect();
                //                                view.getGlobalVisibleRect(rect);
                //                                int leftMargin = (measuredWidth - mRadius) / 2;
                //                                int topMargin = (measuredHeight - mRadius) / 2;
                //                                Rect selfRect = new Rect(leftMargin, topMargin, mRadius + leftMargin, mRadius + topMargin);
                //                                outline.setRoundRect(selfRect, mRadius / 2);
                //                        }
                //                });
                paint = new Paint();
                paint.setStrokeWidth(3);
                paint.setColor(Color.RED);
                paint.setTextSize(30);
                paint.setStyle(Paint.Style.STROKE);
                bitmapOut = BitmapFactory.decodeResource(getResources(), R.drawable.rotatein);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                super.onSizeChanged(w, h, oldw, oldh);
                measuredHeight = getMeasuredHeight();
                measuredWidth = getMeasuredWidth();
                mRadius = Math.min(measuredHeight, measuredWidth);

        }

        private static final String TAG = "CameraSurface";
        //        @Override
        //        protected void onDraw(Canvas canvas) {
        //                int min = Math.min(getWidth(), getHeight());
        ////                canvas.drawCircle(getWidth()/2,getHeight()/2,min/2,paint);
        //                Log.e(TAG, "onDraw: "+ getWidth()+ "     " +getHeight());
        //                Path path = new Path();
        //                path.addCircle(getWidth()/2,getHeight()/2,min/2, Path.Direction.CCW);
        //                canvas.clipPath(path, Region.Op.REPLACE);
        //                super.onDraw(canvas);
        //        }


        // android:background="@android:color/transparent" 这样下面的方法才会调用
        @Override
        public void draw(Canvas canvas) {
                // 下面设置导致没有圆角
//                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
                int min = Math.min(getWidth(), getHeight());
                //                canvas.drawCircle(getWidth()/2,getHeight()/2,min/2,paint);
                Log.e(TAG, "draw.....: " + getWidth() + "     " + getHeight() + "    "+Thread.currentThread().getName());
                Path path = new Path();
                path.addCircle(getWidth() / 2, getHeight() / 2, min / 2, Path.Direction.CCW);
                //                // cai
                canvas.clipPath(path, Region.Op.REPLACE);


                // 这个不出来？？？ 因为super.draw(canvas)绘制预览数据会将它覆盖
                //canvas.drawCircle(getWidth()/2,getHeight()/2, min/2-30,paint);
                // 这个必须放在后面。。
                super.draw(canvas);
                canvas.drawCircle(getWidth()/2,getHeight()/2, min/2-10,paint);
                canvas.drawText(String.valueOf(count++),getWidth()/2,getHeight()/2,paint);

        }


        private volatile boolean bStartDraw =false ;
        private SurfaceHolder surfaceHolder ;
        public void startDraw(SurfaceHolder surfaceHolder){
                this.surfaceHolder = surfaceHolder ;
                bStartDraw = true ;
                Thread drawThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                                while (bStartDraw && !Thread.interrupted()) {
//                                        doDraw();
//                                        postInvalidate();
//                                        try {
//                                                Thread.sleep(1000);
//                                        } catch (InterruptedException e) {
//                                                e.printStackTrace();
//                                        }
                                        doDraw2();
                                }
                        }
                }, "drawThread");
                drawThread.start();
        }

        private void doDraw2() {
                Canvas canvas = null ;
                try {
                        canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
                        //每次绘制之前要 清楚屏幕
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
                        canvas.drawBitmap(bitmapOut,getWidth()/2-bitmapOut.getWidth()/2 , getHeight()/2-bitmapOut.getHeight()/2,null);
                        Thread.sleep(10);
                }catch (InterruptedException e) {
                        e.printStackTrace();
                        bStartDraw  = false ;
                } catch (Exception e){

                }finally {
                        //    java.lang.IllegalArgumentException: canvas object must be the same instance that was previously returned by lockCanvas
                        if (canvas != null) {
                                surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                }
        }

        private int count ;
        private void doDraw() {
                paint.setStrokeWidth(3);
                Canvas canvas = null ;
                try {
                        canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
                        //每次绘制之前要 清楚屏幕
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
                        canvas.drawText(String.valueOf(count++),getWidth()/2,getHeight()/2,paint);
                        Thread.sleep(1000);
                }catch (InterruptedException e) {
                        e.printStackTrace();
                        bStartDraw  = false ;
                } catch (Exception e){

                }finally {
                        //    java.lang.IllegalArgumentException: canvas object must be the same instance that was previously returned by lockCanvas
                        if (canvas != null) {
                                surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                }

        }
}
