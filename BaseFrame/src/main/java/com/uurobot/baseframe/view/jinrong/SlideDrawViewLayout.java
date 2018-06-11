package com.uurobot.baseframe.view.jinrong;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by Administrator on 2018/6/8.
 */

public class SlideDrawViewLayout extends ViewGroup {
        private static final String TAG = SlideDrawViewLayout.class.getSimpleName();
        private int screenW;
        private int screenH;

        public SlideDrawViewLayout(Context context) {
                this(context, null);
        }

        public SlideDrawViewLayout(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public SlideDrawViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                Point screen = SizeUtil.getScreen();
                screenW = screen.x;
                screenH = screen.y;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) { // 这里不调用，下面获取测量宽高
                        getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
                }
                super.onMeasure(widthMeasureSpec,heightMeasureSpec);

                View slideView = getChildAt(1);
                slideViewMeasuredWidth = slideView.getMeasuredWidth();
                int slideViewMeasuredHeight = slideView.getMeasuredHeight();
                Log.e(TAG, "onMeasure: slideViewMeasuredWidth=" + slideViewMeasuredWidth + "  slideViewMeasuredHeight=" + slideViewMeasuredHeight);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                Log.e(TAG, "onLayout: getChildCount = " + getChildCount());
                View contentView = getChildAt(0);
                View slideView = getChildAt(1);
                // 这里获取不到测量宽高
                int contentViewMeasuredWidth = contentView.getMeasuredWidth();
                int contentViewMeasuredHeight = contentView.getMeasuredHeight();
                Log.e(TAG, "onLayout: contentViewMeasuredWidth=" + contentViewMeasuredWidth + "  contentViewMeasuredHeight=" + contentViewMeasuredHeight);
                contentView.layout(0, 0, contentViewMeasuredWidth, contentViewMeasuredHeight);

                slideViewMeasuredWidth = slideView.getMeasuredWidth();
                int slideViewMeasuredHeight = slideView.getMeasuredHeight();
                Log.e(TAG, "onLayout: slideViewMeasuredWidth=" + slideViewMeasuredWidth + "  slideViewMeasuredHeight=" + slideViewMeasuredHeight);
                slideView.layout(-slideViewMeasuredWidth, 0, 0, slideViewMeasuredHeight);
        }

        private float lastX;
        private int slideViewMeasuredWidth;

        @Override

        public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                lastX = event.getX();
                                break;
                        case MotionEvent.ACTION_UP:
                                break;
                        case MotionEvent.ACTION_MOVE:
                                int scrollX = getScrollX();
                                Log.e(TAG, "onTouchEvent: scrollX= " + scrollX + "    " + slideViewMeasuredWidth);
                                float moveX = event.getX();
                                int distance = (int) (moveX - lastX);
                                int diffX = (int) (scrollX - distance);
                                if (diffX >0){
                                        diffX = 0;
                                }else if( diffX < -getWidth()){
                                        diffX = -getWidth();
                                }
                                scrollTo(diffX, getScrollY());
                                lastX = moveX;
                                break;
                }
                return true;
        }
}
