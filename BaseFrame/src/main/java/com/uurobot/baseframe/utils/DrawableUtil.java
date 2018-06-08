package com.uurobot.baseframe.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by Administrator on 2018/6/8.
 */

public class DrawableUtil {
        public static Bitmap zoom(Bitmap source, float width, float height) {
                int widthSource = source.getWidth();
                int heightSource = source.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(width / widthSource, height / heightSource);
                Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, (int) width, (int) height, matrix, false);
                return bitmap;
        }

        public static Bitmap circleBitmap(Bitmap bitmap) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Bitmap bitmap1 = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmap1);
                canvas.drawCircle(width / 2, width / 2, width / 2, null);

                Paint paint = new Paint();
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, 0, 0, paint);
                return bitmap1;
        }
}
