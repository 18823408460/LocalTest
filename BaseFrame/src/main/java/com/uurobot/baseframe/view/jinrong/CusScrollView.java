package com.uurobot.baseframe.view.jinrong;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by WEI on 2018/6/6.
 */
// 上下到顶后可以多余 滑动，，同时 解决 viewpager 和 自己的冲突
public class CusScrollView extends ScrollView {
        public CusScrollView(Context context) {
                this(context, null);
        }

        public CusScrollView(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public CusScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                scroller = new Scroller(getContext());
        }

        private float lastY;
        private View view;

        @Override
        protected void onFinishInflate() {
                super.onFinishInflate();
                if (getChildCount() > 0) {
                        view = getChildAt(0);
                }
        }

        private Scroller scroller;
        private Rect rect = new Rect();

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
                if (view == null) {
                        return super.onTouchEvent(ev);
                }

                float eventY = ev.getY();
                switch (ev.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                lastY = eventY;
                                break;
                        case MotionEvent.ACTION_UP:
                                if (!rect.isEmpty()) {
                                        layout(rect.left, rect.top, rect.right, rect.bottom);
                                        rect.setEmpty();
                                        //  scroller.startScroll(getScrollX(),getTop(),0,rect.bottom - getTop());
                                }
                                break;
                        case MotionEvent.ACTION_MOVE:
                                int diff = (int) (eventY - lastY);
                                if (isNeedScorll()) {
                                        if (rect.isEmpty()) {
                                                rect.set(getLeft(), getTop(), getRight(), getBottom());
                                        }
                                        layout(getLeft(), getTop() + diff / 2, getRight(), getBottom() + diff / 2);
                                }
                                lastY = eventY;

                }
                return super.onTouchEvent(ev);
        }


        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
                return super.onInterceptTouchEvent(ev);
        }

        @Override
        public void computeScroll() {
//                Log.e(TAG, "computeScroll: " );
//                if (scroller.computeScrollOffset()) {
//                        Log.e(TAG, "computeScroll: 1111");
//                        int currY = scroller.getCurrY();
//                        scrollTo(0, currY);
//                        invalidate();
//                }
        }

        private static final String TAG = CusScrollView.class.getSimpleName();

        private boolean isNeedScorll() {
                int scrollY = getScrollY();// (上+ 下 -)
                if (scrollY <= 0) { // 不能往下面滑动了
                        Log.e(TAG, "isNeedScorll: can  not scrool down");
                        return true;
                }
                int childMeasuredHeight = view.getMeasuredHeight();
                int parentMeasuredHeight = getMeasuredHeight();
                int diffHeight = childMeasuredHeight - parentMeasuredHeight;
                ;
                if (scrollY >= diffHeight) {
                        Log.e(TAG, "isNeedScorll: can  not scrool up");
                        return true;
                }
                return false;
        }
}
