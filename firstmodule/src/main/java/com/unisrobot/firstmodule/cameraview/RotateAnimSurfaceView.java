package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.BitmapUtil;

/**
 * Created by WEI on 2018/5/6.
 */

public class RotateAnimSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "RotateAnimSurfaceView";
    private SurfaceHolder surfaceHolder;
    private boolean threadAlive;
    private int[] bitmaps = {R.drawable.rotatein,R.drawable.rotateout};

    public RotateAnimSurfaceView(Context context) {
        this(context, null);
    }

    public RotateAnimSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RotateAnimSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private Thread drawThread;
    private Paint paint ;
    Bitmap bitmapIn;
    Bitmap bitmapOut;
    private void initData() {
        surfaceHolder = getHolder();

        // Thread already started
        //drawThread = new Thread(this, "drawThread");
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);

         bitmapIn = BitmapFactory.decodeResource(getResources(), bitmaps[0]);
         bitmapOut = BitmapFactory.decodeResource(getResources(), bitmaps[1]);
         defaultSize = bitmapOut.getWidth();

         // 背景设置透明，一定要调用下面两个
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        holder.setFormat(PixelFormat.TRANSPARENT);
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
        if (drawThread !=null && drawThread.isAlive()){
            drawThread.interrupt();
        }

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

    private int defaultSize  ;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(modeW==MeasureSpec.EXACTLY?sizeW:defaultSize,modeW==MeasureSpec.EXACTLY?sizeW:defaultSize);
    }

    private int frameRate = 1000; //帧率

    private void doDraw() {
        Canvas canvas = null ;
        try {
            canvas = surfaceHolder.lockCanvas();//从Surface中取出一块矩形区域进行刷新
            //每次绘制之前要 清楚屏幕
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);        // 清除屏幕
//            canvas.drawColor(Color.WHITE); // 这里设置背景
//            drawBitmaps(canvas);
            drawRotataAnim(canvas);
            Thread.sleep(1000/frameRate);
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

    private int angle ;
    private int angleOut ;
    Matrix matrix = new Matrix();

    Matrix matrixOut = new Matrix();
    private void drawRotataAnim(Canvas canvas){
        // 给 canvas 设置抗锯齿， 也可以通过 Paint
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        if (bitmapIn != null){
            int width = bitmapIn.getWidth();
            int height = bitmapIn.getHeight();
//            if (width>600){
//                bitmapIn = BitmapUtil.zoomBitmap1(bitmapIn, 600, 600);
//            }

            angle +=10 ;
            angle = angle%360 ;
            matrix.setTranslate(getWidth()/2-width/2,getHeight()/2-height/2);
            Log.e(TAG, "drawRotataAnim: angle===="+angle );
            matrix.postRotate(angle,getWidth()/2, getHeight()/2);
//            canvas.drawBitmap(bitmapIn,getWidth()/2-width/2,getHeight()/2-height/2,null);
            canvas.drawBitmap(bitmapIn,matrix,null);

            angleOut-=10 ;

            //先移动要正确的位置，，然后在旋转
            matrixOut.setTranslate(getWidth()/2-width/2,getHeight()/2-height/2);
            matrixOut.postRotate(angleOut,getWidth()/2, getHeight()/2);
            canvas.drawBitmap(bitmapOut,matrixOut,null);
        }
    }

    private int index ;
    private void drawBitmaps(Canvas canvas) {
        Log.e(TAG, "drawBitmaps: decodeStart");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmaps[index++ % bitmaps.length]);
        Log.e(TAG, "drawBitmaps: decodeEnd");
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
