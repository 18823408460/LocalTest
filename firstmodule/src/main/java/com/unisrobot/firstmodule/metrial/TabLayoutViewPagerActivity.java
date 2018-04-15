package com.unisrobot.firstmodule.metrial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.fragmentD.DynamicFragment;

/**
 * Created by WEI on 2018/4/15.
 */

public class TabLayoutViewPagerActivity extends AppCompatActivity {

    String[] mTitle = new String[20];
    String[] mData = new String[20];
    TabLayout mTabLayout;
    ViewPager mViewPager;

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mTitle[i] = "TAB" + (i + 1);
            mData[i] = "当前选中的是第" + (i + 1) + "Fragment";
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_tablayout_viewpager);
        initData();
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.first_module_recycleview);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = DynamicFragment.newInstance(mData[position]);
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }

            @Override
            public int getCount() {
                return mData.length;
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
