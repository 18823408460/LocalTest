package com.uurobot.baseframe.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/6/5.
 */
//Camera内部机制实际上还是opengl，不过大大简化了使用

/**
 * Matrix，它是Android提供的一个矩阵工具类，是一个3x3的矩阵，一般要实现2D的旋转(绕z轴旋转)、缩放、平移、倾斜用这个作用于画布
 * <p>
 * camera位于坐标点（0,0），也就是视图的左上角
 */
public class CameraView extends View {
        private Camera cameraDraw;
        private Matrix matrix;
        private Paint paint;


        public CameraView(Context context) {
                this(context, null);
        }

        public CameraView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                cameraDraw = new Camera();
                matrix = new Matrix();
                paint = new Paint();

                setBackgroundColor(Color.parseColor("#44ffffff"));
                paint.setAntiAlias(true);
                paint.setColor(Color.parseColor("#FF0000"));
                paint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(Canvas canvas) {
                matrix.reset();
                cameraDraw.save();
//                cameraDraw.translate(200, -200, 0);  //   左手坐标
                cameraDraw.rotateX(60);
                cameraDraw.getMatrix(matrix);
                cameraDraw.restore();
                canvas.concat(matrix);
                canvas.drawCircle(200, 200, 200, paint);



                matrix.reset();
                cameraDraw.save();
                cameraDraw.rotateX(60);
                cameraDraw.getMatrix(matrix);
                cameraDraw.restore();
                canvas.concat(matrix);
                canvas.drawCircle(400, 400, 200, paint);
        }
}
