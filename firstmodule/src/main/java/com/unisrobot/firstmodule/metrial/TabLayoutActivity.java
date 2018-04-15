package com.unisrobot.firstmodule.metrial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/15.
 */

/**
 * tab view  的自定义：
 * 1. 自定义view
 * 2. 指示器横线的去掉(颜色和tab一样就显示不出来了)
 * 3,.当前选中tab 的放大效果。
 */
public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Toast toast;
    private int initPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_tabyout);
        tabLayout = findViewById(R.id.tablayout);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE); // 可以滚动
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        tabLayout.setTabTextColors(Color.RED, Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
        for (int i = 0; i < 7; i++) {
            // 支持纯文本
            // tabLayout.addTab(tabLayout.newTab().setText("tab=" + i));

            // 支持图片
            // tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.first_module_ic_launcher_round));
            View inflate = View.inflate(this, R.layout.first_module_tab_custom_layout, null);
            TextView viewById = inflate.findViewById(R.id.custom_tab_tv);
            viewById.setText("tab=" + i);
            tabLayout.addTab(tabLayout.newTab().setCustomView(inflate));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toast.setText(tab.getText());
                toast.show();
                //将默认位置选中为false  FEGTRTHFHYJYU
                isSelected(tabLayout.getTabAt(initPosition), false);
                //选中当前位置
                isSelected(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                isSelected(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                isSelected(tab,true);
            }
        });
        //进来默认选中位置第3个item
        initPosition = 2;
        isSelected(tabLayout.getTabAt(initPosition), true);
    }

    /**
     * 设置选中的tab是否带缩放效果
     * @param tab
     * @param isSelected
     */
    private void isSelected(TabLayout.Tab tab, boolean isSelected) {
        View view = tab.getCustomView();
        if (null != view) {
            view.setScaleX(isSelected ? 1.3f : 1.0f);
            view.setScaleY(isSelected ? 1.3f : 1.0f);
        }
    }
}
