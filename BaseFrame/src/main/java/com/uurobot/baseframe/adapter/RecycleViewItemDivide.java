package com.uurobot.baseframe.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/5/23.
 */

public class RecycleViewItemDivide extends RecyclerView.ItemDecoration {
        private int space;

        public RecycleViewItemDivide(int space) {
                this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                if (parent.getChildLayoutPosition(view) != 0) {
//
//                }
                outRect.top = space;

        }
}
