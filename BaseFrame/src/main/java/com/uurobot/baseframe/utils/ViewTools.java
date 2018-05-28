package com.uurobot.baseframe.utils;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by WEI on 2018/5/28.
 */

public class ViewTools {

    public static void hideView(RelativeLayout layout2) {
        // 旋转默认的原点在 view 的 左上角
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, layout2.getWidth() / 2, layout2.getHeight());
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        layout2.startAnimation(rotateAnimation);
    }

    public static void showView(RelativeLayout layout2) {
        RotateAnimation rotateAnimation = new RotateAnimation(180, 360, layout2.getWidth() / 2, layout2.getHeight());
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        layout2.startAnimation(rotateAnimation);
    }
}
