package com.uurobot.baseframe.fragment.smartTree;

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

public class SmartTreeFragmentTwo extends BaseFragment {
        private BaseAdapter recycleViewAdapter;
        private RecyclerView recyclerView;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragmet_smarttree_two, container, false);
                initAdapter();
                initViews(view);
                return view;
        }

        private void initAdapter() {
                List<TTBean> data = new ArrayList<>();
                data.add(new TTBean("项目任务", "worldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworld"));
                data.add(new TTBean("对应知识点", "worldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworld"));
                data.add(new TTBean("功能描述", "worldworldworldworldworldworldworldworldworldworldworld" +
                        "worldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworldworld"));
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
