package com.uurobot.baseframe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/29.
 */

public class DaoyingView extends View {
        private Bitmap bitmap;
        private Bitmap shader;
        // 描边的宽度
        private int border = 3;

        public DaoyingView(Context context) {
                this(context, null);
        }

        public DaoyingView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public DaoyingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initTypeValue(attrs);
                initData();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension(bitmap.getWidth() + border * 2, bitmap.getHeight() + shader.getHeight() / 2 + border * 2);
        }

        public void initData() {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mj);
                shader = BitmapFactory.decodeResource(getResources(), R.drawable.projection);

                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStrokeWidth(3);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.parseColor("#ff0000"));
        }

        Paint paint;

        public void initTypeValue(AttributeSet attrs) {

        }

        @Override
        protected void onDraw(Canvas canvas) {
                //                canvas.drawBitmap(createReflectedImage(bitmap), 0, bitmap.getHeight(), null);

                canvas.drawBitmap(shader, bitmap.getWidth() / 2 - shader.getWidth() / 2 + border, bitmap.getHeight() - shader.getHeight() / 2 + border, null);
                canvas.drawBitmap(bitmap, border, border, null);

                if (drawBorder) {
                        canvas.drawCircle(bitmap.getWidth() / 2 + border, bitmap.getHeight() / 2 + border, bitmap.getWidth() / 2 + border, paint);
                }
        }


        private boolean drawBorder;

        public void drawBorder(boolean draBorder) {
                this.drawBorder = draBorder;
        }

        /**
         * 根据原始图片 创建一个 倒影。
         *
         * @param originalImage
         * @return
         */
        private Bitmap createReflectedImage(Bitmap originalImage) {
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();
                Matrix matrix = new Matrix();
                // 实现图片翻转90度
                matrix.preScale(1, -1);
                // 创建倒影图片（是原始图片的同样大小）
                Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, 0,
                        width, height, matrix, false);

                // 创建倒影绘制区域
                Bitmap drawBitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.ARGB_8888);
                // 创建画布
                Canvas canvas = new Canvas(drawBitmap);
                // 把倒影图片画到画布上
                canvas.drawBitmap(reflectionImage, 0, 0, null);


                Paint shaderPaint = new Paint();
                // 创建线性渐变LinearGradient对象
                // 这个值控制渐变的起始值
                LinearGradient shader = new LinearGradient(0, 0, 0, getHeight(), 0xffffffff,
                        0x00ffffff, Shader.TileMode.CLAMP);
                shaderPaint.setShader(shader);
                shaderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                // 画布画出反转图片大小区域，然后把渐变效果加到其中，就出现了图片的倒影效果。
                canvas.drawRect(0, 0, width, drawBitmap.getHeight(), shaderPaint);
                return drawBitmap;
        }
}
