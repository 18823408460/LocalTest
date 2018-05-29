package com.uurobot.baseframe.fragment.mainhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.TestViewActivity;
import com.uurobot.baseframe.activitys.custom.YouKuActivity;
import com.uurobot.baseframe.adapter.FragmentAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/23.
 */

public class CustomFragment extends BaseFragment {
        private static final String TAG = CustomFragment.class.getSimpleName();
        private RecyclerView recyclerView;
        private FragmentAdapter recycleViewAdapter;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_custom_layout, null);
                recyclerView = inflate.findViewById(R.id.rv_common_fragment);
                initRecycleView();
                return inflate;
        }

        private void initRecycleView() {
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("旋转菜单");
                arrayList.add("旋转View");
                recycleViewAdapter = new FragmentAdapter(arrayList, getFragmentManager()) {
                        @Override
                        public void handlerItemClick(int position) {
                                switch (position) {
                                        case 0:
                                                handlerItemOne();
                                                break;
                                        case 1:
                                                handlerItemTwo();
                                                break;
                                        case 2:
                                                handlerItemThree();
                                                break;
                                }
                        }
                };
                recyclerView.addItemDecoration(new RecycleViewItemDivide(15));
                recyclerView.setAdapter(recycleViewAdapter);
        }

        private void handlerItemThree() {
        }

        private void handlerItemTwo() {
                startActivity(new Intent(getActivity(), TestViewActivity.class));
        }

        private void handlerItemOne() {
                startActivity(new Intent(getActivity(), YouKuActivity.class));
        }
}
