package com.uurobot.baseframe.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Created by Administrator on 2018/5/25.
 */

public class BaseDialog extends DialogFragment {


        @Override
        public void onStart() {
                super.onStart();
                hideBottomUIMenu();
        }

        protected void hideBottomUIMenu() {
                getDialog().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                getDialog().getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        //全屏
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        //隐藏导航栏
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                                uiOptions |= 0x00001000;
                                getDialog().getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                        }
                });

        }
}
