package com.unisrobot.firstmodule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.baronzhang.android.router.RouterInjector;
import com.baronzhang.android.router.annotation.inject.Inject;
import com.unisrobot.firstmodule.dialog.BottomDialogFragment;
import com.unisrobot.firstmodule.dialog.LoadingDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/13.
 */

/**
 * library中要使用最新的butterknife，，所有的id用R2 来代替。
 */
public class FirstMuduleMainActivity extends AppCompatActivity {
    // 传递的参数通过注解获取
    /**
     * getIntent().getIntExtra("intParam", 0);
     * getIntent().getData().getQueryParameter("preActivity"); 取代这种获取方式
     */
    private static final String TAG = "FirstMuduleMainActivity";
    @Inject("cityId")
    String cityId;

    @Inject("brokerIdList")
    String lists;

    @BindView(R2.id.first_module_button)
    Button firstModuleButton;

    boolean flag = false;
    BottomDialogFragment loadingDialogFragment;

    @OnClick(R2.id.first_module_button)
    public void testBtn() {
//                Toast.makeText(this, "show butterknife", Toast.LENGTH_SHORT).show();
        if (!flag) {
            loadingDialogFragment = new BottomDialogFragment();
            loadingDialogFragment.show(getSupportFragmentManager(), "tag");
        } else {
            loadingDialogFragment.dismiss();
        }
        flag = !flag;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_main);
        ButterKnife.bind(this);
        RouterInjector.inject(this);

        Log.e(TAG, "onCreate: cityid === " + cityId);
        Log.e(TAG, "onCreate: lists === " + lists);
    }
}
