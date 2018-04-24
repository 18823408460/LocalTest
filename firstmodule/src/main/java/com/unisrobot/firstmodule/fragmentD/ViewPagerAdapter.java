package com.unisrobot.firstmodule.fragmentD;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/4/14.
 */
// 如果每一页 都是 Fragment， 则用  FragmentPagerAdapter ，
// 如果每一页都是的普通的view， 则用 ViewPager

/**
 * ，该类内的每一个生成的 Fragment 都将保存在内存之中，因此适用于那些相对静态的页，数量也比较少的那种；
 * 如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，应该使用FragmentStatePagerAdapter
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"天九", "地八", "人七", "和五"};//构造传递给fragment用于不同显示内容的参数
    private List<Fragment> list ;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.list = fragmentList ;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = this.list.get(position);

        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
