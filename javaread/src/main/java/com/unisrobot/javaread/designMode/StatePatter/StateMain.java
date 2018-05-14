package com.unisrobot.javaread.designMode.StatePatter;


        import com.unisrobot.javaread.designMode.StatePatter.step2.VideoPlayer;

/**
 * Created by Administrator on 2018/5/14.
 */

public class StateMain {

        public static void main(String[] args) {

                VideoPlayer videoPlayer = new VideoPlayer();
                videoPlayer.request(0);
        }
}
