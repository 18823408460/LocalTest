package com.unisrobot.firstmodule.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

/**
 * Created by WEI on 2018/4/15.
 */

public class AnimUtils {
    public static void slideToUp(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        translateAnimation.setDuration(4000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }
}
