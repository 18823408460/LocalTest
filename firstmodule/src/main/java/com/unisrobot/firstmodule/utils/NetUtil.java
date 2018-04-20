package com.unisrobot.firstmodule.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by Administrator on 2018/4/19.
 */

public class NetUtil {


        /**
         * 获取mac 地址，也需要打开wifi的情况下
         *
         * @param context
         * @return
         */
        public static String getMac(Context context) {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                String macAddress = connectionInfo.getMacAddress();
                return macAddress;
        }


        /**
         * 获取设备的MAC地址，必须打开WIFI，否则获取不到
         *
         * @return
         */
        public static String getMac() {
                String macSerial = "";
                try {
                        Process pp = Runtime.getRuntime().exec(
                                "cat /sys/class/net/wlan0/address");
                        InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                        LineNumberReader input = new LineNumberReader(ir);
                        String line;
                        while ((line = input.readLine()) != null) {
                                macSerial += line.trim();
                        }
                        input.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                if (null != macSerial && macSerial.contains(":")) {
                        macSerial = macSerial.replace(":", "");
                }
                return macSerial;
        }
}
