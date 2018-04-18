package com.unisrobot.comlib.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

import com.unisrobot.comlib.Util;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class WifiUtilMgr {

        private static final String TAG = "WifiUtilMgr";

        private String ssid; // 当前连接的ssid

        private String pwd;

        private static volatile WifiUtilMgr wifiUtilMgr;

        private Handler handler;

        private Runnable conRunnable;

        private Context context;

        private enum WifiType {
                WIFICIPHER_WEP(1),
                WIFICIPHER_NOPASS(2),
                WIFICIPHER_WPA(3);

                private int mState = 1;

                WifiType(int i) {
                        mState = i;
                }
        }

        private WifiInfo wifiInfo;

        private WifiManager wifiManager;

        private WifiUtilMgr(Context context) {
                this.context = context;
                wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiInfo = wifiManager.getConnectionInfo();
                HandlerThread handlerThread = new HandlerThread("wifiConnectThread");
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper());
                conRunnable = new Runnable() {
                        @Override
                        public void run() {
                                connect(ssid, pwd);
                        }
                };
                regigserBr();
        }

        public static WifiUtilMgr getWifiUtilMgr(Context context) {
                if (wifiUtilMgr == null) {
                        synchronized (WifiUtilMgr.class) {
                                if (wifiUtilMgr == null) {
                                        wifiUtilMgr = new WifiUtilMgr(context);
                                }
                        }
                }
                return wifiUtilMgr;
        }

        /**
         * 忘记所有保存的 wifi
         */
        public void forgetAllWifi() {
                List<WifiConfiguration> configurationList = wifiManager.getConfiguredNetworks();
                if (configurationList != null && configurationList.size() > 0) {
                        for (WifiConfiguration configuration : configurationList) {
                                wifiManager.removeNetwork(configuration.networkId);
                        }
                }
                Log.e(TAG, ".............forgetAllWifi...........");
        }

        /**
         * 获取已经连接的ssid
         *
         * @return
         */
        public String getConnectWifiSsId() {
                String ssid = wifiInfo == null ? null : wifiInfo.getSSID();
                Log.e(TAG, "getConnectWifiSsId: ssid===" + ssid);
                return ssid;
        }

        /**
         * Function: 这个在开机的时候调用。。。
         *
         * @return true:打开成功；false:打开失败<br>
         */
        public boolean openWifi() {
                boolean bRet = true;
                if (!wifiManager.isWifiEnabled()) {
                        bRet = wifiManager.setWifiEnabled(true);
                }
                return bRet;
        }

        public String getMacAddress() {
                return (wifiInfo == null) ? null : wifiInfo.getMacAddress();
        }


        /**
         * 获取wifi的加密方式
         *
         * @param ssid
         * @return
         */
        private WifiType getCipherType(String ssid) {
                List<ScanResult> list = wifiManager.getScanResults();
                for (ScanResult scResult : list) {
                        if (!TextUtils.isEmpty(scResult.SSID) && scResult.SSID.equals(ssid)) {
                                String capabilities = scResult.capabilities;
                                Log.e(TAG, "capabilities=" + capabilities);
                                if (!TextUtils.isEmpty(capabilities)) {
                                        if (capabilities.contains("WPA") || capabilities.contains("wpa")) {
                                                Log.e(TAG, "wpa");
                                                return WifiType.WIFICIPHER_WPA;
                                        }
                                        else if (capabilities.contains("WEP") || capabilities.contains("wep")) {
                                                Log.e(TAG, "wep");
                                                return WifiType.WIFICIPHER_WEP;
                                        }
                                        else {
                                                Log.e(TAG, "no");
                                                return WifiType.WIFICIPHER_NOPASS;
                                        }
                                }
                                else {
                                        return WifiType.WIFICIPHER_NOPASS;
                                }
                        }
                }
                return WifiType.WIFICIPHER_NOPASS;
        }

        public void startScan() {
                // 开启wifi
                openWifi();
                // 开始扫描
                handler.post(new Runnable() {
                        @Override
                        public void run() {
                                Log.e(TAG, "run: scanResults start " );
                                wifiManager.startScan();
                                List<ScanResult> scanResults = wifiManager.getScanResults();
                                Log.e(TAG, "scanResults: "+scanResults );
                        }
                });
        }

        /**
         * 创建一个wifi
         *
         * @param SSID
         * @param Password
         * @param Type
         * @return
         */
        private WifiConfiguration createWifiInfo(String SSID, String Password, WifiType Type) {
                WifiConfiguration config = new WifiConfiguration();
                config.SSID = SSID;
                if (Type == WifiType.WIFICIPHER_NOPASS) { // WIFICIPHER_NOPASS
                        config.preSharedKey = null;
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                }
                if (Type == WifiType.WIFICIPHER_WEP) { // WIFICIPHER_WEP
                        config.preSharedKey = "\"" + Password + "\"";
                        config.hiddenSSID = true;
                        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                        config.wepTxKeyIndex = 0;
                }
                if (Type == WifiType.WIFICIPHER_WPA) { //WIFICIPHER_WPA
                        // 修改之后配置
                        config.preSharedKey = "\"" + Password + "\"";
                }
                return config;
        }

        /**
         * 开始连接网络
         *
         * @param SSID
         * @param Password
         */
        public void connectWifiAys(String SSID, String Password) {
                this.ssid = SSID;
                this.pwd = Password;
                handler.post(conRunnable);
                // 这里要设置超时，如果没有连接成功，需要进行重重连。
        }

        /**
         * 连接指定的网络
         *
         * @param SSID
         * @param Password
         * @return
         */
        private boolean connect(String SSID, String Password) {
                Log.e(TAG, "start connect: ssid===" + SSID);
                WifiType Type = getCipherType(SSID);
                if (!this.openWifi()) {
                        Log.e(TAG, "connect: wifi is close");
                        return false;
                }

                // 状态变成WIFI_STATE_ENABLED的时候才能执行下面的语句
                while (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                        try {
                                // 为了避免程序一直while循环，让它睡个100毫秒在检测……
                                Thread.currentThread();
                                Thread.sleep(100);
                        }
                        catch (InterruptedException ie) {
                        }
                }
                // 查看以前是否也配置过这个网络
                WifiConfiguration tempConfig = this.isExsits(SSID);
                if (tempConfig != null) {
                        wifiManager.removeNetwork(tempConfig.networkId);
                }
                WifiConfiguration wifiConfig = createWifiInfo(SSID, Password, Type);
                // 添加一个新的网络描述为一组配置的网络。
                int netID = wifiManager.addNetwork(wifiConfig);
                // 设置为true,使其他的连接断开
                boolean mConnectConfig = wifiManager.enableNetwork(netID, true);
                wifiManager.saveConfiguration();
                Log.e(TAG, ".................connectting.................Type="+Type);
                return mConnectConfig;
        }

        /**
         * 查看是否已经配置过这个网络
         *
         * @param SSID
         * @return
         */
        private WifiConfiguration isExsits(String SSID) {
                List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
                if (existingConfigs == null || existingConfigs.size() == 0) {
                        return null;
                }
                for (WifiConfiguration existingConfig : existingConfigs) {
                        if (existingConfig.SSID.equals(SSID) || existingConfig.SSID.equals("\"" + SSID + "\"")) {
                                return existingConfig;
                        }
                }
                return null;
        }

        private WifiBR wifiBR;

        private void regigserBr() {
                if (wifiBR == null) {
                        wifiBR = new WifiBR();
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
                intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
                intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
                context.registerReceiver(wifiBR, intentFilter);
        }

        private void unRegisterBr() {
                if (wifiBR != null) {
                        try {
                                context.unregisterReceiver(wifiBR);
                        }
                        catch (Exception e) {

                        }
                }
        }

        private class WifiBR extends BroadcastReceiver {

                @Override
                public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        Log.e(TAG, "onReceive: action==== " + Util.printWifiBundle(intent.getExtras()));
                        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {//wifi state == 打开/ 关闭
                                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                                String state = wifiState == WifiManager.WIFI_STATE_DISABLED ? "关闭" : "打开";
                                Log.e(TAG, "onReceive: wifi state=" + state);
                        }
                        else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {//网络状态== 连接/断开。。。
                                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                                NetworkInfo.State state = info.getState();
                                switch (state) {
                                        case DISCONNECTED://断开
                                                wifiInfo = null;
                                                String extraInfo = info.getExtraInfo();
                                                if (!TextUtils.isEmpty(extraInfo)) {
                                                        boolean isDisConnect = extraInfo.equals(ssid) ? true : false;
                                                        if (isDisConnect) {
                                                                wifiInfo = null;
                                                                Log.e(TAG, "onReceive: DISCONNECTED, 连接过程中被断开了，需要重新连接");
                                                        }
                                                }
                                                break;
                                        case CONNECTED: //连接
                                                String stringExtra = intent.getStringExtra(WifiManager.EXTRA_BSSID);
                                                boolean isConnect = TextUtils.isEmpty(stringExtra) ? false : true;
                                                if (isConnect) {
                                                        Log.e(TAG, "onReceive:  connect success............");
                                                        //网络连接成功，需要更新 wifiInfo 的内容
                                                        wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
                                                }
                                                break;
                                        case CONNECTING:// 连接中,获取连接中的详细信息
                                                NetworkInfo.DetailedState detailedState = info.getDetailedState();
                                                Log.e(TAG, "onReceive: CONNECTING state ==" + detailedState);
                                                break;
                                }
                        }
                        else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
                                int error = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
                                switch (error) {
                                        case WifiManager.ERROR_AUTHENTICATING:
                                                Log.e(TAG, "onReceive:  password is error !");
                                                break;
                                }
                        }
                }
        }


        /**
         * 释放相关的资源。
         */
        public void destroy() {
                handler.removeCallbacks(conRunnable);
                unRegisterBr();
        }

}
