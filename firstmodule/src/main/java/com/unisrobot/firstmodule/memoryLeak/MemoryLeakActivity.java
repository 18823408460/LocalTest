package com.unisrobot.firstmodule.memoryLeak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17.
 */

public class MemoryLeakActivity extends Activity {

        @BindView(R2.id.first_module_button2)
        Button firstModuleButton2;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_memoryleak);
                ButterKnife.bind(this);
        }

        @OnClick(R2.id.first_module_button2)
        public void test(){
                createThread();
        }

        private void createThread() {
                for (int i = 0; i < 100; i++) {
                        new Thread(new Runnable() {
                                @Override
                                public void run() {

                                }
                        }).start();
                }
        }
}
