package com.unisrobot.javaread.designMode.StatePatter.step1;

/**
 * Created by Administrator on 2018/5/14.
 */
// 现在如果要新增一个状态。。。则下面每个方法，都需要进行修改，，同时还要增加一个方法。。
public class VideoPlayer implements IPlayer {
        private int currentState; //

        @Override
        public void showAD() {

        }

        @Override
        public void play() {
                switch (currentState) {
                        case StatePlaying:
                                System.out.println("playing");
                                break;
                        case StatePause:
                        case StateStop:
                                System.out.println("play now start");
                                break;
                }
                currentState = StatePlaying;
        }

        @Override
        public void pause() {
                switch (currentState) {
                        case StatePlaying:
                                System.out.println("pause start");
                                break;
                        case StatePause:
                                System.out.println("is pause,nothing to do");
                        case StateStop:
                                System.out.println("is stop ,nothing to do ");
                                break;
                }
                currentState = StatePause;
        }

        @Override
        public void stop() {
                switch (currentState) {
                        case StatePlaying:
                                System.out.println("stop start");
                                break;
                        case StatePause:
                                System.out.println("is pause,nothing to do");
                        case StateStop:
                                System.out.println("is stop ,nothing to do ");
                                break;
                }
                currentState = StateStop;
        }
}
