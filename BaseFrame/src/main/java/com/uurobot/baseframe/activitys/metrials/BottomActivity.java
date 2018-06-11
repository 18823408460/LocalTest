package com.uurobot.baseframe.activitys.metrials;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.uurobot.baseframe.R;

import java.lang.reflect.Field;

/**
 * BottomNavigationView
 * 1. 必须使用 主题 ：  android:theme="@style/Theme.AppCompat"
 * 2. item 不能超过5 个
 * <p>
 * 3 。
 */
public class BottomActivity extends Activity {
        private TextView mTextMessage;
        private BottomNavigationView bottomNavigationView;
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                                case R.id.navigation_home:
                                        mTextMessage.setText(R.string.title_home);
                                        return true;
                                case R.id.navigation_dashboard:
                                        mTextMessage.setText(R.string.title_dashboard);
                                        return true;
                                case R.id.navigation_notifications:
                                        mTextMessage.setText(R.string.title_notifications);
                                        return true;
                        }
                        return false;
                }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_bottom);
                bottomNavigationView = findViewById(R.id.navigation);
                disableShiftMode(bottomNavigationView);
        }

        /**
         * @param view
         */
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
                BottomNavigationMenuView menuView = (BottomNavigationMenuView)
                        view.getChildAt(0);
                try {
                        Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                        shiftingMode.setAccessible(true);
                        shiftingMode.setBoolean(menuView, false);
                        shiftingMode.setAccessible(false);
                        for (int i = 0; i < menuView.getChildCount(); i++) {
                                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i); //noinspection RestrictedApi
                                item.setShiftingMode(false); // set once again checked value, so view will be updated //noinspection RestrictedApi
                                item.setChecked(item.getItemData().isChecked());
                        }
                } catch (NoSuchFieldException e) {
                        Log.e("BNVHelper", "Unable to get shift mode field", e);
                } catch (IllegalAccessException e) {
                        Log.e("BNVHelper", "Unable to change value of shift mode", e);
                }
        }


}
