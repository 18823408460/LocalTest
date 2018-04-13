package com.unisrobot.localtest.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.baronzhang.android.router.Router;
import com.unisrobot.comlib.router.RouterService;
import com.unisrobot.localtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/13.
 */
// 组件 和 模块 的区别：


public class MuduleActivity extends Activity {

        private RouterService routerService;

        @BindView(R.id.button12)
        Button button12;

        @OnClick(R.id.button12)
        public void testModule() {
//                Intent intent = new Intent(this, FirstMuduleMainActivity.class);
//                startActivity(intent);
                routerService.startFirstModuleMainActivity("1111","22222");
        }

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main_module_activity_module);
                ButterKnife.bind(this);

                routerService = new Router(this).create(RouterService.class);
        }
}
