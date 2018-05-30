package com.uurobot.baseframe.utils;

import android.animation.ObjectAnimator;
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

        public static void showViewObj(RelativeLayout layout2) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layout2, "rotation", 180, 360);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                layout2.setPivotX(layout2.getWidth() / 2);
                layout2.setPivotY(layout2.getHeight());
        }


        public static void hideViewObj(RelativeLayout layout2) {
                // ofXX 和 属性的参数有关系
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layout2, "rotation", 0, 180);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                layout2.setPivotX(layout2.getWidth() / 2);
                layout2.setPivotY(layout2.getHeight());
        }
}
