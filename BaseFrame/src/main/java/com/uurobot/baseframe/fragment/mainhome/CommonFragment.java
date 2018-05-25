package com.uurobot.baseframe.fragment.mainhome;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.RecycleViewAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/23.
 */

public class CommonFragment extends BaseFragment {
        private static final String TAG = CommonFragment.class.getSimpleName();
        private RecyclerView recyclerView;
        private RecycleViewAdapter recycleViewAdapter;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_common_layout, null);
                recyclerView = inflate.findViewById(R.id.rv_common_fragment);
                initRecycleView();
                return inflate;
        }

        private void initRecycleView() {
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("智慧苗圃");
                arrayList.add("智慧医生");
                arrayList.add("播放视频");
                recycleViewAdapter = new RecycleViewAdapter(arrayList, getFragmentManager());
                recyclerView.addItemDecoration(new RecycleViewItemDivide(15));
                recyclerView.setAdapter(recycleViewAdapter);
        }

        @Override
        public void onPause() {
                super.onPause();
                Log.d(TAG, "onPause: --------CommonFragment");
        }

        @Override
        public void onResume() {
                super.onResume();
                Log.d(TAG, "onResume:  --------CommonFragment");
        }
}
