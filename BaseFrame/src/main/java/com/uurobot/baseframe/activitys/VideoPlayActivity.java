package com.uurobot.baseframe.activitys;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.uurobot.baseframe.R;

import java.io.File;

/**
 * Created by Administrator on 2018/5/24.
 */

public class VideoPlayActivity extends AppCompatActivity {
        private VideoView mVideoView;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                hideBottomUIMenu();

                setContentView(R.layout.activity_video_play);
                mVideoView = findViewById(R.id.vv_videopaly);
                playVideo();
        }

        public void playVideo() {
                String fileNames = "dance.mp4";
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileNames);
                if (file.exists()) {
                        Uri uri = Uri.parse(file.getAbsolutePath());
                        mVideoView.setMediaController(new MediaController(this));
                        mVideoView.setVideoURI(uri);
                        mVideoView.start();
                        mVideoView.requestFocus();
                        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                        mVideoView.start();
                                }
                        });
                }
        }

        protected void hideBottomUIMenu() {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        //全屏
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        //隐藏导航栏
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                                uiOptions |= 0x00001000;
                                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                        }
                });

        }
}
