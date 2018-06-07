package com.uurobot.baseframe.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.uurobot.baseframe.app.MainApp;

/**
 * Created by Administrator on 2018/5/26.
 */

public class SizeUtil {

        /**
         * 推荐使用这个
         *
         * @return
         */
        public static Point getScreen() {
                Context context = MainApp.getContext();
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                int heightPixels = displayMetrics.heightPixels;
                int widthPixels = displayMetrics.widthPixels;
                return new Point(widthPixels, heightPixels);
        }

        public static Point getScreen2() {
                Context context = MainApp.getContext();
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics outMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(outMetrics);
                int widthPixels = outMetrics.widthPixels;
                int heightPixels = outMetrics.heightPixels;
                return new Point(widthPixels, heightPixels);
        }

        /**
         * dp转换成px
         */
        public static int dp2px(Context context, float dpValue) {
                float scale = context.getResources().getDisplayMetrics().density;
                return (int) (dpValue * scale + 0.5f);
        }

        /**
         * px转换成dp
         */
        public static int px2dp(Context context, float pxValue) {
                float scale = context.getResources().getDisplayMetrics().density;
                return (int) (pxValue / scale + 0.5f);
        }

        /**
         * sp转换成px
         */
        public static int sp2px(Context context, float spValue) {
                float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
                return (int) (spValue * fontScale + 0.5f);
        }

        /**
         * px转换成sp
         */
        public static int px2sp(Context context, float pxValue) {
                float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
                return (int) (pxValue / fontScale + 0.5f);
        }

        //---------------------------------------------------------------------------//
        public static int dp2px(Context context, int dpValue) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
        }

        public static int sp2px(Context context, int spValue) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
        }
}
