package com.uurobot.baseframe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.uurobot.baseframe.bean.shangcheng.ResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class RecomendViewHoler extends RecyclerView.ViewHolder {
        private static final String TAG = RecomendViewHoler.class.getSimpleName();
        private GridView gridView;

        public RecomendViewHoler(View itemView) {
                super(itemView);
        }

        public void setData(List<ResponseBean.ResultBean.RecommendInfoBean> data) {
                Log.e(TAG, "setData: " + data);
        }
}
