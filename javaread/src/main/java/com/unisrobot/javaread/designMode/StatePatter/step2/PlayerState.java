package com.unisrobot.javaread.designMode.StatePatter.step2;

/**
 * Created by Administrator on 2018/5/14.
 */

public abstract class PlayerState {
        public final static int PLAY_OR_PAUSE = 0;
        public final static int STOP = 1;
        protected IPlayer mPlayer;

        public PlayerState(IPlayer player) {
                this.mPlayer = player;
        }

        public abstract void handle(int action);

        @Override
        public String toString() {
                return "current state:" + this.getClass().getSimpleName();
        }
}
