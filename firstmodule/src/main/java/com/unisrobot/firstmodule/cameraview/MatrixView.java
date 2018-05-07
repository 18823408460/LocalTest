package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.BitmapUtil;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/5/7.
 */

/**
 * Matrix提供了Translate(平移)、Scale(缩放)、Rotate(旋转)、Skew(扭曲)四中变换操作
 * 除Translate(平移)外，Scale(缩放)、Rotate(旋转)、Skew(扭曲)都可以围绕一个中心点来进行，如果不指定，在默认情况下是围绕(0, 0)来进行相应的变换的
 * <p>
 * Matrix提供的四种操作，每一种都有pre、set、post三种形式。原因是矩阵乘法不满足乘法交换律，因此左乘还是右乘最终的效果都不一样。
 * 我们可以把Matrix变换想象成一个队列，队列里面包含了若干个变换操作，队列中每个操作按照先后顺序操作变换目标完成变换，pre相当于向队首增加一个操作，
 * post相当于向队尾增加一个操作，set相当于清空当前队列重新设置
 */
public class MatrixView extends View {


        public MatrixView(Context context) {
                this(context, null);
        }

        public MatrixView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private Bitmap bitmap;
        private Paint paint;

        private void initData() {
                defaultSize = ScreenUtil.dip2px(getContext(), 250);
                paint = new Paint();
                paint.setColor(Color.RED);
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_left_normal);
        }

        private int defaultSize;

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int modeW = MeasureSpec.getMode(widthMeasureSpec);
                int sizeW = MeasureSpec.getSize(widthMeasureSpec);

                setMeasuredDimension(modeW == MeasureSpec.EXACTLY ? sizeW : defaultSize, modeW == MeasureSpec.EXACTLY ? sizeW : defaultSize);
        }

        private static final String TAG = "MatrixView";

        @Override
        protected void onDraw(Canvas canvas) {
                int viewCenterX = getWidth() / 2;
                int viewCenterY = getHeight() / 2;
                canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
                Log.e(TAG, "onDraw: " + getWidth() + "    " + getHeight());


                Matrix matrix = new Matrix();
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(70);


                int bitmapcenterX = bitmap.getWidth()/2 ;
                int bitmapcenterY = bitmap.getHeight()/2 ;
                Log.e(TAG, "onDraw:bitmap  " + bitmapcenterX + "    " + bitmapcenterY);

                canvas.drawBitmap(bitmap,viewCenterX-bitmapcenterX, viewCenterY-bitmapcenterY,null);

                matrix.setTranslate(viewCenterX-bitmapcenterX,viewCenterY-bitmapcenterY);
               // matrix.preRotate(90);//默认是以当前view 的左上角为旋转中心
                matrix.postRotate(90,viewCenterX,viewCenterY);
                canvas.drawBitmap(bitmap,matrix,null);

                canvas.drawPoint(viewCenterX, viewCenterY, paint);



                //  matrix.setTranslate(centerX,centerY);
                //  matrix.preRotate(10);
                //  matrix.postRotate(20); //默认旋转中心左上角
                //  matrix.postRotate(centerX,centerY,2);
                // canvas.drawBitmap(bitmap,matrix,paint);
                //  canvas.drawBitmap(bitmap,0,0,paint);
        }
}
