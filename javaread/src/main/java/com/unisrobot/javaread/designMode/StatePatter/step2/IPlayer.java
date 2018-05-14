package com.unisrobot.javaread.designMode.StatePatter.step2;

/**
 * Created by Administrator on 2018/5/14.
 */
public abstract class IPlayer {

        public abstract void request(int flag);

        public abstract void setState(PlayerState state);

        public abstract void palyVedio();

        public abstract void pause();

        public abstract void stop();

        public abstract void showAD();
}
