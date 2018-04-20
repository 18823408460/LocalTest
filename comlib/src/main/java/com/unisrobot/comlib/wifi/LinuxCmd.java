package com.unisrobot.comlib.wifi;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Administrator on 2018/4/19.
 */

public class LinuxCmd {
        private static final String TAG = "LinuxCmd";

        /**
         * ping网络，这个方法必须在子线程调用
         *
         * @param str ==  ip地址，，
         * @return
         */
        public static boolean Ping(String str) {
                boolean resault = false;
                Process p;
                try {
                        //ping -c 3 -w 100  中  ，-c 是指ping的次数 3是指ping 3次 ，-w 100  以秒为单位指定超时间隔，是指超时时间为100秒
                        p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + str);
                        int status = p.waitFor();
                        InputStream input = p.getInputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(input));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = in.readLine()) != null) {
                                buffer.append(line);
                        }
                        System.out.println("Return ============" + buffer.toString());
                        if (status == 0) {
                                resault = true;
                        } else {
                                resault = false;
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return resault;
        }

        public static boolean is5Mic() {
                String sysProperty = getSysProperty("ro.hardware.version");
                if ("".equals(sysProperty)) {
                        return true;
                }
                return false;
        }


        public static String getSysProperty(String key) {
                Process process;
                String property = null;
                try {
                        Process exec = Runtime.getRuntime().exec("getprop" + key);
                        InputStreamReader inputStreamReader = new InputStreamReader(exec.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        property = bufferedReader.readLine();
                } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "getSysProperty: IOException=" + e);
                }
                return property;
        }


        /**
         * 执行adb 命令
         *
         * @param cmd
         * @return
         */
        public static boolean execCmd(String cmd) {
                boolean result = false;
                Process process = null;
                OutputStream out = null;
                DataOutputStream dataOutputStream = null;
                try {
                        process = Runtime.getRuntime().exec("su");
                        out = process.getOutputStream();
                        dataOutputStream = new DataOutputStream(out);

                        dataOutputStream.writeBytes(cmd + "\n");
                        // 提交命令
                        dataOutputStream.flush();
                        dataOutputStream.writeBytes("exit\n");
                        dataOutputStream.flush();
                        byte b[] = new byte[512];
                        int r = 0;
                        StringBuffer sbf = new StringBuffer();
                        while ((r = process.getErrorStream().read(b, 0, 512)) > -1) {
                                sbf.append(new String(b, 0, r));
                        }
                        byte[] dd = new byte[512];
                        StringBuilder stringBuilder = new StringBuilder();
                        int read = process.getInputStream().read(dd);
                        while (read > -1) {
                                String s = new String(dd, 0, read);
                                stringBuilder.append(s);
                                Log.e(TAG, "execCmd11: \n" + s);
                                read = process.getInputStream().read(dd);
                        }
                        String sbftostring = sbf.toString().trim();
                        Log.e(TAG, "execCmd: \n" + stringBuilder);
                        Log.println(Log.DEBUG, TAG, "execCmd:\n" + stringBuilder);
                        if (sbftostring != null && sbftostring.contains("Failure")) {
                                result = false;
                        } else {
                                result = true;
                        }

                        dataOutputStream.close();
                        out.close();
                } catch (Exception e) {
                        e.printStackTrace();
                        result = false;
                        Log.e(TAG, "execCmd: " + e);
                }
                return result;
        }
}
