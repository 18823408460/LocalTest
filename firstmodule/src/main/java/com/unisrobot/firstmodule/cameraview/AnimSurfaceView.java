package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.BitmapUtil;

/**
 * Created by WEI on 2018/5/6.
 */

public class AnimSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "AnimSurfaceView";
    private SurfaceHolder surfaceHolder;
    private boolean threadAlive;
    private int[] bitmaps = {/*R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,*/
            R.drawable.first_module_four, R.drawable.first_module_five,R.drawable.first_module_left_normal,R.drawable.first_module_right_normal,
            /*R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven*/};

    public AnimSurfaceView(Context context) {
        this(context, null);
    }

    public AnimSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AnimSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private Thread drawThread;
    private Paint paint ;
    private void initData() {
        surfaceHolder = getHolder();

        // Thread already started
        //drawThread = new Thread(this, "drawThread");
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated: " );
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged: ");
        threadAlive = true;
        //  Thread already started
        drawThread = new Thread(this, "drawThread");
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        threadAlive = false;
//        drawThread.interrupt();
        Log.e(TAG, "surfaceDestroyed: ");
    }

    @Override
    public void run() {
        Log.e(TAG, "run: onDraw start");
        while (threadAlive && !Thread.interrupted()) {
            doDraw();
        }
        Log.e(TAG, "run: onDraw end");
    }

    private int defaultSize = 500 ;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(modeW==MeasureSpec.EXACTLY?sizeW:defaultSize,modeW==MeasureSpec.EXACTLY?sizeW:defaultSize);
    }

    private int frameRate = 1000; //帧率

    private void doDraw() {
        Canvas  canvas = null ;
        try {
            canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
            //每次绘制之前要 清楚屏幕

            // 这里可能   NullPointerException ， 在退出的时候
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
            canvas.drawColor(Color.BLACK); // 这里设置背景
            drawBitmaps(canvas);
            Thread.sleep(1000/frameRate);
        }catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "doDraw: e1="+e );
            threadAlive  = false ;
        } catch (Exception e){
            Log.e(TAG, "doDraw: e2="+e );
        }finally {
            //    java.lang.IllegalArgumentException: canvas object must be the same instance that was previously returned by lockCanvas
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "doDraw: e3===="+e );
                }
            }
        }
    }

    private void drawRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xFFFF0000);
        canvas.drawRect(0, 0, 100, 100, paint);
    }

    private int index ;
    private void drawBitmaps(Canvas canvas) {
        Log.e(TAG, "drawBitmaps: decodeStart");
        Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(bitmaps[index++ % bitmaps.length]));
        Log.e(TAG, "drawBitmaps: decodeEnd"); //100ms
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width>600){
                bitmap = BitmapUtil.zoomBitmap1(bitmap, 400, 400);
            }
            Log.e(TAG, "drawBitmaps: drawBitmap......width="+width + "   height="+height );
            canvas.drawBitmap(bitmap,100,100,paint);
        }
    }


}
