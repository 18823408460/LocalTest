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

                                scrollBy((int) distanceX, 0); // 相对上一次的位置
                                return  false;
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

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                Log.e(TAG, "onLayout: " + getWidth() + "    " + getHeight());
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                        View childAt = getChildAt(i);
                        childAt.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());

                }
        }

        private int mCurrentItem = 0;
        float startX = 0;

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
                gestureDetector.onTouchEvent(event);

                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                startX = event.getX();
                                break;
                        case MotionEvent.ACTION_UP:
                                float endX = event.getX();
                                if ((startX - endX) > getWidth() / 2) {
                                        mCurrentItem++;

                                } else if ((endX - startX) > getWidth() / 2) {
                                        mCurrentItem--;
                                }
                                Log.e(TAG, "onTouchEvent: endX=" + endX + "    startX=" + startX + "      getWidth=" + getWidth() / 2);
                                scrollToItem(mCurrentItem);
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
                        pageSelectListenter.onSelect(mCurrentItem);
                }
                // scrollBy(distance, getScrollY());
                scroller.startScroll(getScrollX(), getScrollY(), distance, Math.abs(distance));
                invalidate();
                Log.e(TAG, "scrollToItem: ===========" + distance);
        }

        private Scroller scroller;

        @Override
        public void computeScroll() {
                //                super.computeScroll();
                int currX = scroller.getCurrX();
                if (scroller.computeScrollOffset()) {
                        scrollTo(currX, 0);
                        invalidate();

                } else { //滑动到位

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
