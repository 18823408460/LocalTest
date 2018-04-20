package com.unisrobot.localtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.unisrobot.comlib.log.AndroidLogAdapter;
import com.unisrobot.comlib.log.DiskLogAdapter;
import com.unisrobot.comlib.log.Logger;
import com.unisrobot.comlib.log.PrettyFormatStrategy;
import com.unisrobot.comlib.log.TxtFormatStrategy;
import com.unisrobot.comlib.wifi.WifiUtilMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/18.
 */


//    StackTraceElement 打印堆栈信息

public class WifiTestActivity extends AppCompatActivity {

        @BindView(R.id.btn_connet_xiaomi)
        Button btnConnetXiaomi;

        @BindView(R.id.btn_connet_my)
        Button btnConnetMy;

        @BindView(R.id.btn_getssid)
        Button btnGetssid;

        WifiUtilMgr wifiUtilMgr;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_wifitest);
                ButterKnife.bind(this);
                wifiUtilMgr = WifiUtilMgr.getWifiUtilMgr(this);
                wifiUtilMgr.startScan();

//                Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().
//                        tag(getString(R.string.app_name)).build()));
                //把log存到本地
                Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder().
                        tag(getString(R.string.app_name)).build(getPackageName(), getString(R.string.app_name))));


        }

        @OnClick(R.id.btn_connet_xiaomi)
        public void connctxiaoMi() {
                wifiUtilMgr.forgetAllWifi();
                wifiUtilMgr.connectWifiAys("Xiaomi_C4BB", "lijie299");
                for (int i = 0; i < 40; i++) {
                        Logger.d("sssssssss");
                        Logger.e("sfds","sdfsdf");
                }

        }

        @OnClick(R.id.btn_connet_my)
        public void connctxiaoMy() {
                wifiUtilMgr.forgetAllWifi();
                wifiUtilMgr.connectWifiAys("uurobotyin", "987654321");
        }

        @OnClick(R.id.btn_getssid)
        public void getssid() {
                wifiUtilMgr.getConnectWifiSsId();
        }
}
