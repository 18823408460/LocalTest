package com.uurobot.baseframe.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.ITRecyleViewAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.adapter.TTRecyleViewAdapter;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.bean.ITBean;
import com.uurobot.baseframe.bean.TTBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class RecycleActivity extends BaseActivity {
        public static final String TYPE = "type";
        public static String[] RecycleType = {"IT", "TT"};
        private RecyclerView recyclerView;
        private BaseAdapter recycleViewAdapter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_recycle);
                parseIntent();
                initView();
        }

        private void initView() {
                recyclerView = findViewById(R.id.recycleview_recycle_activity);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                recyclerView.addItemDecoration(new RecycleViewItemDivide(4));
                recyclerView.setAdapter(recycleViewAdapter);
        }

        private void parseIntent() {
                Intent intent = getIntent();
                String stringExtra = intent.getStringExtra(TYPE);
                if (stringExtra.equals(RecycleType[0])) {
                        List<ITBean> data = new ArrayList<>();
                        data.add(new ITBean(R.drawable.sensor_bright, "hello"));
                        data.add(new ITBean(R.drawable.sensor_bright, "hello"));
                        data.add(new ITBean(R.drawable.sensor_touch, "hello"));
                        data.add(new ITBean(R.drawable.sensor_touch, "hello"));
                        recycleViewAdapter = new ITRecyleViewAdapter(data);
                } else {
                        List<TTBean> data = new ArrayList<>();
                        data.add(new TTBean("hello", "world"));
                        data.add(new TTBean("hello", "world"));
                        data.add(new TTBean("hello", "world"));
                        data.add(new TTBean("hello", "world"));
                        data.add(new TTBean("hello", "world"));
                        recycleViewAdapter = new TTRecyleViewAdapter(data);
                }
        }
}
