package com.unisrobot.firstmodule.fragmentD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/14.
 */

/**
 * fragment 动态加载， 必须用到事务。。。
 * 对Fragment进行添加、移除、替换或执行其它动作，提交给Activity的每一个变化。这就是Fragment事务,这几个操作都要用到事务
 * Android提供了FragmentManage类来管理Fragment，FragmentTransaction类来管理事务
 */
public class FirstModuleDFragmentPager extends FragmentActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private Button button;
    Fragment dynamicFragment;
    Fragment dynamicFragment1;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_fragment_main_pager);
        button = findViewById(R.id.first_module_button_dy);
        button.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.first_module_fragment_dy_pager);
        viewPager.setAdapter(new ViewPagerAdapter(fragmentManager));

        // 设置预加载的数量，在V4包中，默认的预加载是1，即使你设置为0，也是不起作用的，设置的只能是大于1才会有效果的
        viewPager.setOffscreenPageLimit(1);
    }

    @Override
    public void onClick(View v) {

    }
}
