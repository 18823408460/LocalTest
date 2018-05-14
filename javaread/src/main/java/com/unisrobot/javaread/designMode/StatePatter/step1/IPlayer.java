package com.unisrobot.javaread.designMode.StatePatter.step1;

/**
 * Created by Administrator on 2018/5/14.
 */

public interface IPlayer {
        int StatePlaying = 1;
        int StateStop = 2;
        int StatePause = 3;

        //新增
        int StateAD = 4;

        void showAD();

        void play();

        void pause();

        void stop();
}
