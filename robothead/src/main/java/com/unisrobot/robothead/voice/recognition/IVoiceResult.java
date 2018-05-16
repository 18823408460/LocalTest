package com.unisrobot.robothead.voice.recognition;

import com.iflytek.cloud.ErrorCode;

/**
 * Created by Administrator on 2018/5/16.
 */

public interface IVoiceResult {
        void onResult(String data);

        void onError( String msg);
}
