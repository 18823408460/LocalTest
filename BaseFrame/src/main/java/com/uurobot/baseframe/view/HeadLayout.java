package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by Administrator on 2018/5/23.
 */

public class HeadLayout extends RelativeLayout {
        private int leftResId;
        private int rightResId;
        private String centerContent;
        private int tv_color;
        private float tv_size;

        public HeadLayout(Context context) {
                this(context, null);
        }

        public HeadLayout(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public HeadLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData(attrs);
                initView();
        }

        private void initView() {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View view = layoutInflater.inflate(R.layout.activity_head_common, this);
                Button btnLeft = view.findViewById(R.id.btn_head_common_left);
                Button btnRight = view.findViewById(R.id.btn_head_common_right);
                TextView textView = view.findViewById(R.id.tv_head_common);
                btnLeft.setBackgroundResource(leftResId);
                btnRight.setBackgroundResource(rightResId);
                textView.setText(centerContent);
                textView.setTextColor(tv_color);
                textView.setTextSize(tv_size);
        }

        private void initData(AttributeSet attrs) {
                TypedArray typedArray = null;
                try {
                        typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.headLayout);
                        leftResId = typedArray.getResourceId(R.styleable.headLayout_btn_left_bk, 0);
                        rightResId = typedArray.getResourceId(R.styleable.headLayout_btn_right_bk, 0);
                        centerContent = typedArray.getString(R.styleable.headLayout_tv_center_content);
                        tv_color = typedArray.getColor(R.styleable.headLayout_tv_color, 0);
                        tv_size = typedArray.getDimension(R.styleable.headLayout_tv_size, SizeUtil.dp2px(getContext(), 20f));
                } finally {
                        typedArray.recycle();
                }
        }
}
