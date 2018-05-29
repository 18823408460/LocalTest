package com.uurobot.baseframe.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/5/29.
 */

public class RoundRectDrawable extends Drawable {
        private Bitmap mBitmap;
        private Paint mPaint;
        private RectF rectf;

        public RoundRectDrawable(Bitmap bitmap) {
                this.mBitmap = bitmap;
                mPaint = new Paint();
                mPaint.setAntiAlias(true);
                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
                canvas.drawRoundRect(rectf, 30, 30, mPaint);
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
                super.setBounds(left, top, right, bottom);
                rectf = new RectF(left, top, right, bottom);
        }

        @Override
        public int getIntrinsicWidth() {
                return mBitmap.getWidth();
        }

        @Override
        public int getIntrinsicHeight() {
                return mBitmap.getHeight();
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
