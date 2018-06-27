package com.unisrobot.robothead.visualedit;

public class TaskType {

        public static final String MSG_TYPE_BASE_TASK = "MSG_TYPE_BASE_TASK"; // (运行)发送项目内容 == 可视化编程内容的执行

        public static final String MSG_TYPE_COURSE_VOICE = "MSG_TYPE_COURSE_VOICE"; // 播放课程音频
        public static final String MSG_TYPE_COURSETIP_VOICE = "MSG_TYPE_COURSETIP_VOICE"; // 播放课程音频(手动点击的提示语)

        public static final String MSG_TYPE_SOUND_SOUND = "MSG_TYPE_SOUND_SOUND"; // 播放声音音频

        //public String MSG_TYPE_SOUND_RECORDER = "MSG_TYPE_SOUND_RECORDER";
        public static final String MSG_TYPE_WAITE_LEAN = "MSG_TYPE_WAIT_LEAN"; // 倾斜是否达到要求

        public static final String MSG_TYPE_STOP = "MSG_TYPE_STOP"; // 停止运行项目内容

        public static final String PLAY_SOUND_STOP = "PLAY_SOUND_STOP"; // 停止播放声音

        public static final String MSG_TYPE_GRAVITY = "MSG_TYPE_GRAVITY"; // 重力信息

        public static final String MSG_TYPE_DIRECTION = "MSG_TYPE_DIRECTION"; // 头部转动信息

        public static final String MSG_TYPE_CTRLHELP_SOUND = "MSG_TYPE_CTRLHELP_SOUND";

        public static final String MSG_TYPE_CTRLVOICE_COMMAND = "MSG_TYPE_CTRLVOICE_COMMAND";

        public static final String MSG_TYPE_BODY_SENSOR = "MSG_TYPE_BODYSENSOR"; //人体感应开关

        public static final String MSG_ASK_MAPLIST = "MSG_ASK_MAPLIST";  //请求地图数据

        public static final String MSG_ASK_FACE_FILE_LIST = "MSG_ASK_FACEFILELIST"; // 请求人脸文件列表
}