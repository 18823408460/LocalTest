package com.unisrobot.robothead.voice.recognition;

public class Code {


        // 识别中网络异常错误码
        public static final int NET_ERROR = 20002;

        public static final int NET_TIMEOUT = 10114;

        // 混合识别中，既没有匹配本地语法，也没有返回相比场景时
        public static final int NO_MATCH_SCENE = 20005;

        public static final String NoMatchSceneMsg = "无匹配结果";

        public static final String GernalMsg = "这个问题我不懂";

        public static final int NO_VOICE = 10118;

        public static final String NO_VOICE_MSG = "您好像没有说话哦";

        public static final int CREATE_RECODER_ERROR = 20006;

        public static final String CREATE_RECODER_ERROR_MSG = "启动录音失败";
}