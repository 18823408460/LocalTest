package com.unisrobot.javaread.designMode.StatePatter.step2;

/**
 * Created by Administrator on 2018/5/14.
 */
//对于状态的增加，所带来的修改成本比没用状态机模式要小的多，特别对于状态更多的程序
public class StoppedState extends PlayerState {

        public StoppedState(IPlayer player) {
                super(player);
        }

        @Override
        public void handle(int action) {
                switch (action) {
                        case PlayingState.PLAY_OR_PAUSE:
                                mPlayer.palyVedio();
                                mPlayer.setState(new PlayingState(mPlayer));
                                break;
                        default:
                                throw new IllegalArgumentException("ERROE ACTION:" + action + ",current state:" + this.getClass().getSimpleName());
                }
        }
}

