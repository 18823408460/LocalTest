package com.uurobot.baseframe.activitys.shangcheng;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.adapter.shangcheng.GoodsCarAdapter;
import com.uurobot.baseframe.bean.shangcheng.GoodsBean;
import com.uurobot.baseframe.bean.shangcheng.GoodsCarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/2.
 */
// 在Android中如果想要实现3D效果一般有两种选择，一是使用Open GL ES，二是使用Camera。Open GL ES使用起来太过复杂，
// 一般是用于比较高级的3D特效或游戏，像比较简单的一些3D效果，使用Camera就足够了
public class GoodCarActivity extends Activity implements View.OnClickListener {
    private static final String TAG = GoodCarActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private TextView tvEdit;
    private LinearLayout linearLayoutCaculate;
    private LinearLayout linearLayoutEdit;
    private LinearLayout linearLayoutEmpty;
    private LinearLayout relativeLayoutNotEmpty;
    private LayoutInflater layoutInflater;
    private GoodsCarAdapter goodsCarAdapter;
    private List<GoodsCarBean> allData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_car);
        initView();
        getDataFromLocal();
    }


    private List<GoodsBean> mockData() {
        List<GoodsBean> goodsBeans = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setProduct_id(String.valueOf(i));
            goodsBean.setName(String.valueOf("这是我的商品number：" + i));
            goodsBean.setFigure(String.valueOf(i));
            goodsBean.setCover_price(String.valueOf(i));
            goodsBean.setOrigin_price(String.valueOf(i));
            goodsBeans.add(goodsBean);
        }
        return goodsBeans;
    }

    private void getDataFromLocal() {
        //List<GoodsBean> data = CartStorage.getCartStorage().getAllData();
        List<GoodsBean> data = mockData();
        allData = new ArrayList<>();
        for (GoodsBean goods : data) {
            allData.add(new GoodsCarBean(goods));
        }
        Log.e(TAG, "getDataFromLocal: " + allData);
        goodsCarAdapter = new GoodsCarAdapter(this, allData);
        recyclerView.setAdapter(goodsCarAdapter);
    }


    private void initView() {
        recyclerView = findViewById(R.id.recycleview_goods_car);
        tvEdit = findViewById(R.id.tv_goods_car_edit);
        tvEdit.setOnClickListener(this);
        linearLayoutCaculate = findViewById(R.id.layout_bottom_goods_car_caculate);
        linearLayoutEdit = findViewById(R.id.layout_bottom_goods_car_edit);
        linearLayoutEmpty = findViewById(R.id.layout_goods_car_empty);
        relativeLayoutNotEmpty = findViewById(R.id.layout_goods_car_not_empty);
        layoutInflater = LayoutInflater.from(this);
        recyclerView.addItemDecoration(new RecycleViewItemDivide(10));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    private boolean isEdit = false;

    @Override
    public void onClick(View v) {
        if (v == tvEdit) {
            Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
            isEdit = !isEdit;
            if (isEdit) {
                linearLayoutCaculate.setVisibility(View.INVISIBLE);
                linearLayoutEdit.setVisibility(View.VISIBLE);
                tvEdit.setText(getString(R.string.finish));
            } else {
                linearLayoutCaculate.setVisibility(View.VISIBLE);
                linearLayoutEdit.setVisibility(View.INVISIBLE);
                tvEdit.setText(getString(R.string.edit));
            }
        }
    }
}
