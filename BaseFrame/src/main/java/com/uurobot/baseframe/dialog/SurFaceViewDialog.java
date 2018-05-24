package com.uurobot.baseframe.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.DataUtils;
import com.uurobot.baseframe.utils.EAnimType;
import com.uurobot.baseframe.view.SurfaceViewAnim;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SurFaceViewDialog extends DialogFragment implements View.OnClickListener {
        private SurfaceViewAnim surfaceViewAnim;
        private TextView tv_wendu, tv_shidu;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.dialog_surfaceview, container);
                Button button = inflate.findViewById(R.id.btn_surfaceviewDialog);
                button.setOnClickListener(this);

                Button button2 = inflate.findViewById(R.id.btn_switch);
                button2.setOnClickListener(this);

                Button button3 = inflate.findViewById(R.id.btn_setShidu);
                button3.setOnClickListener(this);

                Button button4 = inflate.findViewById(R.id.btn_setWendu);
                button4.setOnClickListener(this);

                surfaceViewAnim = inflate.findViewById(R.id.surfaceView_dialog);
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

        /**
         * 设置dialog
         */
        @Override
        public void onStart() {
                super.onStart();
                Dialog dialog = getDialog();
                Window window = dialog.getWindow();
                window.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = Gravity.CENTER;
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;

                window.setLayout((int) (widthPixels * 1.0), (int) (heightPixels * 1.0));
                //设置dialog背景为透明色
                window.setBackgroundDrawableResource(R.color.transparent);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setStyle(STYLE_NORMAL, R.style.fragmentDialog);
        }

        private EAnimType[] eAnimTypes = EAnimType.values();
        private int index;

        @Override
        public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                        case R.id.btn_surfaceviewDialog:
                                dismiss();
                                break;
                        case R.id.btn_switch:
                                surfaceViewAnim.updateAnim(eAnimTypes[index++ % eAnimTypes.length]);
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
