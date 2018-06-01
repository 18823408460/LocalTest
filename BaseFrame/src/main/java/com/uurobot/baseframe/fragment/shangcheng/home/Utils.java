package com.uurobot.baseframe.fragment.shangcheng.home;

import android.view.View;

/**
 * Created by Administrator on 2018/6/1.
 */

public class Utils {

        private static float scaleFactor = 0.25f;
        private static float relative = 0.3f;

        public static float getTransLation(float position) {
                int direction = position > 0 ? -1 : 1;
                float scaleX = getScale(position);
                if (position <= 0 && position > -1) {
                        return position * (scaleX * (1 - relative) - 1);
                } else if (position >= 0 && position < 1) {
                        return position * (scaleX * (1 - relative) - 1);
                } else {
                        return -direction * (scaleX * (1 - relative) - 1) + getTransLation(position + direction);
                }
        }

        public static float getScale(float position) {
                return (1 - scaleFactor) + scaleFactor * (1 - Math.abs(position));
        }

        public static float getPageScale(float position) {
                float scale = getScale(position);
                scale = scale == 0 ? 0.0001f : scale;
                return Math.abs(scale);
        }

        public static void transLationX(View page, float position) {
                float transLationX = getTransLation(position) * page.getWidth()/2;
                page.setTranslationX(transLationX);
                float abs = Math.abs(position);
                int tan = (int) ((abs * 600 / 3.0) * Math.tan(26 * Math.PI / 180));

                page.setTranslationY(-tan);
        }

        public static void setPivotXY(View page, float position) {
                if (position > 0) {//右边的以右边边界线中点作为中心点
                        page.setPivotX(page.getWidth());
                        page.setPivotY(page.getHeight() / 2);
                } else {//左边的以左边边界中心点作为中心点
                        page.setPivotX(0);
                        page.setPivotY(page.getHeight() / 2);
                }
        }
}
