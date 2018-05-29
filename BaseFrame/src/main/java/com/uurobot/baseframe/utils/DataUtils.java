package com.uurobot.baseframe.utils;

import android.graphics.Bitmap;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataUtils {

        public static float floatTranslate(float data) {
                DecimalFormat fnum = new DecimalFormat("##0.0");
                return Float.parseFloat(fnum.format(data));
        }

        public static float getBitmapMaxWidth(Bitmap bitmap) {
                return Math.max(bitmap.getWidth(), bitmap.getHeight());
        }

}
