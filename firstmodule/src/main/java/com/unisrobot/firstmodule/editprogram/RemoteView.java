package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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
//        paint.setStrokeWidth(8);

        paint.setColor(Color.parseColor("#6A7185"));
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        // 是否填充，是否连接圆心  要区分
        paint.setStyle(Paint.Style.FILL);
        screenWidth = ScreenUtil.getwindth(getContext());
        screenHeight = ScreenUtil.getheight(getContext());

        bitmapLeft = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_left_normal);
        bitmapLeftPress = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_left_press);
        bitmapRight = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_right_normal);
        bitmapRightPress = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_right_press);
        bitmapUp = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_up_normal);
        bitmapUpPress = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_up_press);
        bitmapDown = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_down_normal);
        bitmapDownPress = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_down_press);
        bitmapHome = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_home_press);
        innerRadio = bitmapHome.getWidth()/2;
        outRadio = bitmapLeft.getWidth();
    }

    private int innerRadio,outRadio;
    Bitmap bitmapLeft, bitmapLeftPress, bitmapRight, bitmapRightPress, bitmapUp, bitmapUpPress, bitmapDown,bitmapDownPress, bitmapHome;

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
        canvas.drawCircle(centerX,centerY,outRadio+padAngle*3,paint);

        if (!leftPress) {
            canvas.drawBitmap(bitmapLeft, centerX - bitmapLeft.getWidth() - padAngle, centerY - bitmapLeft.getHeight() / 2, paint);
        } else {
            canvas.drawBitmap(bitmapLeftPress, centerX - bitmapLeftPress.getWidth() - padAngle, centerY - bitmapLeftPress.getHeight() / 2, paint);
        }
        if (!rightPress) {
            canvas.drawBitmap(bitmapRight, centerX + padAngle, centerY - bitmapRight.getHeight() / 2, paint);
        } else {
            canvas.drawBitmap(bitmapRightPress, centerX + padAngle, centerY - bitmapRight.getHeight() / 2, paint);
        }

        if (!upPress){
            canvas.drawBitmap(bitmapUp, centerX - bitmapUp.getWidth() / 2, centerY - bitmapUp.getHeight() - padAngle, paint);
        }else {
            canvas.drawBitmap(bitmapUpPress, centerX - bitmapUp.getWidth() / 2, centerY - bitmapUp.getHeight() - padAngle, paint);
        }

        if (!downPress){
            canvas.drawBitmap(bitmapDown, centerX - bitmapDown.getWidth() / 2, centerY + padAngle, paint);
        }else {
            canvas.drawBitmap(bitmapDownPress, centerX - bitmapDown.getWidth() / 2, centerY + padAngle, paint);
        }
        canvas.drawBitmap(bitmapHome, centerX - bitmapHome.getWidth() / 2, centerY - bitmapHome.getHeight() / 2, paint);
    }

    private static final String TAG = "RemoteView";
    private boolean leftPress = false;
    private boolean rightPress = false;
    private boolean upPress = false;
    private boolean downPress = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent:ACTION_DOWN :  x= " + event.getX() + "   y=" + event.getY());
                float x = event.getX();
                float y = event.getY();
                float diffx = Math.abs(x-centerX);
                float diffy = Math.abs(y-centerY);
                Log.e(TAG, "onTouchEvent: sqrt1======="+innerRadio + "  outRadio="+outRadio );
                double sqrt = Math.sqrt(diffx * diffx + diffy * diffy);
                Log.e(TAG, "onTouchEvent: sqrt======="+sqrt );
                if (sqrt< innerRadio || sqrt>outRadio){
                    Log.e(TAG, "onTouchEvent: in.................................");
                    return false;
                }
                if (x < centerX && (y > x && y < (getHeight() - x))) {
                    leftPress = true;
                    invalidate();
                    Log.e(TAG, "onTouchEvent: 11111111111111111");
                } else if (x > centerX && (y < x && y > (getWidth() - x))) {
                    Log.e(TAG, "onTouchEvent: 333333333333333333");
                    rightPress = true;
                    invalidate();
                } else if (y < centerY && (x > y && x < (getWidth() - y))) {
                    Log.e(TAG, "onTouchEvent: 2222222222222");
                    upPress = true;
                    invalidate();
                }else if (y > centerY && (x < y && x > (getWidth() - y))) {
                    Log.e(TAG, "onTouchEvent: 4444444444444444");
                    downPress = true;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP  :  x= " + event.getX() + "   y=" + event.getY());
                leftPress = false;
                rightPress = false;
                upPress = false;
                downPress = false;
                invalidate();
                break;

        }
        return true;
    }
}
