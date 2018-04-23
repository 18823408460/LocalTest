package com.unisrobot.firstmodule.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by Administrator on 2018/4/23.
 */

public class WaveProgress extends View {
      
      public WaveProgress(Context context) {
            this(context, null);
      }
      
      public WaveProgress(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, -1);
      }
      
      public WaveProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context = context;
            initData();
      }
      
      private Context context;
      
      private long centerX, centerY;
      
      private long radio;
      
      private Paint roundPaint, progressPaint;
      
      private void initData() {
            centerX = ScreenUtil.getwindth(context) / 2;
            centerY = ScreenUtil.getheight(context) / 2;
            radio = 200;
            
            roundPaint = new Paint();
            roundPaint.setAntiAlias(true);
            roundPaint.setColor(Color.RED);
            
            progressPaint = new Paint();
            progressPaint.setAntiAlias(true);
            progressPaint.setColor(Color.BLUE);
            progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      }
      
      
      private int maxProgress = 1;
      
      private int progress = 0;
      
      @Override
      protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(centerX, centerY, radio, roundPaint);
            
            Bitmap bitmap = Bitmap.createBitmap((int) radio * 2, (int) radio * 2, Bitmap.Config.ARGB_8888);
            Canvas pCanvas = new Canvas(bitmap);
            pCanvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, radio, progressPaint);
            
            float percent = progress * 1.0f / maxProgress;
            int count = (int) (radio * 4 / 60);
            //控制-控制点y的坐标
            float point = (1 - percent) * 15;
            Path path = new Path();
            for (int i = 0; i < count; i++) {
                  // rQuadTo 绘制贝塞尔曲线，传入两个点
                  path.rQuadTo(15, -point, 30, 0);
                  path.rQuadTo(15, point, 30, 0);
            }
      }
}
