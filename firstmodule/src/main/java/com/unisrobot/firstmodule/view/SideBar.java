package com.unisrobot.firstmodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/17.
 */

public class SideBar extends View {

        public SideBar(Context context) {
                this(context, null);
        }

        public SideBar(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                this.context = context;
                initData();

        }

        private Paint paint ,showPaint;

        private TextPaint textPaint,showTPaint;

        private Context context;

        private int barWidth;

        private int barHeight;

        private int radio = 8;

        private float singleTextHeight, singleTextWidth;

        private int startX,textRadio;
        private int startY,centerX,centerY;

        public static String[] A_Z = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z", "#"};

        private void initData() {
                paint = new Paint();
                paint.setColor(Color.parseColor("#3d4e6e"));
                showPaint = new Paint();
                showPaint.setColor(Color.parseColor("#393c3e"));
                int getheight = ScreenUtil.getheight(context);
                int getwindth = ScreenUtil.getwindth(context);
                barHeight = getheight - 80;
                barWidth = getwindth / 26;
                textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
                textPaint.setColor(Color.GREEN);
                textPaint.setTextSize(ScreenUtil.dp2px(context, 14));

                showTPaint = new TextPaint();
                showTPaint.setAntiAlias(true);
                showTPaint.setColor(Color.GREEN);
                showTPaint.setTextSize(ScreenUtil.dp2px(context, 30));

                singleTextHeight = barHeight / A_Z.length - 2;

                startX = getwindth - barWidth - 10 ;
                startY = 10 ;
                centerX = getwindth/2 ;
                centerY = getheight/2;
                textRadio = 100 ;
        }

        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                canvas.drawRoundRect(startX, startY, barWidth + startX, barHeight, radio, radio, paint);
                for (int i = 0, len = A_Z.length; i < len; i++) {
                        float x = barWidth / 2 - textPaint.measureText(A_Z[i]) / 2 + startX;
                        float y = singleTextHeight * i + singleTextHeight;
                        canvas.drawText(A_Z[i], x, y, textPaint);
                }
                if (show){
                        canvas.drawRoundRect(centerX-textRadio,centerY-textRadio,centerX+textRadio,centerY+textRadio,radio,radio,showPaint);
                        float x = centerX-showTPaint.measureText(text)/2;
                        Paint.FontMetrics fontMetrics = showTPaint.getFontMetrics();
                        float y = centerY-(fontMetrics.ascent +fontMetrics.descent)/2;
                        canvas.drawText(text,x,y,showTPaint);
                }
        }
        private boolean show =false ;
        private String text ;
        private static final String TAG = "SideBar";
        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
                float y = event.getY();
                float x = event.getX();
                if (x < startX ){
                        if (show){
                                show = false ;
                                postInvalidate();
                        }
                       return  super.dispatchTouchEvent(event);
                }
                int action = event.getAction();
                int pos = (int) (y / barHeight * A_Z.length);
                if (pos>= A_Z.length){
                        return  super.dispatchTouchEvent(event);
                }
                text = A_Z[pos];
                switch (action) {
                        case MotionEvent.ACTION_UP:
                                show = false ;
                                Log.e(TAG, "dispatchTouchEvent:  up" );
                                break;
                        case MotionEvent.ACTION_DOWN:
                                show = true ;
                                Log.e(TAG, "dispatchTouchEvent: down" );
                                break;
                        case MotionEvent.ACTION_MOVE:
                                Log.e(TAG, "dispatchTouchEvent: move" );
                                break;
                }
                postInvalidate();
                return true;
        }

//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//                Log.e(TAG, "onTouchEvent: "+event.getX() );
//                return super.onTouchEvent(event);
//        }
}
