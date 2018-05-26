package com.uurobot.baseframe.activitys;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/24.
 */

public class BaseFragmentActivity extends BaseActivity {
        protected BaseFragment fromFragment;

        protected void switchFragment(Fragment fromFragment, BaseFragment toFragment) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (fromFragment != null) {
                        fragmentTransaction.hide(fromFragment);
                }
                if (!toFragment.isAdded()) {
                        fragmentTransaction.add(R.id.fl_smartdoctor, toFragment);
                } else {
                        fragmentTransaction.show(toFragment);
                }
                this.fromFragment = toFragment;
                fragmentTransaction.commit();
        }
}
