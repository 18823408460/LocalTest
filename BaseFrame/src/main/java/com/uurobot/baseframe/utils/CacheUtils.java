package com.uurobot.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WEI on 2018/6/2.
 */

public class CacheUtils {
    public static final String spName = "UU";

    public static String getString(Context mContext, String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }
}
