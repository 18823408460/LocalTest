package com.uurobot.baseframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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
                                Log.d(TAG, "onScroll: " + distanceX + "    y=" + distanceY);
                                scrollBy((int) distanceX, 0); // 相对上一次的位置
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

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                Log.e(TAG, "onLayout: " + getWidth() + "    " + getHeight());
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                        View childAt = getChildAt(i);
                        childAt.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
                }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
                gestureDetector.onTouchEvent(event);
                return true;
        }
}
