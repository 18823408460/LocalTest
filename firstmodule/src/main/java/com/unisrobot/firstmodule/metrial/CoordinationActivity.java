package com.unisrobot.firstmodule.metrial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.metrial.adapter.RecycleApdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/4/15.
 */

/**
 * CoordinatorLayout
 * 主要用于作为视图根布局以及协调子控件的行为（根据用户的触摸行为产生一定的动画效果）。
 * 主要是通过设置子View的 Behaviors来实现不同效果
 * <p>
 * CoordinatorLayout是一个增强型的FrameLayout,它的作用就是用来协调其所包裹的子view的手势操作的.
 * <p>
 * <p>
 * 为了使得Toolbar可以滑动，我们必须还得有个条件，就是CoordinatorLayout布局下包裹一个可以滑动的布局，
 * 比如: RecyclerView，NestedScrollView(ListView，ScrollView不支持)具有滑动效果的组件。
 * 并且还需要给这些组件设置如下属性来告诉CoordinatorLayout，该组件是带有滑动行为的组件，
 * 然后CoordinatorLayout在接受到滑动时会通知AppBarLayout 中可滑动的Toolbar可以滑出屏幕了（AppBarLayout 里面的view都会划入划出）
 * <p>
 * <p>
 * 1. CoordinatorLayout 里面 嵌套 Toolbar， RecycleView, 可以让Toolbar 随着RecycleView 上下滑动而影藏显示。
 */
public class CoordinationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    List<String> mData;

    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    RecycleApdater recycleApdater;
    int count = 100 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_coordination);
        Toolbar toolbar = findViewById(R.id.first_module_toolabar);
        setSupportActionBar(toolbar);
        initData(1);

        tabLayout = findViewById(R.id.first_module_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.RED, Color.GREEN);
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
        for (int i = 1; i < 20; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                recycleApdater.notify("增加的item==="+(count++));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        recyclerView = findViewById(R.id.first_module_recycleview_coordination);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 添加分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.first_module_drawable_item));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recycleApdater = new RecycleApdater(this,mData);
        recyclerView.setAdapter(recycleApdater);
    }
}
