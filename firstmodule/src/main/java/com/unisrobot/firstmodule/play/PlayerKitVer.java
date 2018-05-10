package com.unisrobot.firstmodule.play;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class PlayerKitVer {
        private static final int play = 1;
        private PlayHandler playHandler;
        private List<PlayItem> playItemList;
        private IPlayEnd iPlayEnd;
        private int playItemIndex = -1;
        private PlayState playState = PlayState.STOP;
        private static final String TAG = "PlayerKitVer";
        private HandlerThread handlerThread;

        class PlayHandler extends Handler {
                public PlayHandler(Looper looper) {
                        super(looper);
                }

                @Override
                public void handleMessage(Message msg) {
                        switch (msg.what) {
                                case play:
                                        PlayerKitVer playerKitVer = PlayerKitVer.this;
                                        synchronized (playerKitVer) {
                                                playerKitVer.playItemIndex++;
                                                if (playerKitVer.playItemList != null && playerKitVer.playItemIndex < playerKitVer.playItemList.size()) {
                                                        PlayItem playItem = playerKitVer.playItemList.get(playItemIndex);
                                                        play(playItem);
                                                } else {
                                                        playerKitVer.playItemIndex = -1;
                                                        if (iPlayEnd != null) {
                                                                iPlayEnd.onPlayEnd();
                                                        }
                                                }
                                        }
                                        break;
                        }
                }

        }

        public PlayerKitVer() {
                handlerThread = new HandlerThread("playThread");
                handlerThread.start();
                playHandler = new PlayHandler(handlerThread.getLooper());
        }

        public void playItems(List<PlayItem> playItems, IPlayEnd iPlayEnd) {
                if (playItems != null && playItems.size() > 0) {
                        this.stop();
                        this.iPlayEnd = iPlayEnd;
                        this.playItemList = playItems;
                        Log.e(TAG, "playItems: send  start play msg");
                        playHandler.sendEmptyMessage(play);
                }
        }

        public void stop() {
                synchronized (this) {
                        Log.e(TAG, "stop: ");
                        this.playState = PlayState.STOP;
                        if (handlerThread != null) {
                                Log.e(TAG, "stop: runnable................");
                        }
                        this.playItemIndex = -1;
                        this.iPlayEnd = null;
                        if (this.playItemList != null) {
                                this.playItemList.clear();
                        }
                        playHandler.removeCallbacksAndMessages(null);
                }

        }

        public void pause() {
                synchronized (this) {

                }
        }

        private void play(PlayItem playItem) {
                ContentType type = playItem.getType();
                String content = playItem.content;
                Runnable nextRun = playItem.getNextRun();
                Log.e(TAG, "start play thread :" + Thread.currentThread().getName());
                if (type == ContentType.TEXT) {
                        this.playState = PlayState.TEXT_PLAYING;
                        Log.e(TAG, "play: text start====" + content);
                        try {
                                Thread.sleep(10000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                                Log.e(TAG, "play: e=" + e);
                        }
                        Log.e(TAG, "play: text end=====" + content);
                        if (nextRun != null) {
                                nextRun.run();
                                Log.e(TAG, "play: run-----end");
                        }
                        playHandler.sendEmptyMessage(play);

                } else {
                        this.playState = PlayState.MUSIC_PLAYING;
                        Log.e(TAG, "play: sound start====" + content);
                        try {
                                Thread.sleep(10000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                                Log.e(TAG, "play: e=" + e);
                        }
                        Log.e(TAG, "play: text sound=end====" + content);
                        if (nextRun != null) {
                                nextRun.run();
                                Log.e(TAG, "play: run-----end");
                        }
                        playHandler.sendEmptyMessage(play);
                }
        }
}
