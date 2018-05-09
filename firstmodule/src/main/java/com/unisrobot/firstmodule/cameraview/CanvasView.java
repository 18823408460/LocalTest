package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/5/8.
 */

public class CanvasView extends View {
        public CanvasView(Context context) {
                this(context,null);
        }

        public CanvasView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs,-1);
        }

        public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private Paint paint ;
        private void initData() {
                paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setTextSize(100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
//                drawTest(canvas);
                canvas.save();
                canvas.drawRect(new Rect(0, 0, 100, 100), paint);
                canvas.translate(100, 100);
                canvas.drawRect(new Rect(0, 0, 100, 100), paint);

                canvas.rotate(45);
//                canvas.translate(300, 300);
                canvas.drawRect(new Rect(0, 0, 100, 100), paint);
                canvas.translate(300,300);
                canvas.drawRect(new Rect(0, 0, 100, 100), paint);
                canvas.restore(); //restore 后，画布的原点  恢复到 左上角-

                paint.setColor(Color.RED);
                canvas.translate(300,0);
                canvas.drawRect(new Rect(0, 0, 100, 100), paint);
        }

        private void drawTest(Canvas canvas) {
                float x = getX();
                float y = getY();

                // y=0 被状态栏遮挡
                canvas.drawText("normal",0,100,paint);

                canvas.rotate(90);
                // 旋转后整个坐标系也跟着转了
                canvas.drawText("restore90",100,-100,paint);


                canvas.translate(100,-300);
                canvas.drawText("translate",100,-100,paint);

                //                canvas.save();
                //                canvas.restore();
                canvas.drawText("save restore",0,100,paint);
        }
}
