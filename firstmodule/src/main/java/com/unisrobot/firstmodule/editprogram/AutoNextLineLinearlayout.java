package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 * view 自动换行的处理。。。
 *    1、onMeasure:测量子view的宽高，设置自己的宽和高

 2、onLayout:设置子view的位置

 onMeasure:根据子view的布局文件中属性，来为子view设置测量模式和测量值
 测量=测量模式+测量值；

 测量模式有3种：
 EXACTLY：表示设置了精确的值，一般当childView设置其宽、高为精确值、match_parent时，ViewGroup会将其设置为EXACTLY；
 AT_MOST：表示子布局被限制在一个最大值内，一般当childView设置其宽、高为wrap_content时，ViewGroup会将其设置为AT_MOST；
 UNSPECIFIED：表示子布局想要多大就多大，一般出现在AadapterView的item的heightMode中、ScrollView的childView的heightMode中；此种模式比较少见。
 3.LayoutParams
 ViewGroup LayoutParams :每个 ViewGroup 对应一个 LayoutParams; 即 ViewGroup -> LayoutParams
 getLayoutParams 不知道转为哪个对应的LayoutParams ,其实很简单，就是如下：
 子View.getLayoutParams 得到的LayoutParams对应的就是 子View所在的父控件的LayoutParams;
 例如，LinearLayout 里面的子view.getLayoutParams ->LinearLayout.LayoutParams
 所以 咱们的FlowLayout 也需要一个LayoutParams，由于上面的效果图是子View的 margin，
 所以应该使用MarginLayoutParams。即FlowLayout->MarginLayoutParams
 *
 *
 *
 * 继承 LinearLayout， ViewGroup 的区别？？？
 */

public class AutoNextLineLinearlayout extends ViewGroup {
        private static final String TAG = "AutoNextLineLinearlayou";

        public AutoNextLineLinearlayout(Context context) {
                super(context);
                Log.e(TAG, "AutoNextLineLinearlayout:1 ");
        }

        public AutoNextLineLinearlayout(Context context, AttributeSet attrs) { //xml中会创建
                super(context, attrs);
                Log.e(TAG, "AutoNextLineLinearlayout: 2");
        }

        //存储所有子View
        private List<List<View>> mAllChildViews = new ArrayList<>();
        //每一行的高度
        private List<Integer> mLineHeight = new ArrayList<>();

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                Log.e(TAG, "onLayout: l= " + l + "   t=" + t + "  r=" + r + "   b=" + b + "    changed==" + changed);
                int count = getChildCount();
                Log.e(TAG, "onLayout:  count===" + count + "  width==" + getWidth() + "  height=" + getHeight());
                int width = getWidth(); // viewGroup 的 宽度

                mAllChildViews.clear();
                mLineHeight.clear();
                int lineWidth = 0, lineHeight = 0;
                List<View> lineLists = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                        View childView = getChildAt(i);
                        MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                        int childWidth = childView.getMeasuredWidth();
                        int childHeight = childView.getMeasuredHeight();

                        int viewAllWidth = childWidth + lp.leftMargin + lp.rightMargin;
                        int viewAllHeight = childHeight + lp.topMargin + lp.bottomMargin;

                        if ((lineWidth + viewAllWidth) > width) {
                                mLineHeight.add(lineHeight);
                                mAllChildViews.add(lineLists);
                                lineWidth = 0;
                                lineHeight = viewAllHeight;
                                lineLists = new ArrayList<>();
                                Log.e(TAG, "onLayout: 换行-----------");
                        }
                        Log.e(TAG, "onLayout:lineWidth=" + lineWidth + " childHeight=" + childHeight + "  width=" + width);
                        lineWidth += viewAllWidth;
                        lineHeight = Math.max(lineHeight, viewAllHeight);
                        lineLists.add(childView);
                        Log.e(TAG, "onLayout: test======= lineHeight="+lineHeight);
                }
                mAllChildViews.add(lineLists);//add 最后一个
                mLineHeight.add(lineHeight);// add 最后一个height

                int left = 0, top = 0;
                int lineCount = mAllChildViews.size();
                for (int i = 0; i < lineCount; i++) {
                        List<View> lineviews = mAllChildViews.get(i);
                        int height = mLineHeight.get(i);
                        Log.e(TAG, "onLayout:111111   height=" + height);
                        int columuSize = lineviews.size();
                        for (int j = 0; j < columuSize; j++) {
                                View view = lineviews.get(j);
                                MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
                                int childWidth = view.getMeasuredWidth();
                                int childHeight = view.getMeasuredHeight();
                                int viewAllHeight = childHeight + lp.topMargin + lp.bottomMargin;

                                int cleft = left + lp.leftMargin;
                                int ctop = top + lp.topMargin;
                                int cright = cleft + childWidth + lp.rightMargin;
                                int cbottom = ctop + childHeight + lp.bottomMargin;
                                view.layout(cleft, ctop, cright, cbottom);
                                left += lp.leftMargin + childWidth + lp.rightMargin;
                        }
                        //换行；
                        left = 0;
                        Log.e(TAG, "onLayout:height==== " + height);
                        top += height;
                }

                // super.onLayout(changed, l, t, r, b);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);

                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);

                int width = 0, height = 0;
                int lineWidth = 0, lineHeight = 0;
                int count = getChildCount();

                for (int i = 0; i < count; i++) {
                        View childView = getChildAt(i);
                        measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                        int measuredWidth = childView.getMeasuredWidth();
                        int measuredHeight = childView.getMeasuredHeight();

                        int viewAllWidth = measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                        int viewAllHeight = measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                        if (viewAllWidth + lineWidth > widthSize) {
                                // 需要换行
                                width = Math.max(lineWidth, width);//viewgroup的最大宽度
                                height += lineHeight;//viewgroup的总高度+上一行最大的高度

                                lineWidth = viewAllWidth; // 新一行的宽度一开始为第一个view的宽度
                                lineHeight = viewAllHeight;// 新一行的高度一开始为第一个view的高度
                                //Log.e(TAG, "onMeasure: 换行。。。。。。。。。。。。");

                        } else {
                                lineWidth += viewAllWidth;
                                lineHeight = Math.max(lineHeight, viewAllHeight);//每一行，取高度最大的那个view的高度
                        }
                        Log.e(TAG, "onMeasure:measuredHeight= "+measuredHeight + "   viewAllHeight="+viewAllHeight + "  lineWidth="+lineWidth);
                        if (i == (count - 1)) {
                                width = Math.max(lineWidth, width);
                                height += lineHeight;
                        }
                }
                setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                        heightMode == MeasureSpec.EXACTLY ? heightSize : height);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


        /**
         * 与当前ViewGroup对应的LayoutParams
         */
        @Override
        public LayoutParams generateLayoutParams(AttributeSet attrs) {
                // TODO Auto-generated method stub

                return new MarginLayoutParams(getContext(), attrs);
        }
}
