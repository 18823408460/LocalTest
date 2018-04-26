package com.unisrobot.firstmodule.editprogram;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/4/26.
 */

/**
 * padding 还没有实现。。
 */
public class NinePhoneViewGroup extends ViewGroup {
        private static final String TAG = "NinePhoneViewGroup";

        public NinePhoneViewGroup(Context context) {
                this(context, null);
        }

        public NinePhoneViewGroup(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public NinePhoneViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                Log.e(TAG, "NinePhoneViewGroup: ");
                View addPhotoView = new View(context);
                addPhotoView.setTag("addTag");
                addPhotoView.setBackgroundResource(R.drawable.btn_radio_off);
                addView(addPhotoView);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int childCount = getChildCount();
                // Log.e(TAG, "onLayout: ======= childCount=" + childCount);
                // Log.e(TAG, "onLayout: ======= childWidth=" + childWidth + "   childHeight=" + childHeight);
                int left = 0, top = 0;

                for (int i = 0; i < childCount; i++) {
                        View childAt = getChildAt(i);
                        if (i % 3 == 0) { //第一个
                                left = hSpace;
                                top = (i / 3) * childHeight;
                        } else {
                                left = childWidth * (i % 3)+ (i % 3+1)*hSpace;
                        }
                        // Log.e(TAG, "onLayout: left="+left + "   top="+top);
                        childAt.layout(left, top, left + childWidth, top + childHeight);
                        Log.e(TAG, "onLayout: count=" + count + "     i=" + i + "   tag=" + childAt.getTag() + "    childCount=" + childCount);
                        if (i == (childCount - 1) && count < mixNum) {
                                childAt.setBackgroundResource(R.drawable.btn_radio_off);
                                childAt.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                addPhoneClick();
                                        }
                                });
                        } else {
                                childAt.setBackgroundResource(R.drawable.jog_tab_left_confirm_yellow);
                                childAt.setOnClickListener(null);
                        }
                }
        }


        private int hSpace = 100, vSpace = 100;
        private int childWidth, childHeight;

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
                int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

                int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
                int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
                // wrap_content , 一开始的取值为全屏= mathc_parent
                Log.e(TAG, "onMeasure: sizeWidth=" + sizeWidth + "   sizeHeight=" + sizeHeight);
                int childCount = getChildCount();
                childWidth = (sizeWidth -hSpace*4) / 3;
                childHeight = childWidth;

                int lastWidth = sizeWidth;
                int lastHeight = sizeHeight;
                if (childCount < 3) {
                        lastWidth = childCount * childWidth+( (childCount+1)*hSpace );
                        lastHeight = childHeight;
                } else {
                        int diff = (childCount % 3 == 0 ? 0 : 1);
                        lastHeight = (childCount / 3 + diff) * childHeight;
                }
                setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : lastWidth,
                        modeHeight == MeasureSpec.EXACTLY ? sizeHeight : lastHeight);
                //                setMeasuredDimension(300,300);

                // 这个必须去掉
                // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        }

        private static final String[] choiceMsg = {"Take from phone", "Take from camera"};

        private void addPhoneClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setItems(choiceMsg, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                addPhone();
                        }
                });
                builder.show();
        }

        private static final int mixNum = 9;
        private int count = 0;

        private void addPhone() {
                if (count < (mixNum - 1)) {
                        View view = new View(getContext());
                        view.setBackgroundResource(R.drawable.jog_tab_left_confirm_red);
                        addView(view);
                        Log.e(TAG, "addPhone: ");
                        // 下面两个方法为啥都要调用
                        requestLayout();
                        invalidate();
                } else if (count == (mixNum - 1)) {
                        View childAt = getChildAt(mixNum - 1);
                        childAt.setBackgroundResource(R.drawable.jog_tab_left_confirm_red);
                        requestLayout();
                        invalidate();
                        Log.e(TAG, "addPhone: =========================");
                }
                count++;
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
                Log.e(TAG, "dispatchDraw: " );
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);
                super.dispatchDraw(canvas);
        }
}
