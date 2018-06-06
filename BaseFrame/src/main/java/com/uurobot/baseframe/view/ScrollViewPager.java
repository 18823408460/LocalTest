package com.uurobot.baseframe.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/6/6.
 */

public class ScrollViewPager extends ViewPager {
        public ScrollViewPager(Context context) {
                super(context);
        }

        public ScrollViewPager(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_UP) {
                        View view = viewOfClickOnScreen(ev);
                        if (view != null) {
                                int index = indexOfChild(view);
                                if (getCurrentItem() != index) {
                                        setCurrentItem(indexOfChild(view));
                                }
                        }
                }
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
                        @Override
                        public void onChildViewAdded(View parent, View child) {

                        }

                        @Override
                        public void onChildViewRemoved(View parent, View child) {

                        }
                });
                return super.dispatchTouchEvent(ev);
        }

        /**
         * @param ev
         * @return
         */
        private View viewOfClickOnScreen(MotionEvent ev) {
                int childCount = getChildCount();
                int[] location = new int[2];
                for (int i = 0; i < childCount; i++) {
                        View v = getChildAt(i);
                        v.getLocationOnScreen(location);

                        int minX = location[0];
                        int minY = getTop();

                        int maxX = location[0] + v.getWidth();
                        int maxY = getBottom();

                        float x = ev.getX();
                        float y = ev.getY();

                        if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                                return v;
                        }
                }
                return null;
        }
}
