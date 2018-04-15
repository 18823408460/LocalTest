package com.unisrobot.firstmodule.metrial;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.dialog.LoadingDialogFragment;
import com.unisrobot.firstmodule.fragmentD.ViewPagerAdapter;

/**
 * Created by WEI on 2018/4/15.
 */

public class BottomViewActivity extends AppCompatActivity {
    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_module_activity_bottom);
        context = this;
        viewPager = findViewById(R.id.first_module_bottom_viewpager);
        bottomNavigationView = findViewById(R.id.bottomnavigation);


        // 底部》4 ge  menu item 时，文字显示不出来。。。
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(context, item.getTitle() + "   " + item.getOrder(), Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(item.getOrder());
                return false;
            }
        });

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
