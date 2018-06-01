package com.uurobot.baseframe.fragment.shangcheng.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/5/31.
 */

public class HomeFragment extends BaseFragment {
        private static final String TAG = HomeFragment.class.getSimpleName();
        private TextView textViewSearch, textViewType;
        private RecyclerView recyclerView;
        private ImageButton imageButton;
        private HomeFragmentAdapter homeFragmentAdapter;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_home_shangcheng, container, false);
                recyclerView = view.findViewById(R.id.recycleview_home_fragmnet);
                textViewSearch = view.findViewById(R.id.tv_home_fragmnet_head_search);
                textViewSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(mContext, "textViewSearch", Toast.LENGTH_SHORT).show();
                        }
                });
                textViewType = view.findViewById(R.id.tv_home_fragmnet_head_type);
                textViewType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(mContext, "textViewType", Toast.LENGTH_SHORT).show();
                        }
                });
                imageButton = view.findViewById(R.id.imageButton_home_fragment);
                imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(mContext, "homeBtn", Toast.LENGTH_SHORT).show();
                        }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) { // 监听滑动到了 第几个Item
                                if (position > 3) {
                                        imageButton.setVisibility(View.VISIBLE);
                                } else {
                                        imageButton.setVisibility(View.GONE);
                                }
                                return 1;
                        }
                });
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.addItemDecoration(new RecycleViewItemDivide(4));
                homeFragmentAdapter = new HomeFragmentAdapter(mContext);
                recyclerView.setAdapter(homeFragmentAdapter);
                return view;
        }

        @Override
        protected void initData() {
                OkHttpUtils
                        .get()
                        .url(Constans.HOME_URL)
                        .build()
                        .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                        Log.e(TAG, "onError: " + e);
                                }

                                @Override
                                public void onResponse(String response, int id) { // main thread
                                        Log.e(TAG, "onResponse: " + Thread.currentThread().getName());
                                        handlerResponseInfo(response);
                                }
                        });
        }

        private void handlerResponseInfo(String response) {
                if (TextUtils.isEmpty(response)) {
                        return;
                }
                ResponseBean responseBean = JSON.parseObject(response, ResponseBean.class);
                homeFragmentAdapter.setData(responseBean.getResult());
        }
}
