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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.DataUtils;
import com.uurobot.baseframe.utils.EAnimType;
import com.uurobot.baseframe.view.SurfaceViewAnim;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorDialog extends DialogFragment {
        private static final String Args_Msg = "Args_Msg";
        private static final String Args_ResId = "Args_ResId";
        private String showMsg;
        private int resId;

        public static SmartDoctorDialog getInstance(String showMsg, int resId) {
                SmartDoctorDialog smartDoctorDialog = new SmartDoctorDialog();
                Bundle args = new Bundle();
                args.putString(Args_Msg, showMsg);
                args.putInt(Args_ResId, resId);
                smartDoctorDialog.setArguments(args);
                return smartDoctorDialog;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.dialog_smartdoctor, container);
                TextView textView = inflate.findViewById(R.id.tv_smartdoctor_dialog);
                textView.setText(showMsg);
                textView.setBackgroundResource(resId);
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
                attributes.gravity = Gravity.BOTTOM;
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;

                window.setLayout((int) (widthPixels * 0.4), (int) (heightPixels * 0.2));
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
                        resId = arguments.getInt(Args_ResId);
                }
        }
}
