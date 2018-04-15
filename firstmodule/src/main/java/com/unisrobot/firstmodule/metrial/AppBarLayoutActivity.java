package com.unisrobot.firstmodule.metrial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/15.
 */

/**
 * AppBarLayout: 就是放 AppBar的容器，这个容器的LinearLayout，而AppBar是 ActionBar，ToolBar，
 * <p>
 * AppBarLayout要点：
 * 1. 可以让 AppBarLayout里面的view,(除了AppBar，还可以是其他的普通view) ，有自己的滚动行为（view必须设置自己的滚动行为）
 * 2. AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)
 */
public class AppBarLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_appbarlayout);
        TextView viewById = findViewById(R.id.first_module_appbarlayout_tv);
        Toolbar toolbar = findViewById(R.id.first_module_toolbar_appbar);
        getSupportActionBar();

        viewById.setText(" AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView) " +
                "AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                " AppBarLayout必须依赖CoordinatorLayout作为父容器，同时后面要有一个可以滑动的view(RecycleView,NestedScrollView)" +
                "");
    }
}
