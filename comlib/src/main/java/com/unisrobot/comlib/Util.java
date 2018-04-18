package com.unisrobot.comlib;

import android.net.wifi.WifiManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2018/4/13.
 */

public class Util {

        /**
         * 打印 wifi bundle的 广播信息
         *
         * @param bundle
         * @return
         */
        public static String printWifiBundle(Bundle bundle) {
                StringBuilder sb = new StringBuilder();
                for (String key : bundle.keySet()) {
                        if (key.equals(WifiManager.EXTRA_WIFI_STATE)) {
                                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
                        }
                        else {
                                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
                        }
                }
                return sb.toString();
        }
}
