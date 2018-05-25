package com.uurobot.baseframe.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.fragment.mainhome.CommonFragment;
import com.uurobot.baseframe.fragment.mainhome.CustomFragment;
import com.uurobot.baseframe.fragment.mainhome.OtherFragment;
import com.uurobot.baseframe.fragment.mainhome.ThirdPartFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MainActivity extends BaseActivity {
        private static final String TAG = "MainActivity";
        private RadioGroup radioGroup;
        private Fragment fromFragment;
        private List<BaseFragment> baseFragmentList;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                initView();
                initData();
        }

        private void initData() {
                baseFragmentList = new ArrayList<>();
                baseFragmentList.add(new CommonFragment());
                baseFragmentList.add(new ThirdPartFragment());
                baseFragmentList.add(new CustomFragment());
                baseFragmentList.add(new OtherFragment());
                //radioGroup.check(R.id.rb_common); // 这里会导致OnCheckedChangeListener调用两次
                RadioButton radioButton = radioGroup.findViewById(R.id.rb_common);
                radioButton.setChecked(true);

        }

        @Override
        protected void onPause() {
                super.onPause();
                Log.d(TAG, "onPause: ");
        }

        @Override
        protected void onResume() {
                super.onResume();
                Log.d(TAG, "onResume: ");
        }

        private void initView() {
                radioGroup = findViewById(R.id.rg_layout_bottom);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                RadioButton radioButton = group.findViewById(checkedId);
                                String tag = (String) radioButton.getTag();
                                Log.d(TAG, "onCheckedChanged: " + checkedId + "   tag=" + tag);
                                BaseFragment toFragment = baseFragmentList.get(Integer.parseInt(tag));
                                switchFragment(fromFragment, toFragment);
                        }
                });
        }

        private void switchFragment(Fragment fromFragment, BaseFragment toFragment) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                if (fromFragment != null) {
                        fragmentTransaction.hide(fromFragment);
                }
                if (!toFragment.isAdded()) {
                        fragmentTransaction.add(R.id.fl_layout_main, toFragment);
                } else {
                        fragmentTransaction.show(toFragment);
                }
                this.fromFragment = toFragment;
                //fragmentTransaction.commitNowAllowingStateLoss(); //这种方式导致 Fragment onPause-onResume
                fragmentTransaction.commit();
        }
}
