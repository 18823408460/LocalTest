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

/**
 * Created by WEI on 2018/5/6.
 */

public class AnimSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "AnimSurfaceView";
    private SurfaceHolder surfaceHolder;
    private boolean threadAlive;
    private int[] bitmaps = {R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven};

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
        drawThread.interrupt();
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

    private int frameRate = 34; //帧率

    private void doDraw() {
        Canvas canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
        try {
            //每次绘制之前要 清楚屏幕
//            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
//            canvas.drawColor(Color.BLACK);
            drawBitmaps(canvas);
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
            threadAlive  = false ;
        } catch (Exception e){

        }finally {
            //    java.lang.IllegalArgumentException: canvas object must be the same instance that was previously returned by lockCanvas
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmaps[index++ % bitmaps.length]);
        Log.e(TAG, "drawBitmaps: decodeEnd");
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Log.e(TAG, "drawBitmaps: drawBitmap......width="+width + "   height="+height );
            canvas.drawBitmap(bitmap,100,100,paint);
        }
    }


}
