package com.uurobot.baseframe.fragment.smarthome;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.adapter.TTRecyleViewAdapter;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.bean.TTBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartHomeFragmentTwo extends BaseFragment {
        private BaseAdapter recycleViewAdapter;
        private RecyclerView recyclerView;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragmet_smarthome_two, container, false);
                initAdapter();
                initViews(view);
                return view;
        }

        private void initAdapter() {
                List<TTBean> data = new ArrayList<>();
                data.add(new TTBean(getString(R.string.project_task_title), getString(R.string.project_task_smarthome)));
                data.add(new TTBean(getString(R.string.project_study_point_title), getString(R.string.project_study_point_smarthome)));
                data.add(new TTBean(getString(R.string.project_fun_desc_title), getString(R.string.project_fun_desc_smarthome)));
                recycleViewAdapter = new TTRecyleViewAdapter(data);
        }

        private void initViews(View inflater) {
                recyclerView = inflater.findViewById(R.id.recycleview_recycle_smarttree_two);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.addItemDecoration(new RecycleViewItemDivide(4));
                recyclerView.setAdapter(recycleViewAdapter);
        }

        @Override
        public void onResume() {
                super.onResume();
        }
}
