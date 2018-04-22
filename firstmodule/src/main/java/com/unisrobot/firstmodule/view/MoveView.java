package com.unisrobot.firstmodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by WEI on 2018/4/22.
 */

/**
 * getPivotX() getPivotY()
 * view旋转和缩放的时候的中心点
 */
public class MoveView extends View {
    private static final String TAG = "MoveView";

    public MoveView(Context context) {
        this(context, null);

    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initData();
    }

    private Paint paint;

    private float centerX;
    private float centerY;
    private Context context;
    private int radio = 50;
    PointF point;

    private void initData() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        centerX = ScreenUtil.getwindth(context) / 2;
        centerY = ScreenUtil.getheight(context) / 2;
        point = new PointF(centerX, centerY);
        Log.e(TAG, "initData: ");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
//        canvas.drawCircle(point.x, point.y, radio, paint);
        canvas.drawCircle(centerX, centerY, radio, paint);
    }

    private float viewX;
    private float viewY;
    private float viewWidth;
    private float viewHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = radio * 2;
        int mHeight = ScreenUtil.getheight(context) - 100;

        // 当模式是AT_MOST（即wrap_content）时设置默认值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mHeight);
        }

        viewX = getX();
        viewY = getY();
        viewWidth = mWidth;
        viewHeight = mHeight;
        Log.e(TAG, "onMeasure: " + viewX + "   " + viewY);
//        centerX = viewX + radio ;
//        centerY = viewY + radio;
        centerX = radio;
        centerY = radio;
        Log.e(TAG, "onMeasure: " + centerX + "   " + centerY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: down=" + getX() + "   y=" + getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: up=" + getX() + "   y=" + getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                centerX = event.getX();
                centerY = event.getY();
                if (centerY <= radio) {
                    centerY = radio;
                }
                if (centerY >= viewHeight - radio) {
                    centerY = viewHeight - radio;
                }
                if (event.getRawX() < viewX || event.getRawX() > (viewX + viewWidth)) {
                    return false;
                }
                Log.d(TAG, "onTouchEvent: move=" + centerX + "   y=" + centerY);
                Log.d(TAG, "onTouchEvent: move=" + event.getRawX() + "   y=" + event.getRawY());
                // point.set(event.getX(), event.getY());
                invalidate();
                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }
}
