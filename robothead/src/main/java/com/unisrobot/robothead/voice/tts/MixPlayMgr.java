package com.unisrobot.robothead.voice.tts;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.unisrobot.robothead.MainApp;
import com.unisrobot.robothead.voice.tts.play.ContentType;
import com.unisrobot.robothead.voice.tts.play.PlayItem;
import com.unisrobot.robothead.voice.tts.play.PlayState;

import java.util.List;


/**
 * Created by Administrator on 2018/5/16.
 */

public class MixPlayMgr {
        private volatile static MixPlayMgr mixPlayMgr;
        private ITtsEngine iTtsEngine;
        private ITtsEngine iMusicEngine;
        private static final int play = 1;
        private static final int playRunnable = 2;
        private PlayHandler playHandler;
        private List<PlayItem> playItemList;
        private IPlayResult iPlayEnd;
        private PlayItem playItem;
        private int playItemIndex = -1;
        private PlayState playState = PlayState.STOP;
        private static final String TAG = "MixPlayMgr";
        private HandlerThread handlerThread;
        private boolean isStartPlay = false;

        private class PlayHandler extends Handler {
                public PlayHandler(Looper looper) {
                        super(looper);
                }

                @Override
                public void handleMessage(Message msg) {
                        switch (msg.what) {
                                case play:
                                        MixPlayMgr playerKitVer = MixPlayMgr.this;
                                        synchronized (MixPlayMgr.this) {
                                                playerKitVer.playItemIndex++;
                                                if (playerKitVer.playItemList != null && playerKitVer.playItemIndex < playerKitVer.playItemList.size()) {
                                                        playItem = playerKitVer.playItemList.get(playItemIndex);
                                                        play(playItem);
                                                } else {
                                                        playState = PlayState.STOP;
                                                        playerKitVer.playItemIndex = -1;
                                                        if (iPlayEnd != null) {
                                                                iPlayEnd.onPlayEnd(true, playItem.content);
                                                        }
                                                }
                                        }
                                        break;
                                case playRunnable:
                                        if (playItem != null) {
                                                Runnable nextRun = playItem.getNextRun();
                                                if (nextRun != null) {
                                                        nextRun.run();
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


        public void playItems(List<PlayItem> playItems, IPlayResult iPlayEnd) {
                if (playItems != null && playItems.size() > 0) {
                        this.stop();
                        this.iPlayEnd = iPlayEnd;
                        this.playItemList = playItems;
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
                        if (handlerThread != null && isStartPlay) {
                                handlerThread.interrupt();
                        }
                        this.playState = PlayState.STOP;
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
                Log.e(TAG, "play: "+playItem );
                ContentType type = playItem.getType();
                String content = playItem.content;
                if (type == ContentType.TEXT) {
                        this.playState = PlayState.TEXT_PLAYING;
                        iTtsEngine.play(content, new IPlayResult() {
                                @Override
                                public void onPlayEnd(boolean state, String pathOrData) {
                                        Log.e(TAG, "play: text end=====" + pathOrData);
                                        playHandler.sendEmptyMessage(playRunnable);
                                }
                        });


                } else {
                        this.playState = PlayState.MUSIC_PLAYING;
                        iMusicEngine.play(content, new IPlayResult() {
                                @Override
                                public void onPlayEnd(boolean state, String pathOrData) {
                                        Log.e(TAG, "play: text sound=end====" + pathOrData);
                                        playHandler.sendEmptyMessage(playRunnable);
                                }
                        });

                }
        }


        public static MixPlayMgr getMixPlayMgr() {
                if (mixPlayMgr == null) {
                        synchronized (MixPlayMgr.class) {
                                if (mixPlayMgr == null) {
                                        mixPlayMgr = new MixPlayMgr();
                                }
                        }
                }
                return mixPlayMgr;
        }

        private MixPlayMgr() {
                handlerThread = new HandlerThread("playVoiceThread");
                handlerThread.start();
                playHandler = new PlayHandler(handlerThread.getLooper());
                iTtsEngine = new IFlytekTtsEngine(MainApp.getContext());
                iTtsEngine.init();
                iMusicEngine = new MusicPlay();
                iMusicEngine.init();
        }

}
