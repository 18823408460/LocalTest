package com.uurobot.baseframe.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentOne;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentThree;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorActivity extends FragmentActivity {
        private List<BaseFragment> baseFragmentList;
        private BaseFragment fromFragment;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_smartdoctor);
                initView();
                initData();
        }

        private void initData() {
                baseFragmentList = new ArrayList<>();
                baseFragmentList.add(new SmartDoctorFragmentOne());
                baseFragmentList.add(new SmartDoctorFragmentTwo());
                baseFragmentList.add(new SmartDoctorFragmentThree());
                switchFragment(fromFragment, baseFragmentList.get(0));
        }

        private void switchFragment(Fragment fromFragment, BaseFragment toFragment) {
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

        private void initView() {

        }
}
