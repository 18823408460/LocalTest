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
        private static final int playRunnable = 2;
        private PlayHandler playHandler;
        private List<PlayItem> playItemList;
        private IPlayEnd iPlayEnd;
        private PlayItem playItem;
        private int playItemIndex = -1;
        private PlayState playState = PlayState.STOP;
        private static final String TAG = "PlayerKitVer";
        private HandlerThread handlerThread;
        private boolean isStartPlay = false;

        class PlayHandler extends Handler {
                public PlayHandler(Looper looper) {
                        super(looper);
                }

                @Override
                public void handleMessage(Message msg) {
                        switch (msg.what) {
                                case play:
                                        PlayerKitVer playerKitVer = PlayerKitVer.this;
                                        synchronized (PlayerKitVer.this) {
                                                playerKitVer.playItemIndex++;
                                                Log.e(TAG, "handleMessage: playItemIndex=" + playItemIndex);
                                                Log.e(TAG, "handleMessage: playItemList.size()=" + playItemList.size());
                                                if (playerKitVer.playItemList != null && playerKitVer.playItemIndex < playerKitVer.playItemList.size()) {
                                                        playItem = playerKitVer.playItemList.get(playItemIndex);
                                                        play(playItem);
                                                } else {
                                                        playState = PlayState.STOP;
                                                        playerKitVer.playItemIndex = -1;
                                                        if (iPlayEnd != null) {
                                                                iPlayEnd.onPlayEnd();
                                                        }
                                                }
                                        }
                                        break;
                                case playRunnable:
                                        if (playItem != null) {
                                                Runnable nextRun = playItem.getNextRun();
                                                if (nextRun != null) {
                                                        Log.e(TAG, "handleMessage: start run");
                                                        nextRun.run();
                                                        Log.e(TAG, "handleMessage: end run");
                                                }

                                                //这里不加可以吗？
                                                if (PlayState.STOP == playState) {
                                                        Log.e(TAG, "handleMessage: state stop===");
                                                        return;
                                                }
                                                sendEmptyMessage(play);
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
                        isStartPlay = true;
                }
        }

        public PlayState getPlayState() {
                return playState;
        }

        // 这里假设是在主线程执行。。
        public void stop() {
                synchronized (this) {
                        Log.e(TAG, "stop..........." + Thread.currentThread().getName());
                        if (handlerThread != null && isStartPlay) {
                                handlerThread.interrupt();
                                Log.e(TAG, "stop: runnable................");
                        }
                        Log.e(TAG, "stop: start ....................");
                        this.playState = PlayState.STOP;
                        this.playItemIndex = -1;
                        this.iPlayEnd = null;
                        if (this.playItemList != null) {
                                this.playItemList.clear();
                        }
                        playHandler.removeCallbacksAndMessages(null);
                        Log.e(TAG, "stop: end ....................");
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

                        Log.e(TAG, "play: text end=====" + content);

                        //方法一
                        //                        if (nextRun != null){
                        //                                nextRun.run();
                        //                        }
                        // 方法二 ， 和方法一的区别是什么？？？？
                        playHandler.sendEmptyMessage(playRunnable);

                } else {
                        this.playState = PlayState.MUSIC_PLAYING;
                        Log.e(TAG, "play: sound start====" + content);
                        Log.e(TAG, "play: text sound=end====" + content);
                        playHandler.sendEmptyMessage(playRunnable);
                }
        }
}
