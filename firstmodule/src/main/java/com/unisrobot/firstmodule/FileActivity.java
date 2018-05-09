package com.unisrobot.firstmodule;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 2018/5/9.
 */

public class FileActivity extends Activity {
        private static final String TAG = "FileActivity";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                String path = Environment
                        .getExternalStorageDirectory() + "/ai/u5/uuimage/wakeup/";
                int len = getFilesLength(path + "began/", ".png");
                Log.e(TAG, "onCreate: "+len );
        }

        public static int getFilesLength(String dir, final String filter) {
                int n = 0;
                File file = new File(dir);
                if (file != null) {
                        Log.e(TAG, "getFilesLength: 111" );
                        if (file.isDirectory()){
                                Log.e(TAG, "getFilesLength: " );
                                File[] files = file.listFiles(new FilenameFilter() {

                                        @Override
                                        public boolean accept(File dir, String name) {
                                                // TODO Auto-generated method stub
                                                if (name.endsWith(filter)) {
                                                        return true;
                                                }
                                                return false;
                                        }
                                });

                                if (files != null) {
                                        n = files.length;
                                }
                        }
                }else{
                        Log.e(TAG, "getFilesLength: nulll" );
                }
                return n;
        }
}
