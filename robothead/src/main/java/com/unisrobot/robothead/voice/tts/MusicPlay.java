package com.unisrobot.robothead.voice.tts;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Administrator on 2018/5/16.
 */

public class MusicPlay extends ITtsEngine {
        private MediaPlayer mediaPlayer;
        private IPlayResult iPlayResult;
        private MediaPlayer.OnCompletionListener onCompletionListener;
        private MediaPlayer.OnErrorListener onErrorListener;
        private MediaPlayer.OnPreparedListener onPreparedListener;
        private String data;

        @Override
        public void init() {
                mediaPlayer = new MediaPlayer();
                onCompletionListener = new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                                if (iPlayResult != null) {
                                        iPlayResult.onPlayEnd(true, data);
                                }
                        }
                };
                onErrorListener = new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                                if (iPlayResult != null) {
                                        iPlayResult.onPlayEnd(false, data);
                                }
                                return false;
                        }
                };
                onPreparedListener = new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                        }
                };
        }

        @Override
        public boolean isPlay() {
                if (mediaPlayer != null) {
                        return mediaPlayer.isPlaying();
                }
                return false;
        }

        @Override
        public void play(String data, IPlayResult ittsResult) {
                try {
                        this.data = data;
                        this.iPlayResult = ittsResult;
                        mediaPlayer.setDataSource(data);
                        mediaPlayer.setOnCompletionListener(onCompletionListener);
                        mediaPlayer.setOnErrorListener(onErrorListener);
                        mediaPlayer.setOnPreparedListener(onPreparedListener);
                } catch (IOException e) {
                        e.printStackTrace();
                        ittsResult.onPlayEnd(false, data);
                }
        }

        @Override
        public void stop() {
                if (mediaPlayer != null) {
                        mediaPlayer.reset();
                }
        }
}
