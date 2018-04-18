package com.unisrobot.localtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.unisrobot.comlib.wifi.WifiUtilMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/18.
 */

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
        }

        @OnClick(R.id.btn_connet_xiaomi)
        public void connctxiaoMi() {
                wifiUtilMgr.forgetAllWifi();
                wifiUtilMgr.connectWifiAys("Xiaomi_C4BB", "lijie299");
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
