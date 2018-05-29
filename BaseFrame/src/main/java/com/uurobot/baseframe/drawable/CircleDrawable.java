package com.uurobot.baseframe.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/5/29.
 */

public class CircleDrawable extends Drawable {
        private Bitmap mBitmap;
        private Paint mPaint;
        private int width;

        public CircleDrawable(Bitmap bitmap) {
                this.mBitmap = bitmap;
                mPaint = new Paint();
                mPaint.setAntiAlias(true);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                width = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
                canvas.drawCircle(width / 2, width / 2, width / 2, mPaint);
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
                super.setBounds(left, top, right, bottom);
        }

        @Override
        public int getIntrinsicWidth() {
                return width;
        }

        @Override
        public int getIntrinsicHeight() {
                return width;
        }

        @Override
        public void setAlpha(int alpha) {
                mPaint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
                mPaint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
        }
}
