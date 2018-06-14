package com.uurobot.mvpmodule.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.mvpmodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/14.
 */

public class NewsFragment extends BaseFragment {


        @BindView(R.id.tool_bar)
        Toolbar mToolBar;
        @BindView(R.id.tab_layout)
        TabLayout tabLayout;
        @BindView(R.id.view_pager)
        ViewPager viewPager;
        Unbinder unbinder;

        @Override
        protected void initView() {
                initToolBar(mToolBar, true, "新闻");
                setHasOptionsMenu(true);
        }

        @Override
        protected int getLayoutId() {
                return R.layout.fragment_news;
        }
}
