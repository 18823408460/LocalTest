package com.unisrobot.firstmodule.threadD_Animal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ThreadDView extends View {
        public ThreadDView(Context context) {
                this(context, null);
        }

        public ThreadDView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public ThreadDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private Bitmap bitmap;

        @SuppressLint("ResourceType")
        private void initData() {
                bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.people));
        }

        private static final String TAG = "ThreadDView";

        @Override
        protected void onDraw(Canvas canvas) {
                Log.e(TAG, "onDraw: " + bitmap.getWidth() + "   " + bitmap.getHeight());
                canvas.drawBitmap(bitmap,100,100,null);
        }
}
