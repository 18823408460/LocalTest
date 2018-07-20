package com.uurobot.baseframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/7/20.
 */

public class TestAlphaActivity extends Activity {
        private static final String TAG = TestAlphaActivity.class.getSimpleName();
        RelativeLayout relativeLayout;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test_alpha);
                relativeLayout = findViewById(R.id.ll_test_alpha);
        }

        public void sub(View view) {
                float alpha = relativeLayout.getAlpha(); // (0,1)
                Log.d(TAG, "sub: " + alpha);
                alpha = (float) (alpha - 0.1);
                relativeLayout.setAlpha(alpha);
        }

        public void recovery(View view) {
                relativeLayout.setAlpha(1);
        }

}
