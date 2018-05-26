package com.uurobot.baseframe.fragment.smartTree;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.utils.DataUtils;
import com.uurobot.baseframe.utils.EAnimType;
import com.uurobot.baseframe.view.AnimImageView;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartTreeFragmentOne extends BaseFragment implements View.OnClickListener {
        private AnimImageView imageView;
        private TextView tv_wendu, tv_shidu;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_smarttree_one, container, false);
                Button button = inflate.findViewById(R.id.btn_surfaceviewDialog);
                button.setOnClickListener(this);

                Button button2 = inflate.findViewById(R.id.btn_switch);
                button2.setOnClickListener(this);

                Button button3 = inflate.findViewById(R.id.btn_setShidu);
                button3.setOnClickListener(this);

                Button button4 = inflate.findViewById(R.id.btn_setWendu);
                button4.setOnClickListener(this);

                imageView = inflate.findViewById(R.id.imageview_dialog);
                imageView.startAnim();
                tv_shidu = inflate.findViewById(R.id.tv_shidu);
                tv_wendu = inflate.findViewById(R.id.tv_wendu);
                setshiDu(0.9f);
                setWenDu(22f);
                return inflate;
        }


        private void setshiDu(float shidu) {
                tv_shidu.setText(String.valueOf(shidu));
        }

        private void setWenDu(float wendu) {
                String string = getString(R.string.wenduValue);
                String format = String.format(string, String.valueOf(wendu));
                tv_wendu.setText(format);
        }

        private EAnimType[] eAnimTypes = EAnimType.values();
        private int index;

        @Override
        public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                        case R.id.btn_surfaceviewDialog:
                                imageView.stopAnim();
                                break;
                        case R.id.btn_switch:
                                imageView.updateAnim(eAnimTypes[index++ % (eAnimTypes.length - 3)]);
                                break;
                        case R.id.btn_setShidu:
                                setshiDu(DataUtils.floatTranslate((float) Math.random()));
                                break;
                        case R.id.btn_setWendu:
                                setWenDu(DataUtils.floatTranslate((float) (Math.random() * 20)));
                                break;
                }
        }
}
