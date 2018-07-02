package com.unisrobot.robothead.visualedit.nodebean.language;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

import java.util.Random;

/**
 * Created by WEI on 2018/6/27.
 */

public class MusicBean extends Node<Long> {
        private static final String VISUAL_VOICE_DIR = "/ai/ai05res/a1/res/audio/visual/";
        @SuppressLint("NewApi")
        static ArrayMap<String, String> voiceMap = new ArrayMap<>();
        private String path;
        private long time;

        static {
                voiceMap.put("马", VISUAL_VOICE_DIR + "horse.mp3");
                voiceMap.put("海豚", VISUAL_VOICE_DIR + "dolphin.mp3");
                voiceMap.put("猫咪", VISUAL_VOICE_DIR + "cat.mp3");
                voiceMap.put("狮子", VISUAL_VOICE_DIR + "lion.mp3");
                voiceMap.put("小狗", VISUAL_VOICE_DIR + "dog.mp3");

                voiceMap.put("飞机", VISUAL_VOICE_DIR + "plane.mp3");
                voiceMap.put("火车", VISUAL_VOICE_DIR + "train.wav");
                voiceMap.put("警报", VISUAL_VOICE_DIR + "alert.wav");
                voiceMap.put("喇叭", VISUAL_VOICE_DIR + "trumpet.wav");
                voiceMap.put("赛车", VISUAL_VOICE_DIR + "car.wav");

                voiceMap.put("哔哩", VISUAL_VOICE_DIR + "bili.mp3");
                voiceMap.put("嘟嘟", VISUAL_VOICE_DIR + "dudu.mp3");
                voiceMap.put("激光", VISUAL_VOICE_DIR + "jiguang.mp3");
                voiceMap.put("略略", VISUAL_VOICE_DIR + "luelue.mp3");
                voiceMap.put("吱吱", VISUAL_VOICE_DIR + "zhizhi.mp3");
                voiceMap.put("滋滋", VISUAL_VOICE_DIR + "zizi.mp3");

                voiceMap.put("嗨", VISUAL_VOICE_DIR + "hi.mp3");
                voiceMap.put("开心", VISUAL_VOICE_DIR + "happy.mp3");
                voiceMap.put("你好", VISUAL_VOICE_DIR + "hello.mp3");
                voiceMap.put("你好2", VISUAL_VOICE_DIR + "hello2.mp3");
                voiceMap.put("再见", VISUAL_VOICE_DIR + "bye.mp3");
        }

        public static MusicBean getBean(VpJsonBean.NodeDataBase nodeData) {
                MusicBean musicBean = new MusicBean();
                String content = nodeData.Args.get(0).Content;
                String event = nodeData.Event;
                String voicePath = getVoicePath(event, content);
                musicBean.path = voicePath;
                musicBean.time = getTime("6");
                musicBean.setRobotMsgType(RobotMsgType.PlayEnd);
                musicBean.setRobotMsgType(RobotMsgType.Timer);
                return musicBean;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        private static String getRandom(int bound, int index) {
                int nextInt = new Random().nextInt(bound) + index;
                String s = voiceMap.keyAt(nextInt);
                return voiceMap.get(s);
        }

        private static String getVoicePath(String type, String content) {
                String voicePath = null;
                switch (type) {
                        case "动物":
                                if ("随机".equals(content)) {
                                        voicePath = getRandom(5, 0);
                                } else {
                                        voicePath = voiceMap.get(content);
                                }
                                break;
                        case "交通":
                                if ("随机".equals(content)) {
                                        voicePath = getRandom(5, 5);
                                } else {
                                        voicePath = voiceMap.get(content);
                                }
                                break;
                        case "趣味":
                                if ("随机".equals(content)) {
                                        voicePath = getRandom(6, 10);
                                } else {
                                        voicePath = voiceMap.get(content);
                                }
                                break;
                        case "问候":
                                if ("随机".equals(content)) {
                                        voicePath = getRandom(5, 16);
                                } else {
                                        voicePath = voiceMap.get(content);
                                }
                                break;
                        case "我的声音":
                                break;
                        default:
                                break;
                }
                return voicePath;
        }

        @Override
        public Long exeNode() {
                if (!TextUtils.isEmpty(path)) {
                        //            MsgSendUtils.sendStringMsg(MsgType.PLAY_SOUND, voicePath);
                }
                return time;
        }
}
