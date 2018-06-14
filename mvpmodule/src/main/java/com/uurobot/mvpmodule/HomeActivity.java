package com.uurobot.mvpmodule;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.uurobot.mvpmodule.base.BaseActivity;
import com.uurobot.mvpmodule.base.IBaseContract;
import com.uurobot.mvpmodule.fragment.NewsFragment;
import com.uurobot.mvpmodule.fragment.PicFragment;
import com.uurobot.mvpmodule.fragment.VideoFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/14.
 */

public class HomeActivity extends BaseActivity {
        private static final String TAG = HomeActivity.class.getSimpleName();
        @BindView(R.id.fl_home_container)
        FrameLayout flHomeContainer;
        @BindView(R.id.nav_view)
        NavigationView navView;
        @BindView(R.id.drawer_layout)
        DrawerLayout mDrawerLayout;
        private int mItemId = -1;
        private SparseArray<String> stringSparseArray;
        private Fragment newsFragment;
        private Fragment picFragment;
        private Fragment videoFragment;
        private Fragment fromFragmet;

        @Override
        protected void initView() {
                initDrawerLayout(mDrawerLayout, navView);
        }

        @Override
        protected void initData() {
                stringSparseArray = new SparseArray<>();
                stringSparseArray.put(R.id.nav_news, "News");
                stringSparseArray.put(R.id.nav_news_pic, "Pic");
                stringSparseArray.put(R.id.nav_news_video, "Video");
                switchFragment(fromFragmet, R.id.nav_news);
        }


        private void switchFragment(Fragment from, int id) {
                Log.e(TAG, "switchFragment: ===========");
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (from != null) {
                        fragmentTransaction.hide(from);
                }
                Fragment to;
                if (id == R.id.nav_news) {
                        if (newsFragment == null) {
                                newsFragment = new NewsFragment();
                                fragmentTransaction.add(R.id.fl_home_container, newsFragment);
                        }
                        to = newsFragment;
                } else if (id == R.id.nav_news_pic) {
                        if (picFragment == null) {
                                picFragment = new PicFragment();
                                fragmentTransaction.add(R.id.fl_home_container, picFragment);
                        }
                        to = picFragment;
                } else {
                        if (videoFragment == null) {
                                videoFragment = new VideoFragment();
                                fragmentTransaction.add(R.id.fl_home_container, videoFragment);
                        }
                        to = videoFragment;
                }
                fragmentTransaction.commit();
                fragmentTransaction.show(to);
                fromFragmet = to;
        }


        @Override
        protected int getViewLayoutId() {
                return R.layout.activity_home;
        }

        private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {
                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
                drawerLayout.setFitsSystemWindows(true);
                drawerLayout.setClipToPadding(false);
                drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                        @Override
                        public void onDrawerClosed(View drawerView) {
                                // 根据 mItemId 切换 Fragment
                                switchFragment(fromFragmet, mItemId);
                        }
                });
                navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                mItemId = item.getItemId();
                                Log.e(TAG, "onNavigationItemSelected: " + item.getItemId());
                                return false;
                        }
                });
        }

        @Override
        protected IBaseContract.IBasePresenter getPreSenter() {
                return null;
        }

}
