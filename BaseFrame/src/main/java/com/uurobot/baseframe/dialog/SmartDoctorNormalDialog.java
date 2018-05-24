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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorNormalDialog extends DialogFragment {
        private static final String Args_Msg = "Args_Msg";
        private String showMsg;

        public static SmartDoctorNormalDialog getInstance(String showMsg) {
                SmartDoctorNormalDialog smartDoctorDialog = new SmartDoctorNormalDialog();
                Bundle args = new Bundle();
                args.putString(Args_Msg, showMsg);
                smartDoctorDialog.setArguments(args);
                return smartDoctorDialog;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.dialog_smartdoctor_normal, container);
                TextView textView = inflate.findViewById(R.id.tv_smartdoctor_dialog);
                textView.setText(showMsg);
                return inflate;
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
                attributes.gravity = Gravity.CENTER_HORIZONTAL; //默认是这个属性
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;

                //相对 attributes.gravity  位置偏移
                attributes.y = heightPixels/2  ; //底部
                window.setAttributes(attributes);
               // window.setLayout((int) (widthPixels * 0.4), (int) (heightPixels * 0.2));
                //设置dialog背景为透明色
                window.setBackgroundDrawableResource(R.color.transparent);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setStyle(STYLE_NORMAL, R.style.fragmentDialog);
                Bundle arguments = getArguments();
                if (arguments != null) {
                        showMsg = arguments.getString(Args_Msg, null);
                }
        }
}
