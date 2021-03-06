package com.uurobot.baseframe.fragment.smarthome;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.ITRecyleViewAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.bean.ITBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartHomeFragmentThree extends BaseFragment {
    private BaseAdapter recycleViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_smarthome_three, container, false);
        initAdapter();
        initViews(view);
        return view;
    }

    private void initAdapter() {
        List<ITBean> data = new ArrayList<>();
        data.add(new ITBean(R.drawable.sensor_wemdu, getString(R.string.sensor_wendu_tip)));
        data.add(new ITBean(R.drawable.sensor_csb, getString(R.string.sensor_touchu_tip)));
        data.add(new ITBean(R.drawable.sensor_bright, getString(R.string.sensor_bright_tip)));
        data.add(new ITBean(R.drawable.sensor_body, getString(R.string.sensor_bright_tip)));
        recycleViewAdapter = new ITRecyleViewAdapter(data);
    }

    private void initViews(View inflater) {
        recyclerView = inflater.findViewById(R.id.recycleview_recycle_smarttree_three);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.addItemDecoration(new RecycleViewItemDivide(4));
        recyclerView.setAdapter(recycleViewAdapter);
    }
}
