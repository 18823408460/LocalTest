package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
 *
 * Path类的用处？
 * Canvas绘制图形只能画一些常规图形（圆，椭圆，矩形等），如果想绘制更复杂的图形，Path神器来了
 *Path类将多种复合路径（多个轮廓，如直线段、二次曲线、立方曲线）封装在其内部的几何路径; Path还可以用于剪切或者在路径上绘制文本（canvas.drawTextOnPath()）。
 *
 * Path常用方法:
 * > 绘制线操作： lineTo、rLineTo
 * > 点操作 : moveTo、rMoveTo
 * > 添加常规图形:   addRect addRoundRect addCircle addOval  addArc、arcTo
 * > 闭合path  :  close
 * >  贝塞尔曲线: quadTo、rQuadTo、cubicTo、rCubicTo
 *
 * 3. 如果前面没有path的点，默认是屏幕左上角（0,0）.
 */
public class DrawPathView extends View {
    private static final String TAG = "DrawPathView";

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private Paint paint;
    private int centerX, centerY;
    private float outerRadio, inRadio;

    private void initData() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        centerX = ScreenUtil.getwindth(getContext()) / 2;
        centerY = ScreenUtil.getheight(getContext()) / 2;
        outerRadio = Math.min(centerX, centerY) - 100;
        inRadio = outerRadio / 4;

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawPath(canvas);

        drawPic(canvas);
    }

    // Path.Direction.CCW	counter-clockwise ，沿逆时针方向绘制
    // Path.Direction.CW	clockwise ，沿顺时针方向绘制
    private void drawPic(Canvas canvas) {
        Path path = new Path();
        path.addCircle(centerX,centerY,200, Path.Direction.CCW);
//        canvas.drawTextOnPath("我  是  圆 心 的 送 到 发 送 到",path,30,50,paint); // 园外面

        canvas.drawTextOnPath("我  是  圆 心 的 送 到 发 送 到",path,30,-50,paint); // 园里面
        canvas.drawPath(path,paint);
    }

    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200,200);// 相对屏幕左上角-- 绝对坐标
        path.lineTo(400,500);// 相对屏幕左上角
        path.rLineTo(100,300);//这个是相对前一个点的坐标，

        path.moveTo(440,250);//移动坐标系 起始点
        path.rLineTo(100,100);
        path.lineTo(600,600);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: t");
        return true;
    }
}
