package com.uurobot.baseframe.activitys.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.BaseActivity;
import com.uurobot.baseframe.utils.ViewTools;

/**
 * Created by WEI on 2018/5/28.
 */

public class YouKuActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout layout1, layout2, layout3;
    private ImageView imageViewHome, imageMenu;
    private boolean lever1Show, lever2Show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youku);
        layout1 = (RelativeLayout) findViewById(R.id.layout_youku_lever1);
        layout2 = (RelativeLayout) findViewById(R.id.layout_youku_lever2);
        layout3 = (RelativeLayout) findViewById(R.id.layout_youku_lever3);
        imageViewHome = findViewById(R.id.image_icon_home);
        imageMenu = findViewById(R.id.image_icon_menu);
        imageViewHome.setOnClickListener(this);
        imageMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.image_icon_home) {
            if (!lever2Show) {
                ViewTools.hideView(layout2);
            } else {
                ViewTools.showView(layout2);
            }
            lever2Show = !lever2Show;

        } else if (id == R.id.image_icon_menu) {

        }
    }
}
