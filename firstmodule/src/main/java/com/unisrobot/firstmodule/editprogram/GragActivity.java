package com.unisrobot.firstmodule.editprogram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unisrobot.firstmodule.R;

/**
 * Created by WEI on 2018/4/24.
 */

public class GragActivity extends Activity {
    private RecyclerView recyclerView ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_drag);
        recyclerView = findViewById(R.id.first_module_recycleview_drag);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyGragAdapter(this));


    }
}
