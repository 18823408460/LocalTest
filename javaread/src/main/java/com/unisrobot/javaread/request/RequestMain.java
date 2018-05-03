package com.unisrobot.javaread.request;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RequestMain {
        public static void main(String[] args) {
                SerialportMgr.getSerialportMgr().queryData(new IResultLisenter() {
                        @Override
                        public void onResponse(String data) {
                                System.out.println("  data1===" + data);
                                SerialportMgr.getSerialportMgr().queryData(new IResultLisenter() {
                                        @Override
                                        public void onResponse(String data) {
                                                System.out.println("  data2==== " + data);
                                        }
                                });
                        }
                });

        }
}
