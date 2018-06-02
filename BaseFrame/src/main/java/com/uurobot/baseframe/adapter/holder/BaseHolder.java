package com.uurobot.baseframe.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uurobot.baseframe.adapter.HomeFragmentAdapter;

/**
 * Created by Administrator on 2018/6/2.
 */

public class BaseHolder extends RecyclerView.ViewHolder {
        protected IHolderLisenter iHolderLisenter;
        protected Context mContext;

        public BaseHolder(View itemView) {
                super(itemView);
                mContext = itemView.getContext();
        }

        public interface IHolderLisenter {
                void onClick(HomeFragmentAdapter.HolderType holderType, int postion);
        }
}
