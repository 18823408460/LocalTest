package com.unisrobot.firstmodule.metrial;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.metrial.adapter.RecycleApdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/4/15.
 */

public class RefreshActivity extends AppCompatActivity {
    MaterialRefreshLayout materialRefreshLayout;
    RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private RecycleApdater recycleApdater;
    /**
     * 在上拉刷新的时候，判断，是否处于上拉刷新，如果是的话，就禁止在一次刷新，保障在数据加载完成之前
     * 避免重复和多次加载
     */
    private boolean isLoadMore = true;
    private int count = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_refresh);
        materialRefreshLayout = findViewById(R.id.materialrefreshlayout);
        recyclerView = findViewById(R.id.first_module_refresh_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initDatas();
        // 添加分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.first_module_drawable_item));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recycleApdater = new RecycleApdater(this, datas);
        recyclerView.setAdapter(recycleApdater);

        materialRefreshLayout.setLoadMore(isLoadMore);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //一般加载数据都是在子线程中，这里我用到了handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RefreshActivity.this, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
                        recycleApdater.notify("下拉count-----" + (count++));
                        /**
                         * 刷新完成后调用此方法，要不然刷新效果不消失
                         */
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;
                        //通知刷新
                        recycleApdater.notify("上拉count-----" + (count++));
                        //mRecyclerView.scrollToPosition(mAdapter.getLists().size());
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        datas.add("New York");
        datas.add("Bei Jing");
        datas.add("Boston");
        datas.add("London");
        datas.add("San Francisco");
        datas.add("Chicago");
        datas.add("Shang Hai");
        datas.add("Tian Jin");
        datas.add("Zheng Zhou");
        datas.add("Hang Zhou");
        datas.add("Guang Zhou");
        datas.add("Fu Gou");
        datas.add("Zhou Kou");
    }
}
