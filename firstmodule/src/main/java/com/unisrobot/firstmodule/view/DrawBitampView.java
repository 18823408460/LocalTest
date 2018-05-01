package com.unisrobot.firstmodule.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by WEI on 2018/4/30.
 */

/**
 * 利用BitmapShader是从控件左上角开始平铺的，利用canvas.drawXXX系列函数只是用来定义显示哪一块的原理，我们在图片的正中心画一个圆，半径是图片正方形半径的一半：
 */
public class DrawBitampView extends View {
    public DrawBitampView(Context context) {
        this(context, null);
    }

    public DrawBitampView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DrawBitampView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private int centerX, centerY;
    private int radio;
    private Paint paint;

    private void initData() {
        centerX = ScreenUtil.getwindth(getContext()) / 2;
        centerY = ScreenUtil.getheight(getContext()) / 2;
        radio = Math.min(centerX, centerY);
        paint = new Paint();

    }

    private static final String TAG = "DrawBitampView";

    @Override
    protected void onDraw(Canvas canvas) {
        draw3(canvas);
    }


    private void draw3(Canvas canvas) {
        paint.setColor(Color.RED);
        // 这个方法时用于创建bitmap，或。。 对已经存在的bitmap修改。。
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.parseColor("#0000ff"));
        Canvas canvas1 = new Canvas(bitmap);
//        canvas.drawBitmap(bitmap, centerX, centerY, paint);

        //下面这个方法是通过canvas手动绘制的bitmap，这个效果要通过显示bitmpa才能体现。canvas1是绘制在bitmap上。
        canvas1.drawText("hello", bitmap.getWidth() / 2, bitmap.getHeight() / 2, paint);

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private void draw2(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.first_module_ic_launcher);
        canvas.drawBitmap(bitmap, centerX, centerY, paint);
    }

    private void draw1(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.first_module_ic_launcher);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate(71, 0);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        Log.e(TAG, "onDraw:  x= " + centerX + "  centerY=" + centerY);
        Log.e(TAG, "onDraw:  bitmap.getWidth()= " + bitmap.getWidth() + "  bitmap.getHeight()=" + bitmap.getHeight());
        canvas.drawRect(71, 0, bitmap.getWidth() + 111, bitmap.getHeight() + 100, paint);
    }
}
