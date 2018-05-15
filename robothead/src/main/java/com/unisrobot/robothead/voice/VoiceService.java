package com.unisrobot.robothead.voice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/5/15.
 */

public class VoiceService extends Service {
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
                return null;
        }

        @Override
        public void onCreate() {
                super.onCreate();
        }
}
