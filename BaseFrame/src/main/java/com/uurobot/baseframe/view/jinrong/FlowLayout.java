package com.uurobot.baseframe.view.jinrong;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by Administrator on 2018/6/8.
 */

public class FlowLayout extends ViewGroup {
        private int padding;
        private int screenWidth;
        private static final String TAG = FlowLayout.class.getSimpleName();

        public FlowLayout(Context context) {
                this(context, null);
        }

        public FlowLayout(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                padding = SizeUtil.dp2px(getContext(), 10);
                Point screen = SizeUtil.getScreen();
                screenWidth = screen.x;


        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int childCount = getChildCount();
                int maxWidth = padding;
                int maxHeigth = padding;
                int measuredHeight = 0;
                Log.e(TAG, "onMeasure: padding ===== "+ padding );
                for (int i = 0; i < childCount; i++) { // 8个
                        TextView childView = (TextView) getChildAt(i);
                        childView.measure(widthMeasureSpec, heightMeasureSpec);
                        measuredHeight = childView.getMeasuredHeight();
                        int measuredWidth = childView.getMeasuredWidth();

                        maxWidth += measuredWidth + padding;
                        if (maxWidth >= screenWidth) { //超过屏幕宽度，则需要换行
                                maxHeigth += (measuredHeight + padding);
                                maxWidth = padding;
                                Log.d(TAG, "onMeasure: 换行=====" + maxHeigth + "   " + i + "   text=" + childView.getText() + "  measuredHeight="+measuredHeight);
                        } else {
                                Log.e(TAG, "onMeasure: " + measuredWidth + "   " + maxHeigth + "     ="+i + "   "+childView.getText() + "   measuredHeight="+measuredHeight);
                        }
                }

                maxHeigth += (measuredHeight + padding) ;
                Log.e(TAG, "onMeasure: ============maxHeigth====="+maxHeigth );
                setMeasuredDimension(screenWidth, maxHeigth);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int childCount = getChildCount();
                int maxWidth = padding;
                int maxHeigth = padding;
                for (int i = 0; i < childCount; i++) {
                        View childView = getChildAt(i);
                        int measuredwidth = childView.getMeasuredWidth();
                        int measuredHeight = childView.getMeasuredHeight();
                        int tempW = measuredwidth + padding;
                        int currutMaxW = tempW + maxWidth;
                        if (currutMaxW > screenWidth) {
                                maxWidth = padding;
                                maxHeigth += measuredHeight + padding;
                                childView.layout(maxWidth, maxHeigth, maxWidth + measuredwidth, maxHeigth + measuredHeight);
                                //Log.d(TAG, "onLayout: ============换行=======" + i);
                                maxWidth += measuredwidth;
                        } else {
                                childView.layout(maxWidth, maxHeigth, maxWidth + tempW, maxHeigth + measuredHeight);
                                maxWidth = currutMaxW;
                                // Log.e(TAG, "onLayout:  i ==== " + i);
                        }
                }
        }
}
