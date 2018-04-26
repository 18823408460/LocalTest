package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/24.
 */

/**
 * 待完成。。。
 *
 *
 * Path类的用处？？？？？？？？？？？？？？？？？？、
 */
public class CircleView extends View {
    private static final String TAG = "CircleView";

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private Paint paint;
    private int centerX, centerY;
    private float outerRadio, inRadio;

    private void initData() {
        paint = new Paint();
        paint.setAntiAlias(true);

        centerX = ScreenUtil.getwindth(getContext()) / 2;
        centerY = ScreenUtil.getheight(getContext()) / 2;
        outerRadio = Math.min(centerX, centerY) - 100;
        inRadio = outerRadio / 4;

        outerRectStartX = (int) (centerX - outerRadio + padding);
        outerRectStartY = (int) (centerY - outerRadio + padding);
        outRectEndX = (int) (centerX + outerRadio - padding);
        outRectEndY = (int) (centerY + outerRadio - padding);

        inRectStartX = (int) (centerX - outerRadio + paddingIn);
        inRectStartY = (int) (centerY - outerRadio + paddingIn);
        inRectEndX = (int) (centerX + outerRadio - paddingIn);
        inRectEndY = (int) (centerY + outerRadio - paddingIn);
    }

    private int padding = 10;
    private int paddingIn = 110;
    private int outRectEndX,outRectEndY, outerRectStartX,outerRectStartY;
    private int inRectEndX,inRectEndY, inRectStartX,inRectStartY;
    private int padAngle = 10 ;
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, outerRadio, paint);

        paint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY, inRadio, paint);


        // startAngle=0 代表水平线(右边 )，逆时针为负数)，，    -90 = 逆时针转90度
        // canvas.drawArc(oval, -45, -90, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        int[] color = new int[]{Color.BLUE,Color.GREEN,Color.YELLOW,Color.CYAN};
        int[] colorIn = new int[]{Color.GREEN,Color.YELLOW,Color.CYAN,Color.BLUE};
        RectF rectFOut = new RectF(outerRectStartX, outerRectStartY, outRectEndX, outRectEndY);
        RectF rectFIn = new RectF(inRectStartX, inRectStartY, inRectEndX, inRectEndY);
        int startAngle = -45 ;
        for (int i = 0; i < 4; i++) {
            paint.setColor(color[i]);
            canvas.drawArc(rectFOut,-45+i*90,-90,false,paint);
            paint.setColor(colorIn[i]);
            canvas.drawArc(rectFIn,-45+i*90,-90,false,paint);
        }

        paint.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: t");
        return true;
    }
}
