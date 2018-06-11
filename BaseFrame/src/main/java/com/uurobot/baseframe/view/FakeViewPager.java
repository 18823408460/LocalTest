package com.uurobot.baseframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.io.File;

/**
 * Created by Administrator on 2018/6/1.
 */

public class FakeViewPager extends ViewGroup {
        private static final String TAG = FakeViewPager.class.getSimpleName();
        private GestureDetector gestureDetector;

        public FakeViewPager(Context context) {
                this(context, null);
        }

        public FakeViewPager(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public FakeViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        int measuredWidth;

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                        getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
                }
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);

                measuredWidth = getMeasuredWidth();
        }

        private void initData() {
                scroller = new Scroller(getContext());
                gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
                        @Override
                        public boolean onDown(MotionEvent e) {
                                Log.e(TAG, "onDown: ");
                                return false;
                        }

                        @Override
                        public void onShowPress(MotionEvent e) {
                                Log.d(TAG, "onShowPress: ");
                        }

                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                                Log.d(TAG, "onSingleTapUp: ");
                                return false;
                        }

                        @Override
                        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                                Log.d(TAG, "onScroll: " + distanceX + "    getScrollX=" + getScrollX());

                                scrollBy((int) distanceX, 0);
                                return false;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {
                                Log.d(TAG, "onLongPress: ");
                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                                Log.d(TAG, "onFling: ");
                                return false;
                        }
                });
        }

        int childCount;

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                Log.e(TAG, "onLayout: " + getWidth() + "    " + getHeight());
                childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                        View childAt = getChildAt(i);
                        childAt.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());

                }
        }

        /**
         * true === 拦截
         * false === 往下分发，不拦截
         *
         * @param ev
         * @return
         */
        private float downX;
        private float downY;

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
                // gestureDetector.onTouchEvent(ev);
                boolean result = false; // 默认不拦截
                switch (ev.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                downX = ev.getX();
                                downY = ev.getY();
                                break;
                        case MotionEvent.ACTION_UP:
                                break;
                        case MotionEvent.ACTION_MOVE:
                                float x = ev.getX();
                                float y = ev.getY();

                                float distanceX = Math.abs(x - downX);
                                float distanceY = Math.abs(y - downY);
                                if (distanceX > distanceY && distanceX > 10) { // 水平方向滑动，拦截
                                        //反拦截
                                        //   getParent().requestDisallowInterceptTouchEvent(true);
                                        result = true;
                                }
                                break;
                }
                return result;
        }

        private int mCurrentItem = 0;
        float startX = 0;

        @Override // 偏移量 ， 坐标， 这两个概念要区分。。
        public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
                // gestureDetector.onTouchEvent(event);

                switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                                float endX = event.getX();
                                int distance = (int) (endX - startX);
                                int toScroll = getScrollX() - distance;

                                // 边界判断
                                if (toScroll < 0) {
                                        toScroll = 0;
                                } else if (toScroll > (childCount - 1) * measuredWidth) {
                                        toScroll = (childCount - 1) * measuredWidth;
                                }
                                scrollTo(toScroll, 0);
                                startX = event.getX();
                                break;
                        case MotionEvent.ACTION_DOWN:
                                downX = startX = event.getX();
                                break;
                        case MotionEvent.ACTION_UP:
                                endX = event.getX();
                                int dis = (int) (endX - downX);
                                int dd = Math.abs(dis);

                                //   if (getScrollX() - distance < 0){
                                //                                        return true;
                                //                                }
                                //
                                if ((downX - endX) > getWidth() / 2) {
                                        mCurrentItem++;

                                } else if ((endX - downX) > getWidth() / 2) {
                                        mCurrentItem--;
                                }
                                //                                Log.e(TAG, "onTouchEvent up: endX=" + endX + "    startX=" + startX + "      getWidth=" + getWidth() / 2);
                                //
                                scrollToItem(mCurrentItem);
                                //                                startX = endX;
                                break;
                }
                return true;
        }

        public void scrollToItem(int mCurrentItem) {
                if (mCurrentItem < 0) {
                        mCurrentItem = 0;
                } else if (mCurrentItem > (getChildCount() - 1)) {
                        mCurrentItem = getChildCount() - 1;
                }
                int distance = (mCurrentItem * getWidth()) - getScrollX();

                if (pageSelectListenter != null) {
                        Log.e(TAG, "scrollToItem: ==== " + mCurrentItem);
                        pageSelectListenter.onSelect(mCurrentItem);
                }
                Log.e(TAG, "scrollToItem: ===========" + distance);
                isScroll = true;
                scroller.startScroll(getScrollX(), getScrollY(), distance, 0, Math.abs(distance));
                invalidate();
        }

        private Scroller scroller;
        private boolean isScroll = false;

        @Override
        public void computeScroll() {
                if (scroller.computeScrollOffset()) {
                        scrollTo(scroller.getCurrX(), 0);
                        invalidate();

                } else { //滑动到位
                        isScroll = false;
                        // Log.e(TAG, "computeScroll: end" );
                }
        }

        private PageSelectListenter pageSelectListenter;

        public void setPageSelectListenter(PageSelectListenter pageSelectListenter) {
                this.pageSelectListenter = pageSelectListenter;
        }

        public interface PageSelectListenter {
                void onSelect(int position);
        }
}
