package com.unisrobot.firstmodule.editprogram;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/26.
 */

public class PathMenuViewGroup extends ViewGroup {
        private View viewOne;

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

        private int viewGroupWidth;
        private Originate originate;
        private Paint paint;
        private int ScreenWidth, ScreenHeight, arcWidth;

        private enum Originate {
                LEFT_TOP, RIGHT_TOP,
                LEFT_BOTTOM, RIGHT_BOTTOM
        }

        private void initData() {
                ScreenWidth = ScreenUtil.getwindth(getContext());
                ScreenHeight = ScreenUtil.getheight(getContext());
                viewGroupWidth = ScreenWidth / 2;
                originate = Originate.LEFT_BOTTOM;
                paint = new Paint();
                paint.setColor(Color.parseColor("#626A72"));
                Log.e(TAG, "initData: viewGroupWidth====" + viewGroupWidth);
        }

        private int radio;

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int childCount = getChildCount();
                Log.e(TAG, "onLayout:  l===" + l + "   t=" + t + "    r=" + r + "   b=" + b);
                int angle = 90 / (childCount); // 每个角度
                Log.e(TAG, "onLayout: angle===" + Math.sin(30 * Math.PI / 180));
                for (int i = 0; i < childCount; i++) {
                        if (i == 0) {
                                View childAt = getChildAt(0);
                                int width = childAt.getMeasuredWidth();
                                int height = childAt.getMeasuredHeight();
                                Log.e(TAG, "onLayout:      width=" + width + "   height=" + height);
                                childAt.layout(0, viewGroupWidth - width, width, viewGroupWidth);
                                childAt.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                // v.setBackgroundResource(R.drawable.first_module_drawable_circle_green);
                                                startAnim(v);
                                                Log.e(TAG, "onClick: ====================");
                                        }
                                });
                        } else {
                                View childAt = getChildAt(i);
                                if (i == 1) {
                                        viewOne = childAt;
                                }
                                int width = childAt.getMeasuredWidth();
                                int height = childAt.getMeasuredHeight();
                                radio = Math.min(width, height) / 2;
                                arcWidth = viewGroupWidth - radio;
                                double viewAngle = angle * i * Math.PI / 180;
                                Log.e(TAG, "onLayout: viewAngle===" + viewAngle);
                                int x = (int) (arcWidth * Math.sin(viewAngle));
                                int y = (int) (arcWidth * Math.cos(viewAngle));
                                Log.e(TAG, "onLayout:  x=" + x + "    y=" + y + "   radio=" + radio);
                                int localX = x;
                                // 相对当前viewGroup的位置，不用管屏幕
                                int localY = viewGroupWidth - y;
                                childAt.setTag(i - 1);
                                if (i == 1) {
                                        endX = localX + radio;
                                        endY = localY + radio;
                                }
                                childAt.layout(localX - radio, localY - radio, localX + radio, localY + radio);
                                childAt.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                Log.e(TAG, "onClick: ");
                                                Integer tag = (Integer) v.getTag();
                                                Toast.makeText(v.getContext(), "I am " + tag, Toast.LENGTH_SHORT).show();
                                        }
                                });
                        }
                }

        }

        private int endX, endY;

        private void startAnim(final View homeView) {
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, homeView.getPivotX(), homeView.getPivotY());
                rotateAnimation.setDuration(1500);
                rotateAnimation.setFillAfter(true);
                homeView.startAnimation(rotateAnimation);

                TranslateAnimation translateAnimation = new TranslateAnimation(0, radio,viewGroupWidth,viewGroupWidth - radio );
                translateAnimation.setDuration(1500);
                translateAnimation.setFillAfter(true);
                viewOne.startAnimation(translateAnimation);
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
                measureChildren(widthMeasureSpec, heightMeasureSpec);
                //  Log.e(TAG, "onMeasure: childCount====" + childCount);
                for (int i = 0; i < childCount; i++) {
                        View childView = getChildAt(i);
                        int measuredWidth = childView.getMeasuredWidth();
                        int measuredHeight = childView.getMeasuredHeight();
                        realW += measuredWidth;
                        realH += measuredHeight;
                }
                //                realW += 100;
                //                realH += 100;
                if (modeW != MeasureSpec.EXACTLY) {
                        Log.e(TAG, "onMeasure: exactly-------------");
                        viewGroupWidth = realW;
                }
                // setMeasuredDimension(1000,1000);
                setMeasuredDimension(modeW == MeasureSpec.EXACTLY ? sizeW : viewGroupWidth, modeH == MeasureSpec.EXACTLY ? sizeH : viewGroupWidth);
        }


        @Override
        protected void dispatchDraw(Canvas canvas) {
                Log.e(TAG, "dispatchDraw: originate = " + originate);
                Log.e(TAG, "dispatchDraw: ScreenHeight = " + ScreenHeight + "   viewGroupWidth=" + viewGroupWidth);
                if (originate == Originate.LEFT_BOTTOM) {
                        Log.e(TAG, "dispatchDraw: -----------------");
                        //                        canvas.drawRect(0, ScreenHeight - viewGroupWidth, viewGroupWidth, ScreenHeight, paint);
                        canvas.drawRect(0, 0, viewGroupWidth, viewGroupWidth, paint);
                }
                super.dispatchDraw(canvas);
        }
}
