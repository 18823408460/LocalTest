package com.unisrobot.firstmodule.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by WEI on 2018/4/14.
 */

public class ScreenUtil {

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
}
