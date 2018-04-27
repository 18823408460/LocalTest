package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.unisrobot.firstmodule.utils.ScreenUtil;

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
                requestLayout();
                invalidate();
        }

        private int width;
        private Originate originate;
        private Paint paint;
        private int ScreenWindth, ScreenHeight ;

        private enum Originate {
                LEFT_TOP, RIGHT_TOP,
                LEFT_BOTTOM, RIGHT_BOTTOM
        }

        private void initData() {
                ScreenWindth = ScreenUtil.getwindth(getContext());
                ScreenHeight = ScreenUtil.getheight(getContext());
                width = ScreenWindth / 3;
                originate = Originate.LEFT_BOTTOM;
                paint = new Paint();
                paint.setColor(Color.RED);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int childCount = getChildCount();
                Log.e(TAG, "onLayout:  childCount==="+childCount );
                for (int i = 0; i < childCount; i++) {
                        if (i==0){
                                View childAt = getChildAt(0);
                                int width = childAt.getWidth();
                                int height = childAt.getHeight();
                                childAt.layout(0,ScreenHeight-height,width,ScreenHeight);
                        }
                }

        }


        private static final String TAG = "PathMenuViewGroup";

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int modeW = MeasureSpec.getMode(widthMeasureSpec);
                int sizeW = MeasureSpec.getSize(widthMeasureSpec);

                int modeH = MeasureSpec.getMode(heightMeasureSpec);
                int sizeH = MeasureSpec.getSize(heightMeasureSpec);

                int realH = 0, realW = 0;
                int childCount = getChildCount();
                Log.e(TAG, "onMeasure: childCount====" + childCount);
                for (int i = 0; i < childCount; i++) {
                        View childView = getChildAt(i);

                }

                setMeasuredDimension(modeW == MeasureSpec.EXACTLY ? sizeW : width, modeH == MeasureSpec.EXACTLY ? sizeH : width);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
                Log.e(TAG, "dispatchDraw: originate = "+originate );
                if (originate == Originate.LEFT_BOTTOM) {
                        canvas.drawRect(0, ScreenHeight - width, width, ScreenHeight, paint);
                }
                super.dispatchDraw(canvas);
        }
}
