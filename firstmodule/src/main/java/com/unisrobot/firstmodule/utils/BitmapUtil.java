package com.unisrobot.firstmodule.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2018/5/7.
 */

public class BitmapUtil {

        /**
         * 缩放法压缩。。。
         * scale是 float， 缩放比较精确，但是它容易oom，因为他要重新 创建一个 Bitmap
         * @param bitmap
         * @param newWidth
         * @param newHeight
         * @return
         */
        public static Bitmap zoomBitmap1(Bitmap bitmap, double newWidth, double newHeight) {
                if (bitmap != null) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        Matrix matrix = new Matrix();

                        float scaleWidth = (float) (newWidth / width);
                        float scaleHeight = (float) (newHeight / height);
//                        matrix.setScale(scaleWidth, scaleHeight);
                        matrix.postScale(scaleWidth, scaleHeight);
                        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                        return bitmap1;
                }
                return null;
        }

        /**
         * 采样率压缩。。
         * @param context
         * @param resId
         * @param newWidth
         * @param newHeight
         * @return
         */
        public static Bitmap zoomBitmap2(Context context, int resId, double newWidth, double newHeight) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;// 只获取属性
                //                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options)//这里是返回为null 的
                BitmapFactory.decodeResource(context.getResources(), resId, options);//
                options.inSampleSize = caculateSampleSize(options, newWidth, newHeight);
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeResource(context.getResources(), resId, options);
        }

        /**
         * 质量压缩。。。。
         * @param bitmap
         * @param newWidth
         * @param newHeight
         * @return
         */
        public static Bitmap zoomBitmap3(Bitmap bitmap, double newWidth, double newHeight) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //  quality==100 表示不压缩
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                if (bytes.length/1024 > 1024){// 如果超过1M.
                        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
                }
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

                ///??????????
                return null ;
        }


        private static int caculateSampleSize(BitmapFactory.Options options, double newWidth, double newHeight) {
                int outWidth = options.outWidth;
                int outHeight = options.outHeight;
                int sampleSize = 1;
                if (outHeight > newHeight || outWidth > newWidth) {
                        if (outHeight > outWidth) {
                                sampleSize = Math.round((float) outHeight / (float) newHeight);
                        } else {
                                sampleSize = Math.round((float) outWidth / (float) newWidth);
                        }
                }
                return sampleSize;
        }
}
