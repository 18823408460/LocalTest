package com.uurobot.baseframe.view.jinrong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/6/8.
 */

public class WaveView extends View {
        private Paint paint;

        public WaveView(Context context) {
                this(context, null);
        }

        public WaveView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private static final String TAG = WaveView.class.getSimpleName();
        private int downX;
        private int downY;
        private int radio;
        private Handler handler;

        private void initData() {
                downX = 100;
                downY = 100;
                radio = 1;

                paint = new Paint();
                paint.setStrokeWidth(radio / 3);
                paint.setColor(Color.parseColor("#00ffff"));
                handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                                radio += 9;
                                int alpha = paint.getAlpha();
                                alpha -= 6;
                                paint.setAlpha(alpha);
                                Log.e(TAG, "handleMessage: " + alpha);
                                if (alpha <= 0) {

                                } else {
                                        sendEmptyMessageDelayed(1, 50);
                                        invalidate();
                                }
                        }
                };
                handler.sendEmptyMessageDelayed(1, 100);
        }


        @Override
        protected void onDraw(Canvas canvas) {
                Log.e(TAG, "onDraw: ================ ");
                canvas.drawCircle(downX, downY, radio, paint);
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                break;
                        case MotionEvent.ACTION_UP:
                                downX = (int) event.getX();
                                downY = (int) event.getY();
                                radio = 1;
                                paint.reset();
                                paint.setStrokeWidth(radio / 3);
                                paint.setColor(Color.parseColor("#00ffff"));
                                handler.removeCallbacksAndMessages(null);
                                handler.sendEmptyMessageDelayed(1, 100);
                                break;
                }
                return true;
        }
}
