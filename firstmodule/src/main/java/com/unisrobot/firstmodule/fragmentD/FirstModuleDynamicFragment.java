package com.unisrobot.firstmodule.fragmentD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class FirstModuleDynamicFragment extends FragmentActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private Button button;
    Fragment dynamicFragment;
    Fragment dynamicFragment1;
    Fragment dynamicFragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_fragment_main_dy);
        button = findViewById(R.id.first_module_button_dy);
        button.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        dynamicFragment = DynamicFragment.newInstance("我是第1个fragment");
        dynamicFragment1 = DynamicFragment.newInstance("我是第2个fragment");
//        dynamicFragment2 = DynamicFragment.newInstance("我是第3个fragment");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.first_module_fragment_dy_fl, dynamicFragment, "1");
        fragmentTransaction.add(R.id.first_module_fragment_dy_fl, dynamicFragment1, "2");
//        fragmentTransaction.add(R.id.first_module_fragment_dy_fl, dynamicFragment2, "3");
        fragmentTransaction.commit();

        //new LoadingDialog(this,R.style.first_module_loadingdialog).show();
    }

    private boolean showOne;

    @Override
    public void onClick(View v) {
        if (showOne) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(dynamicFragment1);
            fragmentTransaction.show(dynamicFragment);
            fragmentTransaction.commit();
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(dynamicFragment);
            fragmentTransaction.show(dynamicFragment1);
            // replace会 导致 fragment 不断的 创建，销毁。。。。
//            fragmentTransaction.replace(R.id.first_module_fragment_dy_fl, dynamicFragment1);
            fragmentTransaction.commit();
        }
        showOne = !showOne;
    }
}
