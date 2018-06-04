package com.uurobot.baseframe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/6/2.
 */

public class GoodsNumSelectView extends LinearLayout implements View.OnClickListener {
        private ImageButton imageButtonSub;
        private ImageButton imageButtonAdd;
        private TextView textViewNum;
        private static final int Max = 10;
        private static final int Min = 1;

        public GoodsNumSelectView(Context context) {
                this(context, null);
        }

        public GoodsNumSelectView(Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public GoodsNumSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initData();
        }

        private void initData() {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View view = layoutInflater.inflate(R.layout.layout_goods_num_select_view, null);
                imageButtonSub = view.findViewById(R.id.imageButton_sub_goods_num_select_view);
                imageButtonSub.setOnClickListener(this);
                imageButtonAdd = view.findViewById(R.id.imageButton_add_goods_num_select_view);
                imageButtonAdd.setOnClickListener(this);
                textViewNum = view.findViewById(R.id.tv_goods_num_select_view);
        }

        @Override
        public void onClick(View v) {
                String text = textViewNum.getText().toString();
                Integer num = Integer.valueOf(text);
                if (v == imageButtonAdd) {
                        if (num < Max) {
                                textViewNum.setText(String.valueOf(num + 1));
                        }

                } else if (v == imageButtonSub) {
                        if (num > Min) {
                                textViewNum.setText(String.valueOf(num - 1));
                        }
                }
        }

        public void setNumber(int numbers) {
                textViewNum.setText(String.valueOf(numbers));
        }
}
