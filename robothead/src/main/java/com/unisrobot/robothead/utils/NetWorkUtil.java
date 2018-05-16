package com.unisrobot.robothead.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2018/5/16.
 */

public class NetWorkUtil {

        /**
         * 检查网络.
         *
         * @param context
         * @return
         */
        public static boolean checkNetworkConnection(Context context) {
                final ConnectivityManager connMgr = (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if ((wifi.isConnected() && wifi.isAvailable()) || (mobile.isConnected() && mobile.isAvailable())) {
                        return true;
                } else {
                        return false;
                }
        }
}
