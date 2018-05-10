package com.unisrobot.firstmodule;

import android.app.Activity;
import android.content.ContentProvider;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 2018/5/9.
 */

public class FileActivity extends Activity {
        private static final String TAG = "FileActivity";
        private Button button ;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_crash);

                button = findViewById(R.id.crash_btn);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                               int a = 1 ;
                               int b = 0 ;
                               int c = a/b ;
                        }
                });
        }
}
