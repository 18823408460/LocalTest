package com.uurobot.baseframe.activitys.news;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.uurobot.baseframe.R;

public class SlidingTestActivity extends SlidingFragmentActivity {
        private ImageButton imageButton;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sliding_test);
                imageButton = (ImageButton) findViewById(R.id.imageBtn_home);
                imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                });

                setBehindContentView(R.layout.layout_sliding_menu);
                SlidingMenu slidingMenu = getSlidingMenu();

                slidingMenu.setBehindOffset(800);
                slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
                slidingMenu.setSecondaryMenu(R.layout.sliding_right_menu);
                slidingMenu.setAboveOffset(800); //设置right menu 的宽

                slidingMenu.setSlidingEnabled(true);
                slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }
}
