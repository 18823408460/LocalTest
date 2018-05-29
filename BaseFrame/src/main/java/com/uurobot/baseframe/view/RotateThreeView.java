package com.uurobot.baseframe.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.DataUtils;


/**
 * Created by Administrator on 2018/5/29.
 */

/**
 * 该view 有三个 bitmap 叠加显示，中间那个旋转
 */
public class RotateThreeView extends View {
        private static final String TAG = RotateAnimation.class.getSimpleName();
        private int resId1;
        private int resId2;
        private int resId3;
        private float viewWidth;
        private Bitmap bitmap1;
        private Bitmap bitmap2;
        private Bitmap bitmap3;
        private float angle = 0;
        private ValueAnimator valueAnimator;

        public RotateThreeView(Context context) {
                this(context, null);
        }

        public RotateThreeView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public RotateThreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initTypeValue(attrs);
               // mockData();
                initData();
        }

        private void initTypeValue(AttributeSet attrs) {
                TypedArray typedArray = null;
                try {
                        typedArray = getResources().obtainAttributes(attrs, R.styleable.rotate_three_view);
                        resId1 = typedArray.getResourceId(R.styleable.rotate_three_view_resId1, 0);
                        resId2 = typedArray.getResourceId(R.styleable.rotate_three_view_resId2, 0);
                        resId3 = typedArray.getResourceId(R.styleable.rotate_three_view_resId3, 0);
                } finally {
                        typedArray.recycle();
                }
        }


        private void mockData() {
                resId1 = R.drawable.activity_smartdoctor_lizi;
                resId2 = R.drawable.activity_smartdoctor_center2;
                resId3 = R.drawable.icon_size;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                setMeasuredDimension((int) viewWidth, (int) viewWidth);
        }


        @Override
        protected void onDetachedFromWindow() {
                super.onDetachedFromWindow();
        }

        @Override
        protected void onAttachedToWindow() {
                super.onAttachedToWindow();
        }


        @Override
        protected void onWindowVisibilityChanged(int visibility) {
                super.onWindowVisibilityChanged(visibility);
                if (visibility == VISIBLE) {
                        if (!valueAnimator.isStarted() || !valueAnimator.isRunning()) {
                                valueAnimator.start();
                        }
                        Log.e(TAG, "onWindowVisibilityChanged: VISIBLE");
                } else if (visibility == INVISIBLE) {
                        Log.e(TAG, "onWindowVisibilityChanged: INVISIBLE");
                        if (valueAnimator.isRunning()) {
                                valueAnimator.cancel();
                        }
                } else if (visibility == GONE) {
                        if (valueAnimator.isRunning()) {
                                valueAnimator.cancel();
                        }
                        Log.e(TAG, "onWindowVisibilityChanged: GONE");
                }

        }

        private void initData() {
                bitmap1 = BitmapFactory.decodeStream(getResources().openRawResource(resId1));
                bitmap2 = BitmapFactory.decodeStream(getResources().openRawResource(resId2));
                bitmap3 = BitmapFactory.decodeStream(getResources().openRawResource(resId3));
                viewWidth = Math.max(DataUtils.getBitmapMaxWidth(bitmap1), Math.max(DataUtils.getBitmapMaxWidth(bitmap2),
                        DataUtils.getBitmapMaxWidth(bitmap3)));
                initAnim();
        }


        private void initAnim() {
                valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                                float animatedValue = (float) animation.getAnimatedValue();
                                angle += 2;
                                Log.e(TAG, "onAnimationUpdate: " + angle);
                                invalidate();
                        }
                });
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.setDuration(4000);

        }


        @Override
        protected void onDraw(Canvas canvas) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                canvas.drawBitmap(bitmap1, centerX - bitmap1.getWidth() / 2, centerY - bitmap1.getHeight() / 2, null);
                Matrix matrix = new Matrix();
                matrix.setRotate(angle, bitmap2.getWidth() / 2, bitmap2.getHeight() / 2);
                canvas.drawBitmap(bitmap2, matrix, null);
                canvas.drawBitmap(bitmap3, centerX - bitmap3.getWidth() / 2, centerY - bitmap3.getHeight() / 2, null);
        }
}
