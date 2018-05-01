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
import android.graphics.RectF;
import android.graphics.Shader;
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
    private int radio = 200; // px
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    private int paintWidth = 200;

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
            setMeasuredDimension(radio * 2 + paintWidth, radio * 2 + paintWidth);
        }
    }

    private int padAngle = 2;

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        centerX = centerY = width / 2;
        RectF rectf = new RectF(centerX - radio, centerY - radio, centerX + radio, centerY + radio);
        int angle = (360 - padAngle * 4) / 4;
        int startAngle = angle / 2;

        canvas.drawCircle(centerX, centerY, 20, paint);
        paint.setStrokeWidth(paintWidth);
        for (int i = 0; i < 4; i++) {
            paint.setColor(colors[i]);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_ten);
            Matrix matrix = new Matrix();
            matrix.setTranslate(centerX - radio, centerY - radio);
            BitmapShader bitmapShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            canvas.setDrawFilter(paintFlagsDrawFilter);
            canvas.drawArc(rectf, startAngle + padAngle * i + angle * i, angle, false, paint);
        }
    }

    private static final String TAG = "RemoteView";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent:ACTION_DOWN :  x= " + event.getX() + "   y=" + event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP  :  x= " + event.getX() + "   y=" + event.getY());
                break;

        }
        return super.onTouchEvent(event);
    }
}
