package com.unisrobot.firstmodule;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.unisrobot.firstmodule.cameraview.ChildActivity;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CrashHandler implements UncaughtExceptionHandler {

        public static final String TAG = "CrashHandler";
        // 系统默认的UncaughtException处理类
        private UncaughtExceptionHandler mDefaultHandler;
        // CrashHandler实例
        private static CrashHandler instance;
        // 程序的Context对象
        private Context mContext;
        // 用来存储设备信息和异常信息
        private ArrayMap<String, String> infos = new ArrayMap<String, String>();
        // 用于格式化日期,作为日志文件名的一部分
        private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

        /**
         * 保证只有一个CrashHandler实例.
         */
        private CrashHandler() {
        }

        /**
         * 获取CrashHandler实例 ,单例模式.
         */
        public static CrashHandler getInstance() {
                if (instance == null) {
                        instance = new CrashHandler();
                }
                return instance;
        }

        /**
         * 初始化 .
         */
        public void init(Context context) {
                mContext = context;
                // 获取系统默认的UncaughtException处理器
                mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
                // 设置该CrashHandler为程序的默认处理器
                Thread.setDefaultUncaughtExceptionHandler(this);
        }

        /**
         * 当UncaughtException发生时会转入该函数来处理 .
         */
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
                if (!handleException(ex) && mDefaultHandler != null) {
                        // 如果用户没有处理则让系统默认的异常处理器来处理
                        Log.e(TAG, "uncaughtException----if");
                        mDefaultHandler.uncaughtException(thread, ex);
                } else {
                        Log.e(TAG, "uncaughtException----else");
                        // 退出程序-----------这里必须调用
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                }
        }

        /**
         * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
         *
         * @param ex
         * @return true:如果处理了该异常信息;否则返回false.
         */
        private boolean handleException(Throwable ex) {
                if (ex == null) {
                        return false;
                }
                // 收集设备参数信息
                collectDeviceInfo(mContext);
                // 使用Toast来显示异常信息
                if (mContext == null){
                        Log.e(TAG, "handleException: context null ");
                }else {

                        //这里是显示不出来的。这里是主线程
//                        Toast.makeText(mContext.getApplicationContext(), "很抱歉,程序出现异常,即将退出", Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "onCreate: "+android.os.Process.myPid() + "    "+Thread.currentThread().getName());

                        // 通过下面的方式重启Activity，会导致Application自动重启。。。。。
                        mContext.startActivity(new Intent(mContext, ChildActivity.class));
                }

                // 保存日志文件
                return true;
        }

        /**
         * 收集设备参数信息 .
         *
         * @param ctx
         */
        public void collectDeviceInfo(Context ctx) {
                try {
                        PackageManager pm = ctx.getPackageManager();
                        PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
                        if (pi != null) {
                                String versionName = pi.versionName == null ? "null" : pi.versionName;
                                String versionCode = pi.versionCode + "";
                                infos.put("versionName", versionName);
                                infos.put("versionCode", versionCode);
                        }
                } catch (NameNotFoundException e) {
                        Log.e(TAG, "an error occured when collect package info", e);
                }
                Field[] fields = Build.class.getDeclaredFields();
                for (Field field : fields) {
                        try {
                                field.setAccessible(true);
                                infos.put(field.getName(), field.get(null).toString());
                                Log.i(TAG, field.getName() + " : " + field.get(null));
                        } catch (Exception e) {
                                Log.e(TAG, "an error occured when collect crash info", e);
                        }
                }
        }

}