package com.unisrobot.firstmodule.editprogram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/4/26.
 */

public class PopArcViewGroup extends ViewGroup {
        private static final String TAG = "PopArcViewGroup";
        private int mRadius = 40; // 圆角的半径

        private int mBackgroundColor = Color.parseColor("#ffb1df83");
        private int mArrowOffset = 300;
        private int mArrowWidth = 90;
        private int mArrowHeight = 60;

        private int mShadowThickness = 50;
        private int mShadowColor = Color.parseColor("#ff0000");

        public PopArcViewGroup(Context context) {
                this(context, null);
        }

        public PopArcViewGroup(Context context, AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public PopArcViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData(attrs, defStyleAttr);
        }

        private void initData(AttributeSet attrs, int defStyleAttr) {

        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
                int count = getChildCount();
                int topOffset = t + mArrowHeight + mRadius / 2;
                int top = 0;
                int bottom = 0;
                Log.e(TAG, "onLayout: count========" + count);
                for (int i = 0; i < count; i++) {
                        final View child = getChildAt(i);
                        top = topOffset + i * child.getMeasuredHeight();
                        bottom = top + child.getMeasuredHeight();
                       // child.layout(l, top, r - mRadius / 2 - mShadowThickness, bottom);
                }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int count = getChildCount();
                int maxWidth = 0;
                // reserve space for the arrow and round corners
                int maxHeight = mArrowHeight + mRadius;
                for (int i = 0; i < count; i++) {
                        final View child = getChildAt(i);
                        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                        if (child.getVisibility() != GONE) {
                                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                                maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                                maxHeight = maxHeight + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                        }
                }

                maxWidth = maxWidth + getPaddingLeft() + getPaddingRight() + mShadowThickness;
                maxHeight = maxHeight + getPaddingTop() + getPaddingBottom() + mShadowThickness;

                setMeasuredDimension(maxWidth, maxHeight);
        }


        @Override
        public LayoutParams generateLayoutParams(AttributeSet attrs) {
                return new MarginLayoutParams(getContext(), attrs);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);

                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(mBackgroundColor);
                paint.setStyle(Paint.Style.FILL);

                // set Xfermode for source and shadow overlap
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));

                // draw round corner rectangle
                paint.setColor(mBackgroundColor);
                canvas.drawRoundRect(new RectF(0, mArrowHeight, getMeasuredWidth() - mShadowThickness, getMeasuredHeight() - mShadowThickness), mRadius, mRadius, paint);

                // draw arrow
                Path path = new Path();
                int startPoint = getWidth() - mArrowOffset;
                path.moveTo(startPoint, mArrowHeight);
                path.lineTo(startPoint + mArrowWidth, mArrowHeight);
                path.lineTo(startPoint + mArrowWidth / 2, 0);
                path.close();
                canvas.drawPath(path, paint);

                // draw shadow
                if (mShadowThickness > 0) {
                        //模糊效果。。。
                        Log.e(TAG, "dispatchDraw: mShadowThickness= "+mShadowThickness );
                        paint.setMaskFilter(new BlurMaskFilter(mShadowThickness, BlurMaskFilter.Blur.OUTER));
                        paint.setColor(mShadowColor);
                        canvas.drawRoundRect(new RectF(mShadowThickness, mArrowHeight + mShadowThickness, getMeasuredWidth() - mShadowThickness, getMeasuredHeight() - mShadowThickness), mRadius, mRadius, paint);
                }
                super.dispatchDraw(canvas);
        }
}
