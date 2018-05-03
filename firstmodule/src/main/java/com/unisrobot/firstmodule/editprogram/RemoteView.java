package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/28.
 */

public class RemoteView extends View {
    public RemoteView(Context context) {
        this(context, null);
    }

    public RemoteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RemoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private int[] colors = new int[]{Color.RED, Color.GRAY, Color.BLUE, Color.CYAN};
    private Paint paint;
    private int screenWidth, screenHeight;
    private float centerX, centerY;
    private int radio = 220; // px
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    private int paintWidth = 10;

    private void initData() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);

        paint.setColor(Color.CYAN);
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        // 是否填充，是否连接圆心  要区分
        paint.setStyle(Paint.Style.STROKE);
        screenWidth = ScreenUtil.getwindth(getContext());
        screenHeight = ScreenUtil.getheight(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(size, size);
        } else {
            setMeasuredDimension(radio * 2, radio * 2);
        }
    }

    private int padAngle = 3;

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        centerX = centerY = width / 2;
        int angle = (360 - padAngle * 4) / 4;
        int startAngle = angle / 2;

        Bitmap bitmapLeft = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_left_normal);
        Bitmap bitmapLeftPress = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_left_press);
        Bitmap bitmapRight = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_right_normal);
        Bitmap bitmapUp = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_up_normal);
        Bitmap bitmapDown = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_down_normal);
        Bitmap bitmapHome = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_home_press);

        Matrix matrix = new Matrix();
        matrix.setTranslate(centerX - bitmapLeft.getWidth() / 2, centerY - bitmapLeft.getHeight() / 2);
        BitmapShader bitmapShader = new BitmapShader(bitmapLeft, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);

        if (!leftPress) {
            canvas.drawBitmap(bitmapLeft, centerX - bitmapLeft.getWidth() - padAngle, centerY - bitmapLeft.getHeight() / 2, paint);
        } else {
            canvas.drawBitmap(bitmapLeftPress, centerX - bitmapLeftPress.getWidth() - padAngle, centerY - bitmapLeftPress.getHeight() / 2, paint);
        }

        canvas.drawBitmap(bitmapRight, centerX + padAngle, centerY - bitmapRight.getHeight() / 2, paint);
        canvas.drawBitmap(bitmapUp, centerX - bitmapUp.getWidth() / 2, centerY - bitmapUp.getHeight() - padAngle, paint);
        canvas.drawBitmap(bitmapDown, centerX - bitmapDown.getWidth() / 2, centerY + padAngle, paint);

        Log.e(TAG, "onDraw: " + getWidth() + "   " + getHeight());
        Log.e(TAG, "onDraw: bitmap" + bitmapLeft.getWidth() + "   " + bitmapLeft.getHeight());


//        canvas.drawCircle(centerX, centerY, 60, paint);
        canvas.drawBitmap(bitmapHome, centerX - bitmapHome.getWidth() / 2, centerY - bitmapHome.getHeight() / 2, paint);
//        for (int i = 0; i < 4; i++) {
//            paint.setColor(colors[i]);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_ten);
//            Matrix matrix = new Matrix();
//            matrix.setTranslate(centerX - radio, centerY - radio);
//            BitmapShader bitmapShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//            bitmapShader.setLocalMatrix(matrix);
//            paint.setShader(bitmapShader);
//            canvas.setDrawFilter(paintFlagsDrawFilter);
//            canvas.drawArc(rectf, startAngle + padAngle * i + angle * i, angle, false, paint);
//        }
    }

    private static final String TAG = "RemoteView";
    private boolean leftPress = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent:ACTION_DOWN :  x= " + event.getX() + "   y=" + event.getY());
                float x = event.getX();
                float y = event.getY();
                if ( x < centerX   &&   ( y>x && y<(getHeight()-x)   )) {
                    leftPress = true;
                    invalidate();
                    Log.e(TAG, "onTouchEvent: 11111111111111111");
                }else if( x> centerX && (  y>x &&  y<(getHeight()-x)      )  ){
                    Log.e(TAG, "onTouchEvent: 333333333333333333");
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP  :  x= " + event.getX() + "   y=" + event.getY());
                leftPress = false;
                invalidate();
                break;

        }
        return true;
    }
}
