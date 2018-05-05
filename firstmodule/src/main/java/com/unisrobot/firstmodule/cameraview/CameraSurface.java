package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CameraSurface extends SurfaceView   {
        private int defaultSize = 400 ;
        public CameraSurface(Context context) {
                this(context,null);
        }

        public CameraSurface(Context context, AttributeSet attrs) {
                this(context, attrs,-1);
        }

        public CameraSurface(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int modeW = MeasureSpec.getMode(widthMeasureSpec);
                int sizeW = MeasureSpec.getSize(widthMeasureSpec);

                setMeasuredDimension(modeW==MeasureSpec.EXACTLY?sizeW:defaultSize,modeW==MeasureSpec.EXACTLY?sizeW:defaultSize);
        }

        private Paint paint ;
        private void initData() {
                paint =new Paint();
                paint.setStrokeWidth(30);
                paint.setColor(Color.RED);
        }

        private static final String TAG = "CameraSurface";
        @Override
        protected void onDraw(Canvas canvas) {
                int min = Math.min(getWidth(), getHeight());
//                canvas.drawCircle(getWidth()/2,getHeight()/2,min/2,paint);
                Log.e(TAG, "onDraw: "+ getWidth()+ "     " +getHeight());
                Path path = new Path();
                path.addCircle(getWidth()/2,getHeight()/2,min/2, Path.Direction.CCW);
                canvas.clipPath(path, Region.Op.REPLACE);
                super.onDraw(canvas);

        }
}
