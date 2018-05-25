package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.AnimIds;
import com.uurobot.baseframe.utils.EAnimType;

/**
 * Created by Administrator on 2018/5/25.
 */

public class AnimImageView extends AppCompatImageView {
        private static final String TAG = AnimImageView.class.getSimpleName();
        private EAnimType eAnimType = EAnimType.LowShao;
        private Handler handler;

        public AnimImageView(Context context) {
                this(context, null);
        }

        public AnimImageView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public AnimImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
                initTypeVuale(attrs);
        }

        private void initData() {
                handler = new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                                showAnim();
                                sendEmptyMessageDelayed(NextAnim, 250);
                        }
                };
                setBackgroundResource(R.drawable.dry0);
        }

        private int index = 0;
        private static final int NextAnim = 0;

        private void showAnim() {
                int bitmapRes = AnimIds.getBitmapRes(index++, eAnimType);
                setBackgroundResource(bitmapRes);
        }

        public void startAnim() {
                index = 0;
                handler.sendEmptyMessage(NextAnim);
        }

        public void stopAnim() {
                handler.removeMessages(NextAnim);
        }

        public void updateAnim(EAnimType eAnimType) {
                index = 0;
                this.eAnimType = eAnimType;
                handler.sendEmptyMessage(NextAnim);
        }

        private void initTypeVuale(AttributeSet attrs) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SurfaceViewAnim);
                int anInt = typedArray.getInt(R.styleable.SurfaceViewAnim_type, 0);
                boolean aBoolean = typedArray.getBoolean(R.styleable.SurfaceViewAnim_orderOnTop, false);
                EAnimType[] values = EAnimType.values();
                eAnimType = values[anInt];
                Log.e(TAG, "initTypeVuale:  int= " + anInt + "  type=" + eAnimType + "  aBoolean=" + aBoolean);
        }
}
