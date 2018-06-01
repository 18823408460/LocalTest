package com.uurobot.baseframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/6/1.
 */

public class FakeViewPager extends ViewGroup {
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
                                return false;
                        }

                        @Override
                        public void onShowPress(MotionEvent e) {

                        }

                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                                return false;
                        }

                        @Override
                        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                                return false;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {

                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                                return false;
                        }
                });
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                        View childAt = getChildAt(i);
                }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
                gestureDetector.onTouchEvent(event);
                return true;
        }
}
