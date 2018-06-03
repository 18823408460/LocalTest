package com.uurobot.baseframe.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.alibaba.fastjson.JSON;
import com.uurobot.baseframe.app.MainApp;
import com.uurobot.baseframe.bean.shangcheng.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/2.
 */
// 缓存到内存，同时保存到SP(本地)，待下次开机使用

/**
 * 点击 加入购物车
 * > 1. 将数据直接上传到服务器，然后进入到购物车页面时，再从服务器获取
 * >2 . 首先将数据保存到 本地，然后在适当的机会再去 上传到 服务器
 * <p>
 * 两种方法都可以
 */
public class CartStorage {
    private static final String TAG = CartStorage.class.getSimpleName();
    public static final String JSON_CAR = "json_car";
    private static volatile CartStorage cartStorage;
    //性能高于 hashMap<Integer,Object>
    private SparseArray<GoodsBean> sparseArray;
    private Context mContext;

    private CartStorage(Context context) {
        mContext = context;
        sparseArray = new SparseArray<>(100);
        listToSparseArray();
    }

    private void listToSparseArray() {
        List<GoodsBean> allData = getAllData();
        for (int i = 0; i < allData.size(); i++) {
            sparseArray.append(Integer.parseInt(allData.get(i).getProduct_id()), allData.get(i));
        }
    }

    /**
     * 从 本地sp 中加载 所有的数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {
        String json = CacheUtils.getString(mContext, JSON_CAR);
        Log.e(TAG, "getAllData: ==== " + json );
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            goodsBeanList = JSON.parseArray(json, GoodsBean.class);
        }
        return goodsBeanList;
    }

    public static CartStorage getCartStorage() {
        if (cartStorage == null) {
            synchronized (CartStorage.class) {
                if (cartStorage == null) {
                    cartStorage = new CartStorage(MainApp.getContext());
                }
            }
        }
        return cartStorage;
    }

    public void addData(GoodsBean goodsBean) {
        // 保存到内存
        int key = Integer.parseInt(goodsBean.getProduct_id());
        GoodsBean goodsBean1 = sparseArray.get(key);
        if (goodsBean1 != null) {
            goodsBean1.setNumbers(goodsBean1.getNumbers() + 1);
        } else {
            goodsBean1 = goodsBean;
        }
        sparseArray.put(key, goodsBean1);

        // 同步到本地
        saveToLocal();
    }

    public void deleteData(GoodsBean goodsBean) {
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        saveToLocal();
    }

    public void updateData(GoodsBean goodsBean) {
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        saveToLocal();
    }

    private void saveToLocal() {
        List<GoodsBean> goodsBeanList = sparseArrayToList();

        String json = JSON.toJSONString(goodsBeanList);

        CacheUtils.putString(mContext, JSON_CAR, json);
    }

    private List<GoodsBean> sparseArrayToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            goodsBeanList.add(sparseArray.valueAt(i));
        }
        return goodsBeanList;
    }
}
