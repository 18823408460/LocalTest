package com.unisrobot.firstmodule.editprogram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.view.DrawBitampView;


/**
 * Created by Administrator on 2018/4/26.
 */

public class ViewGroupActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //                setContentView(R.layout.first_module_activity_viewgroup);
                //                setContentView(new CircleMenu(this));

                //                setContentView(R.layout.first_module_activity_pathmenu);
                setContentView(R.layout.first_module_activity_remoteview);
//                setContentView(new DrawBitampView(this));
//                setContentView(R.layout.first_module_activity_btn);
//                setContentView(new RemoteView(this));
        }
}
