package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class TemptureShowLayout extends LinearLayout {
        public TemptureShowLayout(Context context) {
                this(context, null);
        }

        public TemptureShowLayout(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public TemptureShowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData(attrs);
        }

        private void initData(AttributeSet attrs) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.temptureShowLayout);
        }
}
