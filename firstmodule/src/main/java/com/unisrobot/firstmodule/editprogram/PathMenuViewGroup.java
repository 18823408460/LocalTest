package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/4/26.
 */

public class PathMenuViewGroup extends ViewGroup {
        public PathMenuViewGroup(Context context) {
                this(context, null);
        }

        public PathMenuViewGroup(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public PathMenuViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
}
