package com.unisrobot.robothead.states;


import com.unisrobot.robothead.states.enums.EPlayerState;
import com.unisrobot.robothead.states.enums.EVoiceOpr;
import com.unisrobot.robothead.states.mode.State;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface IMsgHandler {
        void setState(State state);

        void playEnd(EPlayerState ePlayerState, String path, EVoiceOpr eVoiceOpr);
}
