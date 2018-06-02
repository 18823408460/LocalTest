package com.uurobot.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;

import com.uurobot.baseframe.app.MainApp;
import com.uurobot.baseframe.bean.shangcheng.GoodsBean;

/**
 * Created by Administrator on 2018/6/2.
 */
// 缓存到内存，同时保存到SP，待下次开机使用
public class CacheDataMgr {
        private static final String spName = "UU";
        private static CacheDataMgr cacheDataMgr;
        private SharedPreferences sharedPreferences;
        //性能高于 hashMap<Integer,Object>
        private SparseArray<GoodsBean> sparseArray;

        private CacheDataMgr(Context context) {
                sparseArray = new SparseArray<>(100);
                sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }

        public static CacheDataMgr getCacheDataMgr() {
                if (cacheDataMgr == null) {
                        synchronized (CacheDataMgr.class) {
                                if (cacheDataMgr == null) {
                                        cacheDataMgr = new CacheDataMgr(MainApp.getContext());
                                }
                        }
                }
                return cacheDataMgr;
        }

        public void putData(GoodsBean goodsBean) {
                String product_id = goodsBean.getProduct_id();
        }
}
