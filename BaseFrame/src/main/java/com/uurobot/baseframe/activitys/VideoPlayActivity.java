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
import com.uurobot.baseframe.base.BaseFragment;

import java.io.File;

/**
 * Created by Administrator on 2018/5/24.
 */

public class VideoPlayActivity extends BaseActivity {
        private VideoView mVideoView;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);


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


}
