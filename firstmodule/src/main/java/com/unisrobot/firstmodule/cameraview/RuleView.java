package com.unisrobot.firstmodule.cameraview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RuleView extends View {
        public RuleView(Context context) {
                this(context, null);
        }

        public RuleView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public RuleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private static final String TAG = "RuleView";
        private int ruleWidth;
        private int ruleHeight = 400;
        private int pading = 30; //左右间隙
        private Paint longPaint;
        private Paint shortPaint;
        private Paint rulePaint;
        private TextPaint textPaint;
        private int measureLenght = 5;
        private int longHeight = 150;
        private int shortHeight = 75;
        private int singleWight;

        private void initData() {
                ruleWidth = ScreenUtil.getwindth(getContext()) - pading * 2;
                longPaint = new Paint();
                longPaint.setAntiAlias(true);
                longPaint.setStrokeWidth(8);
                longPaint.setColor(Color.BLUE);

                shortPaint = new Paint();
                shortPaint.setAntiAlias(true);
                shortPaint.setStrokeWidth(4);
                shortPaint.setColor(Color.RED);

                rulePaint = new Paint();
                rulePaint.setStrokeWidth(4);
                rulePaint.setAntiAlias(true);
                rulePaint.setStyle(Paint.Style.STROKE);
                rulePaint.setColor(Color.BLACK);

                textPaint = new TextPaint();
                textPaint.setTextSize(100);
                textPaint.setColor(Color.BLACK);
                singleWight = (ruleWidth - pading * 2) / (measureLenght * 10);
                Log.e(TAG, "initData: singleWight  = " + singleWight);
        }

        @Override
        protected void onDraw(Canvas canvas) {
                canvas.drawRect(pading, pading, ruleWidth + pading, ruleHeight + pading, rulePaint);

                for (int i = 0; i <=measureLenght * 10; i++) {
                        if (i % 10 == 0) {
                                float startX = pading*2 + i*singleWight;
                                float startY = ruleHeight + pading - longHeight ;
                                float endX = startX;
                                float engY  = ruleHeight + pading;
                                canvas.drawLine(startX,startY,endX,engY,longPaint);

                                String  text = String.valueOf(i /10 );
                                float textWidth = textPaint.measureText(text);
                                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                                float textHeight = fontMetrics.descent - fontMetrics.ascent;
                                Log.e(TAG, "onDraw: textHeight===="+textHeight );
                                float textX = startX - textWidth/2 ;

                                // 这里有个问题？？？
                                float textY = startY - textHeight/2;
                                canvas.drawText(text,textX,textY,textPaint);

                        } else {
                                float startX = pading*2 + i*singleWight;
                                float startY = ruleHeight + pading - shortHeight ;
                                float endX = startX;
                                float engY  = ruleHeight + pading;
                                canvas.drawLine(startX,startY,endX,engY,shortPaint);
                        }
                }
        }
}
