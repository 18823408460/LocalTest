package com.uurobot.baseframe.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.dialog.SmartDoctorNormalDialog;
import com.uurobot.baseframe.dialog.SmartDoctorUpDialog;
import com.uurobot.baseframe.fragment.smartTree.SmartTreeFragmentOne;
import com.uurobot.baseframe.fragment.smartTree.SmartTreeFragmentThree;
import com.uurobot.baseframe.fragment.smartTree.SmartTreeFragmentTwo;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentOne;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentThree;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartTreeActivity extends BaseActivity  {
        private List<BaseFragment> baseFragmentList;
        private BaseFragment fromFragment;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_smarttree);
                initData();
        }

        private void initData() {
                baseFragmentList = new ArrayList<>();
                baseFragmentList.add(new SmartTreeFragmentOne());
                baseFragmentList.add(new SmartTreeFragmentTwo());
                baseFragmentList.add(new SmartTreeFragmentThree());
                switchFragment(fromFragment, baseFragmentList.get(2));
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
        int index = 0;
       public void switchF(View view){
               switchFragment(fromFragment, baseFragmentList.get(index++ % baseFragmentList.size()));
       }
}
