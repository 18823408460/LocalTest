package com.unisrobot.firstmodule.metrial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/15.
 */

/**
 * 抽屉布局。。。。。
 * DrawerLayout 用于实现抽屉效果，里面嵌套了两个内容
 * 第一个子元素是主要内容，即抽屉没有打开时显示的布局。
 * 第二个子元素是抽屉中的内容，即抽屉布局。这里采用了NavigationView，但不限于此，可以使用ListView代替
 * <p>
 * <p>
 * <p>
 * NavigationView 作为 DrawerLayout 第二个子元素，实现导航栏。导航栏主要有两个内容，可以根据实际删减。
 * app:headerLayout 导航栏的头部布局
 * app:menu 导航栏的菜单条目
 */
public class DrawerLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_drawlayout);
        Toolbar toolbar = findViewById(R.id.drawlayout_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.first_module_ic_launcher_round);    //设置导航键图片
        ab.setDisplayHomeAsUpEnabled(true);     //显示导航键
        // 关联导航键
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.first_module_navigation_drawer_open, R.string.first_module_navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}
