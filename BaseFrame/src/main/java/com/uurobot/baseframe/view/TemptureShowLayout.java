package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class TemptureShowLayout extends LinearLayout {
    private String tv_range;
    private String tv_range_tip;
    private int bitmapId;

    public TemptureShowLayout(Context context) {
        this(context, null);
    }

    public TemptureShowLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemptureShowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(attrs);
        setOrientation(VERTICAL);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//        View view = layoutInflater.inflate(R.layout.layout_tempture_show, null);
        View view = layoutInflater.inflate(R.layout.layout_tempture_show, this);
        ImageView imageView = view.findViewById(R.id.img_layout_tempeture);
        TextView textView_rang = view.findViewById(R.id.tv_range);
        TextView textView_rang_tip = view.findViewById(R.id.tv_range_tip);

        imageView.setBackgroundResource(bitmapId);
        textView_rang.setText(tv_range);
        textView_rang_tip.setText(tv_range_tip);
    }

    private void initData(AttributeSet attrs) {
        TypedArray typedArray = null;
        try {
            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.temptureShowLayout);
            tv_range = typedArray.getString(R.styleable.temptureShowLayout_tem_range);
            tv_range_tip = typedArray.getString(R.styleable.temptureShowLayout_tem_range_tip);
            bitmapId = typedArray.getResourceId(R.styleable.temptureShowLayout_image_tem, 0);
        } finally {
            typedArray.recycle();
        }
    }
}
