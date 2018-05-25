package com.uurobot.baseframe.activitys;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.view.AnimImageView;

import java.io.File;

/**
 * Created by Administrator on 2018/5/24.
 */

public class ImageAnimActivity extends BaseActivity {
        private AnimImageView animImageView;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_image_anim);
                animImageView = findViewById(R.id.img_imageanim_activity);
        }

        boolean start = true;

        public void onClick(View view) {
                if (start) {
                        animImageView.startAnim();
                } else {
                        animImageView.stopAnim();
                }
                start = !start;
        }
}
