package com.unisrobot.firstmodule.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

/**
 * Created by WEI on 2018/4/14.
 */

public class ScreenUtil {
        public static int dip2px(Context context, float dpValue) {
                float scale = context.getResources().getDisplayMetrics().density;
                return (int) (dpValue * scale + 0.5F);
        }

        public static int px2dip(Context context, float pxValue) {
                float scale = context.getResources().getDisplayMetrics().density;
                return (int) (pxValue / scale + 0.5F);
        }

        /**
         * 获取屏幕密度，不推荐使用了了
         *
         * @param activity
         * @return
         */
        public static int getwindth(Activity activity) {
                return activity.getWindowManager().getDefaultDisplay().getWidth();
        }

        public static int getwindth1(Activity activity) {
                Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
                DisplayMetrics dm = new DisplayMetrics();
                defaultDisplay.getMetrics(dm);
                float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）-逻辑像素密度
                int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）- 屏幕像素密度
                int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
                int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
                int screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：720px）
                int screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：1280px）
                return screenWidth;
        }

        public static int getwindth(Context context) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                return widthPixels;
        }

        public static int getheight(Context context) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                int heightPixels = displayMetrics.heightPixels;
                return heightPixels;
        }


        /**
         * dp转px
         *
         * @param context
         * @param dpVal
         * @return
         */
        public static int dp2px(Context context, float dpVal) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        dpVal, context.getResources().getDisplayMetrics());
        }

        /**
         * sp转px
         *
         * @param context
         * @param spVal
         * @return
         */
        public static int sp2px(Context context, float spVal) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        spVal, context.getResources().getDisplayMetrics());
        }

        /**
         * px转dp
         *
         * @param context
         * @param pxVal
         * @return
         */
        public static float px2dp(Context context, float pxVal) {
                final float scale = context.getResources().getDisplayMetrics().density;
                return (pxVal / scale);
        }

        /**
         * px转sp
         *
         * @param pxVal
         * @param pxVal
         * @return
         */
        public static float px2sp(Context context, float pxVal) {
                return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
        }
}
